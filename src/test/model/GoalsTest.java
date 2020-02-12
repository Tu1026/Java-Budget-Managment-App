package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GoalsTest {
    public Goals testGoals;
    public Goal phone;
    public Goal car;
    public Goal textbook;

    @BeforeEach
    public void setup() {
        testGoals = new Goals();
        phone = new Goal("phone", 800, 10);
        car = new Goal("car", 20000, 7);
        textbook = new Goal("textbook", 70, 0);
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
        assertEquals(car,testGoals.getIthGoal(2));
        assertEquals(textbook, testGoals.getIthGoal(3));
    }


}
