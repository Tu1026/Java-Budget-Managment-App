package categories;

import model.Purchase;

import java.util.LinkedList;

public abstract class Category {
    public LinkedList<Purchase> cat;


    //EFFECTS: Return the total spending in dollars of a given category
    public double sumOfCat() {
        double sum = 0;
        for (Purchase p : cat) {
            sum = p.getAmount() + sum;
        }
        return sum;
    }

    //MODIFIES: This
    //EFFECTS: Adds a purchase to the end of category
    public void addToCat(Purchase p) {
        cat.add(p);
    }

    //REQUIRES: The category is not empty
    //EFFECTS: return the first purchase of the category
    public Purchase getFirstInCat() {
        return cat.getFirst();
    }

    //REQUITES: Non empty category and i > 0
    //EFFECTS: Returns the ith purchase of the category using 1 indexing
    public Purchase getNumInCat(int i) {
        return cat.get(i - 1);
    }

}
