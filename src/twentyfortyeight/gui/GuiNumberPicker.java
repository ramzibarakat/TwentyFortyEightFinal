package twentyfortyeight.gui;

// required imports
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import twentyfortyeight.DrawUtils;
import twentyfortyeight.gui.screens.GuiState;

/**
 * A number picker that allow users to pick numbers within range. It is
 * recommended to use this picker for small range of numbers since the user has
 * to use button to change the number by 1. The number picker is a GUI component
 *
 * @since 15/06/2018
 * @author r.barakat
 */
public class GuiNumberPicker extends GuiComponent {

    // the value 
    private int value;

    // the minimum and maximum values
    private int minValue, maxValue;

    // rectangle variable and button variables
    private Rectangle numberBox;
    private GuiButton upBtn, downBtn;

    // constructor passes all parameters for dimensions, position, and values
    public GuiNumberPicker(int x, int y, int width, int height,
            int value, int minValue, int maxValue) {

        super(x, y, width, height);

        // cannot be below the minimum value
        if (value < minValue) {
            throw new IllegalArgumentException("Entered vlaue (" + value
                    + ") is smaller than minimum value (" + minValue + ")");
        }

        // cannot be higher than the maximum value
        if (value > maxValue) {
            throw new IllegalArgumentException("Entered vlaue (" + value
                    + ") is larger than maximum value (" + minValue + ")");
        }

        // sets value and its min and max
        this.value = value;
        this.minValue = minValue;
        this.maxValue = maxValue;

        // new GuiButton object for the up button
        this.upBtn = new GuiButton(x, y, width, height / 4);

        // new Rectangle object for the number box
        this.numberBox = new Rectangle(x, y + height / 4, width, height / 2);

        // new GuiButton object for the down button
        this.downBtn = new GuiButton(x, y + height * 3 / 4, width, height / 4);

        // do not round corners
        this.upBtn.setIsRoundCorner(false);
        this.downBtn.setIsRoundCorner(false);

        // the up button displays "+1" and the down button displays "-1"
        this.upBtn.setText("+1");
        this.downBtn.setText("-1");

        /**
         * When the up button is pressed, the value of the dimension of the grid
         * increases by 1
         */
        this.upBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (getValue() < getMaxValue()) {
                    oneUp();
                }
            }
        });

        /**
         * When the down button is pressed, the value of the dimension of the
         * grid decreases by 1
         */
        this.downBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (getValue() > getMinValue()) {
                    oneDown();
                }
            }
        });

    }

    // passes the parameters
    public GuiNumberPicker(int x, int y, int width, int height, int value) {

        this(x, y, width, height, value, Integer.MIN_VALUE, Integer.MAX_VALUE);

    }

    // Overridden methods
    // do nothing here
    @Override
    public void update() {
    }

    /**
     * Render the graphics for the number picker panel
     *
     * @param g default graphics object
     */
    @Override
    public void render(Graphics2D g) {

        upBtn.render(g);
        downBtn.render(g);

        // number box
        g.setColor(GuiState.RELEASED.getColor());
        g.fillRect(numberBox.x, numberBox.y, numberBox.width, numberBox.height);

        // number box stroke
        g.setColor(GuiState.PRESSED.getColor());
        g.drawRect(numberBox.x, numberBox.y,
                numberBox.width - 1, numberBox.height - 1);

        // up box stroke
        Rectangle upBounds = upBtn.getBounds();
        g.drawRect(upBounds.x, upBounds.y,
                upBounds.width - 1, upBounds.height - 1);

        // down box stroke
        Rectangle downBounds = downBtn.getBounds();
        g.drawRect(downBounds.x, downBounds.y,
                downBounds.width - 1, downBounds.height - 1);

        // number inside the box
        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString(Integer.toString(value), numberBox.x + numberBox.width / 2
                - DrawUtils.getMessageWidth(text, font, g) / 2,
                numberBox.y + numberBox.height / 2
                + DrawUtils.getMessageHeight(text, font, g) / 2);

    }

    // Overridden methods
    /**
     * update when the mouse is pressed
     *
     * @param e default MouseEvent object
     */
    @Override
    public void mousePressed(MouseEvent e) {
        upBtn.mousePressed(e);
        downBtn.mousePressed(e);
    }

    /**
     * update when the mouse is released
     *
     * @param e default MouseEvent object
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        upBtn.mouseReleased(e);
        downBtn.mouseReleased(e);
    }

    /**
     * update when the mouse is dragged
     *
     * @param e default MouseEvent object
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        upBtn.mouseDragged(e);
        downBtn.mouseDragged(e);
    }

    /**
     * update when the mouse is moved
     *
     * @param e default MouseEvent object
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        upBtn.mouseMoved(e);
        downBtn.mouseMoved(e);
    }

    /**
     * Add 1 to value
     */
    public void oneUp() {
        value++;
    }

    /**
     * Subtract 1 from value
     */
    public void oneDown() {
        value--;
    }

    /**
     * @return the minValue
     */
    public int getMinValue() {
        return minValue;
    }

    /**
     * @return the maxValue
     */
    public int getMaxValue() {
        return maxValue;
    }

    /**
     * @return the value
     */
    public int getValue() {
        return value;
    }

}
