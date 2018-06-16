package twentyfortyeight;

// required imports
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;

/**
 * The DrawUtils class is a convenient way of getting a message width and height
 *
 * @since 15/06/2018
 * @author r.barakat
 */
public class DrawUtils {

    /**
     * tells you how wide the pixels for the values are so it can be centered on
     * the box
     *
     * @param message the message of the box
     * @param font the font of the box
     * @param g graphics object
     * @return width of box
     */
    public static int getMessageWidth(String message, Font font, Graphics2D g) {
        g.setFont(font);
        Rectangle2D bounds = g.getFontMetrics().getStringBounds(message, g);

        /**
         * method getWidth() from Rectangle2D library finds the width of the
         * rectangle in the bounds*
         */
        return (int) bounds.getWidth();
    }

    /**
     * tells you how high the pixels for the values are so it can be centered on
     * the box
     *
     * @param message the message of the box
     * @param font the font of the box
     * @param g graphics object
     * @return height of box
     */
    public static int getMessageHeight(String message, Font font, Graphics2D g) {
        g.setFont(font);
        if (message.length() == 0) {
            return 0;
        }
        TextLayout textlayout = new TextLayout(message, font, g.getFontRenderContext());
        return (int) textlayout.getBounds().getHeight();
    }
}
