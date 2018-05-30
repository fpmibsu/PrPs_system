import Models.*;

import java.sql.*;
import java.util.ArrayList;

public class DBWrapper {
    // class to fetch some info from bd


    public void operationWithHeadInfoInfo(Menu.ChangeInfoType operationType,
                                          HeadInfo item) {

    }

    private SpecialityInfo getSpecialityInfo(Connection c, int speID){
        SpecialityInfo specialityInfo = null;
        try {
            Statement stmtInner = c.createStatement();
            ResultSet rsInner = stmtInner.executeQuery("SELECT * FROM Speciality WHERE ID = " + speID + " ;");
            rsInner.next();
            String speName = rsInner.getString("Name");
            String speShortName = rsInner.getString("ShortName");
            specialityInfo = new SpecialityInfo(speID, speName, speShortName);
            rsInner.close();
            stmtInner.close();
        } catch (Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return specialityInfo;
    }

    public FacultyInfo[] getFacultiesInfo() {

        FacultyInfo[] facult = new FacultyInfo[5];

        return facult;
    }

    public ArrayList<SpecialityInfo> getSpecialitiesWithMaxMathGrade(String year) {
        ArrayList<SpecialityInfo>  rez = new ArrayList<>();
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:db.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM Subject WHERE Name =\'Mathematics\' AND Year = \'"
                    + year + "\' AND PassingScore = (SELECT max(PassingScore) FROM Subject WHERE Name =\'Mathematics\' );" );

            while (rs.next()) {
                int speID = rs.getInt("SpeID");
                SpecialityInfo specialityInfo = getSpecialityInfo(c, speID);
                rez.add(specialityInfo);
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
                SpecialityInfo specialityInfo = getSpecialityInfo(c, speID);
                rez.add(new PlanInfo(studentNumber, year, isDaily, specialityInfo));
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

    public ArrayList<HeadInfo> getHeadsOfEducationUnit(String year) {
        ArrayList<HeadInfo> rez = new ArrayList<>();
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:db.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT *  FROM Deans WHERE AcademicDegree ='PhD'" +
                    "AND StartDate <=" + year + " AND (EndDate >= " + year + " OR EndDate is NULL) ");
            while (rs.next()) {
                String fio = rs.getString("FIO");
                String years = "from: " + rs.getString("StartDate") + " to: ";
                String end = rs.getString("EndDate");
                if (end != null){
                    years += end;
                } else {
                    years += "now";
                }
                rez.add(new HeadInfo(fio, "PhD", years));
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
