package Components;

import Handlers.KeyHandler;

import java.awt.*;
import java.util.Random;
import static Handlers.Constants.*;

public class Enemy extends Entity{

    public Enemy(Point position, Room room) {
        super(position, ENEMY_PATH, room);
    }

    @Override
    public void update() {
        if (this.state == 0) randomState();
        if(steps % 60 ==  0) {
            GameObject collision = checkCollision();
            if (collision == null) move(1);
            else if (collision instanceof Block && ((Block) collision).state == 0) {
                ((Block) collision).setState(this.state);
                ((Block) collision).update();
                resetState();
            } else if (collision instanceof Tp) this.setPosition(((Tp) collision).next.position);
            else if (collision instanceof Player) KeyHandler.KP_R = true;
            else resetState();
        } else move(1);
    }

    public void randomState(){
        Random random = new Random();
        this.state = random.nextInt(4)+1;
    }

    @Override
    public void resetState(){
        this.state = 0;
        this.steps = 0;
    }

}
