package g54720.luckynumbers.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Gabriel.espinosa g54720
 */
public class TileTest {

    public TileTest() {
    }

    /**
     * Test of flipFaceUp method, of class Tile. And check if the face is up
     */
    @Test
    public void test_the_method_FlipFaceUp() {
        Tile instance = new Tile(6);
        instance.flipFaceUp();
        assertEquals(true, instance.isFaceUp());
    }

    /**
     * Check if the face of the tile continue to faceUp
     */
    @Test
    public void test_FlipFaceUp_two_times() {
        Tile instance = new Tile(5);
        instance.flipFaceUp();
        instance.flipFaceUp();
        assertEquals(true, instance.isFaceUp());
    }

}

