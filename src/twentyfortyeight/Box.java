package twentyfortyeight;

// required imports
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * The box class creates a box (tile) and keeps track of all its properties
 *
 * @since 15/06/2018
 * @author r.barakat
 */
public class Box {

    // final values
    public static final int WIDTH = 80;
    public static final int HEIGHT = 80;
    public static final int SLIDE_SPEED = 20;
    public static final int ARC_WIDTH = 15;
    public static final int ARC_HEIGHT = 15;

    // private variables
    private int value;
    private BufferedImage tileImage;
    private Color background;
    private Color text;
    private static Font font;
    private int x, y;
    private boolean added = false;

    /**
     * Box constructor
     *
     * @param value
     * @param x pixel coordinate
     * @param y pixel coordinate
     */
    public Box(int value, int x, int y) {

        if (font == null) {
            font = Game.FONT; // medium font
        }

        // sets value and position of box, then draws the box
        this.value = value;
        this.x = x;
        this.y = y;
        tileImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        drawImage();
    }

    /**
     * Background colours and text colours
     */
    private void drawImage() {
        Graphics2D g = (Graphics2D) tileImage.getGraphics();
        switch (getValue()) {
            case 2:
                background = new Color(0xe9e9e9); // light grey background
                text = new Color(0x000000); // black text
                break;
            case 4: // value of 4
                background = new Color(0xe6daab); // beige background
                text = new Color(0x000000); // black text
                break;
            case 8: // value of 8
                background = Color.RED; // red background
                text = new Color(0xffffff); // white text
                break;
            case 16: // value of 16
                background = Color.GREEN; // green background
                text = new Color(0xffffff); // white text
                break;
            case 32: // value of 32
                background = Color.CYAN; // cyan background
                text = new Color(0xffffff); // black text
                break;
            case 64: // value of 64
                background = Color.MAGENTA; // magenta background
                text = new Color(0xffffff); // black text
                break;
            case 128: // value of 128
                background = Color.ORANGE; // orange background
                text = new Color(0xffffff); // black text
                break;
            case 256: // value of 256
                background = Color.PINK; // pink background
                text = new Color(0xffffff); // black text
                break;
            case 512: // value of 512
                background = new Color(0x6600ff); // purple background
                text = new Color(0xffffff); // black text
                break;
            case 1024: // value of 1024
                background = new Color(0x0000cc); // blue background
                text = new Color(0xffffff); // black text
                break;
            case 2048: // value of 2048
                background = new Color(0xffd700); // gold background
                text = new Color(0xffffff); // black text
                break;
            default: // all other values
                background = new Color(0x33cccc); // light blue background
                text = new Color(0xffffff); // black text
                break;
        }

        // draws a box
        g.setColor(new Color(0, 0, 0, 0));
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setColor(background);
        g.fillRoundRect(0, 0, WIDTH, HEIGHT, ARC_WIDTH, ARC_HEIGHT);
        g.setColor(text);
        // font sizes to accomodate the space of the number (2 digits, 3 digits etc..)
        if (getValue() <= 64) {
            font = font.deriveFont(Game.FONT_SIZE_BIG);
        } else if (getValue() <= 65536) {
            font = font.deriveFont(Game.FONT_SIZE_MEDIUM);
        } else {
            font = font.deriveFont(Game.FONT_SIZE_SMALL);
        }
        g.setFont(font);

        /**
         * (WIDTH/2) goes to the center of the frame then moves to the left half
         * of the width of the box
         */
        int drawX = WIDTH / 2 - DrawUtils.getMessageWidth("" + getValue(), font, g) / 2;
        /**
         * when drawing in java, the starting point is at the bottom left so we
         * must go to half of the height of the frame (HEIGHT/2) then add half
         * of the height of the box
         */
        int drawY = HEIGHT / 2 + DrawUtils.getMessageHeight("" + getValue(), font, g) / 2;
        g.drawString("" + getValue(), drawX, drawY);
        g.dispose();

    }

    /**
     * renders tile
     *
     * @param g default graphics object
     */
    public void render(Graphics2D g) {
        g.drawImage(tileImage, getX(), getY(), null);
    }

    /**
     * @return the added
     */
    public boolean isAdded() {
        return added;
    }

    /**
     * @param added the added set
     */
    public void setAdded(boolean added) {
        this.added = added;
    }

    /**
     * @return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(int value) {
        this.value = value;
        drawImage(); // update value
    }

    /**
     * @return the x coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x coordinate to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y coordinate to set
     */
    public void setY(int y) {
        this.y = y;
    }

}
