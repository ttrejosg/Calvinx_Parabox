package Main;

import Components.Player;
import Components.Room;
import Handlers.*;
import java.awt.Point;
import static Handlers.Constants.*;
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

    private Room[] rooms;
    private Room CurrentRoom;

    private final int FPS_SET = 120;
    private final int UPS_SET = 200;
    private final boolean SHOW_FPS_UPS = true;
    private int steps = 0;

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

        this.gWindow.requestFocus();

        LoadRooms();
        initRoomSelectorActionListeners();

        this.gWindow.setVisible(true);
    }

    private void LoadRooms(){
        File[] rooms = new File(ROOMS_PATH).listFiles();
        this.rooms = new Room[rooms.length];
        for(int i=0;i<rooms.length;i++){
            this.rooms[i] = new Room(i+1,rooms[i]);
        }
    }

    private void initRoomSelectorActionListeners(){
        JButton[] roomButtons = this.gWindow.getRoomSelector().getRoomButtons();
        for(int i=0;i<roomButtons.length;i++){
            int finalI = i;
            roomButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    CurrentRoom = rooms[finalI];
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
        this.gWindow.add(this.gPanel);
        this.gPanel.setEnabled(true);
        this.gPanel.setVisible(true);
        this.gWindow.setFocusable(true);
        this.gWindow.requestFocus();
        this.gameThread = new Thread(this);
        this.playing = true;
        this.gameThread.start();
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

    public Room[] getRooms() {
        return rooms;
    }

    public void setRooms(Room[] rooms) {
        this.rooms = rooms;
    }

    public Room getCurrentRoom() {
        return CurrentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        CurrentRoom = currentRoom;
    }
}
