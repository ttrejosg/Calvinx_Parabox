package Components;

import Handlers.KeyHandler;
import Handlers.SoundHandler;
import Main.Game;

import javax.imageio.ImageIO;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static Handlers.Constants.ANY_SPEED;
import static Handlers.Constants.BLOCKS_SIZE;

public abstract class Entity extends GameObject {
    protected BufferedImage[][] sprite;
    protected int state, anyIndex, anyTick, steps;
    protected Room room;

    public Entity(Point position, String path, Room room) {
        super(position, path);
        this.state = 0; this.steps = 0; this.anyIndex = 0; this.anyTick = 0;
        this.room = room;
        loadAnimations();
    }

    public abstract void update();
    public abstract void resetState();

    /**
     * Método que carga las animaciones del jugador.
     */
    private void loadAnimations() {
        BufferedImage img = getSprite(this.getPath());
        sprite = new BufferedImage[5][8];
        for (int j = 0; j < sprite.length; j++)
            for (int i = 0; i < sprite[j].length; i++)
                sprite[j][i] = img.getSubimage(i * 170, j * 170, 160, 160);
    }

    /**
     * Método que obtiene el sprite del jugador.
     * @param path dirección de la imagen que contiene el sprite.
     * @return una BufferedImagen que contiene el ssprite.
     */
    public BufferedImage getSprite(String path) {
        BufferedImage img = null;
        InputStream is = getClass().getResourceAsStream("/Resources/Images/" + this.getPath());
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
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
    public void updateAnimationTick(){
        anyTick ++;
        if(anyTick >= ANY_SPEED){
            anyTick = 0;
            anyIndex ++;
            if(anyIndex >= 5){
                anyIndex = 0;
            }
        }
    }

    public void move(int speed){
        if(this.state == 1) this.getPosition().translate(0,-speed);
        else if(this.state == 2) this.getPosition().translate(speed,0);
        else if(this.state == 3) this.getPosition().translate(0,speed);
        else if(this.state == 4) this.getPosition().translate(-speed,0);
        this.steps +=3;
    }
    public GameObject checkCollision(){
        GameObject gObject = null;
        if(this.state == 1) gObject = this.room.getObjectAt(this.getPosition().x,this.getPosition().y-BLOCKS_SIZE);
        else if(this.state == 2) gObject = this.room.getObjectAt(this.getPosition().x+BLOCKS_SIZE,this.getPosition().y);
        else if(this.state == 3) gObject = this.room.getObjectAt(this.getPosition().x,this.getPosition().y+BLOCKS_SIZE);
        else if(this.state == 4) gObject = this.room.getObjectAt(this.getPosition().x-BLOCKS_SIZE,this.getPosition().y);
        return gObject;

    }

    /**
     * Método que dibuja al jugador sobre el GamePanel.
     * @param g el lapiz para pintar.
     */
    @Override
    public void paint(Graphics g){
        updateAnimationTick();
        g.drawImage(sprite[state][anyIndex], this.getPosition().x, this.getPosition().y
                ,BLOCKS_SIZE, BLOCKS_SIZE, null);
    }

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
