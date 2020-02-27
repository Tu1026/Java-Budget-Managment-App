package persistence;


import categories.Category;
import model.Goals;
import org.junit.jupiter.api.Test;
import org.w3c.dom.ls.LSOutput;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;
import static persistence.Reader.*;

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
            List<String> fileContent;
            fileContent = Reader.readFile(new File("./data/testBudgetTracker1.txt"));
            List<Category> cats = new ArrayList<>();
                String needsLine = fileContent.get(0);
                ArrayList<String> needsComponents = splitString(needsLine);
                cats.add(parseCategory(needsComponents));
                fail("Should be out of bounds");
        } catch (IOException e) {
            fail("not expecting this exception");
        } catch (IndexOutOfBoundsException e) {
            // expected
        }
    }

    @Test
    void testExceptionParseContentGoals() {
        try {
            List<String> fileContent;
            fileContent = Reader.readFile(new File("./data/testBudgetTracker1.txt"));
            Goals goals = new Goals();
                String goalsList = fileContent.get(4);
                ArrayList<String> goalsComponents = splitString(goalsList);
                goals = parseGoal(goalsComponents);
            fail("Should be out of bounds");
        } catch (IOException e) {
            fail("not expecting this exception");
        } catch (IndexOutOfBoundsException e) {
            // expected
        }
    }
}
