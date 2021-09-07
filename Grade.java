package FinalProject;

public class Grade {
    private double score;
    private String course;

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
