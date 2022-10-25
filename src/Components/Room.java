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
import static Handlers.Constants.*;

/**
 *
 * @author Tomás y Carlos
 */
public class Room {
    private int id;
    private ArrayList<Block> blocks;
    private ArrayList<Wall> walls;
    private ArrayList<ReleaseZone> releaseZones;
    private ArrayList<Floor> floor;
    private Door door;
    private Player player;
    private Enemy enemy;
    private File roomFile;
    private String background;
    private Tp tp;

    public Room(int id, File roomFile, Player player, Enemy enemy) {
        this.id = id;
        this.player = player;
        this.player.setRoom(this);
        this.enemy = enemy;
        this.enemy.setRoom(this);
        this.roomFile = roomFile;
        this.background = Constants.BACKGROUND_PATH;
        createComponents();
        initRoom();
    }

    private void createComponents() {
        this.blocks = new ArrayList<>();
        this.walls = new ArrayList<>();
        this.releaseZones = new ArrayList<>();
        this.floor = new ArrayList<>();
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
                    } else if (linea.charAt(j) == 'i') {
                        this.walls.add(new Wall(new Point(j * Constants.BLOCKS_SIZE, i * Constants.BLOCKS_SIZE), Constants.DETOURRIGHT_PATH));
                    } else if (linea.charAt(j) == 'r') {
                        this.releaseZones.add(new ReleaseZone(new Point(j * Constants.BLOCKS_SIZE, i * Constants.BLOCKS_SIZE), Constants.RELEASEZONE_PATH));
                    } else if (linea.charAt(j) == 'b') {
                        this.blocks.add(new Block(new Point(j * Constants.BLOCKS_SIZE, i * Constants.BLOCKS_SIZE), Constants.BLOCK_PATH,this));
                        this.floor.add(new Floor(new Point(j * Constants.BLOCKS_SIZE, i * Constants.BLOCKS_SIZE), Constants.FLOOR_PATH));
                    } else if (linea.charAt(j) == 'd') {
                        this.door = new Door(new Point(j * Constants.BLOCKS_SIZE, i * Constants.BLOCKS_SIZE), Constants.DOOR_PATH);
                    } else if (linea.charAt(j) == '#') {
                        this.floor.add(new Floor(new Point(j * Constants.BLOCKS_SIZE, i * Constants.BLOCKS_SIZE), Constants.FLOOR_PATH));
                    } else if (linea.charAt(j) == 't') {
                        if(tp == null) tp = new Tp(new Point(j * Constants.BLOCKS_SIZE, i * Constants.BLOCKS_SIZE), Constants.TP_PATH);
                        else{
                            Tp new_tp = new Tp(new Point(j * Constants.BLOCKS_SIZE, i * Constants.BLOCKS_SIZE), Constants.TP_PATH);
                            new_tp.next = this.tp;
                            this.tp.next = new_tp;
                        }
                    } else if (linea.charAt(j) == 'p') {
                        this.player.setPosition(new Point(j * Constants.BLOCKS_SIZE, i * Constants.BLOCKS_SIZE));
                        this.floor.add(new Floor(new Point(j * Constants.BLOCKS_SIZE, i * Constants.BLOCKS_SIZE), Constants.FLOOR_PATH));
                    }  else if (linea.charAt(j) == 'e') {
                        this.enemy.setPosition(new Point(j * Constants.BLOCKS_SIZE, i * Constants.BLOCKS_SIZE));
                        this.floor.add(new Floor(new Point(j * Constants.BLOCKS_SIZE, i * Constants.BLOCKS_SIZE), Constants.FLOOR_PATH));
                    }
                }
            }
            fr.close();
        } catch(Exception e){
            System.out.println("Error");
        }
    }
    
    public void update(){
        for(Block block: this.blocks) block.update();
        this.player.update();
        this.enemy.update();
    }
    
    public void paint(Graphics g){
        g.drawImage(ImageHandler.get(this.background), 0, 0, Constants.GAME_WIDTH, Constants.GAME_HEIGHT, null);
        for(Floor floor: this.floor) floor.paint(g);
        for(Wall wall: this.walls) wall.paint(g);
        for(ReleaseZone rz: this.releaseZones) rz.paint(g);
        if(door != null) door.paint(g);
        for(Block block: this.blocks) block.paint(g);
        this.player.paint(g);
        this.enemy.paint(g);
    }

    public GameObject getObjectAt(int x,int y){
        GameObject gObject = null;
        int i = 0;
        while(i < walls.size()  && gObject == null){
            Wall wall =  walls.get(i);
            if(wall.getPosition().x >= x  && wall.getPosition().x < x+BLOCKS_SIZE
                    && wall.getPosition().y >= y && wall.getPosition().y < y+BLOCKS_SIZE) gObject = wall;
            i ++;
        }
        i=0;
        while(i < blocks.size() && gObject == null){
            Block block =  blocks.get(i);
            if(block.getPosition().x >= x  && block.getPosition().x < x+BLOCKS_SIZE
                    && block.getPosition().y >= y && block.getPosition().y < y+BLOCKS_SIZE) gObject = block;
            i ++;
        }
        if(this.tp != null){
            if(tp.getPosition().x >= x  && tp.getPosition().x <= x+BLOCKS_SIZE
                    && tp.getPosition().y >= y && tp.getPosition().y < y+BLOCKS_SIZE) gObject = this.tp;
            if(tp.next.getPosition().x >= x  && tp.next.getPosition().x < x+BLOCKS_SIZE
                    && tp.next.getPosition().y >= y && tp.next.getPosition().y < y+BLOCKS_SIZE) gObject = this.tp.next;
        }
        if(player.getPosition().x >= x  && player.getPosition().x < x+BLOCKS_SIZE
                && player.getPosition().y >= y && player.getPosition().y < y+BLOCKS_SIZE) gObject = this.player;
        if(enemy.getPosition().x >= x  && enemy.getPosition().x < x+BLOCKS_SIZE
                && enemy.getPosition().y >= y && enemy.getPosition().y < y+BLOCKS_SIZE) gObject = this.enemy;
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
     * @return the background
     */
    public String getBackground() {
        return background;
    }

    /**
     * @param background the background to set
     */
    public void setBackground(String background) {
        this.background = background;
    }

    public File getRoomFile() {
        return roomFile;
    }

    public void setRoomFile(File roomFile) {
        this.roomFile = roomFile;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }
}
