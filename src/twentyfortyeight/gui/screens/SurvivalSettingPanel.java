package twentyfortyeight.gui.screens;

// required imports
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import twentyfortyeight.DrawUtils;
import twentyfortyeight.Game;
import twentyfortyeight.gui.GuiButton;
import twentyfortyeight.gui.GuiNumberPicker;
import twentyfortyeight.gui.GuiScreen;

/**
 * The Survival Setting Panel is a GUI panel and it contains the survival
 * settings screen which can manipulate the grid size
 *
 * @since 15/06/2018
 * @author r.barakat
 */
public class SurvivalSettingPanel extends GuiPanel {

    // minimum grid size
    private static final int MIN_GRID = 4;

    // maximum grid size
    private static final int MAX_GRID = 18;

    // medium font
    private Font font = Game.FONT.deriveFont(Font.BOLD, Game.FONT_SIZE_MEDIUM);

    // title text
    private String text = "Select the size of grid";

    // dimensions for picker and buttons
    private static int pickerWidth = 200;
    private static int pickerHeight = 100;
    private static int buttonWidth = 200;
    private static int buttonHeight = 100;

    // default constructor which creates and adds the number picker and start bubttons
    public SurvivalSettingPanel() {

        // the center x coordinate
        int centerX = Game.SCREEN_WIDTH / 2 - pickerWidth / 2;

        // creates number picker
        GuiNumberPicker numberPicker = new GuiNumberPicker(centerX,
                Game.SCREEN_HEIGHT / 2 - pickerHeight,
                pickerWidth, pickerHeight, MIN_GRID, MIN_GRID, MAX_GRID);

        // creates start button
        GuiButton start = new GuiButton(centerX,
                Game.SCREEN_HEIGHT - buttonHeight - 100,
                buttonWidth, buttonHeight);

        // start button text "Start"
        start.setText("Start");

        // Overridden methods
        // goes to game screen
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GamePanel.setGameBoardSize(numberPicker.getValue());
                GuiScreen.getInstance().setCurrentPanel(GuiScreen.GAME_SCREEN);
            }
        });

        // adds button and number picker to survival setting panel
        add(numberPicker);
        add(start);

    }

    /**
     * Renders graphics for survival setting panel
     *
     * @param g default graphics object
     */
    @Override
    public void render(Graphics2D g) {

        // backgorund render first
        g.setColor(new Color(230, 210, 200));
        g.fillRect(0, 0, Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT);

        // render buttons 
        super.render(g);

        // render text
        g.setFont(font);
        g.setColor(Color.gray);
        g.drawString(text,
                Game.SCREEN_WIDTH / 2 - DrawUtils.getMessageWidth(text, font, g) / 2,
                DrawUtils.getMessageHeight(text, font, g) / 2 + 50);

    }

}
