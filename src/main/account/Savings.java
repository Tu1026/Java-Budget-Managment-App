package account;

//This class represents the amount of money saved in dollars
public class Savings {
    double savings;

    //EFFECTS: Construct a saving account with 0 dollars to start
    public Savings() {
        savings = 0;
    }

    //EFFECTS:Return the money saved in the savings
    public double getSavings() {
        return savings;
    }

    //MODIFIES: This
    //EFFECTS: Take out or put in money to the saving
    public void savingTransaction(double m) {
        savings = savings + m;
    }

    //REQUITES: The interest rate in decimal (eg. 10% = 0.1) and > 0
    //MODIFIES: This
    //EFFECTS: Grow the saving by interest rate
    public void savingInterests(double i) {
        savings = savings * (1 + i);
    }
}
