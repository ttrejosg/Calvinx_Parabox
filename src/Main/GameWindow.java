package Main;

import GUI.Menu;
import GUI.RoomSelector;
import Handlers.KeyHandler;
import Handlers.SoundHandler;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Tomás
 */
public class GameWindow extends JFrame{

    private Menu menu;
    private RoomSelector roomSelector;

    public GameWindow() {
        createComponents();
        initComponents();
    }

    /**
     * Método que crea todos los componentes de la clase
     */
    private void createComponents(){
        this.menu = new Menu();
        this.roomSelector = new RoomSelector();
    }
    
    /**
     * Inicia los componentes de la ventana.
     */
    public void initComponents() {
        initWindow();
        initMenu();
        initRoomSelector();
        initActionListeners();
    }

    /**
     * Inicia la ventana.
     */
    private void initWindow(){
        this.setTitle("Calvinx Parabox");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(new KeyHandler());
        this.setResizable(false);
        //this.setUndecorated(true);
    }

    /**
     * Inicia el menu dentro de la ventana
     */
    private void initMenu(){
        this.add(this.menu);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    /**
     * Inicia el RoomSelector dentro de la ventana.
     */
    private void initRoomSelector(){
        this.add(this.roomSelector);
        this.pack();
        this.roomSelector.setEnabled(false);
        this.roomSelector.setVisible(false);
    }

    /**
     * Método que inicializa los actionListener de los botones de los diferentes paneles.
     */
    private void initActionListeners(){
        initPlayActionListener();
        initQuitActionListener();
        initBackActionListener();
    }

    /**
     * Añade la funcionalidad del boton de play en el menu.
     */
    private void initPlayActionListener(){
        this.menu.getPlayButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Clip clip = SoundHandler.createClip(SoundHandler.get("Button.wav"));
                clip.start();
                menu.setEnabled(false);
                menu.setVisible(false);
                roomSelector.setEnabled(true);
                roomSelector.setVisible(true);
                roomSelector.getBackButton().setVisible(true);
            }
        });
    }
    /**
     * Añade la funcionalidad del boton de salir en el menu.
     */
    private void initQuitActionListener(){
        this.menu.getQuitButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Clip clip = SoundHandler.createClip(SoundHandler.get("Button.wav"));
                clip.start();
                System.exit(0);
            }
        });
    }

    /**
     * Añade la funcionalidad del boton de volver en el RoomSelector y en el ControlsPanel.
     */
    private void initBackActionListener(){
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                roomSelector.setEnabled(false);
                roomSelector.setVisible(false);
                menu.setEnabled(true);
                menu.setVisible(true);
                roomSelector.getBackButton().setVisible(false);

            }
        };
        this.roomSelector.getBackButton().addActionListener(actionListener);

    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public RoomSelector getRoomSelector() {
        return roomSelector;
    }

    public void setRoomSelector(RoomSelector roomSelector) {
        this.roomSelector = roomSelector;
    }


}
