package FinalProject;

public class Major {
    private String name;
    private String code;
    static int HowManyMajors=0;

    public Major(String name, String code) {
        this.name = name;
        this.code = code;
        Major.HowManyMajors += 1;
    }
    public String getName() {
        return name;
    }
    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return  getName() + " (" + getCode() + ")";
    }
}
