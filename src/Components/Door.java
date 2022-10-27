package Components;

import Handlers.Constants;
import Handlers.ImageHandler;

import java.awt.*;

/**
 *Clase que representa el lugar objetiva del jugador.
 * @author Tomás y Carlos
 */
public class Door extends GameObject{

    //Constructors:

    public Door(Point position, String path) {
        super(position, path);
    }

    //Methods:

    @Override
    public void paint(Graphics g) {
        g.drawImage(ImageHandler.get(this.path), this.position.x, this.position.y
                ,Constants.BLOCKS_SIZE, Constants.BLOCKS_SIZE, null);
    }

}
