import Models.*;

import java.sql.*;
import java.util.ArrayList;

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


    public ArrayList<PlanInfo> getSpecialitiesWithPlan(String year, Boolean isDaily) {
        ArrayList<PlanInfo>  rez = new ArrayList<>();
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:db.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM Plans WHERE Year =\'" +
                    year + "\'AND  Daily = \'" + isDaily + "\' ;" );

            while (rs.next()) {
                int studentNumber = rs.getInt("NumberStudents");
                int speID = rs.getInt("SpeID");
                Statement stmtInner = c.createStatement();
                ResultSet rsInner = stmtInner.executeQuery( "SELECT * FROM Speciality WHERE ID = " + speID + " ;" );
                rsInner.next();
                String speName  = rsInner.getString("Name");
                String speShortName = rsInner.getString("ShortName");
                SpecialityInfo specialityInfo = new SpecialityInfo(speID, speName, speShortName);

                rez.add(new PlanInfo(studentNumber, year, isDaily, specialityInfo));
                rsInner.close();
                stmtInner.close();
            }
            rs.close();
            stmt.close();
            c.close();

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
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
