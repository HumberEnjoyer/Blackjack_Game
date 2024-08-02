import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> deck;

    // Constructor to initialize and shuffle the deck
    public Deck() {
        deck = new ArrayList<>();
        initializeDeck();
        shuffleDeck();
    }

    // Method to initialize the deck with 52 cards
    private void initializeDeck() {
        String[] suits = {"c", "d", "h", "s"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        for (String suit : suits) {
            for (String rank : ranks) {
                deck.add(new Card(rank, suit));
            }
        }
    }

    // Method to shuffle the deck
    public void shuffleDeck() {
        Collections.shuffle(deck);
    }

    // Method to draw a card from the top of the deck
    public Card drawCard() {
        return deck.remove(0);
    }
}
