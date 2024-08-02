public class Card {
    private String rank; 
    private String suit; 

    // Constructor to initialize the card with a rank and suit
    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    // Getter for rank
    public String getRank() {
        return rank;
    }

    // Getter for suit
    public String getSuit() {
        return suit;
    }

    // Method to get the value of the card based on its rank
    public int getValue() {
        switch (rank) {
            case "A":
                return 11; 
            case "K":
            case "Q":
            case "J":
                return 10; 
            default:
                return Integer.parseInt(rank); 
        }
    }

    // Override toString method to represent the card as a string
    @Override
    public String toString() {
        return rank + suit;
    }
}
