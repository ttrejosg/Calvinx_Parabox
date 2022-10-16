package GUI;

import javax.swing.*;
import java.awt.*;

import static Handlers.Constants.*;
import static javax.swing.BorderFactory.createEtchedBorder;

/**
 *
 * @author Tomás y Carlos
 */
public class Menu extends JPanel {

    private JLabel backgroundLabel;
    private JButton playButton;
    private JButton controlsButton;
    private JButton quitButton;

    /**
     * Constructor clase Menu
     */
    public Menu(){
        createComponents();
        initComponents();
    }

    /**
     * Metodo que crea los componentes del menu
     */
    private void createComponents(){
        this.backgroundLabel = new JLabel();
        this.playButton = new JButton();
        this.controlsButton = new JButton();
        this.quitButton = new JButton();
    }

    /**
     * Metodo que inicia los componentes del Menu
     */
    private void initComponents(){
        initMenu();
        initPlayButton();
        initControlsButton();
        initQuitButton();
        initBackgroundLabel();
    }

    /**
     * Método que inicializa el menú.
     */
    private void initMenu(){
        this.setPreferredSize(new Dimension(GAME_WIDTH,GAME_HEIGHT));
        this.setLayout(null);
    }

    /**
     * Método que inicializa el fondo del menú.
     */
    private void initBackgroundLabel(){
        this.backgroundLabel.setBounds(0, 0,GAME_WIDTH,GAME_HEIGHT);
        this.backgroundLabel.setVisible(true);
        ImageIcon wallpaper = new ImageIcon("src/Resources/Images/CalvinxParabox.png");
        Icon icon = new ImageIcon(wallpaper.getImage());
        this.backgroundLabel.setIcon(icon);
        this.add(this.backgroundLabel);
    }

    /**
     * Método que inicializa el boton de jugar.
     */
    private void initPlayButton(){
        this.playButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.playButton.setBounds(561,476 ,158, 67);
        this.playButton.setContentAreaFilled(false);
        this.add(this.playButton);
    }

    /**
     * Método que inicializa el boton para ver las instrucciones del juego.
     */
    private void initControlsButton(){
        this.controlsButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.controlsButton.setBounds(658, 559, 158, 67);
        this.controlsButton.setContentAreaFilled(false);
        this.add(this.controlsButton);
    }

    /**
     * Método que inicializa el boton que cierra el programa.
     */
    private void initQuitButton(){
        this.quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.quitButton.setBounds(483, 561, 159, 67);
        this.quitButton.setContentAreaFilled(false);
        this.add(this.quitButton);
    }

    public JLabel getBackgroundLabel() {
        return backgroundLabel;
    }

    public void setBackgroundLabel(JLabel backgroundLabel) {
        this.backgroundLabel = backgroundLabel;
    }

    public JButton getPlayButton() {
        return playButton;
    }

    public void setPlayButton(JButton playButton) {
        this.playButton = playButton;
    }

    public JButton getControlsButton() {
        return controlsButton;
    }

    public void setControlsButton(JButton controlsButton) {
        this.controlsButton = controlsButton;
    }

    public JButton getQuitButton() {
        return quitButton;
    }

    public void setQuitButton(JButton quitButton) {
        this.quitButton = quitButton;
    }
}
