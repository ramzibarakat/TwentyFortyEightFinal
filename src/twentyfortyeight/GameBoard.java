package twentyfortyeight;

// required imports
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

/**
 * The GameBoard class includes all of the functionalities and the core logic
 * for the program
 *
 * @since 15/06/2018
 * @author r.barakat
 */
public class GameBoard {

    // default size of one side of the gameboard
    public static final int DEFAULT_SIZE = 4;

    // spacing between tiles
    public final static int SPACING = 10;

    // the random numbers that can spawn are 2 and 4
    private static final int TWO = 2;
    private static final int FOUR = 4;

    // an empty box will be given a value of 0 so it is easier to work with
    private static final int EMPTY = 0;

    // creates a new random object
    public static Random random = new Random();

    // the number of tiles that you start with
    private final int STARTING_TILES = 2;

    // creates a 2D matrix of boxes
    private Box[][] board;

    // boolean variable for checking if the game has started
    private boolean hasStarted;
    public static boolean isGameOver;

    // the board
    private BufferedImage gameBoard;

    // the final image to display
    private BufferedImage finalBoard;

    // coordinates for the rows and columns 
    private int x, y;
    private int theRow, theColumn;

    private int boardWidth, boardHeight;

    // medium font
    private Font font = Game.FONT.deriveFont(Font.BOLD, Game.FONT_SIZE_MEDIUM);

    // the score
    private int score = 0;
    private String scr = "" + score;

    /**
     * Game Board. The Coordinate are automatically calculated from the size of
     * the board
     *
     * @param size the size of one side of the board
     */
    public GameBoard(int size) {

        // the rows and columns will have the same size of the side of the board
        this.theRow = size;
        this.theColumn = size;

        // the spacing between boxes
        this.boardWidth = (theColumn + 1) * SPACING + theColumn * Box.WIDTH;
        this.boardHeight = boardWidth;

        // x and y of the game board
        this.x = Game.SCREEN_WIDTH / 2 - boardWidth / 2;
        // 40 from bottom of screen
        this.y = Game.SCREEN_HEIGHT - boardHeight - 40;

        // creates a new matrix board based on the size of the board
        board = new Box[theRow][theColumn];

        // creates a new board image
        gameBoard = new BufferedImage(boardWidth, boardHeight,
                BufferedImage.TYPE_INT_ARGB);
        finalBoard = new BufferedImage(boardWidth, boardHeight,
                BufferedImage.TYPE_INT_ARGB);

        
        
        /**
         * this method draws on the board image which gives it its graphical
         * properties
         */
        createBoardImage();

        // starts the game by adding a few random boxes

        start();

    }

    /**
     * Draw on the game board
     */
    private void createBoardImage() {

        // casts the gameboard in Graphics2D so desired properties can be used
        Graphics2D g = (Graphics2D) gameBoard.getGraphics();

        // draw the board background with curved corners
        g.setColor(new Color(187, 173, 160)); // dark brown
        g.fillRoundRect(0, 0, boardWidth, boardHeight, 10, 10);

        // draw the fixed tiles on the gameboard image
        g.setColor(new Color(204, 192, 179)); // light brown
        for (int row = 0; row < theRow; row++) {
            for (int column = 0; column < theColumn; column++) {

                // sets coordinates of the boxes
                int theX = SPACING + SPACING * column + Box.WIDTH * column;
                int theY = SPACING + SPACING * row + Box.HEIGHT * row;

                // creates the boxes with round corners
                g.fillRoundRect(theX, theY, Box.WIDTH, Box.HEIGHT,
                        Box.ARC_WIDTH, Box.ARC_HEIGHT);

            }
        }
    }

    public void resetGame(){
        
        isGameOver = false;
        score = 0;
        updateScore(score);
        // creates a new matrix board based on the size of the board
        board = new Box[theRow][theColumn];
        createBoardImage();
        
    }
    
    /**
     * Render the game board
     *
     * @param g default graphics parameter
     */
    public void render(Graphics2D g) {

        // draw the game board on the final image
        Graphics2D g2d = (Graphics2D) finalBoard.getGraphics();
        g2d.drawImage(gameBoard, 0, 0, null);

        // render text
        g.setFont(font);
        g.setColor(Color.gray);
        g.drawString(scr,
                Game.SCREEN_WIDTH / 2 - DrawUtils.getMessageWidth(scr, font, g) / 2, 50);

        // renders all the boxes
        for (int row = 0; row < theRow; row++) {
            for (int column = 0; column < theColumn; column++) {
                Box box = getBox(row, column);
                if (box == null) {
                    continue;
                }
                box.render(g2d);
            }
        }

        // now draw the board
        g.drawImage(finalBoard, x, y, null);
        
        if (isGameOver) {
            renderGameOverScreen(g);
        }
        
        g2d.dispose();

    }

    /**
     * Updates the positions of the boxes and checks if new actions are passed
     */
    public void update() {

        // checks if a new key is pressed
        checkKeys();

        // updates the position of all the boxes
        for (int row = 0; row < theRow; row++) {
            for (int column = 0; column < theColumn; column++) {
                Box box = getBox(row, column);
                if (box == null) {
                    continue;
                }
                updatePosition(box, row, column);
            }
        }
    }

    /**
     * This method takes a box and updates its position based on the previous
     * move
     *
     * @param box the box
     * @param row the row of the box
     * @param column the column of the box
     */
    private void updatePosition(Box box, int row, int column) {

        // nothing to move 
        if (!isOccupied(row, column)) {
            return;
        }

        // target XY tile
        int xx = getTileX(column);
        int yy = getTileY(row);

        // direction and distance to move
        // poitive means towards left or up
        int distX = box.getX() - xx;
        int distY = box.getY() - yy;

        // if there are smaller pixels to move than speed makes smooth slide
        if (Math.abs(distX) < Box.SLIDE_SPEED) {
            box.setX(box.getX() - distX);
        }
        if (Math.abs(distY) < Box.SLIDE_SPEED) {
            box.setY(box.getY() - distY);
        }

        // checks boundries
        if (distX < 0) {
            box.setX(box.getX() + Box.SLIDE_SPEED);
        } else if (distX > 0) {
            box.setX(box.getX() - Box.SLIDE_SPEED);
        } else if (distY < 0) {
            box.setY(box.getY() + Box.SLIDE_SPEED);
        } else if (distY > 0) {
            box.setY(box.getY() - Box.SLIDE_SPEED);
        }

    }

    /**
     * adds random boxes for the amount of starting tiles
     */
    private void start() {

        // add a few random boxes to start with
        for (int i = 0; i < STARTING_TILES; i++) {
            addRandomBox();
        }

    }

    /**
     * Checks if a key is pressed performs the action for each corresponding key
     */
    private void checkKeys() {

        // move left
        if (Keyboard.typed(KeyEvent.VK_LEFT)) {

            addLeftBoxes();
            updateMove();

            // move right
        } else if (Keyboard.typed(KeyEvent.VK_RIGHT)) {

            addRightBoxes();
            updateMove();

            // move up
        } else if (Keyboard.typed(KeyEvent.VK_UP)) {

            addUpBoxes();
            updateMove();

            // move down
        } else if (Keyboard.typed(KeyEvent.VK_DOWN)) {

            addDownBoxes();
            updateMove();
        }

    }

    /**
     * starts the game if not already started updates the next move by reseting
     * the added counter from the previous move and adding a new random box
     */
    private void updateMove() {

        resetIsAdded();
        addRandomBox();
        isGameOver = isGameOver();

        if (!hasStarted) {
            hasStarted = true;
        }
    }

    private void renderGameOverScreen(Graphics2D g){
        String gameOver = "GAME OVER!";
        g.setColor(new Color(0,0,0,127));
        g.fillRect(0, 0, Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT);
        // render text
        g.setFont(font);
        g.setColor(Color.white); // gray color
        g.drawString(gameOver,
                Game.SCREEN_WIDTH / 2 - DrawUtils.getMessageWidth(gameOver, font, g) / 2,
                DrawUtils.getMessageHeight(gameOver, font, g) / 2 + 50);
    }
    
    /**
     * Recursive function to push boxes to the right and add them if possible
     *
     * @param row the row of the box to be moved
     * @param column the column of the box to be moved
     */
    private void pushRight(int row, int column) {

        // adding the first right box is impossible 
        if (column == theColumn - 1) {
            return;
        }

        // if a box is empty, there is no point of moving it
        if (!isOccupied(row, column)) {
            return;
        }

        // one in right is added, then return
        if (isOccupied(row, column + 1) && getBox(row, column + 1).isAdded()) {
            return;
        }

        // checks if the next box to the right is occupied
        boolean isOccupied = isOccupied(row, column + 1);

        // if it is not occupied then move the box to the right 
        if (!isOccupied) {
            moveBox(row, column, row, column + 1);
            pushRight(row, column + 1);

            // if both boxes are the same then add them by multiplying one of the 
            // values by two, then move the box to the right
        } else if (!getBox(row, column).isAdded()
                && addable(row, column, row, column + 1)) {

            // update the added value
            setValue(row, column, getValue(row, column) * 2);
            // one in front is added, so set true
            getBox(row, column).setAdded(true);
            score += getValue(row, column);
            updateScore(score);
            moveBox(row, column, row, column + 1);
            pushRight(row, column + 1);

        }

    }

    /**
     * Recursive function to push boxes left and add them if possible
     *
     * @param row the row of the box to be moved
     * @param column the column of the box to be moved
     */
    private void pushLeft(int row, int column) {

        // adding first left box is impossible
        if (column == 0) {
            return;
        }

        // if it is empty, there is no point of moving it
        if (!isOccupied(row, column)) {
            return;
        }

        // one in left is added, then return
        if (isOccupied(row, column - 1) && getBox(row, column - 1).isAdded()) {
            return;
        }

        // checks if the next box to the left is occupied
        boolean isOccupied = isOccupied(row, column - 1);

        // if it is not occupied then move the boxes to the left
        if (!isOccupied) {
            moveBox(row, column, row, column - 1);
            pushLeft(row, column - 1);

            // if it is occupied by the same value then add them together by 
            // multiplying the first value by two, then moving the box to the left
        } else if (!getBox(row, column).isAdded()
                && addable(row, column, row, column - 1)) {

            // update the added value
            setValue(row, column, getValue(row, column) * 2);
            // one in front is added, so set true
            getBox(row, column).setAdded(true);
            score += getValue(row, column);
            updateScore(score);
            moveBox(row, column, row, column - 1);
            pushLeft(row, column - 1);

        }
    }

    /**
     * Recursive function to push boxes up and add them if possible
     *
     * @param row the row of the box to be moved
     * @param column the column of the box to be moved
     */
    private void pushUp(int row, int column) {

        // adding top box is impossible
        if (row == 0) {
            return;
        }

        // if it is empty, there is no point of moving it
        if (!isOccupied(row, column)) {
            return;
        }

        // one in top is added, then return
        if (isOccupied(row - 1, column) && getBox(row - 1, column).isAdded()) {
            return;
        }

        // checks if the box above it is empty
        boolean isOccupied = isOccupied(row - 1, column);

        // if it is empty then move the box up
        if (!isOccupied) {
            moveBox(row, column, row - 1, column);
            pushUp(row - 1, column);

            // if it is occupied by the same value then add them together by 
            // multiplying the first value by two, then moving the box up
        } else if (!getBox(row, column).isAdded()
                && addable(row, column, row - 1, column)) {

            // update the added value
            setValue(row, column, getValue(row, column) * 2);
            // one in front is added so set true
            getBox(row, column).setAdded(true);
            score += getValue(row, column);
            updateScore(score);
            moveBox(row, column, row - 1, column);
            pushUp(row - 1, column);
        }
    }

    /**
     * Recursive function to push boxes down and add them if possible
     *
     * @param row the row of the box to be moved
     * @param column the column of the box to be moved
     */
    private void pushDown(int row, int column) {

        // adding bottom box is impossible
        if (row == theRow - 1) {
            return;
        }

        // if it is empty, there is no point of moving it
        if (!isOccupied(row, column)) {
            return;
        }

        // one in bottom is added, then return
        if (isOccupied(row + 1, column) && getBox(row + 1, column).isAdded()) {
            return;
        }

        // chcecks if the box below it is empty
        boolean isOccupied = isOccupied(row + 1, column);

        // if it is empty then move the box down
        if (!isOccupied) {
            moveBox(row, column, row + 1, column);
            pushDown(row + 1, column);

            // if the box below is the same value then add the boxes together by 
            // multiplying the first box's value by two then move the box down
        } else if (!getBox(row, column).isAdded()
                && addable(row, column, row + 1, column)) {

            // update the added value
            setValue(row, column, getValue(row, column) * 2);
            // one in front is added, so set true
            getBox(row, column).setAdded(true);
            score += getValue(row, column);
            updateScore(score);
            moveBox(row, column, row + 1, column);
            pushDown(row + 1, column);

        }
    }

    /**
     * Updates the score
     *
     * @param score the current score
     * @return the updated score
     */
    private int updateScore(int score) {

        this.score = score;
        scr = "" + score;
        return score;

    }

    /**
     * Add all the boxes towards right
     */
    private void addRightBoxes() {
        for (int row = theRow - 1; row >= 0; row--) {
            for (int column = theColumn - 1; column >= 0; column--) {
                pushRight(row, column);
            }
        }
    }

    /**
     * Add all the boxes towards left
     */
    private void addLeftBoxes() {
        for (int row = 0; row < theRow; row++) {
            for (int column = 0; column < theColumn; column++) {
                pushLeft(row, column);
            }
        }
    }

    /**
     * Add all the boxes upwards
     */
    private void addUpBoxes() {
        for (int column = 0; column < theColumn; column++) {
            for (int row = 0; row < theRow; row++) {
                pushUp(row, column);
            }
        }
    }

    /**
     * Add all the boxes downwards
     */
    private void addDownBoxes() {
        for (int column = 0; column < theColumn; column++) {
            for (int row = theRow - 1; row >= 0; row--) {
                pushDown(row, column);
            }
        }
    }

    /**
     * Randomly selects a box from the grid to put a value 2 or 4
     */
    private void addRandomBox() {

        // create a new list of all the empty boxes which will be used to check if a random box can be added
        ArrayList<Integer[]> emptyBoxes = new ArrayList<>();

        // get the list of available empty spots
        for (int row = 0; row < theRow; row++) {
            for (int column = 0; column < theColumn; column++) {
                if (!isOccupied(row, column)) {
                    emptyBoxes.add(new Integer[]{row, column});
                }
            }
        }

        // the size of the empty boxes
        int size = emptyBoxes.size();

        // if there are empty boxes then add a random box within the empty boxes
        if (size > 0) {
            int randomBox = random.nextInt(size);
            int row = emptyBoxes.get(randomBox)[0];
            int column = emptyBoxes.get(randomBox)[1];

            // the random value can only be two or four
            int value = twoOrFour();

            // creates the new box with the random value
            setBox(new Box(value, getTileX(column), getTileY(row)),
                    row, column);

        }

    }

    /**
     * Chooses either two or four giving the value two a 9:1 occurrence ratio
     *
     * @return two or four
     */
    private static int twoOrFour() {

        int answer = random.nextInt(10);

        // 90% of getting a 2
        if (answer > 0) {
            return TWO;
        } // 10% chance of getting a 4
        else {
            return FOUR;
        }

    }

    /**
     * Resets all the added state to false so the boxes can combine again in
     * next iteration
     */
    private void resetIsAdded() {

        for (int row = 0; row < theRow; row++) {
            for (int column = 0; column < theColumn; column++) {

                if (isOccupied(row, column)) {
                    getBox(row, column).setAdded(false);
                }
            }
        }

    }

    /**
     * Check if there is any more valid moves left
     *
     * @return true if the game is over. false if not
     */
    private boolean isGameOver() {

        boolean isOver = true;

        for (int row = 0; row < theRow; row++) {
            for (int column = 0; column < theColumn; column++) {
                if (!isOccupied(row, column)) {
                    isOver = false;
                    break;
                }

                // there is no need to check left and top since they will be 
                // already checked by the previous iteration
                if (addable(row, column, row + 1, column)
                        || addable(row, column, row, column + 1)) {
                    isOver = false;
                    break;
                }
            }

        }
        return isOver;
    }

    /**
     * Gets a box at the index
     *
     * @param row the row of the box
     * @param column the column of the box
     * @return the box or null if out of bound
     */
    private Box getBox(int row, int column) {

        // if out of bound, then return null
        if (row < 0 || row >= theRow || column < 0 || column >= theColumn) {
            return null;
        } else {
            return board[row][column];
        }

    }

    /**
     * Sets a box within the game board
     *
     * @param box the box to be set
     * @param row the row of the box
     * @param column the column of the box
     */
    private void setBox(Box box, int row, int column) {

        // if it is within the bounds then set the box
        if (row >= 0 && row < theRow && column >= 0 && column < theColumn) {
            board[row][column] = box;
        }
    }

    /**
     * Moves a box to a different index and sets the original index to null (it
     * will override the box in the destination)
     *
     * @param row1 the row for the box to be moved
     * @param column1 the column for the box to be moved
     * @param row2 the row for the destination
     * @param column2 the column for the destination
     */
    private void moveBox(int row1, int column1, int row2, int column2) {

        // sets the second box equal to the first then makes the first one null 
        board[row2][column2] = board[row1][column1];
        board[row1][column1] = null;

    }

    /**
     * TESTER method used to add a box of desired value in a random row and
     * column
     */
    private void addBox(int value) {

        ArrayList<Integer[]> emptyBoxes = new ArrayList<>();

        // get the list of available empty spots
        for (int row = 0; row < theRow; row++) {
            for (int column = 0; column < theColumn; column++) {
                if (!isOccupied(row, column)) {
                    emptyBoxes.add(new Integer[]{row, column});
                }
            }
        }

        int size = emptyBoxes.size();

        if (size > 0) {
            int randomBox = random.nextInt(size);
            int row = emptyBoxes.get(randomBox)[0];
            int column = emptyBoxes.get(randomBox)[1];

            setBox(new Box(value, getTileX(column), getTileY(row)),
                    row, column);

        }

    }

    /**
     * Gets a value for a select box
     *
     * @param row the row for the box
     * @param column the column for the box
     * @return the value for the box, if the box is null, return EMPTY
     */
    private int getValue(int row, int column) {

        Box box = getBox(row, column);

        if (box == null) {
            return EMPTY;
        }

        return getBox(row, column).getValue();
    }

    /**
     * Sets value of box
     *
     * @param row the row for the value
     * @param column the column for the value
     * @param value the value to be set
     */
    private void setValue(int row, int column, int value) {
        getBox(row, column).setValue(value);
    }

    /**
     * Checks if a tile in the grid is occupied with a Box
     *
     * @param row the row of the desired box to check
     * @param column the column of the desired box to check
     * @return true if occupied and false if not occupied
     */
    private boolean isOccupied(int row, int column) {
        return getBox(row, column) != null;
    }

    /**
     * Checks if two numbers are equal so they can be added
     *
     * @param row1 the row for the first value
     * @param column1 the column for the first value
     * @param row2 the row for the second value
     * @param column2 the column for the second value
     * @return true if the are equal false if they are not equal
     */
    private boolean addable(int row1, int column1, int row2, int column2) {
        return (getValue(row1, column1) == getValue(row2, column2));
    }

    /**
     * Get the X coordinate of tile at the column in pixel
     *
     * @param column the column of the tile
     * @return the x coordinate of the tile in pixels
     */
    private int getTileX(int column) {
        return SPACING + column * Box.WIDTH + column * SPACING;
    }

    /**
     * Get the Y coordinate of tile at the row in pixel
     *
     * @param row the row of the tile
     * @return the y coordinate of the tile in pixels
     */
    private int getTileY(int row) {
        return SPACING + row * Box.HEIGHT + row * SPACING;
    }
}
