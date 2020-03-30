package categories;

import model.Purchase;

import java.util.LinkedList;

//Represent a category that indicates all purchases in here that the user regrets
public class Regrets extends Category {

    //EFFECTS: Make a empty Regrets category to begin with
    public Regrets() {
        cat = new LinkedList<>();
    }

    //MODIFIES: this
    //EFFECTS: Add purchases to the end of the list and make comments about the purchase
    @Override
    public void addToCat(Purchase p) {
        super.addToCat(p);
        System.out.println("In deed a sad day of humanity");
    }

    @Override
    //EFFECTS: Return the total spending in dollars of a given category and comments about it
    public double sumOfCat() {
        double sum = 0;
        for (Purchase p : cat) {
            sum = p.getAmount() + sum;
        }
        System.out.println("What a shame imagine the money you could have saved");
        return sum;
    }
}
