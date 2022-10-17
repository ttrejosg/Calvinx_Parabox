package Components;

import Handlers.KeyHandler;

import java.awt.Point;

/**
 *
 * @author Tomás y Carlos
 */
public class Block extends GameObject{

    public Block(Point position, String path) {
        super(position, path);
    }
    
    @Override
    public void update() {
        if(KeyHandler.KT_A) this.getPosition().translate(-60, 0);
        else if(KeyHandler.KT_D) this.getPosition().translate(60, 0);
        else if(KeyHandler.KT_W ) this.getPosition().translate(0, -60);
        else if(KeyHandler.KT_S ) this.getPosition().translate(0, 60);
    }
    
}
