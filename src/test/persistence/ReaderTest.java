package persistence;


import org.junit.jupiter.api.Test;
import org.w3c.dom.ls.LSOutput;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.fail;

public class ReaderTest {

    @Test
    void testIOExceptionCat() {
        try {
            Reader.readCategory(new File("./path/does/not/exist/testAccount.txt"));
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
    void testOutOfBoundsExceptionParseContentCategory() {
        try {
            Reader.readCategory(new File("./data/testBudgetTracker1.txt"));
        } catch (IOException e) {
            fail("not expecting this exception");
        } catch (IndexOutOfBoundsException e) {
            // expected
        }
    }

    @Test
    void testExceptionParseContentGoals() {
        try {
            Reader.readGoals(new File("./data/testBudgetTracker1.txt"));
        } catch (IOException e) {
            fail("not expecting this exception");
        } catch (IndexOutOfBoundsException e) {
            // expected
        }
    }
}
