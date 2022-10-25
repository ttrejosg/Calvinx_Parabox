package Main;

import Components.*;
import Handlers.*;
import java.awt.Point;
import static Handlers.Constants.*;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Tomás
 */
public class Game implements Runnable{
    private GameWindow gWindow;
    private GamePanel gPanel;
    private boolean playing;
    private Thread gameThread;

    private File[] roomFiles;
    private Room CurrentRoom;

    private final int FPS_SET = 120;
    private final int UPS_SET = 200;
    private final boolean SHOW_FPS_UPS = true;

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
        Clip clip = SoundHandler.createClip(SoundHandler.get("Patricks_Parabox.wav"));
        clip.start();
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
                    CurrentRoom = new Room(finalI,roomFiles[finalI]
                            ,new Player(new Point(0, 0),null), new Enemy(new Point(0,0),null));
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
        long lastCheck = System.currentTimeMillis();

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
            if(SHOW_FPS_UPS){
                if(System.currentTimeMillis() - lastCheck >= 1000){
                    lastCheck = System.currentTimeMillis();
                    System.out.println("FPS: " + frames + " | UPS: " + updates);
                    frames = 0;
                    updates = 0;
                }
            }
        }
    }
    
    /**
     * Método que actualiza los componentes del juego.
     */
    public void update(){
        this.CurrentRoom.update();
        if(KeyHandler.KP_R){
            this.getCurrentRoom().getEnemy().resetState();
            this.CurrentRoom.getPlayer().resetState();
            this.CurrentRoom = new Room(this.CurrentRoom.getId(),this.CurrentRoom.getRoomFile()
                    ,this.CurrentRoom.getPlayer(),this.CurrentRoom.getEnemy());
        }
        else if(KeyHandler.KP_ESC) pause();
        else if(win()) returnRoomSelector();
    }

    public boolean win(){
        for(ReleaseZone rs: this.CurrentRoom.getReleaseZones()){
            boolean ocupated = false;
            for(Block b: this.CurrentRoom.getBlocks()){
                if(b.getPosition().x == rs.getPosition().x && b.getPosition().y == rs.getPosition().y){
                    ocupated = true;
                }
            }
            if(!ocupated) return false;
        }
        Player p = this.CurrentRoom.getPlayer();
        Door d = this.CurrentRoom.getDoor();
        return p.getPosition().x == d.getPosition().x && p.getPosition().y == d.getPosition().y;
    }

    public void pause(){
        this.CurrentRoom.setBackground(PAUSE_BACKGROUND_PATH);
        this.gPanel.getExit().setVisible(true);
        this.gPanel.getBackToMenuButton().setVisible(true);
        this.gPanel.getResumeButton().setVisible(true);
        playing = false;
        KeyHandler.KP_ESC = false;
    }

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
        this.CurrentRoom.initRoom();
    }

    /**
     * @return the gWindow
     */
    public GameWindow getgWindow() {
        return gWindow;
    }

    /**
     * @param gWindow the gWindow to set
     */
    public void setgWindow(GameWindow gWindow) {
        this.gWindow = gWindow;
    }

    /**
     * @return the gPanel
     */
    public GamePanel getgPanel() {
        return gPanel;
    }

    /**
     * @param gPanel the gPanel to set
     */
    public void setgPanel(GamePanel gPanel) {
        this.gPanel = gPanel;
    }

    /**
     * @return the playing
     */
    public boolean isPlaying() {
        return playing;
    }

    /**
     * @param playing the playing to set
     */
    public void setPlaying(boolean playing) {
        this.playing = playing;
    }

    /**
     * @return the gameThread
     */
    public Thread getGameThread() {
        return gameThread;
    }

    /**
     * @param gameThread the gameThread to set
     */
    public void setGameThread(Thread gameThread) {
        this.gameThread = gameThread;
    }

    public Room getCurrentRoom() {
        return CurrentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        CurrentRoom = currentRoom;
    }
}
