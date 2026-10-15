package cs1632;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

public class Player {
    private Queue<PlayingCard> playingCards;
    public Player() {
        playingCards = new LinkedList<>();
    }
    //adding cards to the player queue
    public void addCard(PlayingCard card) {
        playingCards.add(card);
    }
    //showing what cards player has
    public String displayCards() {
        StringBuilder result = new StringBuilder();
        for (PlayingCard card : playingCards) {
            result.append(card.toString());
            result.append(" ");
        }
        return result.toString().trim();
    }
    //return the first card of the queue and removes it
    public PlayingCard playCard() {
        return playingCards.poll();
    }
    //checking if the player has cards
    public boolean hasNextCard() {
        if (playingCards.isEmpty()) {
            return false;
        } else  {
            return true;
        }
    }
    //adding cards to the queue
    public void addCards(Collection<PlayingCard> cards) {
        for (PlayingCard card : cards) {
            playingCards.add(card);
        }
    }
    //count how many cards the player has
    // will be used for win/ lose condition
    public int cardCount() {
        return playingCards.size();
    }
}

