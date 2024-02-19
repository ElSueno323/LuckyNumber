package g54720.luckynumbers.model;

/**
 * Create a Position for the Board.
 *
 * @author Gabriel.espinosa g54720
 */
public class Position {

    private final int row;
    private final int column;

    /**
     * Constructor to create a position with a row and a column.
     *
     * @param row a row of the Position,is a integer.
     * @param column a column of the Position, is a integer.
     */
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Get the row of position
     *
     * @return a row of the Position, is a integer.
     */
    public int getRow() {
        return this.row;
    }

    /**
     * Get the column of position
     *
     * @return a column of the Position, is a integer.
     */
    public int getColumn() {
        return this.column;
    }

}

