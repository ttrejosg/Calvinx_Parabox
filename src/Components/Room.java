package Components;

import Handlers.Constants;
import Handlers.ImageHandler;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Clase que representa un escenario jugable.
 * Una Room posee:
 * Un entero 'id' que es el identificador.
 * Un {@link ArrayList<Block>} 'blocks' que es una lista de bloques de la sala.
 * Un {@link ArrayList<Wall>} 'walls' que es una lista de paredes de la sala.
 * Un {@link ArrayList<ReleaseZone>} 'releaseZones' que es una lista de zonas de descargue de la sala.
 * Un {@link ArrayList<Floor>} 'floor' que es una lista del suelo de la sala.
 * Una {@link Door} 'door' que es la puerta objetivo del jugador.
 * Un {@link Player} 'player' que es el personaje jugable.
 * Un {@link Enemy} 'enemy' que es el enemigo de la sala.
 * Un {@link File} 'file' que es el archivo de texto que describe la sala.
 * Un {@link String} 'background' que es la ruta del recurso de imagen del fondo de la sala.
 * Un {@link Tp} 'tp' que es un bloque de teletransporte.
 * @author Tomás y Carlos
 */
public class Room {

    //Attributes:

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

    //Constructors:

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

    //Methods:

    /**
     * Metodo que crea los componentes de la sala.
     */
    private void createComponents() {
        this.blocks = new ArrayList<>();
        this.walls = new ArrayList<>();
        this.releaseZones = new ArrayList<>();
        this.floor = new ArrayList<>();
    }

    /**
     * Metodo que inicia los componentes de la sala a partir del 'file'.
     */
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
                    } else if (linea.charAt(j) == 'o') {
                        this.walls.add(new Wall(new Point(j * Constants.BLOCKS_SIZE, i * Constants.BLOCKS_SIZE), Constants.DETOURLEFT_PATH));
                    } else if (linea.charAt(j) == 'k') {
                        this.walls.add(new Wall(new Point(j * Constants.BLOCKS_SIZE, i * Constants.BLOCKS_SIZE), Constants.DETOURUP_PATH));
                    } else if (linea.charAt(j) == 'j') {
                        this.walls.add(new Wall(new Point(j * Constants.BLOCKS_SIZE, i * Constants.BLOCKS_SIZE), Constants.DETOURDOWN_PATH));
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
                            new_tp.setNext(this.tp);
                            this.tp.setNext(new_tp);
                        }
                    }  else if (linea.charAt(j) == 'q') {
                        if(tp == null) {
                            tp = new Tp(new Point(j * Constants.BLOCKS_SIZE, i * Constants.BLOCKS_SIZE), Constants.TP_PATH);
                            this.floor.add(new Floor(new Point(j * Constants.BLOCKS_SIZE, i * Constants.BLOCKS_SIZE), Constants.FLOOR_PATH));
                        }
                        else{
                            Tp new_tp = new Tp(new Point(j * Constants.BLOCKS_SIZE, i * Constants.BLOCKS_SIZE), Constants.TP_PATH);
                            this.floor.add(new Floor(new Point(j * Constants.BLOCKS_SIZE, i * Constants.BLOCKS_SIZE), Constants.FLOOR_PATH));
                            new_tp.setNext(this.tp);
                            this.tp.setNext(new_tp);
                        }
                    }else if (linea.charAt(j) == 'p') {
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

    /**
     * Metodo que restablece la sala a su estado original.
     */
    public void reset(){
        this.blocks.clear();
        this.walls.clear();
        this.releaseZones.clear();
        this.floor.clear();
        this.player.resetState();
        this.enemy.resetState();
        initRoom();
    }

    /**
     * Metodo que hace el llamado al metodo update de cada entidad en la sala.
     */
    public void update(){
        for(Block block: this.blocks) block.update();
        this.player.update();
        this.enemy.update();
    }

    /**
     * Metodo que hace el llamado al metodo paint de cada objeto del juego en la sala.
     * @param g El lapiz con el que se pintaran los objetos del juego.
     */
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

    /**
     * Metodo que busca en la sala un objeto cuya collisionBox se intersecte con la collisionBox dada.
     * @param collisionBox area ocupada por la entidad a verificar.
     * @return null si no encuentra el objeto, de lo contrario retorna el objeto cuya collisionBox intersecta con la dada.
     */
    public GameObject intersects(Rectangle collisionBox){
        GameObject gObject = null;
        int i = 0;
        while(i < this.walls.size() && gObject == null){
            if(this.walls.get(i).getCollisionBox().intersects(collisionBox)) gObject = this.walls.get(i);
            i++;
        }
        i=0;
        while(i < this.blocks.size() && gObject == null){
            if(this.blocks.get(i).getCollisionBox().intersects(collisionBox)) gObject = this.blocks.get(i);
            i ++;
        }
        if(this.tp != null){
            if(this.tp.getCollisionBox().intersects(collisionBox)) gObject = this.tp;
            if(this.tp.getNext().getCollisionBox().intersects(collisionBox)) gObject = this.tp.getNext();
        }
        if(this.player.getCollisionBox().intersects(collisionBox)) gObject = this.player;
        else if(this.enemy.getCollisionBox().intersects(collisionBox)) gObject = this.enemy;
        return gObject;
    }

    //Getters and setters:

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(ArrayList<Block> blocks) {
        this.blocks = blocks;
    }

    public ArrayList<Wall> getWalls() {
        return walls;
    }

    public void setWalls(ArrayList<Wall> walls) {
        this.walls = walls;
    }

    public ArrayList<ReleaseZone> getReleaseZones() {
        return releaseZones;
    }

    public void setReleaseZones(ArrayList<ReleaseZone> releaseZones) {
        this.releaseZones = releaseZones;
    }

    public ArrayList<Floor> getFloor() {
        return floor;
    }

    public void setFloor(ArrayList<Floor> floor) {
        this.floor = floor;
    }

    public Door getDoor() {
        return door;
    }

    public void setDoor(Door door) {
        this.door = door;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    public File getRoomFile() {
        return roomFile;
    }

    public void setRoomFile(File roomFile) {
        this.roomFile = roomFile;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public Tp getTp() {
        return tp;
    }

    public void setTp(Tp tp) {
        this.tp = tp;
    }
}
