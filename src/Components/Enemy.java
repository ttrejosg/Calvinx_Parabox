package Components;

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
        GameObject collision = checkCollision();
        if (collision == null) move(1);
        else if (collision instanceof Block) {
            ((Block) collision).setState(this.state);
            ((Block) collision).update();
            resetState();
        } else if (collision instanceof Tp) this.position.setLocation(((Tp) collision).next.position);
        else if(collision instanceof Player){
            resetState();
            this.room.getPlayer().resetState();
            this.room = new Room(this.room.getId(),this.room.getRoomFile()
                    ,this.room.getPlayer(),this);
        }
        else resetState();
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
