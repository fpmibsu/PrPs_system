package Models;

public class HeadInfo {

    private String fio;
    private String degree;
    private String years;

    public HeadInfo(String fio,
                    String degree,
                    String years ) {
        this.fio = fio;
        this.degree = degree;
        this.years = years;
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

