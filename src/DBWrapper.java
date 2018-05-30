import Models.*;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class DBWrapper {
    // class to fetch some info from bd

    private String getTableNameByInfoType(Menu.InfoType type){
        switch(type){
            case facult:{
                return "Faculty";
            }
            case head: {
                return "Deans";
            }
            default: {
                return "Pulpit";
            }
        }
    }

    public Boolean deleteSomeInfo(String code, Menu.InfoType type) {
        String sql = "DELETE FROM ";
        sql += getTableNameByInfoType(type) + " WHERE ID = " + code + ";";
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:db.db");
            c.setAutoCommit(true);
            stmt = c.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            return false;
        }
        return true;
    }


    public Boolean operationWithHeadInfo(Menu.ChangeInfoType operationType,
                                          HeadInfo item) {
        switch(operationType){
            case add:{
                Connection c = null;
                Statement stmt = null;
                try {
                    int ID = -1;
                    Class.forName("org.sqlite.JDBC");
                    c = DriverManager.getConnection("jdbc:sqlite:db.db");
                    c.setAutoCommit(true);
                    stmt = c.createStatement();
                    ResultSet rs = stmt.executeQuery( "select ID from Faculty WHERE NAME = '" + item.getFaculty() +
                            "' collate nocase;");
                    while (rs.next()) {
                        ID = rs.getInt("ID");
                    }
                    rs.close();
                    if (ID != -1){
                        int year = LocalDate.now().getYear();
                        String sqlUpdate = "UPDATE Deans set EndDate = '" + year + "' where FacID = " + ID + ";";
                        stmt.executeUpdate(sqlUpdate);
                        String sql = "INSERT INTO Deans (FIO,StartDate,AcademicDegree, FacID) VALUES('" +
                                item.getFIO() + "','" + year +
                                "','" + item.getDegree() + "','" + ID + "');";
                        stmt.executeUpdate(sql);
                    }
                    stmt.close();
                    c.close();

                } catch ( Exception e ) {
                    System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                    return false;
                }
                break;
            }
            case update:{
                Connection c = null;
                Statement stmt = null;
                try {
                    Class.forName("org.sqlite.JDBC");
                    c = DriverManager.getConnection("jdbc:sqlite:db.db");
                    c.setAutoCommit(true);
                    stmt = c.createStatement();
                    String sqlUpdate = "UPDATE Deans set AcademicDegree = '" + item.getDegree() + "' where FIO = '" +
                            item.getFIO() + "';";
                    stmt.executeUpdate(sqlUpdate);
                    stmt.close();
                    c.close();
                } catch ( Exception e ) {
                    System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                    return false;
                }
                break;
            }
        }
        return true;
    }

    public Boolean operationWithEducUnit(Menu.ChangeInfoType operationType,
                                        Menu.InfoType  infoType,
                                        String name,
                                        String shortName,
                                        String address,
                                        String telephone,
                                        String site) {
        return true;
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
                rez.add(new HeadInfo(fio, "PhD", years, null));
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

}
