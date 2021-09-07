package FinalProject;

import java.util.ArrayList;

public class Student extends Person {
    private Major major;
    private ArrayList<Grade> scores;

    public Student(String name, String email, Major major) {
        super(name, email);
        scores = new ArrayList<>();
        this.major = major;
    }
    public Major getMajor() {
        return major;
    }
    public ArrayList<Grade> getScores() {
        return scores;
    }
    public void addGrade(int score, String course) {
        scores.add(new Grade(score, course));
    }
    public double getAvgGrade() {
        double sum = 0;
        if (getScores().size() == 0) {
            return 0;
        }
        for (Grade score : getScores()) {
            sum += score.getGrade();
        }
        return sum/getScores().size();
    }

    @Override
    public String getType() {
        return "Student";
    }
    @Override
    public String toString() {
        return "[Student] name: " + getName() + ", email: " + getEmail() + ", major: " + getMajor().getName() + ", avg score: " + getAvgGrade();
    }

}
