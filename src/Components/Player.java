package Components;

import Handlers.Constants;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

/**
 *
 * @author Tomás
 */
public class Player extends GameObject{
    private BufferedImage[][] sprite;
    private int state;
    private int anyIndex, anyTick;

    public Player(Point position) {
        super(position, "player_sprites.png");
        this.state = 0;
        loadAnimations();
    }
    
    @Override
    public void update() {
        
    }
    
    public void paint(Graphics g){
        updateAnimationTick();
        g.drawImage(sprite[state][anyIndex], this.getPosition().x, this.getPosition().y, 60, 60, null);
    }
    
    private void loadAnimations() {
        BufferedImage img = getSprite(this.getPath());
        sprite = new BufferedImage[5][8];
        for (int j = 0; j < sprite.length; j++)
            for (int i = 0; i < sprite[j].length; i++)
                sprite[j][i] = img.getSubimage(i * 170, j * 170, 160, 160);
    }
    
    public BufferedImage getSprite(String path) {
        BufferedImage img = null;
        InputStream is = getClass().getResourceAsStream("player_sprites.png");
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return img;
    }
    
    public void updateAnimationTick(){
        anyTick ++;
        if(anyTick >= Constants.ANY_SPEED){
            anyTick = 0;
            anyIndex ++;
            if(anyIndex >= 5){
                anyIndex = 0;
            }
        }
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
