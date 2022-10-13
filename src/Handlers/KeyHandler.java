package Handlers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Tomás y Carlos
 */
public class KeyHandler implements KeyListener{
    public static boolean KP_W = false;
    public static boolean KP_A = false;
    public static boolean KP_S = false;
    public static boolean KP_D = false;

    public static boolean KT_W = false;
    public static boolean KT_A = false;
    public static boolean KT_S = false;
    public static boolean KT_D = false;

    public KeyHandler(){

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        KT_W = e.getKeyChar() == 'w' && !KP_W;
        KT_A = e.getKeyChar() == 'a' && !KP_A;
        KT_S = e.getKeyChar() == 's' && !KP_S;
        KT_D = e.getKeyChar() == 'd' && !KP_D;

        try {
            Thread.sleep(1000/60);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }

        KT_W = false;
        KT_A = false;
        KT_S = false;
        KT_D = false;

        KP_W = e.getKeyChar() == 'w';
        KP_A = e.getKeyChar() == 'a';
        KP_S = e.getKeyChar() == 's';
        KP_D = e.getKeyChar() == 'd';

    }

    @Override
    public void keyReleased(KeyEvent e) {
        KP_W = false;
        KP_A = false;
        KP_S = false;
        KP_D = false;
    }
}
