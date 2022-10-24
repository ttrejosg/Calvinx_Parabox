package Components;

import Handlers.Constants;
import Handlers.ImageHandler;
import Handlers.KeyHandler;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.security.Key;
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
        player.setRoom(this);
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
    
    public void update(){
        this.player.update();
    }
    
    public void paint(Graphics g){
        for(Wall wall: this.walls) wall.paint(g);

        for(ReleaseZone rz: this.releaseZones) rz.paint(g);

        if(door != null) door.paint(g);

        for(Block block: this.blocks) block.paint(g);

        this.player.paint(g);
    }

    public GameObject getObjectAt(int x,int y){
        GameObject gObject = null;
        int i = 0;
        while(i < walls.size()  && gObject == null){
            Wall wall =  walls.get(i);
            if(wall.getPosition().x == x && wall.getPosition().y == y) gObject = wall;
            i ++;
        }
        i=0;
        while(i < blocks.size() && gObject == null){
            Block block =  blocks.get(i);
            if(block.getPosition().x == x && block.getPosition().y == y) gObject = block;
            i ++;
        }
        return gObject;
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
