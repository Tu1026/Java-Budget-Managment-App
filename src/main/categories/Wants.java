package categories;

import java.util.LinkedList;

//Represent a category that indicates all purchases in here are wanted by the user
public class Wants extends Category {

    //EFFECTS: Make a empty Wants category to begin with
    public Wants() {
        cat = new LinkedList<>();
    }
}
