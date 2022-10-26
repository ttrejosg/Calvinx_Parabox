package Components;

import java.awt.*;

/**
 *
 * @author Tomás y Carlos
 */
public class Block extends Entity{

    public Block(Point position, String path, Room room) {
        super(position, path, room);
    }
    
    @Override
    public void update() {
        if(this.steps < 60 && this.state != 0){
            if(this.steps == 0){
                GameObject collision = checkCollision();
                if(collision == null) move(3);
                else if(collision instanceof Block){
                    ((Block)collision).setState(this.state);
                    ((Block)collision).update();
                    if(((Block)collision).state != 0) move(3);
                    else resetState();
                } else if(collision instanceof Tp) this.position.setLocation(((Tp) collision).next.position);
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
