package g54720.luckynumbers.model;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author MCD <mcodutti@he2b.be>
 * from the method 'setUp()' to 'start_current_player_is_player_0()'.
 *
 * @author Gabriel.espinosa g54720 from the method
 * 'pickTile_the_value_of_the_pickedTile_is_initialized_to_5()' to the end.
 */
public class GameTest {

    private Game game;

    @BeforeEach
    public void setUp() {
        game = new Game();
    }

    /* =====================
         Tests for start()
       ===================== */

 /* --- test related to the state --- */
    @Test
    public void start_when_game_not_started_ok() {
        game.start(4);
    }

    @Test
    public void start_when_game_over_ok() {
        fullPlay();
        game.start(2);
    }

    /* Play a game till the end */
    private void fullPlay() {
        game.start(2);
        while (0 < game.faceDownTileCount()) {
            game.pickFaceDownTile();
            game.dropTile();
            game.nextPlayer();
        }
    }

    @Test
    public void start_when_game_in_progress_ISE() {
        game.start(4);
        assertThrows(IllegalStateException.class,
                () -> game.start(1));
    }

    @Test
    public void start_state_changed_to_PICK_TILE() {
        game.start(3);
        assertEquals(State.PICK_TILE, game.getState());
    }

    /* --- tests related to the parameter --- */
    @Test
    public void start_playerCount_too_small_Exception() {
        assertThrows(IllegalArgumentException.class,
                () -> game.start(1));
    }

    @Test
    public void start_playerCount_minimum_accepted() {
        game.start(2);
    }

    @Test
    public void start_playerCount_maximum_accepted() {
        game.start(4);
    }

    @Test
    public void start_playerCount_too_big_Exception() {
        assertThrows(IllegalArgumentException.class,
                () -> game.start(5));
    }
    /* -- fast tests related for some method --- */
    @Test
    public void getPlayerCount_not_in_state_NOT_STARTED(){
        game.start(2);
        assertThrows(IllegalStateException.class,()-> game.getPlayerCount());
    }
    @Test
    public void getPickedTile_not_in_state_PLACE_TILE_or_PLACE_OR_DROP_TILE(){
        game.start(2);
        assertThrows(IllegalStateException.class,()-> game.getPickedTile());
    }
    @Test
    public void getCurrentPLayerNumber_not_in_the_correct_state(){
        game.start(2);
        game.pickFaceDownTile();
        assertThrows(IllegalStateException.class,()-> game.getCurrentPlayerNumber());
    }

    /* -- tests related to fields initialization --- */
    @Test
    public void start_current_player_is_player_0() {
        game.start(4);
        assertEquals(0, game.getCurrentPlayerNumber());
    }

    /* === À vous de compléter... === */
 /* -- tests realise for the methode pickFaceDownTile ,focus on the initialized --- */
    @Test
    public void pickFaceDownTile_the_value_of_the_pickedTile_is_initialized() {
        game.start(4);
        Tile tile = game.pickFaceDownTile();
        assertEquals(tile.getValue(), game.getPickedTile().getValue());
    }

    /* -- tests related for the methode pickFaceDownTile, focus on the State --- */
    @Test
    public void pickFaceDownTile_the_state_is_changed_to_Place_OR_DROP_TILE() {
        game.start(4);
        game.pickFaceDownTile();
        assertEquals(State.PLACE_OR_DROP_TILE, game.getState());
    }

    @Test
    public void pickFaceDownTile_can_not_use_two_time_in_the_same_turn() {
        game.start(4);
        game.pickFaceDownTile();
        assertThrows(IllegalStateException.class, () -> game.pickFaceDownTile());
    }

    /* -- tests realise for the methode pickFaceUpTile ,focus on the initialized --- */
    @Test
    public void pickFaceUpTile_the_value_of_the_pickedTile_is_initialized() {
        game.start(2);

        game.pickFaceDownTile();
        game.dropTile();
        game.nextPlayer();

        Tile tile = game.getAllfaceUpTiles().get(0);
        game.pickFaceUpTile(game.getAllfaceUpTiles().get(0));

        assertEquals(tile.getValue(), game.getPickedTile().getValue());
    }

    @Test
    public void pickFaceUpTile_no_valid_tile_select() {
        game.start(2);
        assertThrows(IllegalArgumentException.class,
                () -> game.pickFaceUpTile(new Tile(5)));
    }

    /* -- tests related for the methode pickFaceUpTile, focus on the State --- */
    @Test
    public void pickFaceUpTile_change_the_state_in_PLACE_TILE() {
        game.start(2);

        game.pickFaceDownTile();
        game.dropTile();
        game.nextPlayer();

        game.pickFaceUpTile(game.getAllfaceUpTiles().get(0));

        assertEquals(State.PLACE_TILE, game.getState());

    }

    @Test
    public void pickFaceUpTile_can_not_pick_if_the_state_as_not_PICK_TILE() {
        game.start(2);
        game.pickFaceDownTile();
        game.dropTile();

        assertThrows(IllegalStateException.class,
                () -> game.pickFaceUpTile(game.getAllfaceUpTiles().get(0)));
    }

    /* -- tests realise for the methode dropTile ,focus on the initialized --- */
    @Test
    public void dropTile_replace_are_correct() {
        game.start(2);
        Tile tile = game.pickFaceDownTile();
        game.dropTile();
        assertEquals(true, game.getAllfaceUpTiles().contains(tile));
    }

    /* -- tests related for the methode dropTile, focus on the State --- */
    @Test
    public void dropTile_change_the_state_to_TURN_END() {
        game.start(2);
        game.pickFaceDownTile();
        game.dropTile();
        assertEquals(State.TURN_END, game.getState());
    }

    /* -- tests realise for the methode faceDownTileCount --- */
    @Test
    public void faceDownTileCount_are_correct() {
        game.start(2);
        int count = game.faceDownTileCount();
        game.pickFaceDownTile();
        game.dropTile();
        assertEquals(count - 1, game.faceDownTileCount());
    }

    /* -- tests realise for the methode faceUpTileCount --- */
    @Test
    public void faceUpTileCount_are_correct() {
        game.start(2);
        game.pickFaceDownTile();
        game.dropTile();
        assertEquals(1, game.faceUpTileCount());
    }

    /* -- tests realise for the methode getAllfaceUpTiles --- */
    @Test
    public void getAllfaceUpTiles_are_correct() {
        game.start(2);

        Tile tile = game.pickFaceDownTile();
        game.dropTile();
        ArrayList<Tile> t = new ArrayList<>();
        t.add(tile);
        game.nextPlayer();

        tile = game.pickFaceDownTile();
        game.dropTile();
        t.add(1, tile);

        assertEquals(true, t.equals(game.getAllfaceUpTiles()));
    }

    /* -- tests related for the methode putTile, focus on the initialized --- */
    @Test
    public void putTile_check_if_the_tile_is_in_the_given_Position() {
        game.start(2);
        Position pos = new Position(1, 3);
        Tile ancienTile = game.getTile(0, pos);
        game.pickFaceDownTile();
        Tile newTile = game.getPickedTile();
        if (game.canTileBePut(pos)) {

            game.putTile(pos);
            assertEquals(newTile,
                    game.getTile(0,
                            new Position(1, 3)));
        } else {

            assertThrows(IllegalArgumentException.class, () -> game.putTile(pos));
        }
    }

    @Test
    public void putTile_the_position_is_impossible() {
        game.start(4);
        game.pickFaceDownTile();
        assertThrows(IllegalArgumentException.class,
                () -> game.putTile(new Position(4, 4)));
    }

    /* -- tests related for the methode putTile, focus on the state --- */
    @Test
    public void putTile_the_state_after_put_a_tile_on_the_start() {
        game.start(4);
        game.pickFaceDownTile();
        game.putTile(new Position(1, 1));
        assertEquals(State.TURN_END, game.getState());
    }

    @Test
    public void putTile_the_state_of_the_if_all_case_of_a_player_is_full() {
        fullPlay();
        assertEquals(State.GAME_OVER, game.getState());
    }

    @Test
    public void putTile_can_not_putTile_is_state_as_not_PLACE_TILE() {
        game.start(4);
        game.pickFaceDownTile();
        game.dropTile();
        game.nextPlayer();
        assertThrows(IllegalStateException.class,
                () -> game.putTile(new Position(1, 1)));
    }

    @Test
    public void putTile_can_not_putTile_two_times_in_the_same_turn() {
        game.start(4);
        game.pickFaceDownTile();
        game.putTile(new Position(1, 1));
        assertThrows(IllegalStateException.class,
                () -> game.putTile(new Position(2, 1)));
    }

    /* -- tests related for the methode nextPlayer, focus on the initialized */
    @Test
    public void nextPlayer_past_to_the_next_player() {
        game.start(4);
        game.pickFaceDownTile();
        game.dropTile();
        game.nextPlayer();
        assertEquals(1, game.getCurrentPlayerNumber());
    }

    @Test
    public void nextPlayer_past_to_the_first_player_after_the_last() {
        game.start(2);
        game.pickFaceDownTile();
        game.dropTile();
        game.nextPlayer();

        game.pickFaceDownTile();
        game.dropTile();
        game.nextPlayer();
        assertEquals(0, game.getCurrentPlayerNumber());
    }

    /* -- tests related for the methode nextPlayer, focus on the state --- */
    @Test
    public void
            nextPlayer_can_not_past_to_the_next_player_is_state_as_not_TURN_END() {
        game.start(2);
        game.pickFaceDownTile();
        assertThrows(IllegalStateException.class,
                () -> game.nextPlayer());
    }

    @Test
    public void
            nextPlayer_cannot_past_to_the_next_player_2_times_in_the_same_turn() {
        game.start(2);
        game.pickFaceDownTile();
        game.dropTile();
        game.nextPlayer();
        assertThrows(IllegalStateException.class,
                () -> game.nextPlayer());

    }

    /* -- tests related for the methode isInside, check the return --- */
    @Test
    public void isInside_return_true_is_the_position_is_inside() {
        game.start(2);
        assertEquals(true, game.isInside(new Position(1, 3)));
    }

    @Test
    public void isInside_return_false_is_the_position_is_outside() {
        game.start(2);
        assertEquals(false, game.isInside(new Position(5, 5)));
    }

    /*tests related for the methode cantTileBePut, focus on the initialized */
    @Test
    public void canTileBePut_can_not_put_is_the_state_as_not_PLACE_TILE() {
        game.start(2);
        assertThrows(IllegalStateException.class,
                () -> game.canTileBePut(new Position(1, 1)));
    }

    @Test
    public void canTileBePut_can_not_put_a_column_is_outside() {
        game.start(2);
        game.pickFaceDownTile();
        assertThrows(IllegalArgumentException.class,
                () -> game.canTileBePut(new Position(3, 5)));
    }

    @Test
    public void canTileBePut_can_not_put_a_row_is_outside() {
        game.start(2);
        game.pickFaceDownTile();
        assertThrows(IllegalArgumentException.class,
                () -> game.canTileBePut(new Position(5, 3)));
    }

    /* -- tests related for the methode getWinner, focus on the return --- */
    @Test
    public void getWinners_as_a_winner() {
        fullPlay();
        assertEquals(12, game.getWinners());
    }

    /* -- tests related for the methode getWinner, focus on the state --- */
    @Test
    public void getWinners_can_give_a_winner_is_the_state_as_not_GAME_OVER() {
        game.start(2);
        game.pickFaceDownTile();
        assertThrows(IllegalStateException.class, () -> game.getWinners());
    }

    /* -- tests related for the methode versionBGM, focus on the return --- */
    @Test
    public void versionBGM_diagonale_create() {
        game.start(2);
        Position pos0 = new Position(0, 0);
        boolean tile0 = (game.getTile(0, pos0).getValue() > 0);

        Position pos1 = new Position(1, 1);
        boolean tile1 = (game.getTile(0, pos0).getValue() > 0);

        Position pos2 = new Position(2, 2);
        boolean tile2 = (game.getTile(0, pos2).getValue() > 0);

        Position pos3 = new Position(3, 3);
        boolean tile3 = (game.getTile(0, pos3).getValue() > 0);
        assertEquals(true, tile0 == tile1 == tile2 == tile3);
    }

    /* -- tests related for the methode versionBGM, focus on the state --- */
//    @Test
//    public void versionBGM_can_not_create_double_diagonale() {
//        game.start(2);
//        assertThrows(IllegalStateException.class, () -> game.versionBGA(new Board()));
//    }
//
//    @Test
//    public void versionBGM_diagonale_as_not_in_state_NOT_STARTED_or_GAME_OVER() {
//        game.start(2);
//        Position pos0 = new Position(0, 0);
//        boolean tile0 = (game.getTile(0, pos0).getValue() > 0);
//
//        Position pos1 = new Position(1, 1);
//        boolean tile1 = (game.getTile(0, pos0).getValue() > 0);
//
//        Position pos2 = new Position(2, 2);
//        boolean tile2 = (game.getTile(0, pos2).getValue() > 0);
//
//        Position pos3 = new Position(3, 3);
//        boolean tile3 = (game.getTile(0, pos3).getValue() > 0);
//
//        game.pickFaceDownTile();
//        assertThrows(IllegalStateException.class, () -> game.versionBGA(new Board()));
//    }
    
}











