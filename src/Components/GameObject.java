package Components;

import java.awt.*;

/**
 *
 * @author Tomás
 */
public abstract class GameObject {
    protected Point position;
    protected String path;

    public GameObject(Point position, String path) {
        this.position = position;
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
        this.position = position;
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
}
