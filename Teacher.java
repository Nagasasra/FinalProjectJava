package FinalProject;

import java.util.ArrayList;

public class Teacher extends Person implements Work {
    private ArrayList<Major> majors;// list of majors the teacher is teaching

    public Teacher(String name, String email) {
        super(name, email);
        this.majors = new ArrayList<>();
    }

    @Override
    public String getType() {// is this a student, teacher, or employee?
        return "Teacher";
    }

    public ArrayList<Major> getMajors() {
        return majors;
    }
    public void addMajor(Major major) {
        majors.add(major);
    }
    // giving score to a student
    public void giveScore(Student student, int score, String course) {
        student.addGrade(score, course);
    }

    @Override
    public String toString() {
        return "[Teacher] name: " + getName() + ", email: " + getEmail();
    }
    @Override
    public String attendanceIn(String date, String time) {
        return "Teacher " + getName() + " checked-in in " + date + " at " + time + ".";
    }
    @Override
    public String attendanceOut(String date, String time) {
        return "Teacher " + getName() + " checked-out in " + date + " at " + time + ".";
    }
    @Override
    public String getSalary(int salary) {
        return "Teacher " + getName() + "'s salary is Rp" + salary;
    }
}
