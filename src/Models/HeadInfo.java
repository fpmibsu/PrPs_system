package Models;

public class HeadInfo {

    private String fio;
    private String degree;
    private String years;
    private String faculty;

    public HeadInfo(String fio,
                    String degree,
                    String years,
                    String fac) {
        this.fio = fio;
        this.degree = degree;
        this.years = years;
        this.faculty = fac;
    }

    public String getFaculty(){
        return this.faculty;
    }

    public String getFIO() {
        return this.fio;
    }

    public String getDegree() {
        return this.degree;
    }

    public String getYears() {
        return this.years;
    }


    public String toString() {
        return "\tФИО: " + this.fio +
                "\n\tУченая степпень: " + this.degree +
                "\n\tГоды: " + this.years;
    }

}

