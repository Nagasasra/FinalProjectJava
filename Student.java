package FinalProject;

import java.util.ArrayList;

public class Student extends Person {
    private Major major;// every student is registered in one major
    private ArrayList<Grade> scores;// list of courses and its scores

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
    // add new score to a student (to be utilized by Teacher)
    public void addGrade(int score, String course) {
        scores.add(new Grade(score, course));
    }
    // get average score for this student
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
    public String getType() {// is this a student, teacher, or employee?
        return "Student";
    }
    @Override
    public String toString() {
        return "[Student] name: " + getName() + ", email: " + getEmail() + ", major: " + getMajor().getName() + ", avg score: " + getAvgGrade();
    }

}
