package Test;

import Models.EducationUnit;
import Models.*;
import org.junit.*;

public class TestSystem extends Assert {

    @Test
    public void TestPulpit() {

        HeadInfo[] heads = new HeadInfo[4];
        SpecialityInfo[] specs = new SpecialityInfo[4];

        EducationUnit item = new EducationUnit(12,
        "Test",
        "T",
                heads,
                specs,
        "address",
        "telephone",
        "site");
        assertTrue("The heads the same", item.getHeads().equals(heads));
    }

    @Test
    public void HeadInfo() {
        HeadInfo head = new HeadInfo("fio", "PhD", "2015", null);

        assertEquals("The fio the same","fio",  head.getFIO());
        assertEquals("The degree the same", "PhD", head.getDegree() );
        assertEquals("The years the same","2015",  head.getYears());
    }

    @Test
    public void TestFaculties() {
        FacultyInfo[] tt =  new FacultyInfo[3];
        assertTrue("The Faculties the same", tt.equals(tt));
    }

    @Ignore
    @Test
    public void todo1() {
       
    }

    @Ignore
    @Test
    public void todo() {
        
    }

}
