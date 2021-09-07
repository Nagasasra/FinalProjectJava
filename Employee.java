package FinalProject;

public class Employee extends Person implements Work {

    public Employee(String name, String email) {
        super(name, email);
    }

    @Override
    public String getType() {// is this a student, teacher, or employee?
        return "Employee";
    }

    @Override
    public String toString() {
        return "[Employee] name: " + getName() + ", email: " + getEmail();
    }
    @Override
    public String attendanceIn(String date, String time) {
        return "Employee " + getName() + " checked in in " + date + " at " + time + ".";
    }
    @Override
    public String attendanceOut(String date, String time) {
        return "Employee " + getName() + " checked out in " + date + " at " + time + ".";
    }
    @Override
    public String getSalary(int salary) {
        return "Employee " + getName() + "'s salary is Rp" + salary;
    }
}
