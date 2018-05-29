import java.lang.reflect.Array;

public class DBWrapper {
    // class to fetch some info from bd


    public void operationWitInfo(Menu.ChangeInfoType operationType,
                                 Menu.InfoType infoType) {

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

}
