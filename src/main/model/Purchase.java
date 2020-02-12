package model;

// A purchase has a name of the item purchased and money in dollars spent on it.
public class Purchase {
    public double amount;
    public String itemName;

    // REQUITES: amount not be negative integers
    // MODiFIES: This
    // EFFECTS: The purchase records the name of item purchased
    //          the amount paid for it
    //          and the category of spending it belongs
    public Purchase(String itemName, double amount) {
        this.amount = amount;
        this.itemName = itemName;
    }

    //EFFECTS: return the money spent on the purchase
    public double getAmount() {
        return amount;
    }

    //EFFECTS: return the name of item bought from the purchase
    public String getItemName() {
        return itemName;
    }
}