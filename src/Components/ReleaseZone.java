package Components;

import Handlers.Constants;
import Handlers.ImageHandler;

import java.awt.*;

/**
 *
 * @author Tomás y Carlos
 */
public class ReleaseZone extends GameObject{

    public ReleaseZone(Point position, String path) {
        super(position, path);
    }

    @Override
    public void update() {

    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(ImageHandler.get(this.getPath()), this.getPosition().x, this.getPosition().y
                ,Constants.BLOCKS_SIZE, Constants.BLOCKS_SIZE, null);
    }
}
