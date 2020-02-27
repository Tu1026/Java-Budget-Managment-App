package persistence;


import categories.Category;
import categories.Needs;
import model.Goals;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ReaderTest {

    @Test
    void testIOExceptionNeeds() {
        try {
            Reader.readNeeds(new File("./path/does/not/exist/testAccount.txt"));
        } catch (IOException e) {
            // expected
        }
    }

    @Test
    void testIOExceptionRegrets() {
        try {
            Reader.readRegrets(new File("./path/does/not/exist/testAccount.txt"));
        } catch (IOException e) {
            // expected
        }
    }

    @Test
    void testIOExceptionWants() {
        try {
            Reader.readWants(new File("./path/does/not/exist/testAccount.txt"));
        } catch (IOException e) {
            // expected
        }
    }

    @Test
    void testIOExceptionSavings() {
        try {
            Reader.readSavings(new File("./path/does/not/exist/testAccount.txt"));
        } catch (IOException e) {
            // expected
        }
    }

    @Test
    void testIOExceptionGoals() {
        try {
            Reader.readGoals(new File("./path/does/not/exist/testAccount.txt"));
        } catch (IOException e) {
            // expected
        }
    }

    @Test
    void testConstructor() {
        new Reader();
    }

    @Test
    void testOutOfBoundsExceptionParseContentNeeds() {
        List<String> fileContent;
        try {
            fileContent = Reader.readFile(new File("./data/testBudgetTracker1.txt"));
            Reader.parseContentNeeds(fileContent);
            fail("Should be out of bounds");
        } catch (IOException e) {
            fail("not expecting this exception");
        } catch (IndexOutOfBoundsException e) {
            // expected
        }
    }

    @Test
    void testOutOfBoundsExceptionParseContentRegrets() {
        List<String> fileContent;
        try {
            fileContent = Reader.readFile(new File("./data/testBudgetTracker1.txt"));
            Reader.parseContentRegrets(fileContent);
            fail("Should be out of bounds");
        } catch (IOException e) {
            fail("not expecting this exception");
        } catch (IndexOutOfBoundsException e) {
            // expected
        }
    }

    @Test
    void testOutOfBoundsExceptionParseContentWants() {
        List<String> fileContent;
        try {
            fileContent = Reader.readFile(new File("./data/testBudgetTracker1.txt"));
            Reader.parseContentWants(fileContent);
            fail("Should be out of bounds");
        } catch (IOException e) {
            fail("not expecting this exception");
        } catch (IndexOutOfBoundsException e) {
            // expected
        }
    }

    @Test
    void testExceptionParseContentGoals() {
        List<String> fileContent;
        try {
            fileContent = Reader.readFile(new File("./data/testBudgetTracker1.txt"));
            Reader.parseContentGoals(fileContent);
            fail("Should be out of bounds");
        } catch (IOException e) {
            fail("not expecting this exception");
        } catch (IndexOutOfBoundsException e) {
            // expected
        }
    }

    @Test
    void testReadNeedsCaughtException() {
        Category needs= new Needs();
        try {
           needs = Reader.readNeeds(new File("./data/testBudgetTracker1.txt"));
        } catch (IOException e) {
            fail("should not happen");
        } catch (IndexOutOfBoundsException e) {
            fail("Should've been caught before");
        }
    }

    @Test
    void testReadRegretsCaughtException() {
        Category regrets = new Needs();
        try {
            regrets = Reader.readRegrets(new File("./data/testBudgetTracker1.txt"));
        } catch (IOException e) {
            fail("should not happen");
        } catch (IndexOutOfBoundsException e) {
            fail("Should've been caught before");
        }
    }

    @Test
    void testReadWantsCaughtException() {
        Category wants = new Needs();
        try {
            wants = Reader.readWants(new File("./data/testBudgetTracker1.txt"));
        } catch (IOException e) {
            fail("should not happen");
        } catch (IndexOutOfBoundsException e) {
            fail("Should've been caught before");
        }
    }

    @Test
    void testReadGoalsCaughtException() {
        Goals goals = new Goals();
        try {
            goals = Reader.readGoals(new File("./data/testBudgetTracker1.txt"));
        } catch (IOException e) {
            fail("should not happen");
        } catch (IndexOutOfBoundsException e) {
            fail("Should've been caught before");
        }
    }
}
