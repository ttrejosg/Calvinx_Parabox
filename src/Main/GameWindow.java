package Main;

import Handlers.KeyHandler;
import javax.swing.JFrame;

/**
 *
 * @author Tomás
 */
public class GameWindow extends JFrame{

    public GameWindow(GamePanel gPanel) {
        this.getContentPane().add(gPanel);
        initComponents();
    }
    
    /**
     * Método que inicializa todos los componentes
     */
    public void initComponents(){
        this.setTitle("Calvinx Parabox");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
        this.addKeyListener(new KeyHandler());
        this.setFocusable(true);
        
    }
}
