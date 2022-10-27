package Main;

import Components.*;
import Handlers.*;
import java.awt.Point;
import static Handlers.Constants.*;
import static javax.sound.sampled.Clip.LOOP_CONTINUOUSLY;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Clase que representa el juego y contiene todos sus elementos y componentes.
 * El juego posee:
 * Una {@link GameWindow} 'gWindow' que es la ventana donde se ejecuta el hilo del juego.
 * Un {@link GamePanel} 'gPanel' que es el panel sobre el cual se renderiza el juego.
 * Un booleano 'playing' el cual indica si el juego está en ejecución.
 * Un {@link Thread} 'gameThread' que es el hilo donde se ejecuta el juego.
 * Un {@link File[]} 'roomFiles' que es una lista con los archivos de texto que describen las salas jugables.
 * Un {@link Room} 'currentRoom' que es la sala en la que se está jugando.
 *
 * @author Tomás y Carlos
 */
public class Game implements Runnable{
    private GameWindow gWindow;
    private GamePanel gPanel;
    private boolean playing;
    private Thread gameThread;
    private File[] roomFiles;
    private Room currentRoom;

    /**
     * Constructor de la clase Game
     */
    public Game() {
        createComponents();
        initComponents();
    }

    /**
     * Método que crea todos los componentes de la clase
     */
    private void createComponents(){
        this.gWindow = new GameWindow();
        this.gPanel = new GamePanel(this);
        this.playing = false;
    }

    /**
     * Método que inicializa los componentes de la clase
     */
    private void initComponents(){
        ImageHandler.loadImage();
        SoundHandler.loadAudio();
        this.gWindow.requestFocus();
        this.gWindow.add(this.gPanel);
        this.gPanel.setVisible(false);
        this.gPanel.setEnabled(false);
        LoadRoomFiles();
        initRoomSelectorActionListeners();
        SoundHandler.createClip(SoundHandler.get("Multi_Infinite.wav")).loop(LOOP_CONTINUOUSLY);
        this.gWindow.setVisible(true);
    }

    /**
     * Método que carga todos los niveles.
     */
    private void LoadRoomFiles(){
        this.roomFiles = new File(ROOMS_PATH).listFiles();
    }

    /**
     * Método que inicia los ActionListeners de Selector de niveles.
     */
    private void initRoomSelectorActionListeners(){
        JButton[] roomButtons = this.gWindow.getRoomSelector().getRoomButtons();
        for(int i=0;i<roomButtons.length;i++){
            int finalI = i;
            roomButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    currentRoom = new Room(finalI,roomFiles[finalI]
                            ,new Player(new Point(0, 0),null), new Enemy(new Point(-60,-60),null));
                    SoundHandler.createClip(SoundHandler.get("Room_Select.wav")).start();
                    initGameThread();
                }
            });
        }
    }

    /**
     * Método que inicializa el GameLooop
     */
    public void initGameThread(){
        this.gWindow.getRoomSelector().setEnabled(false);
        this.gWindow.getRoomSelector().setVisible(false);
        this.gWindow.setFocusable(true);
        this.gWindow.requestFocus();
        this.gameThread = new Thread(this);
        this.playing = true;
        this.gameThread.start();
        this.gPanel.setEnabled(true);
        this.gPanel.setVisible(true);
    }

    /**
     * GameLoop que se ejecuta en el GameThread y establece los FPS y UPS
     */
    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;
        long previousTime = System.nanoTime();
        int frames = 0;
        int updates = 0;
        double deltaU = 0;
        double deltaF = 0;
        while (playing){
            long currentTime = System.nanoTime();
            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;
            if(deltaU >= 1){
                update();
                updates++;
                deltaU--;
            }
            if(deltaF >= 1){
                gPanel.repaint();
                frames++;
                deltaF--;
            }
        }
        if(KeyHandler.KP_R) resetRoom();
    }
    
    /**
     * Método que actualiza el estado del juego y la sala jugable.
     */
    public void update(){
        this.currentRoom.update();
        if(KeyHandler.KP_R) this.playing = false;
        else if(KeyHandler.KP_ESC) pause();
        else if(win()) {
            SoundHandler.createClip(SoundHandler.get("Win.wav")).start();
            returnRoomSelector();
        }
    }

    /**
     * Metodo que determina si se ha cumplido el objetivo en la sala.
     * @return true si el jugador gana, de lo contrario false.
     */
    public boolean win(){
        for(ReleaseZone rs: this.currentRoom.getReleaseZones()){
            boolean ocupated = false;
            for(Block b: this.currentRoom.getBlocks()){
                if(b.getPosition().x == rs.getPosition().x && b.getPosition().y == rs.getPosition().y){
                    ocupated = true;
                }
            }
            if(!ocupated) return false;
        }
        Player p = this.currentRoom.getPlayer();
        Door d = this.currentRoom.getDoor();
        return p.getPosition().x == d.getPosition().x && p.getPosition().y == d.getPosition().y;
    }

    /**
     * Metodo que termina el hilo del juego y lo deja en estado de pausa.
     */
    public void pause(){
        SoundHandler.createClip(SoundHandler.get("Pause.wav")).start();
        this.currentRoom.setBackground(PAUSE_BACKGROUND_PATH);
        this.gPanel.getExit().setVisible(true);
        this.gPanel.getBackToMenuButton().setVisible(true);
        this.gPanel.getResumeButton().setVisible(true);
        this.gPanel.getResumeButton().requestFocus();
        this.gPanel.getResumeButton().setFocusable(true);
        KeyHandler.KP_ESC = false;
        playing = false;
    }

    /**
     * Metodo que restablece la sala en la que se está jugando actualmente.
     */
    public void resetRoom(){
        SoundHandler.createClip(SoundHandler.get("Reset.wav")).start();
        this.currentRoom.reset();
        this.playing = true;
        KeyHandler.KP_R = false;
        this.gameThread.run();
    }

    /**
     * Metodo que retorna al selector de niveles.
     */
    public void returnRoomSelector(){
        try {
            Thread.sleep(800);
        }
        catch(Exception e){
            System.out.println("Error");
        }
        this.gWindow.getRoomSelector().setEnabled(true);
        this.gWindow.getRoomSelector().setVisible(true);
        this.playing = false;
        this.gPanel.setEnabled(false);
        this.gPanel.setVisible(false);
        this.currentRoom.initRoom();
    }

    //Getters and setters:

    public GameWindow getgWindow() {
        return gWindow;
    }

    public void setgWindow(GameWindow gWindow) {
        this.gWindow = gWindow;
    }

    public GamePanel getgPanel() {
        return gPanel;
    }

    public void setgPanel(GamePanel gPanel) {
        this.gPanel = gPanel;
    }

    public boolean isPlaying() {
        return playing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }

    public Thread getGameThread() {
        return gameThread;
    }

    public void setGameThread(Thread gameThread) {
        this.gameThread = gameThread;
    }

    public File[] getRoomFiles() {
        return roomFiles;
    }

    public void setRoomFiles(File[] roomFiles) {
        this.roomFiles = roomFiles;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

}
