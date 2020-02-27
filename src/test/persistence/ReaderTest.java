package persistence;


import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

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
}
