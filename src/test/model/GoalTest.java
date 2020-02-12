package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GoalTest {
    public Goal phone;
    public Goal car;
    public Goal textbook;

    @BeforeEach
    public void setup() {
        phone = new Goal("phone", 800, 10);
        car = new Goal("car", 20000, 7);
        textbook = new Goal("textbook", 70, 0);
    }

    @Test
    public void testConstructor() {
        assertEquals("phone", phone.getName());
        assertEquals(800, phone.getPrice());
        assertEquals(10, phone.getDesire());
    }

    @Test
    public void testGetName() {
        assertEquals("phone", phone.getName());
        assertEquals("car", car.getName());
        assertEquals("textbook", textbook.getName());
    }

    @Test
    public void testGetPrice() {
        assertEquals(800, phone.getPrice());
        assertEquals(20000, car.getPrice());
        assertEquals(70, textbook.getPrice());
    }

    @Test
    public void testGetDesire() {
        assertEquals(10, phone.getDesire());
        assertEquals(7, car.getDesire());
        assertEquals(0, textbook.getDesire());
    }

}
