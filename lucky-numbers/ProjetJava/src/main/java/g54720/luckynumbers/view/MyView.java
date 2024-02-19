package g54720.luckynumbers.view;

import g54720.luckynumbers.model.Model;
import g54720.luckynumbers.model.Position;
import g54720.luckynumbers.model.Tile;
import java.util.List;
import java.util.Scanner;

/**
 * Implement for the View model.
 *
 * @author Gabriel.espinosa g54720
 */
public class MyView implements View {

    Model model;

    /**
     * Constructor of the view
     *
     * @param model the model of the game
     */
    public MyView(Model model) {
        this.model = model;
    }

    @Override
    public void displayWelcome() {
        System.out.println(Color.RED.color("------Lucky Number-------\n")
                + " Made by Gabriel Espinosa" + "\n v2.0\n");
    }

    @Override
    public void displayGame() {
        System.out.print(Color.BLUE.color("\nPlayer \t" + (this.model.getCurrentPlayerNumber() + 1)) + "\n\n | ");

        for (int i = 0; i < this.model.getBoardSize(); i++) {
            System.out.print(Color.BLUE.color(" " + (i + 1) + " "));
        }
        System.out.print("\n");
        for (int i = 0; i < this.model.getBoardSize(); i++) {
            System.out.print("----");
        }
        System.out.println("");

        for (int row = 0; row < this.model.getBoardSize(); row++) {
            System.out.print(Color.BLUE.color((row + 1) + "|"));

            for (int column = 0; column < this.model.getBoardSize(); column++) {

                if (this.model.getTile(this.model.getCurrentPlayerNumber(),
                        new Position(row, column)) != null) {
                    System.out.print(Color.GREEN.color(" " + this.model.getTile(
                            this.model.getCurrentPlayerNumber(),
                            new Position(row, column))));
                } else {
                    System.out.print(Color.YELLOW.color(" ##"));
                }
            }
            System.out.println("");
        }
        for (int i = 0; i < this.model.getBoardSize(); i++) {
            System.out.print("----");
        }
        System.out.print("\nDeck of Cards (" + Color.RED.color("" + this.model.faceDownTileCount())
                + ")\n Table :" + Color.GREEN.color("" + this.model.getAllfaceUpTiles()) + "\n");
    }

    @Override
    public void displayWinner(int winners) {
        if (winners > 3) {
            System.out.print(Color.MAGENTA.color("\nCongratulation "));

            int affichage;
            while (winners > 0) {
                affichage = winners % 10;
                if (affichage != 0) {
                    System.out.print(Color.YELLOW.color("Player " + affichage + " "));
                }
                winners /= 10;
            }
        } else {
            System.out.println(Color.YELLOW.color("\nCongratulation Player " + (winners + 1)
                    + "! You are the new Master "));
        }
        System.out.print("\n");
    }

    @Override
    public int askPlayerCount() {
        Scanner clavier = new Scanner(System.in);
        System.out.print("How much player play?");
        int playerNumber = 0;
        while (playerNumber < 2 || playerNumber > 4) {

            while (!clavier.hasNextInt()) {
                clavier.next();
                displayError("It's not a integer");
            }
            playerNumber = clavier.nextInt();
            if (playerNumber < 2 || playerNumber > 4) {
                displayError("It's not between 2 and 4");
            }
        }
        return playerNumber;

    }

    @Override
    public Position askPosition() {
        Scanner clavier = new Scanner(System.in);
        System.out.println(Color.MAGENTA.color("\nThe selected tile :"
                + this.model.getPickedTile()) + "\nGive me a row and a column");
        int row = clavier.nextInt();
        int rowReal = row / 10;
        int column = row % 10;

        while ((rowReal <= 0 || rowReal > this.model.getBoardSize())
                && (column <= 0 || column > this.model.getBoardSize())) {

            displayError("It's not between 1 and "
                    + (this.model.getBoardSize()));

            row = clavier.nextInt();
            rowReal = row / 10;
            column = row % 10;
        }
        return new Position(rowReal - 1, column - 1);
    }

    @Override
    public boolean askIsPickTileUpOrDown() {
        Scanner clavier = new Scanner(System.in);
        System.out.println("Take a card from the Table or a card from the Deck?" + Color.CYAN.color("(t/d)"));
        char answer = 'a';
        while (answer != 't' && answer != 'd') {
            while (clavier.hasNextInt()) {

                displayError("It's not a character");
                clavier.next();
            }
            answer = clavier.nextLine().charAt(0);
            if (answer != 't' && answer != 'd') {
                displayError("It's not t or d");
            }
        }
        return answer == 't';
    }

    @Override
    public boolean askDropOrPut() {
        Scanner clavier = new Scanner(System.in);
        System.out.println(Color.MAGENTA.color("\nThe selected tile :"
                + this.model.getPickedTile()) + "\nDo you want to Drop the card "
                + "or Put it in the board?" + Color.CYAN.color("(d/p)"));
        char answer = 'a';
        while (answer != 'd' && answer != 'p') {
            while (clavier.hasNextInt()) {
                clavier.next();
                displayError("It's not a character");
            }
            answer = clavier.nextLine().charAt(0);
            if (answer != 'd' && answer != 'p') {
                displayError("It's not between d or p");
            }
        }
        return answer == 'd';
    }

    @Override
    public Tile askPositionTileOnTable() {
        Scanner clavier = new Scanner(System.in);
        if (this.model.faceUpTileCount() == 0) {
            displayError("The table has not tile");
            return new Tile(0);
        }
        System.out.println("Give the value of the tile ");
        int answer = -1;
        while (answer < 1 || this.model.getAllfaceUpTiles().contains(new Tile(answer))) {
            while (!clavier.hasNextInt()) {
                clavier.next();
                displayError("It's not a integer");
            }
            answer = clavier.nextInt();
            if (this.model.getAllfaceUpTiles().contains(new Tile(answer))) {
                displayError("it's not in the the table");
            }
        }       
        int position=-1;
        for (int i = 0; i < this.model.getAllfaceUpTiles().size(); i++) {
            if(this.model.getAllfaceUpTiles().get(i).getValue()== new Tile(answer).getValue()) {
                position=i;
                i=(this.model.getAllfaceUpTiles().size());
            }           
        }
        if(position==-1){
            throw new IllegalArgumentException("out of bande");           
        }
        return this.model.getAllfaceUpTiles().get(position);
    }

    @Override
    public void displayError(String message) {
        System.out.println(Color.RED.color("!!Error Messages :" + message + "!!"));
    }

}
