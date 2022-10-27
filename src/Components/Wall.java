package Components;

import Handlers.Constants;
import Handlers.ImageHandler;
import java.awt.*;

/**
 * Clase que representa una pared del juego.
 * @author Tomás y Carlos.
 */
public class Wall extends GameObject{

    //Constructors:

    public Wall(Point position, String path) {
        super(position, path);
    }

    //Methods:

    @Override
    public void paint(Graphics g) {
        g.drawImage(ImageHandler.get(this.getPath()), this.getPosition().x, this.getPosition().y
                ,Constants.BLOCKS_SIZE, Constants.BLOCKS_SIZE, null);
    }

}
