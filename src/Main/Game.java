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

    private Room[] rooms;
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
        LoadRooms();
        initRoomSelectorActionListeners();
        Clip clip = SoundHandler.createClip(SoundHandler.get("Patricks_Parabox.wav"));
        clip.start();
        this.gWindow.setVisible(true);
    }

    /**
     * Método que carga todos los niveles.
     */
    private void LoadRooms(){
        File[] rooms = new File(ROOMS_PATH).listFiles();
        this.rooms = new Room[rooms.length];
        Player player = new Player(new Point(180, 180));
        for(int i=0;i<rooms.length;i++){
            this.rooms[i] = new Room(i+1,rooms[i], player, BACKGROUND_PATH);
        }
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
        if(KeyHandler.KT_A) {
            if(this.getCurrentRoom().getPlayer().getSteps() == 0){
                if(!colissionWallLeft(this.getCurrentRoom().getPlayer())) {
                    Block b = colissionBlockLeft();
                    if(b != null) {
                        if(!colissionWallLeft(b)){
                            b.update();
                            this.CurrentRoom.getPlayer().update();
                        }

                    }else this.CurrentRoom.getPlayer().update();
                }
            }else this.CurrentRoom.getPlayer().update();

            if(win()) returnRoomSelector();
        }
        else if(KeyHandler.KT_D){
            if(this.getCurrentRoom().getPlayer().getSteps() == 0){
                if(!colissionWallRight(this.getCurrentRoom().getPlayer())) {
                    Block b = colissionBlockRight();
                    if(b != null) {
                        if(!colissionWallRight(b)){
                            b.update();
                            this.CurrentRoom.getPlayer().update();
                        }

                    }else this.CurrentRoom.getPlayer().update();
                }
            }else this.CurrentRoom.getPlayer().update();

            if(win()) returnRoomSelector();
        }
        else if(KeyHandler.KT_W ){
            if(this.getCurrentRoom().getPlayer().getSteps() == 0){
                if(!colissionWallUp(this.getCurrentRoom().getPlayer())) {
                    Block b = colissionBlockUp();
                    if(b != null) {
                        if(!colissionWallUp(b)){
                            b.update();
                            this.CurrentRoom.getPlayer().update();
                        }

                    }else this.CurrentRoom.getPlayer().update();
                }
            }else this.CurrentRoom.getPlayer().update();

            if(win()) returnRoomSelector();
        }
        else if(KeyHandler.KT_S ){
            if(this.getCurrentRoom().getPlayer().getSteps() == 0){
                if(!colissionWallDown(this.getCurrentRoom().getPlayer())) {
                    Block b = colissionBlockDown();
                    if(b != null) {
                        if(!colissionWallDown(b)){
                            b.update();
                            this.CurrentRoom.getPlayer().update();
                        }

                    }else this.CurrentRoom.getPlayer().update();
                }
            }else this.CurrentRoom.getPlayer().update();

            if(win()) returnRoomSelector();
        } else if (KeyHandler.KP_R) {
            this.CurrentRoom.initRoom();
        } else if (KeyHandler.KP_ESC) {
            if(playing){
                this.CurrentRoom.setBackground(PAUSE_BACKGROUND_PATH);
                this.gPanel.getExit().setVisible(true);
                this.gPanel.getBackToMenuButton().setVisible(true);
                this.gPanel.getResumeButton().setVisible(true);
                playing = false;
                KeyHandler.KP_ESC = false;
            }

        } else this.CurrentRoom.getPlayer().setState(0);
    }

    public boolean colissionWallRight(GameObject go){
        int newX = go.getPosition().x + 60;
        int y = go.getPosition().y;
        for(Wall w: this.getCurrentRoom().getWalls()){
            if(y == w.getPosition().y && newX == w.getPosition().x) return true;
        }
        return false;
    }

    public boolean colissionWallLeft(GameObject go){
        int newX = go.getPosition().x - 60;
        int y = go.getPosition().y;
        for(Wall w: this.getCurrentRoom().getWalls()){
            if(y == w.getPosition().y && newX == w.getPosition().x) return true;
        }
        return false;
    }

    public boolean colissionWallUp(GameObject go){
        int newY = go.getPosition().y - 60;
        int x = go.getPosition().x;
        for(Wall w: this.getCurrentRoom().getWalls()){
            if(x == w.getPosition().x && newY == w.getPosition().y) return true;
        }
        return false;
    }

    public boolean colissionWallDown(GameObject go){
        int newY = go.getPosition().y + 60;
        int x = go.getPosition().x;
        for(Wall w: this.getCurrentRoom().getWalls()){
            if(x == w.getPosition().x && newY == w.getPosition().y) return true;
        }
        return false;
    }

    public Block colissionBlockDown(){
        int newY = this.getCurrentRoom().getPlayer().getPosition().y + 60;
        int x = this.getCurrentRoom().getPlayer().getPosition().x;
        for(Block b: this.getCurrentRoom().getBlocks()){
            if(x == b.getPosition().x && newY == b.getPosition().y) return b;
        }
        return null;
    }

    public Block colissionBlockRight(){
        int newX = this.getCurrentRoom().getPlayer().getPosition().x + 60;
        int y = this.getCurrentRoom().getPlayer().getPosition().y;
        for(Block b: this.getCurrentRoom().getBlocks()){
            if(y == b.getPosition().y && newX == b.getPosition().x) return b;
        }
        return null;
    }

    public Block colissionBlockLeft(){
        int newX = this.getCurrentRoom().getPlayer().getPosition().x - 60;
        int y = this.getCurrentRoom().getPlayer().getPosition().y;
        for(Block b: this.getCurrentRoom().getBlocks()){
            if(y == b.getPosition().y && newX == b.getPosition().x) return b;
        }
        return null;
    }

    public Block colissionBlockUp(){
        int newY = this.getCurrentRoom().getPlayer().getPosition().y - 60;
        int x = this.getCurrentRoom().getPlayer().getPosition().x;
        for(Block b: this.getCurrentRoom().getBlocks()){
            if(x == b.getPosition().x && newY == b.getPosition().y) return b;
        }
        return null;
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
        if(p.getPosition().x == d.getPosition().x && p.getPosition().y == d.getPosition().y) return true;
        else return false;
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
