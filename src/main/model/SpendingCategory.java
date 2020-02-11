package model;

import java.util.LinkedList;

// A spending can be categorized as either a need, a want, or a regret
public class SpendingCategory {
    public LinkedList<Purchase> needs;
    public LinkedList<Purchase> wants;
    public LinkedList<Purchase> regrets;

    //EFFECTS: Make all three categories empty to begin with
    public SpendingCategory() {
        needs = new LinkedList<>();
        wants = new LinkedList<>();
        regrets = new LinkedList<>();
    }

    //REQUIRES: Non empty category
    //EFFECTS: Return the total spending in dollars of a given category
    public double sumOfNeeds() {
        double sum = 0;
        for (Purchase p : needs) {
            sum = p.getAmount() + sum;
        }
        return sum;
    }

    //REQUIRES: Non empty category
    //EFFECTS: Return the total spending in dollars of a given category
    public double sumOfWants() {
        double sum = 0;
        for (Purchase p : wants) {
            sum = p.getAmount() + sum;
        }
        return sum;
    }

    //REQUIRES: Non empty category
    //EFFECTS: Return the total spending in dollars of a given category
    public double sumOfRegrets() {
        double sum = 0;
        for (Purchase p : regrets) {
            sum = p.getAmount() + sum;
        }
        return sum;
    }

    //MODIFIES: This
    //EFFECTS: Adds a purchase to the needs category
    public void addToNeeds(Purchase p) {
        needs.add(p);
    }

    //MODIFIES: This
    //EFFECTS: Adds a purchase to the wants category
    public void addToWants(Purchase p) {
        wants.add(p);
    }

    //MODIFIES: This
    //EFFECTS: Adds a purchase to the Regrets category
    public void addToRegrets(Purchase p) {
        regrets.add(p);
    }

    //REQUIRES: The category is not empty
    //EFFECTS: return the first purchase on the list
    public Purchase getFirstInNeeds() {
        return needs.getFirst();
    }

    //REQUIRES: The category is not empty
    //EFFECTS: return the first purchase on the list
    public Purchase getFirstInWants() {
        return wants.getFirst();
    }

    //REQUIRES: The category is not empty
    //EFFECTS: return the first purchase on the list
    public Purchase getFirstInRegrets() {
        return regrets.getFirst();
    }
}
