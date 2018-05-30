package Models;

public class SpecialityInfo {

    private int code;
    private String name;
    private String shortName;

    public SpecialityInfo(int code,
                          String name,
                          String shortName) {
        this.code = code;
        this.name = name;
        this.shortName = shortName;
    }

    public String toString() {
        return "\tКод: " + this.code +
                "\n\tНазвание: " + this.name +
                "\n\tКороткое название: " + this.shortName;
    }

}

