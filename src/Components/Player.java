package Components;

import Handlers.Constants;
import Handlers.KeyHandler;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import static Handlers.Constants.*;

/**
 *
 * @author Tomás
 */
public class Player extends GameObject{
    private BufferedImage[][] sprite;
    private int state;
    private int anyIndex, anyTick, steps;

    private Room room;

    public Player(Point position) {
        super(position, "player_sprites.png");
        this.state = 0;
        this.steps = 0;
        loadAnimations();
    }

    /**
     * Método que se encarga de actualizar la posición del jugador gradualmente de acuerdo a los pasos  que haya dado
     * y la tecla que haya oprimido el jugador.
     */
    @Override
    public void update() {
        if(this.state == 0) updateState();
        else if(this.steps < 60){
            if(this.steps == 0){
                GameObject collision = checkCollision();
                System.out.println("hola");
                if(collision == null){
                    move(3);
                }else if(collision instanceof Block){
                    collision.update();
                    move(3);
                } else resetState();
            } else move(3);
        } else resetState();
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
        InputStream is = getClass().getResourceAsStream("/Resources/Images/player_sprites.png");
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

    public void updateState(){
        if(KeyHandler.KT_W) this.state = 1;
        else if(KeyHandler.KT_D) this.state = 2;
        else if(KeyHandler.KT_S) this.state = 3;
        else if(KeyHandler.KT_A) this.state = 4;
    }
    public GameObject checkCollision(){
        GameObject gObject = null;
        if(this.state == 1) gObject = this.room.getObjectAt(this.getPosition().x,this.getPosition().y-BLOCKS_SIZE);
        else if(this.state == 2) gObject = this.room.getObjectAt(this.getPosition().x+BLOCKS_SIZE,this.getPosition().y);
        else if(this.state == 3) gObject = this.room.getObjectAt(this.getPosition().x,this.getPosition().y+BLOCKS_SIZE);
        else if(this.state == 4) gObject = this.room.getObjectAt(this.getPosition().x-BLOCKS_SIZE,this.getPosition().y);
        return gObject;

    }

    public void resetState(){
        this.steps = 0;
        this.state = 0;
        KeyHandler.KT_W = false;
        KeyHandler.KT_A = false;
        KeyHandler.KT_S = false;
        KeyHandler.KT_D = false;
    }

    /**
     * @return the state
     */
    public int getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(int state) {
        this.state = state;
    }

    public int getSteps(){
        return this.steps;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
