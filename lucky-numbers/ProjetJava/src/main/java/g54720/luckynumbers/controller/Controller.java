package g54720.luckynumbers.controller;

import g54720.luckynumbers.model.Model;
import g54720.luckynumbers.model.Position;
import g54720.luckynumbers.view.View;

/**
 * Controller for the Game model.
 */
/**
 * @author Gabriel.espinosa g54720
 */
public class Controller {

    private final Model model;
    private final View view;

    /**
     * Create a Controller
     *
     * @param model the model of the game
     * @param view the view with display
     */
    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    /**
     * Print the the game and switch with the different state and play the each
     * step of the game. If as a exception catch it and display the error. When
     * the state fo model is GAME_OVER rematch the game.
     */
    public void play() {
        while (true) {
            try {
                switch (this.model.getState()) {
                    case NOT_STARTED:
                        this.view.displayWelcome();
                        this.model.start(this.view.askPlayerCount());
                        break;
                    case PICK_TILE:
                        this.view.displayGame();
                        if (this.view.askIsPickTileUpOrDown()) {
                            this.model.pickFaceUpTile((this.view.askPositionTileOnTable()));
                        } else {
                            this.model.pickFaceDownTile();;
                        }
                        break;
                    case PLACE_TILE:
                        Position position = this.view.askPosition();
                        if (this.model.canTileBePut(position)) {
                            this.model.putTile(position);
                        }
                        break;
                    case PLACE_OR_DROP_TILE:
                        if (this.view.askDropOrPut()) {
                            this.model.dropTile();
                        } else {
                            position = this.view.askPosition();
                            this.model.canTileBePut(position);
                            this.model.putTile(position);
                        }
                        break;
                    case TURN_END:
                        this.model.nextPlayer();
                        break;

                    case GAME_OVER:
                        int winners = this.model.getWinners();
                        this.view.displayWinner(winners);
                        break;
                }
            } catch (Exception exception) {
                this.view.displayError(exception.getMessage());
            }
        }
    }
}


