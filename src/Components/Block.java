package Components;

import Handlers.Constants;
import Handlers.ImageHandler;
import Handlers.KeyHandler;
import static Handlers.Constants.*;

import java.awt.*;

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
        if(KeyHandler.KT_A) this.getPosition().translate(-BLOCKS_SIZE, 0);
        else if(KeyHandler.KT_D) this.getPosition().translate(BLOCKS_SIZE, 0);
        else if(KeyHandler.KT_W ) this.getPosition().translate(0, -BLOCKS_SIZE);
        else if(KeyHandler.KT_S ) this.getPosition().translate(0, BLOCKS_SIZE);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(ImageHandler.get(this.getPath()), this.getPosition().x, this.getPosition().y
                , BLOCKS_SIZE, BLOCKS_SIZE, null);
    }

}
