package Components;

import java.awt.Point;

/**
 *
 * @author Tomás
 */
public abstract class GameObject {
    private Point position;
    private String path;

    public GameObject(Point position, String path) {
        this.position = position;
        this.path = path;
    }
    
    abstract public void update();

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
