package categories;

import model.Purchase;
import persistence.Reader;
import persistence.Saveable;

import java.io.PrintWriter;
import java.util.LinkedList;

//A category contains many purchases within it
public abstract class Category implements Saveable {
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

    //REQUIRES: Non empty category
    //EFFECTS: Return the names of all purchases in a category
    public String getListOfPurchases() {
        String allName = "";
        for (Purchase p : cat) {
            allName = allName + p.getItemName() + "\n";
        }
        return allName;
    }

    // MODIFIES: printWriter
    // EFFECTS: writes the saveable to printWriter
    @Override
    public void save(PrintWriter printWriter) {
        for (Purchase p : cat) {
            printWriter.print(p.getItemName());
            printWriter.print(Reader.DELIMITER_1);
            printWriter.print(p.getAmount());
            printWriter.print(Reader.DELIMITER);
        }
    }

    //MODIFIES: This
    //EFFECTS: Clears everything in the category
    public void clearList() {
        cat.clear();
    }
}
