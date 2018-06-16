package twentyfortyeight;

import java.awt.event.KeyEvent;

/**
 * This class creates all the functionalities with the keyboard
 *
 * @since 15/06/2018
 * @author r.barakat
 */
public class Keyboard {

    // list of all keys
    public static boolean[] pressed = new boolean[256];
    public static boolean[] prev = new boolean[256];

    /**
     * Apply the updated key to set each key with its corresponding value
     */
    public static void update() {

        // we only care about 4 keys
        for (int i = 0; i < 4; i++) {
            // way to reduce redundant else ifs
            switch (i) {
                case 0:
                    prev[KeyEvent.VK_LEFT] = pressed[KeyEvent.VK_LEFT];
                case 1:
                    prev[KeyEvent.VK_RIGHT] = pressed[KeyEvent.VK_RIGHT];
                case 2:
                    prev[KeyEvent.VK_UP] = pressed[KeyEvent.VK_UP];
                case 3:
                    prev[KeyEvent.VK_DOWN] = pressed[KeyEvent.VK_DOWN];
            }
        }
    }

    /**
     * Set the key state to pressed.
     *
     * @param e the KeyEvent object
     */
    public static void keyPressed(KeyEvent e) {
        pressed[e.getKeyCode()] = true;
    }

    /**
     * Set the key state to released.
     *
     * @param e the KeyEvent object
     */
    public static void keyRelesaed(KeyEvent e) {
        pressed[e.getKeyCode()] = false;
    }

    /**
     * Check if the key is typed (not hold).
     *
     * @param key KeyCode
     * @return whether or not the key is pressed as a true or false statement.
     * True if pressed and false if not
     */
    public static boolean typed(int key) {

        // check if pressed is not equal to previous action
        // to only check the typed action, instead of holding key
        return !pressed[key] && prev[key];
    }

}
