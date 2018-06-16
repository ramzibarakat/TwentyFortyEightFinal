package twentyfortyeight;

/**
 * The point class is a convenient way of getting a box's position as a point
 * from a row and column
 *
 * @since 15/06/2018
 * @author r.barakat
 */
public class Point {

    // the row and column of the box
    public int row, column;

    /**
     * This method returns the row and column of the box as a point (row,
     * column)
     *
     * @param row the row
     * @param column the column
     */
    public Point(int row, int column) {
        this.row = row;
        this.column = column;
    }

}
