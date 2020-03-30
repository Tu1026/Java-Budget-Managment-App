package categories;

import model.Purchase;

import java.util.LinkedList;

//Represent a category that indicates all purchases in here are wanted by the user
public class Wants extends Category {

    //EFFECTS: Make a empty Wants category to begin with
    public Wants() {
        cat = new LinkedList<>();
    }

    //MODIFIES: this
    //EFFECTS: Add purchases to the end of the list and make comments about the purchase
    @Override
    public void addToCat(Purchase p) {
        super.addToCat(p);
        System.out.println("You sure you really wanted this?");
    }

    @Override
    //EFFECTS: Return the total spending in dollars of a given category and comments about it
    public double sumOfCat() {
        double sum = 0;
        for (Purchase p : cat) {
            sum = p.getAmount() + sum;
        }
        System.out.println("At least you got something you wanted? ... right?");
        return sum;
    }
}
