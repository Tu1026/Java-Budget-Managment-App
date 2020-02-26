package persistence;

import categories.Category;
import categories.Needs;
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

    // EFFECTS: returns a list of accounts parsed from file; throws
    // IOException if an exception is raised when opening / reading from file
    public static List<Category> readCategory(File file) throws IOException {
        List<String> fileContent = readFile(file);
        return parseContentCategory(fileContent);
    }

    // EFFECTS: returns a list of accounts parsed from file; throws
    // IOException if an exception is raised when opening / reading from file
    public static Double readSavings(File file) throws IOException {
        List<String> fileContent = readFile(file);
        Double savings = Double.parseDouble(fileContent.get(3));
        return savings;
    }

    // EFFECTS: returns a list of accounts parsed from file; throws
    // IOException if an exception is raised when opening / reading from file
    public static Goals readGoals(File file) throws IOException {
        List<String> fileContent = readFile(file);
        return parseContentCategory(fileContent);
    }

    // EFFECTS: returns content of file as a list of strings, each string
    // containing the content of one row of the file
    private static List<String> readFile(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    // EFFECTS: returns a list of accounts parsed from list of strings
    // where each string contains data for one account
    private static List<Category> parseContentCategory(List<String> fileContent) {
        List<Category> cats = new ArrayList<>();
        String needsLine = fileContent.get(0);
        ArrayList<String> needsComponents = splitStringCategory(needsLine);
        cats.add(parseCategory(needsComponents));
        String regretsLine = fileContent.get(1);
        ArrayList<String> regretsComponents = splitStringCategory(regretsLine);
        cats.add(parseCategory(regretsComponents));
        String wantsLine = fileContent.get(2);
        ArrayList<String> wantsComponents = splitStringCategory(wantsLine);
        cats.add(parseCategory(wantsComponents));
        return cats;
    }

    // EFFECTS: returns a list of accounts parsed from list of strings
    // where each string contains data for one account
    private static List<Category> parseContentGoals(List<String> fileContent) {
        List<Category> cats = new ArrayList<>();
        String needsLine = fileContent.get(0);
        ArrayList<String> needsComponents = splitStringCategory(needsLine);
        cats.add(parseCategory(needsComponents));
        String regretsLine = fileContent.get(1);
        ArrayList<String> regretsComponents = splitStringCategory(regretsLine);
        cats.add(parseCategory(regretsComponents));
        String wantsLine = fileContent.get(2);
        ArrayList<String> wantsComponents = splitStringCategory(wantsLine);
        cats.add(parseCategory(wantsComponents));
        return cats;
    }

    // EFFECTS: returns a list of strings obtained by splitting line on DELIMITER
    private static ArrayList<String> splitString(String line) {
        String[] splits = line.split(DELIMITER);
        return new ArrayList<>(Arrays.asList(splits));
    }

    // EFFECTS: returns a list of strings obtained by splitting line on DELIMITER
    private static ArrayList<String> splitStringCategory(String line) {
        String[] splits = line.split(DELIMITER_1);
        return new ArrayList<>(Arrays.asList(splits));
    }

    // REQUIRES: components has size 4 where element 0 represents the
    // id of the next account to be constructed, element 1 represents
    // the id, elements 2 represents the name and element 3 represents
    // the balance of the account to be constructed
    // EFFECTS: returns an account constructed from components
    private static Category parseCategory(List<String> components) {
        Category cat = new Needs;
        for (String s : components) {
            ArrayList<String> lineComponents = splitStringCategory(s);
            String name = lineComponents.get(1);
            double amount = Double.parseDouble(lineComponents.get(2));
            Purchase p = new Purchase(name, amount);
            cat.addToCat(p);
        }
    }
}
