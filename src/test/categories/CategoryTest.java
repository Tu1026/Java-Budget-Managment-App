package categories;

import exception.NameNotValidException;
import exception.NotInTheListException;
import exception.NothingInFirstOfCatException;
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
        Purchase assertObject1 = null;
        Purchase assertObject2 = null;
        try {
            assertObject1 = testCat.getFirstInCat();
        } catch (NothingInFirstOfCatException e) {
            fail("Should have something here");
        }
        try {
            assertObject2 = testCat.getFirstInCat();
        } catch (NothingInFirstOfCatException e) {
            fail("Should have something here");
        }
        assertEquals(food, assertObject1);
        testCat.addToCat(uselessPen);
        assertEquals(food, assertObject2);
        assertEquals(uselessPen, testCat.getNumInCat(2));
    }

    @Test
    public void testGetFirstInCatThrowException() {
        try {
            testCat.getFirstInCat();
            fail("Should throw exception");
        } catch (NothingInFirstOfCatException e) {
            //should catch here
        }
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
    public void testEmptyListOfPurchase() {
        assertEquals("", testCat.getListOfPurchases());
    }

    @Test
    public void testClearList() {
        testCat.addToCat(food);
        testCat.addToCat(uselessPen);
        testCat.clearList();
        testCat.addToCat(phone);
        Purchase assertObject1 = null;
        try {
            assertObject1 = testCat.getFirstInCat();
        } catch (NothingInFirstOfCatException e) {
            fail("Should have something here");
        }
        assertEquals(phone, assertObject1);
    }

    @Test
    public void testRemoveNthListNoException() {
        testCat.addToCat(food);
        testCat.addToCat(uselessPen);
        try {
            testCat.removeNthList(2);
            Purchase assertObject1 = null;
            try {
                assertObject1 = testCat.getFirstInCat();
            } catch (NothingInFirstOfCatException e) {
                fail("Should have something here");
            }
            assertEquals(food, assertObject1);
        } catch (NotInTheListException e) {
            fail("This should not be thrown");
        }
    }

    @Test
    public void testRemoveNthListThrowException() {
        testCat.addToCat(food);
        testCat.addToCat(uselessPen);
        try {
            testCat.removeNthList(3);
            fail("Exception should have been thrown here");
        } catch (NotInTheListException e) {
            //Should throw exception
        }
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
        }
    }

    @Test
    public void testRemovedNamedItemNormal() {
        testCat.addToCat(food);
        testCat.addToCat(uselessPen);
        try {
            testCat.removeNamedPurchase("food");
            Purchase assertObject1 = null;
            try {
                assertObject1 = testCat.getFirstInCat();
            } catch (NothingInFirstOfCatException e) {
                fail("Should have something here");
            }
            assertEquals(uselessPen, assertObject1);
        } catch (NameNotValidException e1) {
            fail("The name should be valid");
        }
    }

    @Test
    public void testGetNumInCatThrowException() {
        testCat.addToCat(food);
        try {
            testCat.getNumInCat(100);
            fail("Should catch the exception");
        } catch (IndexOutOfBoundsException e) {
            //Should pass
        }
    }

}
