/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g54720.luckynumbers.model;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Gabriel.espinosa g54720
 */
public class DeckTest {

    private Deck deck;

    @BeforeEach
    public void setUp() {
        deck = new Deck(3);
    }

    @Test
    public void faceDownCount_the_size_is_correct() {

        assertEquals(3*20, deck.faceDownCount());
    }

    @Test
    public void pickFaceDown_the_tiles_on_the_faceDown_List_remove_is_correct() {
        Tile tile = deck.pickFaceDown();
        assertEquals(false, deck.getAllFaceDown().contains(tile));
    }

    @Test
    public void faceUpCount_the_face_up_count_is_correct() {
        Tile tile = deck.pickFaceDown();
        deck.putBack(tile);
        assertEquals(1, deck.faceUpCount());
    }

    @Test
    public void getAllFaceUp_return_the_correct_list() {
        List<Tile> teste = new ArrayList<>();
        Tile tile = deck.pickFaceDown();
        deck.putBack(tile);
        teste.add(tile);

        tile = deck.pickFaceDown();
        deck.putBack(tile);
        teste.add(tile);

        assertEquals(teste, deck.getAllFaceUp());
    }

    @Test
    public void hasFaceUp_the_FaceUp_list_has_the_chosen_tile() {
        Tile tile = deck.pickFaceDown();
        deck.putBack(tile);


        assertEquals(true, deck.hasFaceUp(tile));
    }

    @Test
    public void hasFaceUp_the_FaceUp_list_does_not_have_the_select_tile() {
        Tile tile = deck.pickFaceDown();

        assertEquals(false, deck.hasFaceUp(tile));
    }
    
    @Test
    public void pickFaceUp_check_if_the_tile_is_in(){
        Tile tile = deck.pickFaceDown();
        deck.putBack(tile);
        deck.pickFaceUp(tile);
        assertEquals(false, deck.hasFaceUp(tile));
    }
    
    @Test
    public void putBack_check_if_the_select_tile_are_replace_on_the_FaceUp_List(){
        Tile tile = deck.pickFaceDown();
        deck.putBack(tile);
        assertEquals(true, deck.hasFaceUp(tile));
    }
}













