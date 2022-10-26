package Components;

import Handlers.Constants;
import Handlers.KeyHandler;
import Handlers.SoundHandler;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.sound.sampled.Clip;

import static Handlers.Constants.*;

/**
 *
 * @author Tomás
 */
public class Player extends Entity{

    public Player(Point position, Room room) {
        super(position, "player_sprites.png", room);
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
                collision();
            } else move(3);
        } else resetState();
    }

    public void collision(){
        GameObject collision = checkCollision();
        if(collision == null){
            move(3);
        }else if(collision instanceof Block){
            ((Block)collision).setState(this.state);
            ((Block)collision).update();
            if(((Block)collision).state != 0) move(3);
            else resetState();
        } else if(collision instanceof Tp) {
            Clip clip = SoundHandler.createClip(SoundHandler.get("Tp.wav"));
            clip.start();
            this.position.setLocation(((Tp) collision).next.position);
        } else resetState();
    }

    public void updateState(){
        if(KeyHandler.KT_W) this.state = 1;
        else if(KeyHandler.KT_D) this.state = 2;
        else if(KeyHandler.KT_S) this.state = 3;
        else if(KeyHandler.KT_A) this.state = 4;
    }

    @Override
    public void resetState(){
        this.steps = 0;
        this.state = 0;
        KeyHandler.KT_W = false;
        KeyHandler.KT_A = false;
        KeyHandler.KT_S = false;
        KeyHandler.KT_D = false;
    }
}
