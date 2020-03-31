package model;

import exception.NameNotValidException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class GoalsTest {
    public Goals testGoals;
    public Goal phone;
    public Goal car;
    public Goal textbook;

    @BeforeEach
    public void setup() {
        testGoals = new Goals();
        phone = new Goal("Phone", 800, 10);
        car = new Goal("Car", 20000, 7);
        textbook = new Goal("Textbook", 70, 0);
    }

    @Test
    public void testAddToGoals() {
        testGoals.addToGoals(phone);
        assertEquals(phone, testGoals.getIthGoal(1));
    }

    @Test
    public void testRemoveIthGoals() {
        testGoals.addToGoals(phone);
        testGoals.addToGoals(car);
        testGoals.addToGoals(textbook);
        testGoals.removeIthGoal(2);
        assertEquals(textbook, testGoals.getIthGoal(2));
    }

    @Test
    public void testGetIthGoals() {
        testGoals.addToGoals(phone);
        testGoals.addToGoals(car);
        testGoals.addToGoals(textbook);
        assertEquals(phone, testGoals.getIthGoal(1));
        assertEquals(car, testGoals.getIthGoal(2));
        assertEquals(textbook, testGoals.getIthGoal(3));
    }

    @Test
    public void testGetAllGoals() {
        testGoals.addToGoals(phone);
        testGoals.addToGoals(car);
        testGoals.addToGoals(textbook);
        assertEquals("Phone, $800.0, 10 desire points" + "\n" + "Car, $20000.0, 7 desire points"
                + "\n" + "Textbook, $70.0, 0 desire points" + "\n", testGoals.getListOfGoals());
    }

    @Test
    public void testClearGoals() {
        testGoals.addToGoals(phone);
        testGoals.addToGoals(car);
        testGoals.clearGoals();
        testGoals.addToGoals(textbook);
        assertEquals(textbook, testGoals.getIthGoal(1));
    }

    @Test
    public void testClearNthGoals() {
        testGoals.addToGoals(phone);
        testGoals.addToGoals(car);
        testGoals.clearNthGoals(1);
        assertEquals(car, testGoals.getIthGoal(1));
    }

    @Test
    public void testRemoveGiven() {
        testGoals.addToGoals(phone);
        testGoals.addToGoals(car);
        try {
            testGoals.removeGivenGoal("phone");
            assertEquals(car, testGoals.getIthGoal(1));
        } catch (NameNotValidException e) {
            fail("Should not fail here");
        }
    }

    @Test
    public void testRemoveGivenThrowException() {
        testGoals.addToGoals(phone);
        testGoals.addToGoals(car);
        try {
            testGoals.removeGivenGoal("fone");
            fail("Should throw exception");
        } catch (NameNotValidException e) {
            //should pass
        }
    }
}
