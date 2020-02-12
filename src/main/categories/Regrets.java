package categories;

import java.util.LinkedList;

//Represent a category that indicates all purchases in here that the user regrets
public class Regrets extends Category {

    //EFFECTS: Make a empty Regrets category to begin with
    public Regrets() {
        cat = new LinkedList<>();
    }
}
