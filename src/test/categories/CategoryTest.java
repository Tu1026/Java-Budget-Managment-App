package categories;

import exception.NameNotValidException;
import model.Purchase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

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

    @Test
    public void testGetAllItemNames() {
        testCat.addToCat(food);
        testCat.addToCat(uselessPen);
        testCat.addToCat(phone);
        assertEquals("Food, $10.5" + "\n" + "Pen, $1.0" + "\n" + "Phone, $800.0" +
                "\n", testCat.getListOfPurchases());
    }

    @Test
    public void testClearList() {
        testCat.addToCat(food);
        testCat.addToCat(uselessPen);
        testCat.clearList();
        testCat.addToCat(phone);
        assertEquals(phone, testCat.getFirstInCat());
    }

    @Test
    public void testRemoveNthList() {
        testCat.addToCat(food);
        testCat.addToCat(uselessPen);
        testCat.removeNthList(2);
        assertEquals(food, testCat.getFirstInCat());
    }

    @Test
    public void testRemovedNamedItemThrowException() {
        testCat.addToCat(food);
        testCat.addToCat(uselessPen);
        try {
            testCat.removeNamedPurchase("not exist");
            fail("This name should not pass the test");
        } catch (NameNotValidException e) {
            //should pass
            assertEquals(food, testCat.getFirstInCat());
        }
    }

    @Test
    public void testRemovedNamedItemNormal() {
        testCat.addToCat(food);
        testCat.addToCat(uselessPen);
        try {
            testCat.removeNamedPurchase("food");
            assertEquals(uselessPen, testCat.getFirstInCat());
        } catch (NameNotValidException e1) {
            fail("The test should fail");
        }
    }

}
