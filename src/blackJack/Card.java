package blackJack;

public class Card {

    private CardSuit suit;
    private CardRank rank;

    public Card(CardSuit suit, CardRank name) {
        this.suit = suit;
        this.rank = name;
    }

    public CardSuit getSuit() {
        return suit;
    }

    public void setSuit(CardSuit suit) {
        this.suit = suit;
    }

    public CardRank getRank() {
        return rank;
    }

    public void setRank(CardRank rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return rank + " OF " + suit;
    }
}
