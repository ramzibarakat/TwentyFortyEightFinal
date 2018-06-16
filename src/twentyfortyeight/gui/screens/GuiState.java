package twentyfortyeight.gui.screens;

// required import
import java.awt.Color;

/**
 * This class keeps track of the state of the button (released, hover, pressed)
 *
 * @since 15/06/2018
 * @author r.barakat
 */
public enum GuiState {

    // the color when the button is released
    RELEASED(new Color(187, 173, 160)), // light gray color

    // the color when the button is being hovered
    HOVER(new Color(147, 157, 148)), // darker gray color

    // the color when the button is pressed
    PRESSED(new Color(127, 137, 128)); // even darker gray color

    // Color variable
    Color color;

    /**
     * Sets the color
     *
     * @param c the color to be set
     */
    GuiState(Color c) {
        color = c;
    }

    /**
     * Gets the color of current state
     *
     * @return the color
     */
    public Color getColor() {
        return color;
    }

}
