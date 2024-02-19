package g54720.luckynumbers.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Implement for The Model.
 *
 * @author Gabriel.espinosa g54720
 */
public class Game implements Model {

    private State state;
    private int playerCount;
    private int currentPlayerNumber;
    private Board[] boards;
    private Tile pickedTile;
    private Deck deck;

    /**
     * Constructor of the game, the state of the game is NOT_STARTED
     */
    public Game() {
        this.state = State.NOT_STARTED;
    }

    @Override
    public void start(int playerCount) {
        if (this.state != State.NOT_STARTED && this.state != State.GAME_OVER) {
            throw new IllegalStateException("The state is not"
                    + State.NOT_STARTED + " or " + State.GAME_OVER);
        }
        if (playerCount < 2) {
            throw new IllegalArgumentException("Not enough player");
        }
        if (playerCount > 4) {
            throw new IllegalArgumentException("Too much player");
        }
        this.playerCount = playerCount;
        this.boards = new Board[playerCount];
        this.deck = new Deck(playerCount);
        this.currentPlayerNumber = 0;
        for (int count = 0; count < playerCount; count++) {
            this.boards[count] = new Board();
            versionBGA(this.boards[count]);
        }

        this.state = State.PICK_TILE;
    }

    /**
     * Create a diagonale with tile chosend from the face-down deck for the
     * board's player.
     *
     * @param b a board of tile from a player.
     * @throws IllegalStateException if game state is not NOT_STARTED or
     * GAME_OVER
     */
    private void versionBGA(Board b) {
        if (this.state != State.NOT_STARTED && this.state != State.GAME_OVER) {
            throw new IllegalStateException("The state is not"
                    + State.NOT_STARTED + " or " + State.GAME_OVER);
        }

        List<Tile> diagonale = new ArrayList<>();
        diagonale.add(this.deck.pickFaceDown());
        for (int j = 1; j < getBoardSize(); j++) {
            int index = 0;
            Tile actual = this.deck.pickFaceDown();
            while (index < diagonale.size() && actual.getValue() < diagonale.get(index).getValue()) {
                index++;
            }
            diagonale.add(index, actual);
        }
        while (!(diagonale.isEmpty())) {
            int size = diagonale.size() - 1;
            b.put(diagonale.get(0), new Position(size, size));
            diagonale.remove(0);
        }
    }

    @Override
    public void putTile(Position pos) {
        if (this.state != State.PLACE_TILE && this.state != State.PLACE_OR_DROP_TILE) {
            throw new IllegalStateException("The state is not "
                    + State.PLACE_TILE
                    + " or not " + State.PLACE_OR_DROP_TILE);
        }
        if (canTileBePut(pos)) {
            if (this.boards[this.currentPlayerNumber].getTile(pos) != null) {
                this.deck.putBack(this.boards[this.currentPlayerNumber].getTile(pos));
            }
            this.boards[this.currentPlayerNumber].put(this.pickedTile, pos);
            this.state = State.TURN_END;

        } else {
            throw new IllegalArgumentException("Impossible position");
        }
        if (this.boards[this.currentPlayerNumber].isFull()) {
            this.state = State.GAME_OVER;
        }
    }

    @Override
    public void nextPlayer() {
        if (this.state != State.TURN_END) {
            throw new IllegalStateException("The state is not "
                    + State.TURN_END);
        }
        this.currentPlayerNumber = (this.currentPlayerNumber + 1) % this.playerCount;
        if (tableEmpty()) {
            this.state = State.GAME_OVER;
        } else {
            this.state = State.PICK_TILE;
        }
    }

    @Override
    public boolean isInside(Position pos) {
        return this.boards[currentPlayerNumber].isInside(pos);
    }

    @Override
    public boolean canTileBePut(Position pos) {
        if (this.state != State.PLACE_TILE && this.state != State.PLACE_OR_DROP_TILE) {
            throw new IllegalStateException("The state is not "
                    + State.PLACE_TILE + " or not " + State.PLACE_OR_DROP_TILE);
        }
        if (pos.getColumn() < 0 || pos.getColumn() > 4) {
            throw new IllegalArgumentException("The column(" + pos.getColumn()
                    + ")is not between 1 and 4 ");
        }
        if (pos.getRow() < 0 || pos.getRow() > 4) {
            throw new IllegalArgumentException("The row(" + pos.getRow()
                    + ")is not between 1 and 4 ");
        }
        return this.boards[currentPlayerNumber].canBePut(pickedTile, pos);
    }

    @Override
    public int getWinners() {
        if (this.state != State.GAME_OVER) {
            throw new IllegalStateException("The state is not "
                    + State.GAME_OVER);
        }
        this.state = State.NOT_STARTED;
        if (tableEmpty()) {
            return MultipleWinners();
        }
        return this.currentPlayerNumber;
    }

    /**
     * Give the number of player concatenate who win the game.
     *
     * @return the numbers of players win
     */
    private int MultipleWinners() {

        int winners = 0;
        int emptyNbrMin = 100;
        for (int actuelPlayer = 0; actuelPlayer < this.playerCount; actuelPlayer++) {
            int actuelEmpty = 0;
            for (int i = 0; i < this.boards[actuelPlayer].getSize(); i++) {
                for (int j = 0; j < this.boards[actuelPlayer].getSize(); j++) {
                    if (this.boards[actuelPlayer].getTile(new Position(i, j)) == null) {
                        actuelEmpty++;
                    }
                }
            }
            if (emptyNbrMin == actuelEmpty) {
                double puissanceDix = Math.pow(10, actuelPlayer);
                winners = winners * ((int) puissanceDix) + (actuelPlayer + 1);
            }
            if (emptyNbrMin > actuelEmpty) {
                winners = actuelPlayer + 1;
                emptyNbrMin = actuelEmpty;
            }
        }
        return winners;
    }

    /**
     * Check if the deck is empty.
     *
     * @return true if the deck is empty.
     */
    private boolean tableEmpty() {
        return this.deck.faceDownCount() < 1;
    }

    @Override
    public Tile getTile(int playerNumber, Position pos) {
        return this.boards[playerNumber].getTile(pos);
    }

    @Override
    public int getBoardSize() {
        return this.boards[currentPlayerNumber].getSize();
    }

    @Override
    public State getState() {
        return this.state;
    }

    @Override
    public int getCurrentPlayerNumber() {
        if (this.state != State.NOT_STARTED && this.state != State.GAME_OVER && this.state != State.PICK_TILE) {
            throw new IllegalStateException("The state is not "
                    + State.NOT_STARTED + " , " + State.GAME_OVER + " or " + State.PICK_TILE);
        }
        return this.currentPlayerNumber;
    }

    @Override
    public Tile getPickedTile() {
        if (this.state != State.PLACE_TILE && this.state != State.PLACE_OR_DROP_TILE) {
            throw new IllegalStateException("The state is not "
                    + State.PLACE_TILE + " or " + State.PLACE_OR_DROP_TILE);
        }
        return this.pickedTile;
    }

    @Override
    public int getPlayerCount() {//corection
        if (this.state != State.NOT_STARTED) {
            throw new IllegalStateException("The state is not "
                    + State.NOT_STARTED);
        }
        return this.playerCount;
    }

    @Override
    public Tile pickFaceDownTile() {
        if (this.state != State.PICK_TILE) {
            throw new IllegalStateException("The state is not "
                    + State.PICK_TILE);
        }
        this.state = State.PLACE_OR_DROP_TILE;

        return this.pickedTile = this.deck.pickFaceDown();
    }

    @Override
    public void pickFaceUpTile(Tile tile) {
        if (this.state != State.PICK_TILE) {
            throw new IllegalStateException("The state is not "
                    + State.PICK_TILE);
        }
        if (!(getAllfaceUpTiles().contains(tile))) {
            throw new IllegalArgumentException("Invalid tile");
        }
        this.state = State.PLACE_TILE;
        this.pickedTile = tile;
        this.deck.pickFaceUp(tile);
    }

    @Override
    public void dropTile() {
        if (this.state != State.PLACE_OR_DROP_TILE) {
            throw new IllegalStateException("The state is not "
                    + State.PLACE_OR_DROP_TILE);
        }
        this.pickedTile.flipFaceUp();
        this.state = State.TURN_END;
        this.deck.putBack(pickedTile);

    }

    @Override
    public int faceDownTileCount() {
        return this.deck.faceDownCount();
    }

    @Override
    public int faceUpTileCount() {
        return this.deck.faceUpCount();
    }

    @Override
    public List<Tile> getAllfaceUpTiles() {
        return Collections.unmodifiableList(this.deck.getAllFaceUp());
    }

}
