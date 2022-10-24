package Components;

import Handlers.Constants;
import Handlers.ImageHandler;

import java.awt.*;

public class Tp extends GameObject{
    Tp next;
    public Tp(Point position, String path) {
        super(position, path);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(ImageHandler.get(this.getPath()), this.getPosition().x, this.getPosition().y,
                Constants.BLOCKS_SIZE, Constants.BLOCKS_SIZE, null);
    }
}
