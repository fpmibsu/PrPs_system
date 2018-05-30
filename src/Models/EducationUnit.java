package Models;

public class EducationUnit {

    private int code;
    private String name;
    private String shortName;
    private HeadInfo[] heads;
    private SpecialityInfo[] spec;
    private String address;
    private String telephone;
    private String site;

    public EducationUnit(int code,
                         String name,
                         String shortName,
                         HeadInfo[] heads,
                         SpecialityInfo[] spec,
                         String address,
                         String telephone,
                         String site) {
        this.code = code;
        this.name = name;
        this.shortName = shortName;
        this.heads = heads;
        this.spec = spec;
        this.address = address;
        this.telephone = telephone;
        this.site = site;
    }

    public HeadInfo[] getHeads() {
        return this.heads;
    }

}
