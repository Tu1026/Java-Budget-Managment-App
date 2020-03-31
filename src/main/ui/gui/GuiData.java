package ui.gui;

import account.Savings;
import categories.Category;
import categories.Needs;
import categories.Regrets;
import categories.Wants;
import model.Goals;
import persistence.Reader;
import ui.gui.exception.CategoryInvalidException;

import java.io.File;
import java.io.IOException;

//A static class that is dedicated to storing data and allow the gui's to access it
public class GuiData {
    private static String budgetFile = "./data/budget.txt";
    private static Category needs;
    private static Category regrets;
    private static Category wants;
    private static Savings savings;
    private static Goals goals;

    //MODIFIES: this
    //EFFECTS: Initialise the data by loading from the default file
    public GuiData() {
        try {
            needs = Reader.readNeeds(new File(budgetFile));
            regrets = Reader.readRegrets(new File(budgetFile));
            wants = Reader.readWants(new File(budgetFile));
            savings = Reader.readSavings(new File(budgetFile));
            goals = Reader.readGoals(new File(budgetFile));
        } catch (IOException e) {
            init();
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes a empty budget tracker
    private void init() {
        needs = new Needs();
        regrets = new Regrets();
        wants = new Wants();
        savings = new Savings();
        goals = new Goals();
    }


    //EFFECTS: return the goals in this file
    public static Goals getGoals() {
        return goals;
    }

    //EFFECTS: return savings in the file
    public static Savings getSavings() {
        return savings;
    }

    //EFFECTS: Return a given category in this file
    public static Category getCategory(String s) throws CategoryInvalidException {
        switch (s) {
            case "needs":
                return needs;
            case "wants":
                return wants;
            case "regrets":
                return regrets;
            default:
                throw new CategoryInvalidException();
        }
    }

    //EFFECTS: Return a given category in this file without possibility of expression
    //         only used in cases where we are certain exception will never be thrown
    public static Category getCategoryWithoutEx(String s) {
        switch (s) {
            case "needs":
                return needs;
            case "wants":
                return wants;
            case "regrets":
                return regrets;
            default:
                return new Needs();
        }
    }

    //MODIFIES: this
    //EFFECTS: Set the savings store in this file to the given savings
    public static void setSavings(Savings s) {
        savings = s;
    }

    //MODIFIES: this
    //EFFECTS: Set the a category in this file to be a given category
    public static void setCategory(String s, Category c) throws CategoryInvalidException {
        switch (s) {
            case "needs":
                needs = c;
            case "wants":
                wants = c;
            case "regrets":
                regrets = c;
            default:
                throw new CategoryInvalidException();
        }
    }

    //MODIFIES: this
    //EFFECTS: Set the goals in this file to the given goals
    public static void setGoals(Goals gs) {
        goals = gs;
    }

    //MODIFIES: this
    //EFFECTS: Set the file path to this file to the given path
    public static void setFilePath(String s) {
        budgetFile = s;
    }

    //EFFECTS: Set the file path to this file
    public static String getBudgetFile() {
        return budgetFile;
    }
}