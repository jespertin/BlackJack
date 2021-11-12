package blackJack;

import java.util.ArrayList;
import java.util.Collections;

public class DeckManager {

    ArrayList<Card> deck = new ArrayList<>();

    public DeckManager() {
        fillDeck();
    }

    public void fillDeck() {
        for (CardSuit suit : CardSuit.values()) {
            for (CardRank rank : CardRank.values()) {
                Card c = new Card(suit, rank);
                deck.add(c);
            }
        }
    }

    public void resetDeck(){
        deck.removeAll(deck);
        fillDeck();
    }

    public void shuffleDeck(){
        Collections.shuffle(deck);
    }

    public void showDeck() {
        deck.forEach(System.out::println);
    }


}
