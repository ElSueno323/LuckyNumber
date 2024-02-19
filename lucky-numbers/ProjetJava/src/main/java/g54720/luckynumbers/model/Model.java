package g54720.luckynumbers.model;

import java.util.List;

/**
 * Interface for the Game model.
 *
 * @author MCD {@literal <mcodutti@he2b.be>}
 *
 */
public interface Model {

    /**
     * Initialize a game.
     * <ul>
     * <li>An empty board is created for each player</li>
     * <li>Player number 0 starts the game</li>
     * <li>State becomes PICK_TILE</li>
     * </ul>
     *
     * @param playerCount the number of players
     * @throws IllegalArgumentException if the number of players is not between
     * 2 and 4 (both included).
     * @throws IllegalStateException if called when state is not NOT_STARTED or
     * GAME_OVER.
     */
    void start(int playerCount);

    /**
     * Give the size of the boards. We suppose that all boards are squares and
     * of the same size. So this is both number of lines and number of columns.
     * With the official rules, this should be 4 but this must not be assumed
     * and this methode must be used instead of hardcoding that value elsewhere
     * in the code.
     *
     * @return the size of the board.
     */
    int getBoardSize();

    /**
     * Choose randomly a tile face down and change the actual pickedTile. Change
     * the state in PLACE_OR_DROP_TILE.
     *
     * @throws IllegalStateException if called when state is not PICK_TILE
     * @return the picked tile.
     */
    Tile pickFaceDownTile();

    /**
     * Choose a select face up tile and remover it from the list of face up
     * tiles. Change the state in PLACE_TILE.
     *
     * @throws IllegalStateException if called when state is not PICK_TILE
     * @throws IllegalArgumentException if called when the choose tile as not
     * found in the list of fac-up tiles.
     * @param tile the tile that has been selected.
     */
    void pickFaceUpTile(Tile tile);

    /**
     * Replace the previous tile select on the list of face up tiles. Change the
     * attribut from the tile on face up and put him on the list of face up
     * tiles. Change the state in TURN_END.
     *
     * @throws IllegalStateException if called when state is not
     * PLACE_OR_DROP_TILE .
     */
    void dropTile();

    /**
     * Give the number of tiles on the list of face down tiles.
     *
     * @return the number of face down tiles.
     */
    int faceDownTileCount();

    /**
     * Give the number of tiles on the list of face up tiles.
     *
     * @return the number of face up tiles.
     */
    int faceUpTileCount();

    /**
     * Give all element of the list of tiles face up.
     *
     * @return a unmodifiable list of tiles face up.
     */
    List<Tile> getAllfaceUpTiles();

    /**
     * Put a tile at the given position. Put the previously picked tile of the
     * current player at the given position on its board. State becomes TURN_END
     * or GAME_OVER.
     *
     * @param pos where to put the tile.
     * @throws IllegalArgumentException if the tile can't be put on that
     * position (position outside of the board or position not allowed by the
     * rules)
     * @throws IllegalStateException if called when state is not PLACE_TILE
     */
    void putTile(Position pos);

    /**
     * Change current player. The next player becomes the current one. The order
     * is : 0, 1, 2, 3 and again 0, 1, ... State becomes PICK_TILE
     *
     * @throws IllegalStateException if called when state is not TURN_END
     */
    void nextPlayer();

    /**
     * Give the total number of players in this game.
     *
     * @return the total number of players in this game.
     * @throws IllegalArgumentException if state is NOT_STARTED
     */
    int getPlayerCount();

    /**
     * Get the current state of the game.
     *
     * @return the current state of the game.
     */
    State getState();

    /**
     * Give the number of the current player. Players are numeroted from 0 to
     * (count-1).
     *
     * @return the number of the current player.
     * @throws IllegalArgumentException if state is NOT_STARTED , GAME_OVER
     * or PICK_TILE
     */
    int getCurrentPlayerNumber();

    /**
     * Get the current picked tile. Get the tile picked by the current player.
     *
     * @return the current picked tile.
     * @throws IllegalStateException if state is not PLACE_TILE or PLACE_OR_DROP_TILE
     */
    Tile getPickedTile();

    /**
     * Check if a position is inside the board of the current player or not.
     *
     * @param pos a position
     * @return true if the given position is inside the board.
     */
    boolean isInside(Position pos);

    /**
     * Check if a tile can be put at the given position. Check if the current
     * player is allowed to put its previously picked tile at the given position
     * on the board of the current player.
     *
     * @param pos the position to check
     * @return true if the picked tile can be put at that position.
     * @throws IllegalArgumentException if the position is outside the board.
     * @throws IllegalStateException if state is not PLACE_TILE.
     */
    boolean canTileBePut(Position pos);

    /**
     * Give a tile at a given position of the board of a given player.
     *
     * @param playerNumber the player number
     * @param pos a position on the board
     * @return the tile at that position for that player or <code>null</code> if
     * there is no tile there.
     * @throws IllegalArgumentException if the position is outside the board or
     * if the playerNUmber is outside of range.
     * @throws IllegalStateException if game state is NOT_STARTED
     */
    Tile getTile(int playerNumber, Position pos);

    /**
     * Give the winner.
     *
     * @return the number of the winner.
     * @throws IllegalStateException if game state is not GAME_OVER
     */
    int getWinners();




}









