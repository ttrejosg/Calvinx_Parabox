package Components;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

/**
 *
 * @author Tomás
 */
public class Player extends GameObject{
    private Image[][] sprite;
    private int state;

    public Player(Point position) {
        super(position, "blue");
        this.state = 0;
        this.setSprite();
    }
    
    public void setSprite(){
        
    }
    
    @Override
    public void update() {
        
    }
    
    public void paint(Graphics g){
        
    }

    /**
     * @return the sprite
     */
    public Image[][] getSprite() {
        return sprite;
    }

    /**
     * @param sprite the sprite to set
     */
    public void setSprite(Image[][] sprite) {
        this.sprite = sprite;
    }

    /**
     * @return the state
     */
    public int getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(int state) {
        this.state = state;
    }
    
}
