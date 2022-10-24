package GUI;

import Components.Room;
import Main.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import static Handlers.Constants.*;

/**
 *
 * @author Tomás y Carlos
 */
public class RoomSelector extends JPanel {

    private JButton backButton;
    private JButton[] roomButtons;
    private JLabel backgroundLabel;
    public RoomSelector(){
        initComponents();
    }

    /**
     * Inicializa los componentes de RoomSelector
     */
    private void initComponents(){
        initRoomSelector();
        initBackButton();
        initBackgroundLabel();
    }

    /**
     * Inicializa RoomSelector
     */
    private void initRoomSelector(){
        this.setPreferredSize(new Dimension(GAME_WIDTH,GAME_HEIGHT));
        this.setLayout(null);
        File file = new File(ROOMS_PATH);
        File[] rooms = file.listFiles();
        initRoomButtons(rooms);
    }

    /**
     * Inicializa los botones para seleccionar sala.
     * @param rooms
     */
    private void initRoomButtons(File[] rooms){
        this.roomButtons = new JButton[9];
        int n = 1;
        for(int i = 123; i < 420; i += 145){
            for(int j = 456; j < 750; j += 145){
                JButton roomButton = createRoomButton(n);
                roomButton.setBounds(j, i, 91, 91);
                this.roomButtons[n - 1] = roomButton;
                this.add(roomButton);
                n ++;
            }
        }
    }

    /**
     * Método que inicializa el fondo.
     */
    private void initBackgroundLabel() {
        this.backgroundLabel = new JLabel();
        this.backgroundLabel.setBounds(0, 0,GAME_WIDTH,GAME_HEIGHT);
        this.backgroundLabel.setVisible(true);
        ImageIcon wallpaper = new ImageIcon("src/Resources/Images/SelectorRoom.png");
        Icon icon = new ImageIcon(wallpaper.getImage().getScaledInstance(backgroundLabel.getWidth(),
                backgroundLabel.getHeight(), Image.SCALE_DEFAULT));
        this.backgroundLabel.setIcon(icon);
        this.add(this.backgroundLabel);
    }

    /**
     * Crea un boton para la sala con el id asignado
     * @param id el nmúmero de la sala.
     * @return el boton creado.
     */
    private JButton createRoomButton(int id){
        JButton roomButton = new JButton();
        roomButton.setForeground(Color.red);
        roomButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        roomButton.setFont(new Font("Arial",1,13));
        roomButton.setText(Integer.toString(id));
        roomButton.setContentAreaFilled(false);
        roomButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return roomButton;
    }

    /**
     * Inicializa el boton de volver al menu.
     */
    private void initBackButton() {
        this.backButton = new JButton();
        this.backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.backButton.setBounds(574, 646, 143, 61);
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

    public JButton[] getRoomButtons() {
        return roomButtons;
    }

    public void setRoomButtons(JButton[] roomButtons) {
        this.roomButtons = roomButtons;
    }
}
