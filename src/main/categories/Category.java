package categories;

import exception.NameNotValidException;
import exception.NotInTheListException;
import exception.NothingInFirstOfCatException;
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


    //EFFECTS: return the first purchase of the category
    public Purchase getFirstInCat() throws NothingInFirstOfCatException {
        if (cat.size() == 0) {
            throw new NothingInFirstOfCatException();
        }
        return cat.getFirst();
    }

    //EFFECTS: Returns the ith purchase of the category using 1 indexing
    public Purchase getNumInCat(int i) throws IndexOutOfBoundsException {
        return cat.get(i - 1);
    }

    //EFFECTS: Return the names of all purchases in a category
    public String getListOfPurchases() {
        String allName = "";
        for (Purchase p : cat) {
            allName = allName + p.getItemName() + ", $" + p.getAmount() + "\n";
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

    //MODIFIES: This
    //EFFECTS: Clears nth purchase in the category
    public void removeNthList(int i) throws NotInTheListException {
        if (cat.size() < i) {
            throw new NotInTheListException();
        }
        cat.remove(i - 1);
    }

    //MODIFIES: This
    //EFFECTS: Remove the purchase in category with the given name
    public void removeNamedPurchase(String s) throws NameNotValidException {
        for (Purchase p : cat) {
            if (p.getItemName().toLowerCase().equals(s.toLowerCase())) {
                cat.remove(p);
                return;
            }
        }
        throw new NameNotValidException();
    }
}
