package persistence;

import account.Savings;
import categories.Category;
import categories.Needs;
import categories.Regrets;
import categories.Wants;
import model.Goal;
import model.Goals;
import categories.Purchase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.*;

//REFERENCE CODE FROM THE TELLER APP
class WriterTest {
    private static final String TEST_FILE = "./data/testBudgetTracker.txt";
    private Writer testWriter;
    private Category needs;
    private Category regrets;
    private Category wants;
    private Savings savings = new Savings();
    private Goals goals = new Goals();
    Purchase food = new Purchase("Food", 10.5);
    Purchase uselessPen = new Purchase("Pen", 1);
    Purchase phone = new Purchase("Phone", 800);
    Goal phones = new Goal("Phones", 800, 10);
    Goal car = new Goal("Car", 20000, 7);
    Goal textbook = new Goal("Textbook", 70, 0);

    @BeforeEach
    void runBefore() throws FileNotFoundException, UnsupportedEncodingException {
        testWriter = new Writer(new File(TEST_FILE));
        needs = new Needs();
        needs.addToCat(food);
        needs.addToCat(uselessPen);
        needs.addToCat(phone);
        regrets = new Regrets();
        regrets.addToCat(uselessPen);
        regrets.addToCat(phone);
        regrets.addToCat(food);
        wants = new Wants();
        wants.addToCat(phone);
        wants.addToCat(food);
        wants.addToCat(uselessPen);
        savings.savingTransaction(2500);
        goals.addToGoals(phones);
        goals.addToGoals(car);
        goals.addToGoals(textbook);
    }

    @Test
    void testWriteAccounts() {
        // save needs, regrets, wants, savings, and goals to file
        testWriter.write(needs);
        testWriter.write(regrets);
        testWriter.write(wants);
        testWriter.write(savings);
        testWriter.write(goals);
        testWriter.close();

        // now read them back in and verify that the accounts have the expected values
        try {
            assertEquals(savings.getSavings(),(Reader.readSavings(new File(TEST_FILE)).getSavings()));

            Goals testGoals;
            testGoals = Reader.readGoals(new File(TEST_FILE));
            assertEquals(phones.getName(), testGoals.getIthGoal(1).getName());
            assertEquals(phones.getPrice(), testGoals.getIthGoal(1).getPrice());
            assertEquals(phones.getDesire(), testGoals.getIthGoal(1).getDesire());
            assertEquals(car.getName(), testGoals.getIthGoal(2).getName());
            assertEquals(car.getPrice(), testGoals.getIthGoal(2).getPrice());
            assertEquals(car.getDesire(), testGoals.getIthGoal(2).getDesire());
            assertEquals(textbook.getName(), testGoals.getIthGoal(3).getName());
            assertEquals(textbook.getPrice(), testGoals.getIthGoal(3).getPrice());
            assertEquals(textbook.getDesire(), testGoals.getIthGoal(3).getDesire());

            Category needs = Reader.readNeeds(new File(TEST_FILE));
            assertEquals(food.getItemName(), needs.getNumInCat(1).getItemName());
            assertEquals(food.getAmount(), needs.getNumInCat(1).getAmount());
            assertEquals(uselessPen.getItemName(), needs.getNumInCat(2).getItemName());
            assertEquals(uselessPen.getAmount(), needs.getNumInCat(2).getAmount());
            assertEquals(phone.getItemName(), needs.getNumInCat(3).getItemName());
            assertEquals(phone.getAmount(), needs.getNumInCat(3).getAmount());

            Category regrets = Reader.readRegrets(new File(TEST_FILE));
            assertEquals(food.getItemName(), regrets.getNumInCat(3).getItemName());
            assertEquals(food.getAmount(), regrets.getNumInCat(3).getAmount());
            assertEquals(uselessPen.getItemName(), regrets.getNumInCat(1).getItemName());
            assertEquals(uselessPen.getAmount(), regrets.getNumInCat(1).getAmount());
            assertEquals(phone.getItemName(), regrets.getNumInCat(2).getItemName());
            assertEquals(phone.getAmount(), regrets.getNumInCat(2).getAmount());

            Category wants = Reader.readWants(new File(TEST_FILE));
            assertEquals(food.getItemName(), wants.getNumInCat(2).getItemName());
            assertEquals(food.getAmount(), wants.getNumInCat(2).getAmount());
            assertEquals(uselessPen.getItemName(), wants.getNumInCat(3).getItemName());
            assertEquals(uselessPen.getAmount(), wants.getNumInCat(3).getAmount());
            assertEquals(phone.getItemName(), wants.getNumInCat(1).getItemName());
            assertEquals(phone.getAmount(), wants.getNumInCat(1).getAmount());

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }
}
