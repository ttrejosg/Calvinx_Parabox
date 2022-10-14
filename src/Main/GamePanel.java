package Main;

import static Handlers.Constants.*;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Tomás
 */
public class GamePanel extends JPanel{
    private Game game;

    public GamePanel(Game game) {
        this.game = game;
        setPanelSize();
    }
    
    /**
     * Inicializa el tamaño del panel
     */
    private void setPanelSize() {
        Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        this.setPreferredSize(size);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.getPlayer().paint(g);
        
    }

    /**
     * @return the game
     */
    public Game getGame() {
        return game;
    }
}
