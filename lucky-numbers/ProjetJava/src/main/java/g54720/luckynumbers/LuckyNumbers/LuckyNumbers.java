package g54720.luckynumbers.LuckyNumbers;

import g54720.luckynumbers.controller.Controller;
import g54720.luckynumbers.model.Game;
import g54720.luckynumbers.model.Model;
import g54720.luckynumbers.view.MyView;

/**
 * Launch the Lucky Number
 *
 * @author Gabriel.espinosa g54720
 */
public class LuckyNumbers {

    /**
     * Initialisate the model game ande the controller
     *
     * @param args default
     */
    public static void main(String[] args) {
        Model game = new Game();
        Controller controller = new Controller(game, new MyView(game));
        controller.play();
    }
}


