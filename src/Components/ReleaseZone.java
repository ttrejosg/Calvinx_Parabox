package Components;

import Handlers.Constants;
import Handlers.ImageHandler;

import java.awt.*;

/**
 * Clase que representa el lugar objetivo del bloque
 * @author Tomás y Carlos
 */
public class ReleaseZone extends GameObject{

    //Constructors:

    public ReleaseZone(Point position, String path) {
        super(position, path);
    }

    //Methods:

    @Override
    public void paint(Graphics g) {
        g.drawImage(ImageHandler.get(this.path), this.position.x, this.position.y
                ,Constants.BLOCKS_SIZE, Constants.BLOCKS_SIZE, null);
    }
}
