package ui;

import account.Savings;
import categories.Category;
import categories.Needs;
import categories.Regrets;
import categories.Wants;
import com.sun.xml.internal.ws.api.ha.StickyFeature;
import exceptions.DesireNotValidException;
import model.Goal;
import model.Goals;
import model.Purchase;

import java.util.Scanner;

//Budget tracker application　(Reference from the UI code in the tellerApp)
public class BudgetTracker {
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
    public void runBudgetTracker() {
        boolean keepGoing = true;
        String command = null;
        input = new Scanner(System.in);
        needs = new Needs();
        regrets = new Regrets();
        wants = new Wants();
        savings = new Savings();
        goals = new Goals();

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

    public void displayMenu() {
        System.out.println("\nSelect from");
        System.out.println("\tp -> add a new purchase");
        System.out.println("\ts -> put money in your savings");
        System.out.println("\tg -> add a goal");
    }

    public void processCommand(String command) {
        if (command.equals("p")) {
            doPurchase();
        } else if (command.equals("s")) {
            doSavings();
        } else if (command.equals("g")) {
            try {
                doAddGoals();
            } catch (DesireNotValidException e) {
                System.out.println("Input desire not valid!!");
            }
        } else {
            System.out.println("Invalid input");
        }
    }

    public void doPurchase() {
        System.out.println("What did you buy!?");
        input.nextLine();
        String name = input.next();
        System.out.println("How much was it?");
        double amount = input.nextDouble();
        System.out.println("What category does it belong? (Needs, Regrets, Wants)");
        String category = input.next();

        category.toLowerCase();
        Purchase newPurchase = new Purchase(name, amount);
        if (category.equals("needs")) {
            needs.addToCat(newPurchase);
        } else if (category.equals("regrets")) {
            regrets.addToCat(newPurchase);
        } else if (category.equals("wants")) {
            wants.addToCat(newPurchase);
        } else {
            System.out.println("Invalid input!");
        }
    }

    public void doSavings() {
        System.out.println("How much money are you saving this time?");
        Double amount = input.nextDouble();
        savings.savingTransaction(amount);
        System.out.println("What is the interest rate? (given in decimal)");
        Double interest = input.nextDouble();
        savings.savingInterests(interest);
    }

    public void doAddGoals() throws DesireNotValidException {
        System.out.println("What is a financial goal you have");
        String goal = input.next();
        System.out.println("How much does it cost?");
        Double cost = input.nextDouble();
        System.out.println("On a scale of 1 - 10 how much do you want it?");
        int desire = input.nextInt();
        if ((desire > 10) | (desire < 0)) {
            throw new DesireNotValidException();
        }
    }
}

