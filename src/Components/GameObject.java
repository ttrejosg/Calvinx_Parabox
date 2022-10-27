package Components;

import java.awt.*;
import static Handlers.Constants.*;

/**
 * Clase que representa un objeto del juego.
 * Un objeto del juego posee:
 * Un {@link Point} 'point' que representa la posición del objecto.
 * Un {@link String} 'path' que es la ruta del recurso de imagen.
 * Un {@link Rectangle} 'collisionBox' que representa el area que ocupa el objeto.
 * @author Tomás y Carlos
 */
public abstract class GameObject {
    //Attributes:

    protected Point position;
    protected String path;
    protected Rectangle collisionBox;

    //Constructors:

    public GameObject(Point position, String path) {
        this.position = position;
        this.collisionBox = new Rectangle(position.x,position.y,BLOCKS_SIZE,BLOCKS_SIZE);
        this.path = path;
    }

    //Methods:

    /**
     * Metodo abstracto que indica al panel del juego como se va a pintar el objeto.
     * Este metodo se ejecuta una vez por frame es decir 120 veces por segundo.
     * @param g El lapiz que pinta el objeto
     */
    abstract public void paint(Graphics g);

    //Getters and setters:

    /**
     * Setea la posición del gameObject junto con su hitbox.
     * @param position La posicion a establecer.
     */
    public void setPosition(Point position) {
        this.position.setLocation(position.x,position.y);
        this.collisionBox.setLocation(position.x,position.y);
    }

    public Point getPosition() {
        return position;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Rectangle getCollisionBox() {
        return collisionBox;
    }

    public void setCollisionBox(Rectangle collisionBox) {
        this.collisionBox = collisionBox;
    }
}
