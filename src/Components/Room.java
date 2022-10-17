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
    private File roomFile;

    public Room(int id, File roomFile, Player player) {
        this.id = id;
        this.player = player;
        this.passed = false;
        this.roomFile = roomFile;
        createComponents();
        initRoom();
    }

    private void createComponents() {
        this.blocks = new ArrayList<>();
        this.walls = new ArrayList<>();
        this.releaseZones = new ArrayList<>();
    }

    public void initRoom(){
        try {
            FileReader fr = new FileReader(roomFile);
            BufferedReader br = new BufferedReader(fr);
            String linea = "";
            this.walls.clear();
            this.releaseZones.clear();
            this.blocks.clear();

            for(int i = 0; (linea=br.readLine())!=null; i ++){
                for(int j = 0; j < linea.length(); j ++){
                    if(linea.charAt(j) == '_') {
                        this.walls.add(new Wall(new Point(j * Constants.BLOCKS_SIZE, i * Constants.BLOCKS_SIZE), Constants.DOWN_PATH));
                    } else if (linea.charAt(j) == '-') {
                        this.walls.add(new Wall(new Point(j * Constants.BLOCKS_SIZE, i * Constants.BLOCKS_SIZE), Constants.UP_PATH));
                    } else if (linea.charAt(j) == '[') {
                        this.walls.add(new Wall(new Point(j * Constants.BLOCKS_SIZE, i * Constants.BLOCKS_SIZE), Constants.LEFT_PATH));
                    } else if (linea.charAt(j) == ']') {
                        this.walls.add(new Wall(new Point(j * Constants.BLOCKS_SIZE, i * Constants.BLOCKS_SIZE), Constants.RIGHT_PATH));
                    } else if (linea.charAt(j) == '1') {
                        this.walls.add(new Wall(new Point(j * Constants.BLOCKS_SIZE, i * Constants.BLOCKS_SIZE), Constants.DLCORNER_PATH));
                    } else if (linea.charAt(j) == '2') {
                        this.walls.add(new Wall(new Point(j * Constants.BLOCKS_SIZE, i * Constants.BLOCKS_SIZE), Constants.DRCORNER_PATH));
                    } else if (linea.charAt(j) == '3') {
                        this.walls.add(new Wall(new Point(j * Constants.BLOCKS_SIZE, i * Constants.BLOCKS_SIZE), Constants.ULCORNER_PATH));
                    } else if (linea.charAt(j) == '4') {
                        this.walls.add(new Wall(new Point(j * Constants.BLOCKS_SIZE, i * Constants.BLOCKS_SIZE), Constants.URCORNER_PATH));
                    } else if (linea.charAt(j) == '5') {
                        this.walls.add(new Wall(new Point(j * Constants.BLOCKS_SIZE, i * Constants.BLOCKS_SIZE), Constants.INDLCORNER_PATH));
                    } else if (linea.charAt(j) == '6') {
                        this.walls.add(new Wall(new Point(j * Constants.BLOCKS_SIZE, i * Constants.BLOCKS_SIZE), Constants.INDRCORNER_PATH));
                    } else if (linea.charAt(j) == '7') {
                        this.walls.add(new Wall(new Point(j * Constants.BLOCKS_SIZE, i * Constants.BLOCKS_SIZE), Constants.INULCORNER_PATH));
                    } else if (linea.charAt(j) == '8') {
                        this.walls.add(new Wall(new Point(j * Constants.BLOCKS_SIZE, i * Constants.BLOCKS_SIZE), Constants.INURCORNER_PATH));
                    } else if (linea.charAt(j) == '*') {
                        this.walls.add(new Wall(new Point(j * Constants.BLOCKS_SIZE, i * Constants.BLOCKS_SIZE), Constants.UDOWN_PATH));
                    } else if (linea.charAt(j) == '^') {
                        this.walls.add(new Wall(new Point(j * Constants.BLOCKS_SIZE, i * Constants.BLOCKS_SIZE), Constants.UUP_PATH));
                    } else if (linea.charAt(j) == '+') {
                        this.walls.add(new Wall(new Point(j * Constants.BLOCKS_SIZE, i * Constants.BLOCKS_SIZE), Constants.ULEFT_PATH));
                    } else if (linea.charAt(j) == '%') {
                        this.walls.add(new Wall(new Point(j * Constants.BLOCKS_SIZE, i * Constants.BLOCKS_SIZE), Constants.URIGHT_PATH));
                    } else if (linea.charAt(j) == 'v') {
                        this.walls.add(new Wall(new Point(j * Constants.BLOCKS_SIZE, i * Constants.BLOCKS_SIZE), Constants.TUNELVERTICAL_PATH));
                    } else if (linea.charAt(j) == 'h') {
                        this.walls.add(new Wall(new Point(j * Constants.BLOCKS_SIZE, i * Constants.BLOCKS_SIZE), Constants.TUNELHORIZONTAL_PATH));
                    } else if (linea.charAt(j) == 'r') {
                        this.releaseZones.add(new ReleaseZone(new Point(j * Constants.BLOCKS_SIZE, i * Constants.BLOCKS_SIZE), Constants.RELEASEZONE_PATH));
                    } else if (linea.charAt(j) == 'b') {
                        this.blocks.add(new Block(new Point(j * Constants.BLOCKS_SIZE, i * Constants.BLOCKS_SIZE), Constants.BLOCK_PATH));
                    } else if (linea.charAt(j) == 'd') {
                        this.door = new Door(new Point(j * Constants.BLOCKS_SIZE, i * Constants.BLOCKS_SIZE), Constants.DOOR_PATH);
                    } else if (linea.charAt(j) == 'p') {
                        this.player.setPosition(new Point(j * Constants.BLOCKS_SIZE, i * Constants.BLOCKS_SIZE));
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
        for(Wall wall: this.walls){
            g.drawImage(ImageHandler.get(wall.getPath()), wall.getPosition().x, wall.getPosition().y, Constants.BLOCKS_SIZE, Constants.BLOCKS_SIZE, null);
        }
        for(ReleaseZone rz: this.releaseZones){
            g.drawImage(ImageHandler.get(rz.getPath()), rz.getPosition().x, rz.getPosition().y, Constants.BLOCKS_SIZE, Constants.BLOCKS_SIZE, null);
        }
        if(door != null) g.drawImage(ImageHandler.get(door.getPath()), door.getPosition().x, door.getPosition().y, Constants.BLOCKS_SIZE, Constants.BLOCKS_SIZE, null);
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
