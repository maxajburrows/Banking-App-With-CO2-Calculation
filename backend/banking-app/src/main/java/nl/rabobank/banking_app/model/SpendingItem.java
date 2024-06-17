package nl.rabobank.banking_app.model;


public class SpendingItem {
    private final int euroAmount;
    private final int centAmount;
    public SpendingItem(int euroAmount, int centAmount) {
        this.euroAmount = euroAmount;
        this.centAmount = centAmount;
    }

    public int getEuroAmount() {
        return euroAmount;
    }

    public int getCentAmount() {
        return centAmount;
    }
}
