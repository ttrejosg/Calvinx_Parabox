package Components;

import Handlers.KeyHandler;
import Handlers.SoundHandler;

import java.awt.*;
import java.util.Random;
import static Handlers.Constants.*;

/**
 * Clase que representa el enemigo.
 * @author: Tomás y Carlos
 */
public class Enemy extends Entity{

    //Constructors:

    public Enemy(Point position, Room room) {
        super(position, ENEMY_PATH, room);
    }

    //Methods:

    @Override
    public void update() {
        updateAnimationTick();
        if (this.state == 0) randomState();
        if(steps % 60 ==  0) {
            GameObject collision = checkCollision();
            if (collision == null) move(1);
            else if (collision instanceof Block && ((Block) collision).state == 0) {
                ((Block) collision).setState(this.state);
                ((Block) collision).update();
                resetState();
            } else if (collision instanceof Tp) {
                SoundHandler.createClip(SoundHandler.get("Tp.wav")).start();
                this.setPosition(((Tp) collision).next.position);
            }
            else if (collision instanceof Player) KeyHandler.KP_R = true;
            else resetState();
        } else move(1);
    }

    /**
     * Metodo que establece el estado del enemigo de manera aleatoria.
     */
    private void randomState(){
        Random random = new Random();
        this.state = random.nextInt(4)+1;
    }

    @Override
    public void resetState(){
        this.state = 0;
        this.steps = 0;
    }

}
