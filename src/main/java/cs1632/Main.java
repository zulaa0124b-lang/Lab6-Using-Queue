package cs1632;

public class Main {
    public static void main(String[] args) {
        PlayingCardDeck deck = new PlayingCardDeck();
        Player p1 = new Player();
        Player p2 = new Player();

        //loop to split the cards to two players
        for(int i = 0; i < 26; i++) {
            p1.addCard(deck.nextCard());
            p2.addCard(deck.nextCard());
        }
        System.out.println("Player 1:" + p1.displayCards());
        System.out.println("Player 2:" + p2.displayCards());
    }
}