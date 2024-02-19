package g54720.luckynumbers.model;

/**
 * Create a Tile for the Board.
 *
 * @author Gabriel.espinosa g54720
 */
public class Tile {

    private final int value;
    private boolean faceUp;

    /**
     * Constructor to create a Tile with a value and a with a face down
     *
     * @param value a Integer
     */
    public Tile(int value) {
        this.value = value;
        this.faceUp = false;
    }

    /**
     * Get the value of the Tile
     *
     * @return a Integer
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Check if the face of the tile is up
     *
     * @return true if the face is up
     */
    public boolean isFaceUp() {
        return this.faceUp;
    }

    /**
     * Turn the faceUp of the tile on true
     */
    void flipFaceUp() {
        this.faceUp = true;
    }

    @Override
    public String toString() {
        if (this.value < 10) {
            return "0" + this.value;
        }
        return "" + this.value;
    }
}


