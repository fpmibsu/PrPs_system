package Models;

public class FacultyInfo {

    private int code;
    private String name;
    private String shortName;
    private HeadInfo[] heads;
    private SpecialityInfo[] spec;

    public FacultyInfo(int code,
                       String name,
                       String shortName,
                       HeadInfo[] heads,
                       SpecialityInfo[] spec) {
        this.code = code;
        this.name = name;
        this.shortName = shortName;
        this.heads = heads;
        this.spec = spec;

    }
}
