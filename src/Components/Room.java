package Components;

import Handlers.Constants;
import Handlers.ImageHandler;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

/**
 *
 * @author Tomás y Carlos
 */
public class Room {
    private int id;
    private ArrayList<Block> blocks;
    private ArrayList<Wall> walls;
    private ArrayList<ReleaseZone> releaseZones;
    private Door door;
    private Player player;
    private boolean passed;

    public Room(int id, File roomFile, Player player) {
        this.id = id;
        this.player = player;
        this.passed = false;
        createComponents();
        initRoom(roomFile);
    }

    private void createComponents() {
        this.blocks = new ArrayList<>();
        this.walls = new ArrayList<>();
        this.releaseZones = new ArrayList<>();
    }

    private void initRoom(File level){
        try {
            FileReader fr = new FileReader(level);
            BufferedReader br = new BufferedReader(fr);
            String linea = "";
            for(int i = 0; (linea=br.readLine())!=null; i ++){
                for(int j = 0; j < linea.length(); j ++){
                    if(linea.charAt(j) == '#') {
                        this.blocks.add(new Block(new Point(i * Constants.BLOCKS_SIZE, j * Constants.BLOCKS_SIZE), Constants.WALL_PATH));
                    }
                }
            }
            fr.close();
        } catch(Exception e){
            System.out.println("Error");
        }
    }
    
    private void update(){
        
    }
    
    public void paint(Graphics g){
        for(Block block: this.blocks){
            g.drawImage(ImageHandler.get(block.getPath()), block.getPosition().x, block.getPosition().y, Constants.BLOCKS_SIZE, Constants.BLOCKS_SIZE, null);
        }
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the blocks
     */
    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    /**
     * @param blocks the blocks to set
     */
    public void setBlocks(ArrayList<Block> blocks) {
        this.blocks = blocks;
    }

    /**
     * @return the walls
     */
    public ArrayList<Wall> getWalls() {
        return walls;
    }

    /**
     * @param walls the walls to set
     */
    public void setWalls(ArrayList<Wall> walls) {
        this.walls = walls;
    }

    /**
     * @return the releaseZones
     */
    public ArrayList<ReleaseZone> getReleaseZones() {
        return releaseZones;
    }

    /**
     * @param releaseZones the releaseZones to set
     */
    public void setReleaseZones(ArrayList<ReleaseZone> releaseZones) {
        this.releaseZones = releaseZones;
    }

    /**
     * @return the door
     */
    public Door getDoor() {
        return door;
    }

    /**
     * @param door the door to set
     */
    public void setDoor(Door door) {
        this.door = door;
    }

    /**
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * @param player the player to set
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * @return the passed
     */
    public boolean isPassed() {
        return passed;
    }

    /**
     * @param passed the passed to set
     */
    public void setPassed(boolean passed) {
        this.passed = passed;
    }
}
