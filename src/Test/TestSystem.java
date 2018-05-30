package Test;
package Models;
import org.junit.*;

public class TestSystem extends Assert {

    @Test
    public void TestPulpit() {

        HeadInfo[] heads = HeadInfo[4];
        SpecialityInfo[] specs = SpecialityInfo[4];

        EducationUnit item = new EducationUnit(12,
        "Test",
        "T",
        heads,
        specs,
        "address",
        "telephone",
        "site");
        assertEquals(item.getHeads(), heads, "The heads the same");
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
