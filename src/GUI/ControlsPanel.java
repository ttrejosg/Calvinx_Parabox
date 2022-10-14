package GUI;

import Main.Game;

import javax.swing.*;
import java.awt.*;

import static Handlers.Constants.*;

public class ControlsPanel extends JPanel {

    private JLabel controlsInformation;
    private JButton backButton;
    public ControlsPanel(){
        initComponents();
    }

    private void initComponents(){
        initControlsPanel();
        initControlsInformation();
        initBackButton();
    }

    private void initControlsPanel(){
        this.setBackground(Color.blue);
        this.setPreferredSize(new Dimension(GAME_WIDTH,GAME_HEIGHT));
        this.setLayout(null);
    }

    private void initControlsInformation(){
        this.controlsInformation = new JLabel("Esto es una prueba");
        this.controlsInformation.setBounds((int)(GAME_WIDTH*0.1),(int)(GAME_HEIGHT*0.05),(int)(GAME_WIDTH*0.8),(int)(GAME_HEIGHT*0.9));
        this.add(this.controlsInformation);
    }

    private void initBackButton() {
        this.backButton = new JButton();
        this.backButton.setForeground(Color.red);
        this.backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.backButton.setFont(new Font("Arial",1,10));
        this.backButton.setText("Volver");
        this.backButton.setBounds((int)(GAME_WIDTH*0.05),(int)(GAME_WIDTH*0.05),(int)(GAME_WIDTH*0.04),(int)(GAME_WIDTH*0.04));
        this.add(this.backButton);
    }

    public JLabel getControlsInformation() {
        return controlsInformation;
    }

    public void setControlsInformation(JLabel controlsInformation) {
        this.controlsInformation = controlsInformation;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public void setBackButton(JButton backButton) {
        this.backButton = backButton;
    }
}
