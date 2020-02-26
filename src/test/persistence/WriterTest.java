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
    private Savings savings;
    private Goals goals;
    private Purchase food;
    private Purchase uselessPen;
    private Purchase phone;
    private Goal phones;
    private Goal car;
    private Goal textbook;

    @BeforeEach
    void runBefore() throws FileNotFoundException, UnsupportedEncodingException {
        testWriter = new Writer(new File(TEST_FILE));
        food = new Purchase("Food", 10.5);
        uselessPen = new Purchase("Pen", 1);
        phone = new Purchase("Phone", 800 );
        phones = new Goal("Phones", 800, 10);
        car = new Goal("Car", 20000, 7);
        textbook = new Goal("Textbook", 70, 0);
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
        // save chequing and savings accounts to file
        testWriter.write(chequing);
        testWriter.write(savings);
        testWriter.close();

        // now read them back in and verify that the accounts have the expected values
        try {
            List<Account> accounts = Reader.readAccounts(new File(TEST_FILE));
            Account chequing = accounts.get(0);
            assertEquals(1, chequing.getId());
            assertEquals("Mae", chequing.getName());
            assertEquals(123.56, chequing.getBalance());

            Account savings = accounts.get(1);
            assertEquals(2, savings.getId());
            assertEquals("Jo", savings.getName());
            assertEquals(435.23, savings.getBalance());

            // verify that ID of next account created is 3 (checks that nextAccountId was restored)
            Account next = new Account("Chris", 0.00);
            assertEquals(3, next.getId());
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }
}
