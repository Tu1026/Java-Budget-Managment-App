package persistence;

import account.Savings;
import categories.Category;
import categories.Needs;
import model.Goal;
import model.Goals;
import model.Purchase;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Reference code from the tellerApp
//A reader reads different data from a file
public class Reader {
    public static final String DELIMITER = ",";
    public static final String DELIMITER_1 = ";";


    // EFFECTS: returns content of file as a list of strings, each string
    // containing the content of one row of the file
    private static List<String> readFile(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    // EFFECTS: returns a list of accounts parsed from file; throws
    // IOException if an exception is raised when opening / reading from file
    public static List<Category> readCategory(File file) throws IOException {
        List<String> fileContent = readFile(file);
        return parseContentCategory(fileContent);
    }

    // EFFECTS: returns a list of accounts parsed from list of strings
    // where each string contains data for one account
    private static List<Category> parseContentCategory(List<String> fileContent) {
        List<Category> cats = new ArrayList<>();
        String needsLine = fileContent.get(0);
        ArrayList<String> needsComponents = splitString(needsLine);
        cats.add(parseCategory(needsComponents));
        String regretsLine = fileContent.get(1);
        ArrayList<String> regretsComponents = splitString(regretsLine);
        cats.add(parseCategory(regretsComponents));
        String wantsLine = fileContent.get(2);
        ArrayList<String> wantsComponents = splitString(wantsLine);
        cats.add(parseCategory(wantsComponents));
        return cats;
    }

    // REQUIRES: components has size 4 where element 0 represents the
    // id of the next account to be constructed, element 1 represents
    // the id, elements 2 represents the name and element 3 represents
    // the balance of the account to be constructed
    // EFFECTS: returns an account constructed from components
    private static Category parseCategory(List<String> components) {
        Category cat = new Needs();
        for (String s : components) {
            ArrayList<String> lineComponents = splitStringOne(s);
            String name = lineComponents.get(1);
            double amount = Double.parseDouble(lineComponents.get(2));
            Purchase p = new Purchase(name, amount);
            cat.addToCat(p);
        }
        return cat;
    }

    // EFFECTS: returns a list of accounts parsed from file; throws
    // IOException if an exception is raised when opening / reading from file
    public static Savings readSavings(File file) throws IOException {
        List<String> fileContent = readFile(file);
        Double savingsDouble = Double.parseDouble(fileContent.get(3));
        Savings savings = new Savings();
        savings.savingTransaction(savingsDouble);
        return savings;
    }

    // EFFECTS: returns a list of accounts parsed from file; throws
    // IOException if an exception is raised when opening / reading from file
    public static Goals readGoals(File file) throws IOException {
        List<String> fileContent = readFile(file);
        return parseContentGoals(fileContent);
    }


    // EFFECTS: returns a list of accounts parsed from list of strings
    // where each string contains data for one account
    private static Goals parseContentGoals(List<String> fileContent) {
        Goals goals;
        String goalsList = fileContent.get(4);
        ArrayList<String> goalsComponents = splitString(goalsList);
        goals = parseGoal(goalsComponents);
        return goals;
    }


    // REQUIRES: components has size 4 where element 0 represents the
    // id of the next account to be constructed, element 1 represents
    // the id, elements 2 represents the name and element 3 represents
    // the balance of the account to be constructed
    // EFFECTS: returns an account constructed from components
    private static Goals parseGoal(List<String> components) {
        Goals gs = new Goals();
        for (String s : components) {
            ArrayList<String> lineComponents = splitStringOne(s);
            String name = lineComponents.get(1);
            double amount = Double.parseDouble(lineComponents.get(2));
            int desire = Integer.parseInt(lineComponents.get(3));
            Goal g = new Goal(name, amount, desire);
            gs.addToGoals(g);
        }
        return gs;
    }

    // EFFECTS: returns a list of strings obtained by splitting line on DELIMITER
    private static ArrayList<String> splitString(String line) {
        String[] splits = line.split(DELIMITER);
        return new ArrayList<>(Arrays.asList(splits));
    }

    // EFFECTS: returns a list of strings obtained by splitting line on DELIMITER
    private static ArrayList<String> splitStringOne(String line) {
        String[] splits = line.split(DELIMITER_1);
        return new ArrayList<>(Arrays.asList(splits));
    }

}
