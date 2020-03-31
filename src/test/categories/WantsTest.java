package categories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WantsTest extends CategoryTest {

    @BeforeEach
    public void setUp() {
        testCat = new Wants();
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
