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
    public static boolean KP_R = false;

    public KeyHandler(){

    }

    @Override
    public void keyTyped(KeyEvent e) {
        KT_W = e.getKeyChar() == 'w';
        KT_A = e.getKeyChar() == 'a';
        KT_S = e.getKeyChar() == 's';
        KT_D = e.getKeyChar() == 'd';
    }

    @Override
    public void keyPressed(KeyEvent e) {
        KP_W = e.getKeyChar() == 'w';
        KP_A = e.getKeyChar() == 'a';
        KP_S = e.getKeyChar() == 's';
        KP_D = e.getKeyChar() == 'd';
        KP_R = e.getKeyChar() == 'r';
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
