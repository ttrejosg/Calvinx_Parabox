package Components;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static Handlers.Constants.*;

/**
 * Clase que representa a una entidad del juego.
 * Una entidad es un objeto del juego que puede moverse.
 * Una entidad posee:
 * Una {@link BufferedImage} 'sprite' que es una secuencia de imagenes.
 * Un entero 'state': 0 = quieto, 1 = arriba, 2 = derecha, 3 = abajo, 4 = izquierda.
 * Un entero 'steps' que cuenta la cantida de pasos que ha dado la entidad.
 * Dos enteros 'anyIndex' y 'anyTick' que actualizan el sprite de la entidad.
 * Una {@link Room} 'room' que representa la sala en la cual se encuentra la entidad.
 * @author: Tomás y Carlos.
 */
public abstract class Entity extends GameObject {

    //Attributes:

    protected BufferedImage[][] sprite;
    protected int state, anyIndex, anyTick, steps;
    protected Room room;

    //Constructors:

    public Entity(Point position, String path, Room room) {
        super(position, path);
        this.state = 0; this.steps = 0; this.anyIndex = 0; this.anyTick = 0;
        this.room = room;
        loadAnimations();
    }

    //Methods:

    /**
     * Metodo abstracto en el cual se implementa la actualizacion de una entidad.
     * Este metodo se ejecuta una vez por frame es decir 200 veces por segundo.
     */
    public abstract void update();

    /**
     * Metodo abstracto en el cual se implementa la forma de restablecer el estado de la entidad.
     */
    public abstract void resetState();

    /**
     * Método que carga las animaciones de la entidad.
     */
    private void loadAnimations() {
        BufferedImage img = getSprite(this.getPath());
        sprite = new BufferedImage[5][8];
        for (int j = 0; j < sprite.length; j++)
            for (int i = 0; i < sprite[j].length; i++)
                sprite[j][i] = img.getSubimage(i * 170, j * 170, 160, 160);
    }

    /**
     * Método que obtiene el sprite de la entidad.
     * @param path ruta de la imagen que contiene el sprite.
     * @return BufferedImage que contiene el sprite.
     */
    private BufferedImage getSprite(String path) {
        BufferedImage img = null;
        InputStream is = getClass().getResourceAsStream("/Resources/Images/" + this.getPath());
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return img;
    }

    /**
     * Método que actualiza la imagen a pintar, con el fin de generar el efecto de la animación.
     */
    protected void updateAnimationTick(){
        anyTick ++;
        if(anyTick >= ANY_SPEED){
            anyTick = 0;
            anyIndex ++;
            if(anyIndex >= 5){
                anyIndex = 0;
            }
        }
    }

    /**
     * Metodo que mueve a la entidad en funcion de la velocidad.
     * @param speed velocidad con la que se movera la entidad.
     * la velocidad indica cuantos pixeles se va a mover.
     */
    protected void move(int speed){
        if(this.state == 1){
            this.position.translate(0,-speed);
            this.collisionBox.translate(0,-speed);
        } else if(this.state == 2){
            this.position.translate(speed,0);
            this.collisionBox.translate(speed,0);
        } else if(this.state == 3){
            this.position.translate(0,speed);
            this.collisionBox.translate(0,speed);
        } else if(this.state == 4){
            this.position.translate(-speed,0);
            this.collisionBox.translate(-speed,0);
        }
        this.steps +=speed;
    }

    /**
     * Metodo que revisa si hay colision en funcion del estado de la entidad.
     * @return null si no hay colision, de lo contrario el objeto con el que colisiona.
     */
    protected GameObject checkCollision(){
        GameObject collision = null;
        if(this.state == 1) collision = this.room.intersects(new Rectangle(this.position.x,this.position.y-BLOCKS_SIZE,BLOCKS_SIZE,BLOCKS_SIZE));
        else if(this.state == 2) collision = this.room.intersects(new Rectangle(this.position.x+BLOCKS_SIZE,this.position.y,BLOCKS_SIZE,BLOCKS_SIZE));
        else if(this.state == 3) collision = this.room.intersects(new Rectangle(this.position.x,this.position.y+BLOCKS_SIZE,BLOCKS_SIZE,BLOCKS_SIZE));
        else if(this.state == 4) collision = this.room.intersects(new Rectangle(this.position.x-BLOCKS_SIZE,this.position.y,BLOCKS_SIZE,BLOCKS_SIZE));
        return collision;
    }

    /**
     * Método que pinta al jugador sobre el panel del juego.
     * @param g el lapiz para pintar.
     */
    @Override
    public void paint(Graphics g){
        g.drawImage(sprite[state][anyIndex], this.getPosition().x, this.getPosition().y
                ,BLOCKS_SIZE, BLOCKS_SIZE, null);
    }

    //Getters and setters:

    public BufferedImage[][] getSprite() {
        return sprite;
    }

    public void setSprite(BufferedImage[][] sprite) {
        this.sprite = sprite;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getAnyIndex() {
        return anyIndex;
    }

    public void setAnyIndex(int anyIndex) {
        this.anyIndex = anyIndex;
    }

    public int getAnyTick() {
        return anyTick;
    }

    public void setAnyTick(int anyTick) {
        this.anyTick = anyTick;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
