package Components;

import Handlers.Constants;
import Handlers.ImageHandler;

import java.awt.*;

/**
 *
 * @author Tomás y Carlos
 */
public class Door extends GameObject{

    public Door(Point position, String path) {
        super(position, path);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(ImageHandler.get(this.path), this.position.x, this.position.y
                ,Constants.BLOCKS_SIZE, Constants.BLOCKS_SIZE, null);
    }
}
