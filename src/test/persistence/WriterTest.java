package persistence;

import account.Savings;
import categories.Category;
import categories.Needs;
import categories.Regrets;
import categories.Wants;
import model.Goal;
import model.Goals;
import model.Purchase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//REFERENCE CODE FROM THE TELLER APP
class WriterTest {
    private static final String TEST_FILE = "./data/testBudgetTracker.txt";
    private Writer testWriter;
    private Category needs;
    private Category regrets;
    private Category wants;
    private Savings savings = new Savings();
    private Goals goals;
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
            List<Category> cats = Reader.readCategory(new File(TEST_FILE));
            Category needs = cats.get(0);
            assertEquals(food, needs.getNumInCat(1));
            assertEquals(uselessPen, needs.getNumInCat(2));
            assertEquals(phone, needs.getNumInCat(3));

            Category regrets = cats.get(1);
            assertEquals(food, regrets.getNumInCat(3));
            assertEquals(uselessPen, regrets.getNumInCat(1));
            assertEquals(phone, regrets.getNumInCat(2));

            Category wants = cats.get(2);
            assertEquals(food, regrets.getNumInCat(2));
            assertEquals(uselessPen, regrets.getNumInCat(3));
            assertEquals(phone, regrets.getNumInCat(1));

            assertEquals(savings, Reader.readCategory(new File(TEST_FILE)));

            Goals testGoals;
            testGoals = Reader.readGoals(new File(TEST_FILE));
            assertEquals(phones, testGoals.getIthGoal(1));
            assertEquals(car, testGoals.getIthGoal(2));
            assertEquals(textbook, testGoals.getIthGoal(3));

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }
}
