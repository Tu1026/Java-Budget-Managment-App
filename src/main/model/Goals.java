package model;

import exception.NameNotValidException;
import persistence.Reader;
import persistence.Saveable;

import java.io.PrintWriter;
import java.util.LinkedList;

//Goals have a list of goals within it
public class Goals implements Saveable {
    public LinkedList<Goal> goals;

    //EFFECTS: Construct a list of empty goals to begin with
    public Goals() {
        goals = new LinkedList<>();
    }

    //MODIFIES: This
    //EFFECTS: add a goal to the list of goals
    public void addToGoals(Goal goal) {
        goals.add(goal);
    }

    //REQUIRES: 0=<i-1<= total number of goals
    //MODIFIES: This
    //EFFECTS: Takes out ith goal from the list of goals using 1 indexing
    public void removeIthGoal(int i) {
        goals.remove(i - 1);
    }

    //REQUITES 0=<i-1<= total number of goals
    //EFFECTS: Returns the ith goal using 1 indexing
    public Goal getIthGoal(int i) {
        return goals.get(i - 1);
    }

    //REQUIRES: Non empty list of goals
    //EFFECTS: Return all the names, costs, and desires for all goals in the list of goals as a string
    public String getAllGoals() {
        String goals = "";
        for (Goal g : this.goals) {
            double price = g.getPrice();
            String priceString = Double.toString(price);
            int desire = g.getDesire();
            String desireString = Integer.toString(desire);
            goals = goals + g.getName() + ", $" + priceString + ", " + desireString + " desire points\n";
        }
        return goals;
    }

    // MODIFIES: printWriter
    // EFFECTS: writes the saveable to printWriter
    @Override
    public void save(PrintWriter printWriter) {
        for (Goal g : goals) {
            printWriter.print(g.getName());
            printWriter.print(Reader.DELIMITER_1);
            printWriter.print(g.getPrice());
            printWriter.print(Reader.DELIMITER_1);
            printWriter.print(g.getDesire());
            printWriter.print(Reader.DELIMITER);
        }
    }

    //MODIFIES: This
    //EFFECTS: Empty the list of goals
    public void clearGoals() {
        goals.clear();
    }

    //MODIFIES: This
    //EFFECTS: Removes the nth goal
    public void clearNthGoals(int n) {
        goals.remove(n - 1);
    }

    //MODIFIES: tHIS
    //EFFECTS: Remove the given Goal from list of Goals
    public void removeGivenGoal(String s) throws NameNotValidException {
        for (Goal g : goals) {
            if (g.getName().toLowerCase().equals(s)) {
                goals.remove(g);
                return;
            }
        }
        throw new NameNotValidException();
    }
}
