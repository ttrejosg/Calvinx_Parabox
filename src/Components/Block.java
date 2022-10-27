package Components;

import Handlers.SoundHandler;
import java.awt.*;

/**
 * Clase que representa un bloque que puede ser movido.
 * @author Tomás y Carlos
 */
public class Block extends Entity{

    //Constructors:
    public Block(Point position, String path, Room room) {
        super(position, path, room);
    }

    //Methods:

    @Override
    public void update() {
        updateAnimationTick();
        if(this.steps < 60 && this.state != 0){
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
                }
                else resetState();
            } else move(3);
        }else resetState();
    }

    @Override
    public void resetState(){
        this.steps = 0;
        this.state = 0;
    }

}
