public class Dealer extends Player {
    // Method to determine if the dealer should hit
    public boolean shouldHit() {
        return getTotal() < 17;
    }
}
