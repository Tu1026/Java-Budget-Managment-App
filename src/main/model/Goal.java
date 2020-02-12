package model;

//Represents something the use want that have a value and how much the user wants it
public class Goal {
    public String name;
    public double price;
    public int desire;

    //REQUIRES:  0 <= i <= 10, d must be non negative
    //EFFECTS: A goal has its name, price, and on a scale of 1-10 how much the user wants it
    public Goal(String s, double d, int i) {
        name = s;
        price = d;
        desire = i;
    }

    //EFFECTS: Return the name for the goal
    public String getName() {
        return name;
    }

    //EFFECTS: Return the price this goal would cost
    public double getPrice() {
        return price;
    }

    //EFFECTS: Return how much the user want the desire
    public int getDesire() {
        return desire;
    }
}
