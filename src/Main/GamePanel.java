package Main;

import Handlers.Constants;
import Handlers.ImageHandler;

import static Handlers.Constants.*;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Tomás
 */
public class GamePanel extends JPanel{
    private Game game;

    public GamePanel(Game game) {
        this.game = game;
        Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        this.setBackground(new Color(39, 68, 145));
        this.setPreferredSize(size);
    }

    /**
     * Método sobrescrito que se encarga de dibujar todos los GameObjects.
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.getCurrentRoom().paint(g);
        game.getCurrentRoom().getPlayer().paint(g);
    }

    /**
     * @return the game
     */
    public Game getGame() {
        return game;
    }
}
