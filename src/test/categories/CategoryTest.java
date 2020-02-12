package categories;

import model.Purchase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class CategoryTest {
    public Category testCat;
    Purchase food;
    Purchase uselessPen;
    Purchase phone;

    @BeforeEach
    public void samplePurchases() {
        food = new Purchase("Food", 10.5);
        uselessPen = new Purchase("Pen", 1);
        phone = new Purchase("Phone", 800 );
    }


    @Test
    public void testAddToCatAndGetFirstInCat() {
        testCat.addToCat(food);
        assertEquals(food, testCat.getFirstInCat());
        testCat.addToCat(uselessPen);
        assertEquals(food, testCat.getFirstInCat());
        assertEquals(uselessPen, testCat.getNumInCat(2));
    }

    @Test
    public void testGetNumInClass() {
        testCat.addToCat(food);
        testCat.addToCat(uselessPen);
        testCat.addToCat(phone);
        assertEquals(food, testCat.getNumInCat(1));
        assertEquals(uselessPen, testCat.getNumInCat(2));
        assertEquals(phone, testCat.getNumInCat(3));
    }

    @Test
    public void testSumOfCat() {
        assertEquals(0, testCat.sumOfCat());
        testCat.addToCat(food);
        assertEquals(10.5, testCat.sumOfCat());
        testCat.addToCat(phone);
        assertEquals(810.5,testCat.sumOfCat());
    }


}
