package FinalProject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainMenu implements ActionListener{
    JFrame frame = new JFrame("Main Menu");
    JButton majorButton = new JButton("View Majors");
    JButton studentButton = new JButton("View Students");
    JButton teacherButton = new JButton("View Teachers");
    JButton employeeButton = new JButton("View Employee");
    ArrayList<Major> majors = new ArrayList<>();
    ArrayList<Student> students = new ArrayList<>();
    ArrayList<Teacher> teachers = new ArrayList<>();
    ArrayList<Employee> employees = new ArrayList<>();

    public MainMenu() {
        frame.setLayout(new FlowLayout());
        frame.add(majorButton);
        frame.add(studentButton);
        frame.add(teacherButton);
        frame.add(employeeButton);
        frame.setSize(700, 80);
        majorButton.addActionListener(this);
        studentButton.addActionListener(this);
        teacherButton.addActionListener(this);
        employeeButton.addActionListener(this);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new MainMenu();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == majorButton) {
            new MajorMenu(majors, true);
        }
        else if (actionEvent.getSource() == studentButton) {
            new StudentMenu(students, majors);
        }
        else if (actionEvent.getSource() == teacherButton) {
            new TeacherMenu(teachers, majors, students);
        }
        else if (actionEvent.getSource() == employeeButton) {
            new EmployeeMenu(employees);
        }

    }
}
