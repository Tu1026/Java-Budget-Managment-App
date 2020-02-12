package categories;

import java.util.LinkedList;

//Represent a category that indicates all purchases in here are needed by the user
public class Needs extends Category {

    //EFFECTS: Make a empty needs category to begin with
    public Needs() {
        cat = new LinkedList<>();
    }
}
