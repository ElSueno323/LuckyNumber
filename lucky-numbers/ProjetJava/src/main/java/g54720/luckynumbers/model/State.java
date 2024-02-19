package g54720.luckynumbers.model;

/**
 * Enumeration for the game. Give the actual state of the game.
 *
 * @author Gabriel.espinosa g54720
 *
 */
public enum State {
    /**
     * State of begin the game
     */
    NOT_STARTED,
    /**
     * State of pick a tile from the deck or the table
     */
    PICK_TILE,
    /**
     * State of put a tile from the table in the board
     */
    PLACE_TILE,
    /**
     * State of put a tile from the deck or drop the tile in the table
     */
    PLACE_OR_DROP_TILE,
    /**
     * State of end turn of player and change for the next player
     */
    TURN_END,
    /**
     * State of end game when one or more players win if the deck is empty or a
     * player fills his board
     */
    GAME_OVER,
}
