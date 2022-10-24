package Main;

import Handlers.Constants;
import Handlers.ImageHandler;

import static Handlers.Constants.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Tomás
 */
public class GamePanel extends JPanel{
    private Game game;
    private JButton resumeButton;
    private JButton backToMenuButton;
    private JButton exit;

    public GamePanel(Game game) {
        this.game = game;
        Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        this.setPreferredSize(size);
        this.setLayout(null);
        initButtons();
    }

    private void initButtons() {
        this.resumeButton = new JButton();
        this.backToMenuButton = new JButton();
        this.exit = new JButton();

        initResumeButton();
        initBackToMenuButton();
        initExitButton();
    }

    private void initExitButton() {
        this.exit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.exit.setBounds(1103, 324, 52, 25);
        this.exit.setContentAreaFilled(false);
        this.exit.setBorderPainted(false);
        this.exit.setVisible(false);
        this.exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        this.add(this.exit);
    }

    private void initBackToMenuButton() {
        this.backToMenuButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.backToMenuButton.setBounds(1103, 288, 158, 25);
        this.backToMenuButton.setContentAreaFilled(false);
        this.backToMenuButton.setBorderPainted(false);
        this.backToMenuButton.setVisible(false);
        this.backToMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.getCurrentRoom().setBackground(BACKGROUND_PATH);
                backToMenuButton.setVisible(false);
                exit.setVisible(false);
                resumeButton.setVisible(false);
                game.getgWindow().setFocusable(true);
                game.getgWindow().requestFocus();
                game.getgPanel().setVisible(false);
                game.getCurrentRoom().initRoom();
                game.getgWindow().getRoomSelector().setEnabled(true);
                game.getgWindow().getRoomSelector().setVisible(true);
            }
        });
        this.add(this.backToMenuButton);
    }

    private void initResumeButton() {
        this.resumeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.resumeButton.setBounds(1103, 250, 102, 28);
        this.resumeButton.setContentAreaFilled(false);
        this.resumeButton.setBorderPainted(false);
        this.resumeButton.setVisible(false);
        this.resumeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.getCurrentRoom().setBackground(BACKGROUND_PATH);
                backToMenuButton.setVisible(false);
                exit.setVisible(false);
                resumeButton.setVisible(false);
                game.getgWindow().setFocusable(true);
                game.getgWindow().requestFocus();
                game.setGameThread(new Thread(game));
                game.setPlaying(true);
                game.getGameThread().start();
            }
        });
        this.add(resumeButton);
    }

    /**
     * Método sobrescrito que se encarga de dibujar todos los GameObjects.
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.getCurrentRoom().paint(g);
        SwingUtilities.updateComponentTreeUI(this.game.getgWindow());
        game.getCurrentRoom().getPlayer().paint(g);
    }

    /**
     * @return the game
     */
    public Game getGame() {
        return game;
    }

    public JButton getResumeButton() {
        return resumeButton;
    }

    public void setResumeButton(JButton resumeButton) {
        this.resumeButton = resumeButton;
    }

    public JButton getBackToMenuButton() {
        return backToMenuButton;
    }

    public void setBackToMenuButton(JButton backToMenuButton) {
        this.backToMenuButton = backToMenuButton;
    }

    public JButton getExit() {
        return exit;
    }

    public void setExit(JButton exit) {
        this.exit = exit;
    }
}
