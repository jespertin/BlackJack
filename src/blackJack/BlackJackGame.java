package blackJack;

import java.util.ArrayList;

public class BlackJackGame {

    private DeckManager dm;
    private IO io;
    private ArrayList<Card> playerHand;
    private ArrayList<Card> dealerHand;

    public BlackJackGame(DeckManager dm, IO io) {
        this.dm = dm;
        this.io = io;
    }

    public ArrayList<Card> getPlayerHand() {
        return playerHand;
    }

    public void setPlayerHand() {
        if (this.playerHand != null) {
            this.playerHand.removeAll(playerHand);
        }
        this.playerHand = dealStarterHand();
    }

    public ArrayList<Card> getDealerHand() {
        return dealerHand;
    }

    public void setDealerHand() {
        if (this.dealerHand != null) {
            this.dealerHand.removeAll(playerHand);
        }
        this.dealerHand = dealStarterHand();
    }

    private ArrayList<Card> dealStarterHand() {
        ArrayList<Card> hand = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            hand.add(dm.deck.get(0));
            dm.deck.remove(0);
        }
        return hand;
    }

    public void showDealerHand(Boolean showOneCard) {
        io.printAnything("\nDealers Hand:");
        if (showOneCard == true)
            io.printAnything("[" + dealerHand.get(0) + ", HIDDEN]");
        else {
            io.printAnything(dealerHand);
            io.printAnything("Dealer hand value: " + handValueSum(dealerHand));
        }
    }

    public void showPlayerHand() {
        io.printAnything("\nYour Hand:");
        io.printAnything(playerHand);
        io.printAnything("Player hand value: " + handValueSum(playerHand));
    }

    public int handValueSum(ArrayList<Card> hand) {
        int valueSum = 0;
        for (int i = 0; i < hand.size(); i++) {
            int value = hand.get(i).getRank().getValue();
            valueSum = valueSum + value;
        }
        return valueSum;
    }

    private void playerGameLoop() {
        boolean loop = true;
        while (loop) {
            if (handValueSum(playerHand) == 21) {
                io.printAnything("Blackjack!");
                loop = false;
            }
            io.printAnything("Hit(H to Hit), Stand(S to Stand)");
            String choice = io.getString();

            if (choice.equalsIgnoreCase("h")) {
                playerHand.add(dm.deck.get(0));
                dm.deck.remove(0);
                showPlayerHand();

                if (handValueSum(playerHand) == 21) {
                    io.printAnything("\nBlackJack!");
                    loop = false;
                } else if (handValueSum(playerHand) > 21) {
                    io.printAnything("\nBust");
                    loop = false;
                }

            } else if (choice.equalsIgnoreCase("s"))
                loop = false;
            else io.printAnything("Faulty input, try again");
        }
    }

    private void dealerGameLoop() {
        showDealerHand(false);
        io.printAnything("\nDealer will now draw until hand value of >=16 or bust.");
        io.pauseCode();
        while (handValueSum(dealerHand) < 16) {
            dealerHand.add(dm.deck.get(0));
            dm.deck.remove(0);
        }
        showDealerHand(false);
        if (handValueSum(dealerHand) > 21)
            io.printAnything("\nDealer Bust, you win!");
    }

    private void checkWinner() {
        if (handValueSum(dealerHand) > 21)
            io.printAnything("\nDealer Bust, you win.");
        else if (handValueSum(playerHand) > handValueSum(dealerHand))
            io.printAnything("\nCongrats, you won");
        else if (handValueSum(playerHand) == handValueSum(dealerHand))
            io.printAnything("\nEven game");
        else
            io.printAnything("\nYou lost, dealer wins");
    }

    private void pullOneCard(ArrayList<Card> hand){
        hand.add(dm.deck.get(0));

        dm.deck.remove(0);
    }
//TODO fix ace value so if >21 it should be 1, else 11
    private void checkAceValue(){
        if (handValueSum(playerHand)>10)
            CardRank.ACE.setValue(1);
    }

    public void playBlackJack() {
        io.printAnything("Game of blackjack");
        boolean gameLoop = true;
        while (gameLoop) {
            io.printAnything(dm.deck.size());
            dm.shuffleDeck();
            setPlayerHand();
            setDealerHand();
            showDealerHand(true);
            showPlayerHand();
            playerGameLoop();
            if (handValueSum(playerHand) <= 21) {
                dealerGameLoop();
                if (handValueSum(dealerHand) <= 21)
                    checkWinner();
            }
            io.printAnything("Play again? y/n");
            String choice = io.getString();
            if (choice.equalsIgnoreCase("n"))
                gameLoop = false;
            else if (choice.equalsIgnoreCase("y"))
                dm.resetDeck();
        }
    }
}
