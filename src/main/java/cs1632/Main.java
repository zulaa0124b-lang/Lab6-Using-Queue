package cs1632;

import java.util.ArrayList;
import java.util.List;

public class Main {

    //outcomes for War represented by numbers
    static final int CONTINUE = 0;
    static final int PLAYER1WIN = 1;
    static final int PLAYER2WIN = 2;
    static final int TIE = 3;

    public static void main(String[] args) {

        PlayingCardDeck deck = new PlayingCardDeck();
        Player p1 = new Player();
        Player p2 = new Player();

        //loop to split the cards to two players
        for (int i = 0; i < 26; i++) {
            p1.addCard(deck.nextCard());
            p2.addCard(deck.nextCard());
        }

        // main game logic where robot players both show cards and the one who has higher card wins both card
        //if same card declare war
        int rounds = 0;
        int outcome = CONTINUE;
        while (outcome == CONTINUE && p1.hasNextCard() && p2.hasNextCard()) {
            rounds++;
            PlayingCard c1 = p1.playCard();
            PlayingCard c2 = p2.playCard();
            int result = c1.compareTo(c2);
            System.out.println("Round " + rounds + ": Player 1 - " + c1 + " vs Player 2 - " + c2);
            if (result > 0) {
                System.out.println("Player 1 got the cards");
                p1.addCard(c1);
                p1.addCard(c2);
            } else if (result < 0) {
                System.out.println("Player 2 got the cards");
                p2.addCard(c2);
                p2.addCard(c1);
            } else {
                System.out.println("War declared");
                outcome = resolveWar(p1, p2, c1, c2);
            }

            //in every round we can see how many cards each player has
            //I just did it to see if my logic was working or not at first
            //keeping it because it seems helpful
            System.out.println("Player 1 cards = " + p1.cardCount() + " vs Player 2 cards = " + p2.cardCount());
        }

        // outcome variable changer
        //to keep the code understandable not printing the final decision here
        if (outcome == CONTINUE) {
            if (p1.hasNextCard()) {
                outcome = PLAYER1WIN;
            } else {
                outcome = PLAYER2WIN;
            }
        }

        //Game final decision maker
        // kind of just for printing the final result
        System.out.println();
        if (outcome == PLAYER1WIN) {
            System.out.println("Player 1 wins the game!");
        } else if (outcome == PLAYER2WIN) {
            System.out.println("Player 2 wins the game!");
        } else {
            System.out.println("The game is a tie!");
        }
    }

    //war logic
    static int resolveWar(Player p1, Player p2, PlayingCard c1, PlayingCard c2) {
        List<PlayingCard> pile = new ArrayList<>();
        pile.add(c1);
        pile.add(c2);

        while (true) {
            //player needs 3 face-down cards + 1 face-up card
            boolean p1Short = p1.cardCount() < 4;
            boolean p2Short = p2.cardCount() < 4;

            if (p1Short && p2Short) {
                System.out.println("Both players ran out of cards during the War");
                return TIE;
            } else if (p1Short) {
                System.out.println("Player 1 ran out of cards during the War");
                return PLAYER2WIN;
            } else if (p2Short) {
                System.out.println("Player 2 ran out of cards during the War");
                return PLAYER1WIN;
            }

            //3 face-down cards each
            for (int i = 0; i < 3; i++) {
                pile.add(p1.playCard());
                pile.add(p2.playCard());
            }
            System.out.println("Each player puts down 3 cards face down");

            //face-up cards
            PlayingCard f1 = p1.playCard();
            PlayingCard f2 = p2.playCard();
            pile.add(f1);
            pile.add(f2);
            System.out.println("War cards: Player 1 plays " + f1 + ", Player 2 plays " + f2);

            //same logic here as the main one, but winner gets other player's 3 cards + its own 1 = 4
            int warResult = f1.compareTo(f2);
            if (warResult > 0) {
                System.out.println("Player 1 wins the War and takes " + pile.size() + " cards");
                p1.addCards(pile);
                return CONTINUE;
            } else if (warResult < 0) {
                System.out.println("Player 2 wins the War and takes " + pile.size() + " cards");
                p2.addCards(pile);
                return CONTINUE;
            } else {
                System.out.println("Another tie!");
            }
        }
    }
}