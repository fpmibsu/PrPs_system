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
    public void HeadInfo() {
        HeadInfo heads = HeadInfo("fio", "PhD", "2015");
        assertEquals(item.getFIO(), "fio", "The fio the same");
        assertEquals(item.getDegree(), "PhD", "The degree the same");
        assertEquals(item.getYears(), "2015", "The years the same");
    }

    @Ignore
    @Test
    public void todo() {
        
    }

}
