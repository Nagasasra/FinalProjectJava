package FinalProject;

// this class is used by Student class
public class Grade {
    private double score;
    private String course;// a grade is marked for each course

    public Grade(double score, String course) {
        this.score = score;
        this.course = course;
    }
    public double getGrade() {
        return score;
    }
    public String getCourse() {
        return course;
    }
}
