package categories;

import model.Purchase;

import java.util.LinkedList;

//Represent a category that indicates all purchases in here are needed by the user
public class Needs extends Category {

    //EFFECTS: Make a empty needs category to begin with
    public Needs() {
        cat = new LinkedList<>();
    }

    //MODIFIES: this
    //EFFECTS: Add purchases to the end of the list and make comments about the purchase
    @Override
    public void addToCat(Purchase p) {
        super.addToCat(p);
        System.out.println("It okay you needed this");
    }

    @Override
    //EFFECTS: Return the total spending in dollars of a given category and comments about it
    public double sumOfCat() {
        double sum = 0;
        for (Purchase p : cat) {
            sum = p.getAmount() + sum;
        }
        System.out.println("Don't worry about it, it was necessary");
        return sum;
    }
}
