package FinalProject;

public class Major {
    private String name;// name of the major
    private String code;// the code for the major
    static int HowManyMajors=0;// how many majors are there in this school? will be added every time a new major is created

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
