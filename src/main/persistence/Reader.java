package persistence;

import account.Savings;
import categories.Category;
import categories.Needs;
import categories.Regrets;
import categories.Wants;
import model.Goal;
import model.Goals;
import categories.Purchase;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Reference code from the tellerApp
//A reader reads all data for categories, savings, and goals from a file
public class Reader {
    public static final String DELIMITER = ",";
    public static final String DELIMITER_1 = ";";


    // EFFECTS: returns content of file as a list of strings, each string
    // containing the content of one row of the file with the first row being needs category
    // second row being regrets category
    // third row being wants category
    // fourth row being savings
    // fifth row being goals
    public static List<String> readFile(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    // EFFECTS: returns a list of categories parsed from file; throws
    // IOException if an exception is raised when opening / reading from file
    public static Category readNeeds(File file) throws IOException {
        Category needs;
        try {
            List<String> fileContent = readFile(file);
            needs = parseContentNeeds(fileContent);
        } catch (IndexOutOfBoundsException e) {
            needs = new Needs();
        }
        return needs;
    }

    // EFFECTS: returns a list of categories parsed from file; throws
    // IOException if an exception is raised when opening / reading from file
    public static Category readRegrets(File file) throws IOException {
        Category regrets;
        try {
            List<String> fileContent = readFile(file);
            regrets = parseContentRegrets(fileContent);
        } catch (IndexOutOfBoundsException e) {
            regrets = new Regrets();
        }
        return regrets;
    }

    // EFFECTS: returns a list of categories parsed from file; throws
    // IOException if an exception is raised when opening / reading from file
    public static Category readWants(File file) throws IOException {
        Category wants;
        try {
            List<String> fileContent = readFile(file);
            wants = parseContentWants(fileContent);
        } catch (IndexOutOfBoundsException e) {
            wants = new Wants();
        }
        return wants;
    }

    // EFFECTS: returns a list of categories parsed from list of strings
    // where the first item being the need category
    // second item being the regrets category
    // third item being the wants category
    public static Category parseContentNeeds(List<String> fileContent) throws IndexOutOfBoundsException {
        Category needs;
        String needsLine = fileContent.get(0);
        ArrayList<String> needsComponents = splitString(needsLine);
        needs = parseCategory(needsComponents);
        return needs;
    }

    // EFFECTS: returns a list of categories parsed from list of strings
    // where the first item being the need category
    // second item being the regrets category
    // third item being the wants category
    public static Category parseContentRegrets(List<String> fileContent) throws IndexOutOfBoundsException {
        Category regrets;
        String needsLine = fileContent.get(1);
        ArrayList<String> needsComponents = splitString(needsLine);
        regrets = parseCategory(needsComponents);
        return regrets;
    }


    // EFFECTS: returns a list of categories parsed from list of strings
    // where the first item being the need category
    // second item being the regrets category
    // third item being the wants category
    public static Category parseContentWants(List<String> fileContent) throws IndexOutOfBoundsException {
        Category wants;
        String needsLine = fileContent.get(2);
        ArrayList<String> needsComponents = splitString(needsLine);
        wants = parseCategory(needsComponents);
        return wants;
    }


    // EFFECTS: returns an Category constructed from components
    public static Category parseCategory(List<String> components) {
        Category cat = new Needs();
        for (String s : components) {
            ArrayList<String> lineComponents = splitStringOne(s);
            String name = lineComponents.get(0);
            double amount = Double.parseDouble(lineComponents.get(1));
            Purchase p = new Purchase(name, amount);
            cat.addToCat(p);
        }
        return cat;
    }

    // EFFECTS: returns a savings parsed from file; throws
    // IOException if an exception is raised when opening / reading from file
    public static Savings readSavings(File file) throws IOException {
        Savings savings = new Savings();
        try {
            List<String> fileContent = readFile(file);
            double savingsDouble = Double.parseDouble(fileContent.get(3));
            savings.savingTransaction(savingsDouble);
        } catch (Exception e) {
            //ignore this
        }
        return savings;
    }

    // EFFECTS: returns a list of goals parsed from file; throws
    // IOException if an exception is raised when opening / reading from file
    public static Goals readGoals(File file) throws IOException {
        List<String> fileContent = readFile(file);
        Goals goals;
        try {
            goals = parseContentGoals(fileContent);
        } catch (IndexOutOfBoundsException e) {
            goals = new Goals();
        }
        return goals;
    }


    // EFFECTS: returns a list of goals parsed from list of strings
    // where each string contains data for one goal
    public static Goals parseContentGoals(List<String> fileContent) {
        Goals goals;
        String goalsList = fileContent.get(4);
        ArrayList<String> goalsComponents = splitString(goalsList);
        goals = parseGoal(goalsComponents);
        return goals;
    }


    // EFFECTS: returns a list of goals from given components
    private static Goals parseGoal(List<String> components) {
        Goals gs = new Goals();
        for (String s : components) {
            ArrayList<String> lineComponents = splitStringOne(s);
            String name = lineComponents.get(0);
            double amount = Double.parseDouble(lineComponents.get(1));
            int desire = Integer.parseInt(lineComponents.get(2));
            Goal g = new Goal(name, amount, desire);
            gs.addToGoals(g);
        }
        return gs;
    }

    // EFFECTS: returns a list of strings obtained by splitting line on DELIMITER
    public static ArrayList<String> splitString(String line) {
        String[] splits = line.split(DELIMITER);
        return new ArrayList<>(Arrays.asList(splits));
    }

    // EFFECTS: returns a list of strings obtained by splitting line on DELIMITER_1
    public static ArrayList<String> splitStringOne(String line) {
        String[] splits = line.split(DELIMITER_1);
        return new ArrayList<>(Arrays.asList(splits));
    }

}
