package Main;

import Handlers.KeyHandler;
import Handlers.SoundHandler;
import static Handlers.Constants.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.sound.sampled.Clip;
import javax.swing.*;

/**
 * Clase que representa el panel del juego y sus elementos.
 * El panel del juego posee:
 * Un {@link  Game} 'game' que es la referencia al juego.
 * @author Tomás y Carlos
 */
public class GamePanel extends JPanel{

    //Attributes:

    private Game game;
    private JButton resumeButton;
    private JButton backToMenuButton;
    private JButton exit;

    //Constructors:

    public GamePanel(Game game) {
        this.game = game;
        Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        this.setPreferredSize(size);
        this.setLayout(null);
        initButtons();
    }

    //Methods:

    /**
     * Metodo que inicia los botones del panel.
     */
    private void initButtons() {
        this.resumeButton = new JButton();
        this.backToMenuButton = new JButton();
        this.exit = new JButton();
        initResumeButton();
        initBackToMenuButton();
        initExitButton();
    }

    /**
     * Metodo que inicia el boton de salida del panel.
     */
    private void initExitButton() {
        this.exit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.exit.setBounds(1103, 324, 52, 25);
        this.exit.setContentAreaFilled(false);
        this.exit.setBorderPainted(false);
        this.exit.setVisible(false);
        this.exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SoundHandler.createClip(SoundHandler.get("Button.wav")).start();
                System.exit(0);
            }
        });
        this.add(this.exit);
    }

    /**
     * Metodo que inicia el boton de regresar a menú del panel.
     */
    private void initBackToMenuButton() {
        this.backToMenuButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.backToMenuButton.setBounds(1103, 288, 158, 25);
        this.backToMenuButton.setContentAreaFilled(false);
        this.backToMenuButton.setBorderPainted(false);
        this.backToMenuButton.setVisible(false);
        this.backToMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backToMenu();
            }
        });
        this.add(this.backToMenuButton);
    }

    /**
     * Metodo que inicia el boton de reanudar del panel.
     */
    private void initResumeButton() {
        this.resumeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.resumeButton.setBounds(1103, 250, 102, 28);
        this.resumeButton.setContentAreaFilled(false);
        this.resumeButton.setBorderPainted(false);
        this.resumeButton.setVisible(false);
        this.resumeButton.addKeyListener(new KeyAdapter() {@Override public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(e.getKeyCode() == KeyEvent.VK_ESCAPE) resume();
            }});
        this.resumeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resume();
            }
        });
        this.add(resumeButton);
    }

    /**
     * Metodo que devuelve al menu principal.
     */
    public void backToMenu(){
        SoundHandler.createClip(SoundHandler.get("Back_to_menu.wav")).start();
        game.getCurrentRoom().setBackground(BACKGROUND_PATH);
        backToMenuButton.setVisible(false);
        exit.setVisible(false);
        resumeButton.setVisible(false);
        game.getgWindow().setFocusable(true);
        game.getgWindow().requestFocus();
        this.setVisible(false);
        game.getCurrentRoom().initRoom();
        game.getgWindow().getRoomSelector().setEnabled(true);
        game.getgWindow().getRoomSelector().setVisible(true);
    }

    /**
     * Metodo que reanuda el juego.
     */
    public void resume(){
        SoundHandler.createClip(SoundHandler.get("Resume.wav")).start();
        game.getCurrentRoom().setBackground(BACKGROUND_PATH);
        backToMenuButton.setVisible(false);
        exit.setVisible(false);
        resumeButton.setVisible(false);
        game.getgWindow().setFocusable(true);
        game.getgWindow().requestFocus();
        game.setGameThread(new Thread(game));
        KeyHandler.resetKeyStates();
        game.setPlaying(true);
        game.getGameThread().start();
    }

    /**
     * Método sobrescrito que pinta todos los objetos del juego en el panel de acuerdo a sus implementaciones del metodo paint.
     * @param g El lapiz que pinta los objetos.
     */
    @Override
    public void paintComponent(Graphics g) {
        try{
            super.paintComponent(g);
            game.getCurrentRoom().paint(g);
            SwingUtilities.updateComponentTreeUI(this.game.getgWindow());
        }catch (Exception e){
            System.out.println("Error de libreria");
        }
    }

    //Getters and setters:

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
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
