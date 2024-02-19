package g54720.luckynumbers.view;

import g54720.luckynumbers.model.Position;
import g54720.luckynumbers.model.Tile;

/**
 * Interface for The View model.
 *
 * @author Gabriel.espinosa g54720
 */
public interface View {

    /**
     * Display the begin of the Game. Print the Game name. Print the creator.
     * Print the version of the game.
     */
    void displayWelcome();

    /**
     * Print the state of the game. Print the player who plays and his board if
     * the position on the board are empty print a "##" else the value of the
     * tile.
     */
    void displayGame();

    /**
     * Print the player who win the game and a message of congratulation.
     *
     * @param winners the player number concatenate.
     */
    void displayWinner(int winners);

    /**
     * Ask to the user how many players will play.
     *
     * @throws IllegalArgumentException if is not a integer.
     * @throws IllegalArgumentException if is not between 2 or 4 players.
     *
     * @return the number of player who plays.
     */
    int askPlayerCount();

    /**
     * Ask to the user a number of a row and a number of a column and return
     * them in a position and check if the position is valid.
     *
     * @throws IllegalArgumentException if is not between 1 and the size of
     * board
     * @return a valid position with a row and a column.
     */
    Position askPosition();

    /**
     * Ask to the user if he want pick a tile in the deck or on the table.
     *
     * @throws IllegalArgumentException if is not a character.
     * @throws IllegalArgumentException if is not 't' or 'd'.
     * @return true if the user want to pick a tile in the deck.
     */
    boolean askIsPickTileUpOrDown();

    /**
     * Ask to the user if he want drop on table the selected tile or put it on
     * the board.
     *
     * @throws IllegalArgumentException if is not a character.
     * @throws IllegalArgumentException if is not 'd' or 'p'.
     * @return true if the user want to drop on table.
     */
    boolean askDropOrPut();

    /**
     * Ask to the user a position on the table of a tile and return a tile in
     * the position of the list given.
     *
     * @throws IllegalArgumentException if is the table has not tiles.
     * @throws IllegalArgumentException if is not a integer.
     * @throws IllegalArgumentException if is not between 1 and the length of
     * table.
     * @return a tile.
     */
    Tile askPositionTileOnTable();

    /**
     * Print a error message
     *
     * @param message the error to print
     */
    void displayError(String message);

}

