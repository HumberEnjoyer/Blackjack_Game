import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Card> hand; // list to hold the player's hand of cards

    // Constructor to initialize the player's hand
    public Player() {
        hand = new ArrayList<>();
    }

    // Method to add a card to the player's hand
    public void addCard(Card card) {
        hand.add(card);
    }

    // Method to get the player's hand
    public List<Card> getHand() {
        return hand;
    }

    // Method to get the total value of the player's hand
    public int getTotal() {
        int total = 0;
        int aceCount = 0;
        for (Card card : hand) {
            int value = card.getValue();
            if (value == 11) {
                aceCount++;
            }
            total += value;
        }
        // Adjust for aces if total is greater than 21
        while (total > 21 && aceCount > 0) {
            total -= 10;
            aceCount--;
        }
        return total;
    }

    // Method to check if the player is bust
    public boolean isBust() {
        return getTotal() > 21;
    }
}
