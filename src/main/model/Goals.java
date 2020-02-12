package model;

import java.util.LinkedList;

//Goals have a list of goals within it
public class Goals {
    public LinkedList<Goal> goals;

    //EFFECTS: Construct a list of empty goals to begin with
    public Goals() {
        goals = new LinkedList<>();
    }

    //MODIFIES: This
    //EFFECTS: add a goal to the list of goals
    public void  addToGoals(Goal goal) {
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

}
