package twentyfortyeight.gui.screens;

// required imports
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import twentyfortyeight.DrawUtils;
import twentyfortyeight.Game;
import twentyfortyeight.GameBoard;
import twentyfortyeight.gui.GuiButton;
import twentyfortyeight.gui.GuiScreen;

/**
 * The Mode Select Panel is a GUI panel and it contains the two mode buttons
 * (Classic and Survival)
 *
 * @since 15/06/2018
 * @author r.barakat
 */
public class ModeSelectPanel extends GuiPanel {

    // medium font
    private Font font = Game.FONT.deriveFont(Font.BOLD, Game.FONT_SIZE_MEDIUM);

    // select mode title
    private String text = "Select the game mode";

    // button dimensions and spacing
    private static int buttonWidth = 220;
    private static int buttonHeight = 100;
    private static int verticalSpacing = 50;

    // default constructor which creates and adds the Classic and Survival buttons
    public ModeSelectPanel() {

        // the center x coordinate
        int centerX = Game.SCREEN_WIDTH / 2 - buttonWidth / 2;

        // the classic button
        GuiButton classic = new GuiButton(centerX,
                Game.SCREEN_HEIGHT / 2 - buttonHeight - verticalSpacing,
                buttonWidth, buttonHeight);

        // the survival button
        GuiButton survival = new GuiButton(centerX,
                classic.getY() + buttonHeight + verticalSpacing,
                buttonWidth, buttonHeight);

        // text for each button
        classic.setText("Classic");
        survival.setText("Survival");

        // Overridden methods
        // goes to game screen
        classic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GamePanel.setGameBoardSize(GameBoard.DEFAULT_SIZE);
                GuiScreen.getInstance().setCurrentPanel(GuiScreen.GAME_SCREEN);
            }
        });

        // goes to survival setting screen
        survival.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GuiScreen.getInstance().setCurrentPanel(GuiScreen.SURVIVAL_SETTING_SCREEN);
            }
        });

        // adds the buttons in the mode select panel
        add(classic);
        add(survival);

    }

    /**
     * Renders the mode select panel graphics
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
        g.setColor(Color.gray); // gray color
        g.drawString(text,
                Game.SCREEN_WIDTH / 2 - DrawUtils.getMessageWidth(text, font, g) / 2,
                DrawUtils.getMessageHeight(text, font, g) / 2 + 50);

    }

}
