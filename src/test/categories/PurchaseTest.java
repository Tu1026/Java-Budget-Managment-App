package categories;

import categories.Purchase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PurchaseTest {
    Purchase food;
    Purchase uselessPen;
    Purchase phone;

    @BeforeEach
    public void setUp() {
        food = new Purchase("Food", 10.5);
        uselessPen = new Purchase("Pen", 1);
        phone = new Purchase("Phone", 800 );
    }

    @Test
    public void testConstructor() {
        assertEquals("Food", food.getItemName());
        assertEquals(10.5, food.getAmount());
    }

    @Test
    public void testsGetAmount() {
        assertEquals(10.5, food.getAmount());
        assertEquals(1, uselessPen.getAmount());
        assertEquals(800,phone.getAmount());
    }

    @Test
    public void testGetName() {
        assertEquals("Food", food.getItemName());
        assertEquals("Pen", uselessPen.getItemName());
        assertEquals("Phone", phone.getItemName());
    }
}