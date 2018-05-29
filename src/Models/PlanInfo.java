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
    private subjects[] subjectsToPass;
    private int scoreToPass;


    public PlanInfo(int numberOfStudents,
            subjects[] subjectsToPass,
            int scoreToPass) {
        this.numberOfStudents = numberOfStudents;
        this.subjectsToPass = subjectsToPass;
        this.scoreToPass = scoreToPass;

    }
}
