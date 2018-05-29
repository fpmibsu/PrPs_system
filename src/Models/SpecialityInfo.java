package Models;

public class SpecialityInfo {

    private int code;
    private String name;
    private String shortName;

    private Boolean isDaily;

    public SpecialityInfo(int code,
                          String name,
                          String shortName,
                          Boolean isDaily) {
        this.code = code;
        this.name = name;
        this.shortName = shortName;
        this.isDaily = isDaily;

    }
}

