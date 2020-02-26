package account;

import persistence.Reader;
import persistence.Saveable;

import java.io.PrintWriter;

//This class represents the amount of money saved in dollars
public class Savings implements Saveable {
    double savings;
    double interest;

    //EFFECTS: Construct a saving account with 0 dollars to start
    public Savings() {
        savings = 0;
    }

    //EFFECTS:Return the money saved in the savings
    public double getSavings() {
        return savings;
    }

    //MODIFIES: This
    //EFFECTS: Take out or put in money to the saving if savings + m >= 0 and return true
    //         return false and leave savings untouched
    public boolean savingTransaction(double m) {
        boolean state;
        if ((savings + m) >= 0) {
            savings = savings + m;
            state = true;
        } else {
            state = false;
        }
        return state;
    }

    //REQUITES: The interest rate in decimal (eg. 10% = 0.1) and > 0
    //MODIFIES: This
    //EFFECTS: Grow the saving by interest rate
    public void savingInterests(double i) {
        savings = savings * (1 + i);
    }

    // MODIFIES: printWriter
    // EFFECTS: writes the saveable to printWriter
    @Override
    public void save(PrintWriter printWriter) {
        printWriter.print(savings);
    }
}
