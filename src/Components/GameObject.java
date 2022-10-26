package Components;

import java.awt.*;
import static Handlers.Constants.*;

/**
 *
 * @author Tomás
 */
public abstract class GameObject {
    protected Point position;
    protected String path;
    protected Rectangle collisionBox;

    public GameObject(Point position, String path) {
        this.position = position;
        this.collisionBox = new Rectangle(position.x,position.y,BLOCKS_SIZE,BLOCKS_SIZE);
        this.path = path;
    }

    abstract public void paint(Graphics g);

    /**
     * @return the position
     */
    public Point getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(Point position) {
        this.position.setLocation(position.x,position.y);
        this.collisionBox.setLocation(position.x,position.y);
    }

    /**
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path the path to set
     */
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
