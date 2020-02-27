package ui;

import account.Savings;
import categories.Category;
import categories.Needs;
import categories.Regrets;
import categories.Wants;
import model.Goal;
import model.Goals;
import model.Purchase;
import persistence.Reader;
import persistence.Writer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

//Budget tracker application　(Reference from the UI code in the tellerApp)
public class BudgetTracker {
    private static final String BUDGET_FILE = "./data/budget.txt";
    private Category needs;
    private Category regrets;
    private Category wants;
    private Savings savings;
    private Goals goals;
    private Scanner input;

    //EFFECTS: run the application budget tracker
    public BudgetTracker() {
        runBudgetTracker();
    }

    //Reference from the tellerApp class code
    //EFFECTS: Start the main menu of the application and take user inputs
    public void runBudgetTracker() {
        boolean keepGoing = true;
        String command;
        input = new Scanner(System.in);
        needs = new Needs();
        regrets = new Regrets();
        wants = new Wants();
        savings = new Savings();
        goals = new Goals();

        loadAccounts();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: loads categories, savings, and goals from BUDGET_FILE, if that file exists;
    // otherwise initializes accounts with default values
    private void loadAccounts() {
        try {
            needs = Reader.readNeeds(new File(BUDGET_FILE));
            regrets = Reader.readRegrets(new File(BUDGET_FILE));
            wants = Reader.readWants(new File(BUDGET_FILE));
            savings = Reader.readSavings(new File(BUDGET_FILE));
            goals = Reader.readGoals(new File(BUDGET_FILE));
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

    //Reference code from teller app
    // EFFECTS: saves state of Categories, Savings, and Goals to BUDGET_FILE
    private void saveAccounts() {
        try {
            Writer writer = new Writer(new File(BUDGET_FILE));
            writer.write(needs);
            writer.write(regrets);
            writer.write(wants);
            writer.write(savings);
            writer.write(goals);
            writer.close();
            System.out.println("Purchases, Savings and Goals are saved to " + BUDGET_FILE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save to " + BUDGET_FILE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            // this is due to a programming error
        }
    }

    //Reference from the teller app
    //EFFECTS: Display a list of input options a user can choose from
    public void displayMenu() {
        System.out.println("\nSelect from");
        System.out.println("\tp -> Add a new purchase");
        System.out.println("\ts -> Put money in your savings");
        System.out.println("\tg -> Add a goal");
        System.out.println("\tc -> Check categories");
        System.out.println("\ta -> Check all goals");
        System.out.println("\tm -> Check money in savings");
        System.out.println("\tr -> Save everything about the budget tracker to file");
        System.out.println("\to -> Empty all goals");
    }

    //Reference from the teller app
    //EFFECTS: Takes the user to different functionality according to input command
    public void processCommand(String command) {
        if (command.equals("p")) {
            doPurchase();
        } else if (command.equals("s")) {
            doSavings();
        } else if (command.equals("g")) {
            doAddGoals();
        } else if (command.equals("c")) {
            doCategory();
        } else if (command.equals("a")) {
            doCheckAllGoals();
        } else if (command.equals("m")) {
            doDisplaySavings();
        } else if (command.equals("r")) {
            saveAccounts();
        } else if (command.equals("o")) {
            doClearAllGoals();
        } else {
            System.out.println("Invalid input");
        }
    }

    //MODIFIES: This
    //EFFECTS: Empty all the goals in the list of goals
    public void doClearAllGoals() {
        goals.clearGoals();
    }

    //EFFECTS: Prints the current balance in user's saving account
    public void doCheckAllGoals() {
        System.out.println("Your goals are" + "\n" + goals.getAllGoals());
    }

    //MODIFIES: This
    //EFFECT: Add new purchases with the given properties from the user and add it to one of the three categories
    public void doPurchase() {
        System.out.println("What did you buy!?");
        input.nextLine();
        String name = input.nextLine();
        System.out.println("How much was it?");
        double amount = input.nextDouble();
        System.out.println("What category does it belong? (Needs, Regrets, Wants)");
        String category = input.next();

        category = category.toLowerCase();
        Purchase newPurchase = new Purchase(name, amount);
        switch (category) {
            case "needs":
                needs.addToCat(newPurchase);
                break;
            case "regrets":
                regrets.addToCat(newPurchase);
                break;
            case "wants":
                wants.addToCat(newPurchase);
                break;
            default:
                System.out.println("Invalid input!");
                break;
        }
    }

    //MODIFIES: This
    //EFFECTS: Add money to the savings and increase the money in the savings according to interest rate
    public void doSavings() {
        System.out.println("How much money are you saving this time?");
        double amount = input.nextDouble();
        savings.savingTransaction(amount);
        System.out.println("What is the interest rate? (given in decimal)");
        double interest = input.nextDouble();
        savings.savingInterests(interest);
    }

    //MODIFIES:This
    //EFFECTS: Add a goal with given properties from the user to a list of goals
    public void doAddGoals() {
        System.out.println("What is a financial goal you have");
        input.nextLine();
        String name = input.nextLine();
        System.out.println("How much does it cost?");
        double cost = input.nextDouble();
        System.out.println("On a scale of 1 - 10 how much do you want it?");
        int desire = input.nextInt();
        if ((desire > 10) | (desire < 0)) {
            System.out.println("Too much or too less desire!!");
        } else {
            Goal newGoal = new Goal(name, cost, desire);
            goals.addToGoals(newGoal);
        }
    }

    //EFFECTS: Take user to different functionality of about category according to their input
    public void doCategory() {
        System.out.println("\nSelect from");
        System.out.println("\ts -> Check total money spent in a category");
        System.out.println("\tp -> List all purchases in a category");
        System.out.println("\tc -> Clear all purchases in a given category");
        String code = input.next();
        switch (code) {
            case "s":
                doCategorySum();
                break;
            case "p":
                doCategoryList();
                break;
            case "c":
                doCategoryClear();
                break;
            default:
                System.out.println("Invalid input!!");
                break;
        }
    }

    //MODIFIES: This
    //EFFECTS: Clears everything in a category
    public void doCategoryClear() {
        System.out.println("\nSelect from");
        System.out.println("\tn -> Clear all purchases in need");
        System.out.println("\tr -> Clear all purchases in regrets");
        System.out.println("\tw -> Clear all purchases in wants");
        String code = input.next();
        switch (code) {
            case "n":
                needs.clearList();
                System.out.println("All purchases in needs cleared");
                break;
            case "r":
                regrets.clearList();
                System.out.println("All purchases in regrets cleared");
                break;
            case "w":
                wants.clearList();
                System.out.println("All purchases in wants cleared");
                break;
            default:
                System.out.println("Invalid input");
                break;
        }
    }

    //EFFECTS: Return the total money spent in respective category given user input
    public void doCategorySum() {
        System.out.println("\nSelect from");
        System.out.println("\tn -> Check total money spent in need");
        System.out.println("\tr -> Check total money spent in regrets");
        System.out.println("\tw -> Check total money spent in wants");
        String code = input.next();
        switch (code) {
            case "n":
                System.out.println(needs.sumOfCat());
                break;
            case "r":
                System.out.println(regrets.sumOfCat());
                break;
            case "w":
                System.out.println(wants.sumOfCat());
                break;
            default:
                System.out.println("Invalid input");
                break;
        }
    }

    //EFFECTS: Return all the purchases made in a given category given a user input
    public void doCategoryList() {
        System.out.println("\nSelect from");
        System.out.println("\tn -> Check all purchases in need");
        System.out.println("\tr -> Check all purchases in regrets");
        System.out.println("\tw -> Check all purchases in wants");
        String code = input.next();
        switch (code) {
            case "n":
                System.out.println("All purchases are" + needs.getListOfPurchases());
                break;
            case "r":
                System.out.println("All purchases are" + regrets.getListOfPurchases());
                break;
            case "w":
                System.out.println("All purchases are" + wants.getListOfPurchases());
                break;
            default:
                System.out.println("Invalid input");
                break;
        }
    }

    //EFFECTS: Return the amount of money the user has in the savings
    public void doDisplaySavings() {
        double saving = savings.getSavings();
        String savingString = Double.toString(saving);

        System.out.println("You have " + savingString + "$ in your savings");
    }
}

