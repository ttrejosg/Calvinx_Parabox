package Components;

import Handlers.KeyHandler;
import Handlers.SoundHandler;
import java.awt.Point;


/**
 * Clase que representa al personaje jugable.
 * @author Tomás y Carlos
 */
public class Player extends Entity{

    //Constructors:

    public Player(Point position, Room room) {
        super(position, "player_sprites.png", room);
    }

    //Methods:

    @Override
    public void update() {
        updateAnimationTick();
        if(this.state == 0) updateState();
        else if(this.steps < 60){
            if(this.steps == 0){
                GameObject collision = checkCollision();
                if(collision == null) move(3);
                else if(collision instanceof Block && ((Block) collision).state == 0){
                    ((Block)collision).setState(this.state);
                    ((Block)collision).update();
                    if(((Block)collision).state != 0) move(3);
                    else resetState();
                } else if(collision instanceof Tp) {
                    SoundHandler.createClip(SoundHandler.get("Tp.wav")).start();
                    this.setPosition(((Tp) collision).getNext().position);
                } else if (collision instanceof Enemy) KeyHandler.KP_R = true;
                else resetState();
            } else move(3);
        } else resetState();
    }

    /**
     * Metodo que actualiza el estado del jugador segun la tecla presionada.
     */
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
