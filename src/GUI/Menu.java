package GUI;

import javax.swing.*;
import java.awt.*;
import static Handlers.Constants.*;

/**
 * Clase que representa el menu de inicio del juego y contiene todos sus elementos.
 * @author Tomás y Carlos
 */
public class Menu extends JPanel {

    //Attributes:

    private JLabel backgroundLabel;
    private JButton playButton;
    private JButton quitButton;

    //Constructors:

    public Menu(){
        createComponents();
        initComponents();
    }

    //Methods:

    /**
     * Metodo que crea los componentes del menu
     */
    private void createComponents(){
        this.backgroundLabel = new JLabel();
        this.playButton = new JButton();
        this.quitButton = new JButton();
    }

    /**
     * Metodo que inicia los componentes del Menu
     */
    private void initComponents(){
        initMenu();
        initPlayButton();
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
     * Método que inicializa el boton que cierra el programa.
     */
    private void initQuitButton(){
        this.quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.quitButton.setBounds(561, 558, 159, 67);
        this.quitButton.setContentAreaFilled(false);
        this.add(this.quitButton);
    }

    //Getters and setters:

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

    public JButton getQuitButton() {
        return quitButton;
    }

    public void setQuitButton(JButton quitButton) {
        this.quitButton = quitButton;
    }
}
