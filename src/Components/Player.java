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
public class Player extends Entity{

    public Player(Point position) {
        super(position, "player_sprites.png");
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
                if(collision == null){
                    move(3);
                }else if(collision instanceof Block){
                    ((Block)collision).update();
                    move(3);
                } else resetState();
            } else move(3);
        } else resetState();
    }

    public void resetState(){
        this.steps = 0;
        this.state = 0;
        KeyHandler.KT_W = false;
        KeyHandler.KT_A = false;
        KeyHandler.KT_S = false;
        KeyHandler.KT_D = false;
    }
}
