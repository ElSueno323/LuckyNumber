package g54720.luckynumbers.model;

/**
 * The Board for the Game Model.
 *
 * @author Gabriel.espinosa g54720
 */
public class Board {

    private final Tile[][] tiles;

    /**
     * Constructor to create a Board with a array of Tile.
     */
    public Board() {
        this.tiles = new Tile[4][4];
    }

    /**
     * Give the size of column or row.
     *
     * @return the size , a Integer.
     */
    public int getSize() {
        return this.tiles.length;
    }

    /**
     * Getting the tile on the Position pos.
     *
     * @param pos a Position of a tile.
     * @return a Tile the value of the tile.
     */
    public Tile getTile(Position pos) {
        return this.tiles[pos.getRow()][pos.getColumn()];
    }

    /**
     * Check if the Position is in the board.
     *
     * @param pos a Position to check.
     * @return true if the position as in the board.
     */
    public boolean isInside(Position pos) {
        return pos.getColumn() <= this.getSize() - 1 && pos.getColumn() >= 0
                && pos.getRow() <= this.getSize() - 1 && pos.getRow() >= 0;
    }

    /**
     * Check if the value of the cases of around the position is appropriate.
     *
     * @param tile the tile to check.
     * @param pos the position to check.
     * @return true if the tile can put in the position.
     * @throws IllegalArgumentException if the position as not inside the board.
     */
    public boolean canBePut(Tile tile, Position pos) {
        boolean canPut = true;
        for (int countI = 0; countI < getSize(); countI++) {
            for (int countJ = countI + 1; countJ < getSize(); countJ++) {
                if (!isInside(pos)) {
                    throw new IllegalArgumentException("Outside position");
                }
                if (isInside(new Position(pos.getRow()
                        - countJ, pos.getColumn()))
                        && this.tiles[pos.getRow() - countJ][pos.getColumn()]
                        != null
                        && this.tiles[pos.getRow() - countJ][pos.getColumn()]
                                .getValue() >= tile.getValue()) {
                    canPut = false;
                }

                if (isInside(new Position(pos.getRow(),
                        pos.getColumn() - countJ))
                        && this.tiles[pos.getRow()][pos.getColumn() - countJ]
                        != null
                        && this.tiles[pos.getRow()][pos.getColumn()
                                - countJ].getValue() >= tile.getValue()) {
                    canPut = false;
                }
                if (isInside(new Position(pos.getRow(),
                        pos.getColumn() + countJ))
                        && this.tiles[pos.getRow()][pos.getColumn() + countJ]
                        != null
                        && this.tiles[pos.getRow()][pos.getColumn() + countJ]
                                .getValue() <= tile.getValue()) {
                    canPut = false;
                }
                if (isInside(new Position(pos.getRow()
                        + countJ, pos.getColumn()))
                        && this.tiles[pos.getRow() + countJ][pos.getColumn()]
                        != null && this.tiles[pos.getRow()
                                + countJ][pos.getColumn()].getValue()
                        <= tile.getValue()) {
                    canPut = false;
                }
            }
        }
        return canPut;
    }
    

    /**
     * Put a Tile in the given Position.
     *
     * @param tile the tile to check.
     * @param pos the position to check.
     */
    public void put(Tile tile, Position pos) {
        this.tiles[pos.getRow()][pos.getColumn()] = tile;
    }

    /**
     * Check if the array of Tiles as full.
     *
     * @return true if the board is full.
     */
    public boolean isFull() {
        for (Tile[] ArrayTiles : tiles) {
            for (Tile tile : ArrayTiles) {
                if (tile == null) {
                    return false;
                }
            }
        }
        return true;
    }
}




