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
    public RoomSelector(){
        initComponents();
    }

    /**
     * Inicializa los componentes de RoomSelector
     */
    private void initComponents(){
        initRoomSelector();
        initBackButton();
    }

    /**
     * Inicializa RoomSelector
     */
    private void initRoomSelector(){
        this.setPreferredSize(new Dimension(GAME_WIDTH,GAME_HEIGHT));
        this.setBackground(Color.blue);
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
        this.roomButtons = new JButton[rooms.length];
        int x = (int)(GAME_WIDTH*0.1);
        int y = (int)(GAME_WIDTH*0.05);
        for(int i=0;i<rooms.length;i++){
            JButton roomButton = createRoomButton(i+1);
            roomButton.setBounds(x,y,(int)(GAME_WIDTH*0.05),(int)(GAME_WIDTH*0.05));
            this.roomButtons[i] = roomButton;
            this.add(roomButton);
            if(x >= (int)(GAME_WIDTH*0.9)){
                x = (int)(GAME_WIDTH*0.1);
                y+= (int)(GAME_WIDTH*0.1);
            }
            x+= (int)(GAME_WIDTH*0.05);
        }
    }

    /**
     * Crea un boton para la sala con el id asignado
     * @param id
     * @return
     */
    private JButton createRoomButton(int id){
        JButton roomButton = new JButton();
        roomButton.setForeground(Color.red);
        roomButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        roomButton.setFont(new Font("Arial",1,13));
        roomButton.setText(Integer.toString(id));
        return roomButton;
    }

    /**
     * Inicializa el boton de volver al menu.
     */
    private void initBackButton() {
        this.backButton = new JButton();
        this.backButton.setForeground(Color.red);
        this.backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.backButton.setFont(new Font("Arial",1,13));
        this.backButton.setText("Volver");
        this.backButton.setBounds((int)(GAME_WIDTH*0.05),(int)(GAME_WIDTH*0.05),(int)(GAME_WIDTH*0.04),(int)(GAME_WIDTH*0.04));
        this.add(this.backButton);
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
