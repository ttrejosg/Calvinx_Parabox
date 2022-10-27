package Components;

import Handlers.Constants;
import Handlers.ImageHandler;

import java.awt.*;

/**
 * Clase que representa un objeto teletransportador.
 * Un objeto teletransportador posee:
 * Un {@link Tp} 'next' que contiene una referencia al bloque destino.
 * @author Tomás y Carlos
 */
public class Tp extends GameObject{

    //Attributes:

    Tp next;

    //Constructors:

    public Tp(Point position, String path) {
        super(position, path);
    }

    //Methods:

    @Override
    public void paint(Graphics g) {
        g.drawImage(ImageHandler.get(this.getPath()), this.getPosition().x, this.getPosition().y,
                Constants.BLOCKS_SIZE, Constants.BLOCKS_SIZE, null);
    }

}
