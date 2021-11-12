package blackJack;

public class Main {
    public static void main(String[] args) {
        DeckManager dm = new DeckManager();
        IO io = new IO();

        BlackJackGame blackJackGame = new BlackJackGame(dm,io);
        blackJackGame.playBlackJack();


        // Get value(in int) attached to rank
        //dm.deck.get(0).getRank().getValue();h
    }
}
