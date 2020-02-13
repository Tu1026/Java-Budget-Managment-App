package ui;

import account.Savings;
import categories.Category;
import categories.Needs;
import categories.Regrets;
import categories.Wants;
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
    // EFFECTS: Start the main menu of the application and take user inputs
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
        System.out.println("\tc -> check categories");
    }

    public void processCommand(String command) {
        if (command.equals("p")) {
            doPurchase();
        } else if (command.equals("s")) {
            doSavings();
        } else if (command.equals("g")) {
            doAddGoals();
        } else if (command.equals("c")) {
            doCategory();
        } else {
            System.out.println("Invalid input");
        }
    }

    public void doPurchase() {
        System.out.println("What did you buy!?");
        input.nextLine();
        String name = input.nextLine();
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

    public void doAddGoals() {
        System.out.println("What is a financial goal you have");
        input.nextLine();
        String name = input.nextLine();
        System.out.println("How much does it cost?");
        Double cost = input.nextDouble();
        System.out.println("On a scale of 1 - 10 how much do you want it?");
        int desire = input.nextInt();
        if ((desire > 10) | (desire < 0)) {
            System.out.println("Too much or too less desire!!");
        } else {
            Goal newGoal = new Goal(name, cost, desire);
            goals.addToGoals(newGoal);
        }
    }

    public void doCategory() {
        System.out.println("\nSelect from");
        System.out.println("\ts -> Check total money spent in a category");
        System.out.println("\tp -> List all purchases in a category");
        System.out.println("\tr -> Remove all items in a category");
        String code = input.next();
        if (code.equals("s")) {
            doCategorySum();
        } else if (code.equals("p")) {
            doCategoryList();
        }
    }

    public void doCategorySum() {
        System.out.println("\nSelect from");
        System.out.println("\tn -> Check total money spent in need");
        System.out.println("\tr -> Check total money spent in regrets");
        System.out.println("\tw -> Check total money spent in wants");
        String code = input.next();
        if (code.equals("n")) {
            System.out.println(needs.sumOfCat());
        } else if (code.equals("r")) {
            System.out.println(regrets.sumOfCat());
        } else if (code.equals("w")) {
            System.out.println(wants.sumOfCat());
        } else {
            System.out.println("Invalid input");
        }
    }

    public void doCategoryList() {
        System.out.println("\nSelect from");
        System.out.println("\tn -> Check all purchases in need");
        System.out.println("\tr -> Check all purchases in regrets");
        System.out.println("\tw -> Check all purchases in wants");
        String code = input.next();
        if (code.equals("n")) {
            System.out.println("All purchases are" + needs.getListOfPurchases());
        } else if (code.equals("r")) {
            System.out.println("All purchases are" + regrets.getListOfPurchases());
        } else if (code.equals("w")) {
            System.out.println("All purchases are" + wants.getListOfPurchases());
        } else {
            System.out.println("Invalid input");
        }
    }
}

