package Components;

import Handlers.Constants;
import Handlers.ImageHandler;

import java.awt.*;

/**
 *
 * @author Tomás
 */
public class Wall extends GameObject{

    public Wall(Point position, String path) {
        super(position, path);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(ImageHandler.get(this.getPath()), this.getPosition().x, this.getPosition().y
                ,Constants.BLOCKS_SIZE, Constants.BLOCKS_SIZE, null);
    }
}
