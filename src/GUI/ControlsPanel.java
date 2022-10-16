package GUI;

import Main.Game;

import javax.swing.*;
import java.awt.*;

import static Handlers.Constants.*;

public class ControlsPanel extends JPanel {

    private JButton backButton;
    private JLabel backgroundLabel;
    public ControlsPanel(){
        initComponents();
    }

    /**
     * Método que inicializa los componentes.
     */
    private void initComponents(){
        initControlsPanel();
        initBackButton();
        initBackgroundLabel();
    }

    /**
     * Método que inicializa el fondo.
     */
    private void initBackgroundLabel() {
        this.backgroundLabel = new JLabel();
        this.backgroundLabel.setBounds(0, 0,GAME_WIDTH,GAME_HEIGHT);
        this.backgroundLabel.setVisible(true);
        ImageIcon wallpaper = new ImageIcon("src/Resources/Images/Fond.png");
        Icon icon = new ImageIcon(wallpaper.getImage());
        this.backgroundLabel.setIcon(icon);
        this.add(this.backgroundLabel);
    }

    /**
     * Método que inicializa el Panel.
     */
    private void initControlsPanel(){
        this.setPreferredSize(new Dimension(GAME_WIDTH,GAME_HEIGHT));
        this.setLayout(null);
    }

    /**
     * Inicializa el boton de volver al menu.
     */
    private void initBackButton() {
        this.backButton = new JButton();
        this.backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.backButton.setBounds(563, 546, 158, 67);
        this.backButton.setContentAreaFilled(false);
        this.add(this.backButton);
    }

    public JLabel getBackgroundLabel() {
        return backgroundLabel;
    }

    public void setBackgroundLabel(JLabel backgroundLabel) {
        this.backgroundLabel = backgroundLabel;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public void setBackButton(JButton backButton) {
        this.backButton = backButton;
    }
}
