package account;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SavingsTest {
    public Savings testSave;

    @BeforeEach
    void setup() {
        testSave = new Savings();
    }

    @Test
    void testConstructor() {
        assertEquals(0, testSave.getSavings());
    }

    @Test
    void testGetSavings() {
        assertEquals(0,testSave.getSavings());
        testSave.savingTransaction(100);
        assertEquals(100,testSave.getSavings());
        testSave.savingTransaction(50);
        assertEquals(150, testSave.getSavings());
    }

    @Test
    void testSavingTransaction() {
        assertFalse(testSave.savingTransaction(-100));
        assertTrue(testSave.savingTransaction(0));
        assertEquals(0, testSave.getSavings());
        assertTrue(testSave.savingTransaction(100));
        assertTrue(testSave.savingTransaction(50.5));
        assertEquals(150.5, testSave.getSavings());
    }

    @Test
    void testSavingInterest() {
        testSave.savingInterests(0.05);
        assertEquals(0, testSave.getSavings());
        testSave.savingTransaction(100);
        testSave.savingInterests(0.05);
        assertEquals(105,testSave.getSavings());
    }
}
