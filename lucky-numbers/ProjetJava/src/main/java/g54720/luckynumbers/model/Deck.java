package g54720.luckynumbers.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Create a Deck with a list of Tile with face down and a list of Tile with face
 * up.
 *
 * @author Gabriel.espinosa g54720
 */
public final class Deck {

    private final List<Tile> faceUpTiles;
    private final List<Tile> faceDownTiles;

    /**
     * Contructor of Deck, who create a empty list of face up tile and a list
     * with tiles of values 1 to 20 face down
     *
     * @param playerCount the number of player.
     */
    public Deck(int playerCount) {
        this.faceUpTiles = new ArrayList<>();
        this.faceDownTiles = new ArrayList<>();
        for (int j = 0; j < playerCount; j++) {
            for (int i = 1; i < 21; i++) {
                faceDownTiles.add(((int) (Math.random() * faceDownCount())), new Tile(i));
            }

        }

    }

    /**
     * Give the number of face down tiles on the deck.
     *
     * @return the number of face-down tiles.
     */
    public int faceDownCount() {
        return this.faceDownTiles.size();
    }

    /**
     * Remove a tile on the list faceDownTiles and return it.
     *
     * @return the tile who is remove.
     */
    public Tile pickFaceDown() {
        Tile remove = this.faceDownTiles.get(faceDownCount() - 1);
        remove.flipFaceUp();
        this.faceDownTiles.remove(faceDownCount() - 1);
        return remove;
    }

    /**
     * Give the number of face up tiles on the deck.
     *
     * @return the number of face-up tiles.
     */
    public int faceUpCount() {
        return this.faceUpTiles.size();
    }

    /**
     * Give the list of face up tiles.
     *
     * @return the tiles on the list faceUpTiles.
     */
    public List<Tile> getAllFaceUp() {
        return this.faceUpTiles;
    }

    /**
     * Give the list of face down tiles.
     *
     * @return the tiles on the list faceDownTiles.
     */
    List<Tile> getAllFaceDown() {
        return this.faceDownTiles;
    }

    /**
     * Check if the chosen tile is on the list face-up tiles.
     *
     * @param tile the chosen tile to check.
     * @return true if the tile is on the chosen list.
     */
    public boolean hasFaceUp(Tile tile) {
        return this.faceUpTiles.contains(tile);
    }

    /**
     * Pick the tile to the list of face-up tiles.
     *
     * @param tile the chosen tile to remove
     */
    public void pickFaceUp(Tile tile) {
        this.faceUpTiles.remove(tile);
    }

    /**
     * Replace the chosen tile to the list of face-ip tiles.
     *
     * @param tile the chosen tile to replace.
     */
    public void putBack(Tile tile) {
        this.faceUpTiles.add(tile);
    }

}
















