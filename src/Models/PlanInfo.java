package Models;

public class PlanInfo {

    enum subjects {
        math,
        rus,
        eng,
        phis,
        chem,
        geo
    }

    private int numberOfStudents;
    private String year;
    private Boolean isDaily;
    private SpecialityInfo specialityInfo;

    public PlanInfo(int numberOfStudents,
                    String year,
                    Boolean isDaily,
                    SpecialityInfo specialityInfo) {
        this.numberOfStudents = numberOfStudents;
        this.year = year;
        this.isDaily = isDaily;
        this.specialityInfo = specialityInfo;
    }

    public String toString() {
        return "План приема на специальность: " + this.numberOfStudents +
                "\nГод: " + this.year +
                "\nФорма обучения: " + (this.isDaily ? "дневная" : "заочная") +
                "\nСпециальность: \n" + this.specialityInfo;
    }

}
