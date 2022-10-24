package Components;

import Handlers.Constants;
import Handlers.KeyHandler;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

/**
 *
 * @author Tomás
 */
public class Player extends GameObject{
    private BufferedImage[][] sprite;
    private int state;
    private int anyIndex, anyTick, steps = 0;

    public Player(Point position) {
        super(position, "player_sprites.png");
        this.state = 0;
        loadAnimations();
    }

    /**
     * Método que se encarga de actualizar la posición del jugador gradualmente de acuerdo a los pasos  que haya dado
     * y la tecla que haya oprimido el jugador.
     */
    @Override
    public void update() {
        if(KeyHandler.KT_A) {
            if(steps < 60){
                this.state = 4;
                this.getPosition().translate(-3, 0);
                steps += 3;
            } else {
                steps = 0;
                KeyHandler.KT_A = false;
            }
        }
        else if(KeyHandler.KT_D){
            if(steps < 60){
                this.state = 2;
                this.getPosition().translate(3, 0);
                steps += 3;
            } else {
                steps = 0;
                KeyHandler.KT_D = false;
            }
        }
        else if(KeyHandler.KT_W ){
            if(steps < 60){
                this.state = 1;
                this.getPosition().translate(0, -3);
                steps += 3;
            } else {
                steps = 0;
                KeyHandler.KT_W = false;
            }
        }
        else if(KeyHandler.KT_S ){
            if(steps < 60){
                this.state = 3;
                this.getPosition().translate(0, 3);
                steps += 3;
            } else {
                steps = 0;
                KeyHandler.KT_S = false;
            }
        }
    }

    /**
     * Método que dibuja al jugador sobre el GamePanel.
     * @param g el lapiz para pintar.
     */
    public void paint(Graphics g){
        updateAnimationTick();
        g.drawImage(sprite[state][anyIndex], this.getPosition().x, this.getPosition().y, Constants.BLOCKS_SIZE, Constants.BLOCKS_SIZE, null);
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
        if(anyTick >= Constants.ANY_SPEED){
            anyTick = 0;
            anyIndex ++;
            if(anyIndex >= 5){
                anyIndex = 0;
            }
        }
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
    
}
