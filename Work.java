package FinalProject;

// Teacher and Employee will use this interface
public interface Work {
    String attendanceIn(String date, String time);
    String attendanceOut(String date, String time);
    String getSalary(int salary);
}
