package Main;

import Components.Player;
import Components.Room;
import Handlers.*;
import java.awt.Point;
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
    private ArrayList<Room> rooms;
    private Player player;

    private final int FPS_SET = 120;
    private final int UPS_SET = 200;
    private final boolean SHOW_FPS_UPS = false;
    private int steps = 0;
    
    /**
     * Constructor de la clase Game
     */
    public Game() {
        createComponents();
        initComponents();
        initThread();
    }
    
    /**
     * Método que crea todos los componentes de la clase
     */
    private void createComponents(){
        this.gPanel = new GamePanel(this);
        this.gWindow = new GameWindow(gPanel);
        this.playing = false;
        this.rooms = new ArrayList<>();
        this.player = new Player(new Point(200, 200));
    }
    
    /**
     * Método que inicializa los componentes de la clase
     */
    private void initComponents(){
        this.gWindow.requestFocus();
        this.gWindow.setVisible(true);
    }
    
    /**
     * Método que inicializa el GameLooop
     */
    public void initThread(){
        this.gameThread = new Thread(this);
        this.playing = true;
        this.gameThread.start();
    }

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
     * Método que actualiza los componentes del juego
     */
    public void update(){
        if(KeyHandler.KT_A) {
            if(steps < 60){
                this.player.setState(4);
                this.player.getPosition().translate(-3, 0);
                steps += 3;
            } else {
                steps = 0;
                KeyHandler.KT_A = false;
            }
        }
        else if(KeyHandler.KT_D){
            if(steps < 60){
                this.getPlayer().setState(2);
                this.player.getPosition().translate(3, 0);
                steps += 3;
            } else {
                steps = 0;
                KeyHandler.KT_D = false;
            }
        }
        else if(KeyHandler.KT_W ){
            if(steps < 60){
                this.getPlayer().setState(1);
                this.player.getPosition().translate(0, -3);
                steps += 3;
            } else {
                steps = 0;
                KeyHandler.KT_W = false;
            }
        }
        else if(KeyHandler.KT_S ){
            if(steps < 60){
                this.getPlayer().setState(3);
                this.player.getPosition().translate(0, 3);
                steps += 3;
            } else {
                steps = 0;
                KeyHandler.KT_S = false;
            }
        }
        else this.getPlayer().setState(0);
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

    /**
     * @return the rooms
     */
    public ArrayList<Room> getRooms() {
        return rooms;
    }

    /**
     * @param rooms the rooms to set
     */
    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    /**
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }
}
