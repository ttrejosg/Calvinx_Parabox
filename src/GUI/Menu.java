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

    private JLabel tittleLabel;
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
        this.tittleLabel = new JLabel();
        this.playButton = new JButton();
        this.controlsButton = new JButton();
        this.quitButton = new JButton();
    }

    /**
     * Metodo que inicia los componentes del Menu
     */
    private void initComponents(){
        initMenu();
        initTittleLabel();
        initPlayButton();
        initControlsButton();
        initQuitButton();
    }

    private void initMenu(){
        this.setBackground(Color.blue);
        this.setPreferredSize(new Dimension(GAME_WIDTH,GAME_HEIGHT));
        this.setLayout(null);
    }

    private void initTittleLabel(){
        this.tittleLabel.setForeground(Color.red);
        this.tittleLabel.setBorder(createEtchedBorder(0, new Color(0, 128, 105), new Color(0, 128, 105)));
        this.tittleLabel.setFont(new Font("Arial",1,34));
        this.tittleLabel.setText("Calvinx Parabox");
        this.tittleLabel.setBounds((int)(GAME_WIDTH*0.2),(int)(GAME_HEIGHT*0.1) ,(int)(GAME_WIDTH*0.6),(int)(GAME_HEIGHT*0.3));
        this.tittleLabel. setVisible(true);
        this.add(this.tittleLabel);
    }

    private void initPlayButton(){
        this.playButton.setForeground(Color.red);
        this.playButton.setBorder(createEtchedBorder(0, new Color(0, 128, 105), new Color(0, 128, 105)));
        this.playButton.setFont(new Font("Arial",1,15));
        this.playButton.setText("Play");
        this.playButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.playButton.setBounds((int)(GAME_WIDTH*0.45),(int)(GAME_HEIGHT*0.6) ,(int)(GAME_WIDTH*0.1),(int)(GAME_HEIGHT*0.1));
        this.playButton. setVisible(true);
        this.add(this.playButton);
    }
    private void initControlsButton(){
        this.controlsButton.setForeground(Color.red);
        this.controlsButton.setBorder(createEtchedBorder(0, new Color(0, 128, 105), new Color(0, 128, 105)));
        this.controlsButton.setFont(new Font("TimesNewRoman",1,15));
        this.controlsButton.setText("Controls");
        this.controlsButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.controlsButton.setBounds((int)(GAME_WIDTH*0.45),(int)(GAME_HEIGHT*0.7) ,(int)(GAME_WIDTH*0.1),(int)(GAME_HEIGHT*0.1));
        this.add(this.controlsButton);
    }
    private void initQuitButton(){
        this.quitButton.setForeground(Color.red);
        this.quitButton.setBorder(createEtchedBorder(0, new Color(0, 128, 105), new Color(0, 128, 105)));
        this.quitButton.setFont(new Font("TimesNewRoman",1,15));
        this.quitButton.setText("Quit");
        this.quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.quitButton.setBounds((int)(GAME_WIDTH*0.45),(int)(GAME_HEIGHT*0.8) ,(int)(GAME_WIDTH*0.1),(int)(GAME_HEIGHT*0.1));
        this.add(this.quitButton);
    }

    public JLabel getTittleLabel() {
        return tittleLabel;
    }

    public void setTittleLabel(JLabel tittleLabel) {
        this.tittleLabel = tittleLabel;
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
