package Test;
package Models;
import org.junit.*;

public class TestSystem extends Assert {

    @Test
    public void TestPulpit() {
        EducationUnit item = new EducationUnit();
        assertTrue()
    }

    @Test
    public void factorial() {
        assertTrue(math.factorial(0) == 1);
        assertTrue(math.factorial(1) == 1);
        assertTrue(math.factorial(5) == 120);
    }

    @Ignore
    @Test
    public void todo() {
        assertTrue(math.plus(1, 1) == 3);
    }

}
