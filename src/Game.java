
import java.util.List;
import java.util.Scanner;

public class Game {

    private Deck deck;
    private Player player;
    private Dealer dealer;
    private Art art;

    // Constructor to initialize the game components
    public Game() {
        deck = new Deck();
        player = new Player();
        dealer = new Dealer();
        art = new Art();
    }

    // Method to play the game
    public void play() {
        Scanner scn = new Scanner(System.in);
        art.welcomeMessage();  // Display welcome message

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Sleep was interrupted");
        }

        // Deal initial cards to player and dealer
        player.addCard(deck.drawCard());
        player.addCard(deck.drawCard());
        dealer.addCard(deck.drawCard());
        dealer.addCard(deck.drawCard());

        // Display initial cards
        displayCards(player.getHand(), "user");
        displaySingleCard(dealer.getHand(), "dealer");

        // User's turn
        boolean userWantsMore = true;
        while (userWantsMore && !player.isBust()) {
            System.out.println("Would you like to hit or stand?");
            String userResponse = scn.nextLine();
            if (userResponse.equalsIgnoreCase("hit")) {
                player.addCard(deck.drawCard());
                displayCards(player.getHand(), "user");

                if (player.isBust()) {
                    System.out.println("You have busted!");
                }
            } else if (userResponse.equalsIgnoreCase("stand")) {
                userWantsMore = false;
            } else {
                System.out.println("Invalid input. Please type 'hit' or 'stand'.");
            }
        }

        // Dealer's turn
        if (!player.isBust()) {
            while (dealer.shouldHit() && !dealer.isBust()) {
                dealer.addCard(deck.drawCard());
            }

            if (dealer.isBust()) {
                System.out.println("The dealer has busted!");
                System.out.println("You win!");
            } else {
                displayCards(dealer.getHand(), "dealer");
                determineWinner();
            }
        } else {
            System.out.println("Dealer wins!");
        }

        scn.close();
    }

    // Method to display all cards of a player
    private void displayCards(List<Card> cards, String player) {
        String subject = (player.equals("user")) ? "Your" : "The dealer's"; // subject of sentence
        String msg = subject + " cards are: "; // well-formatted sentence
        for (int i = 0; i < cards.size(); i++) {
            if (i == 0) {
                msg += cards.get(i).toString();
            } else if (cards.size() == 2 && i == 1) {
                msg += " and " + cards.get(i).toString();
            } else if (i == cards.size() - 1) {
                msg += ", and " + cards.get(i).toString();
            } else {
                msg += ", " + cards.get(i).toString();
            }
        }
        System.out.println(msg);
        printCards(cards);
    }

    // Method to display only one card of the dealer
    private void displaySingleCard(List<Card> cards, String player) {
        String msg = (player.equals("user")) ? "Your" : "The dealer's";
        msg += " visible card is: " + cards.get(0).toString();
        System.out.println(msg);
        printCard(cards.get(0));
    }

    // Method to determine and display the winner
    private void determineWinner() {
        int playerTotal = player.getTotal();
        int dealerTotal = dealer.getTotal();

        if (playerTotal > dealerTotal) {
            System.out.println("You win!");
        } else if (playerTotal < dealerTotal) {
            System.out.println("Dealer wins!");
        } else {
            System.out.println("It's a tie!");
        }
    }

    // Method to print all cards of a player
    private void printCards(List<Card> cards) {
        String[] cardLines = new String[7];
        for (int i = 0; i < cardLines.length; i++) {
            cardLines[i] = "";
        }
        for (Card card : cards) {
            String[] lines = getCardArt(card);
            for (int i = 0; i < lines.length; i++) {
                cardLines[i] += lines[i] + " ";
            }
        }
        for (String line : cardLines) {
            System.out.println(line);
        }
    }

    // Method to print a single card
    private void printCard(Card card) {
        String[] lines = getCardArt(card);
        for (String line : lines) {
            System.out.println(line);
        }
    }

    // Method to get the ASCII art representation of a card
    private String[] getCardArt(Card card) {
        String rank = card.getRank();
        String suit = card.getSuit();
        String[] lines = {
            "┌─────────┐",
            String.format("│ %-2s      │", rank),
            "│         │",
            String.format("│    %s    │", suit),
            "│         │",
            String.format("│       %-2s│", rank),
            "└─────────┘"
        };
        return lines;
    }
}
