package weight5;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class HelloTest {
    Hello obj;

    @Test
    public void testAdd_twoPositiveIntegers() {
        obj = new Hello();
        int expected = 9;
        int actual = obj.add(5, 4);
        //testing
        assertTrue(expected == actual);  // this is a valid comparison
    }
}
