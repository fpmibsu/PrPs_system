import Models.EducationUnit;
import Models.FacultyInfo;
import Models.HeadInfo;
import Models.SpecialityInfo;
import java.sql.*;

public class DBWrapper {
    // class to fetch some info from bd


    public void operationWithHeadInfoInfo(Menu.ChangeInfoType operationType,
                                          HeadInfo item) {

    }

    public FacultyInfo[] getFacultiesInfo() {

        FacultyInfo[] facult = new FacultyInfo[5];

        return facult;
    }

    public SpecialityInfo[] getSpecialitiesWithMaxMathGrade(String year) {
        SpecialityInfo[] rez = new SpecialityInfo[5];

        return rez;
    }


    public SpecialityInfo[] getSpecialitiesWithPlan(String year,Boolean isDaily) {
        SpecialityInfo[] rez = new SpecialityInfo[5];

        return rez;
    }

    public EducationUnit[] getHeadsOfEducationUnit() {
        EducationUnit[] rez = new EducationUnit[5];

        return rez;
    }

    public static void simple() {
        Connection c = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }

}
