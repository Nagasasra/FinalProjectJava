package FinalProject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainMenu implements ActionListener{
    // creating the frame for the main menu
    JFrame frame = new JFrame("Main Menu");

    // creating various buttons to view different things
    JButton majorButton = new JButton("View Majors");
    JButton studentButton = new JButton("View Students");
    JButton teacherButton = new JButton("View Teachers");
    JButton employeeButton = new JButton("View Employee");

    // the data of people are stored in lists, loaded in the main menu
    ArrayList<Major> majors = new ArrayList<>();
    ArrayList<Student> students = new ArrayList<>();
    ArrayList<Teacher> teachers = new ArrayList<>();
    ArrayList<Employee> employees = new ArrayList<>();

    public MainMenu() {
        // using flowlayout as the frame
        frame.setLayout(new FlowLayout());
        // adding the various buttons created earlier to the frame
        frame.add(majorButton);
        frame.add(studentButton);
        frame.add(teacherButton);
        frame.add(employeeButton);
        // set the frame with this specific size when opened
        frame.setSize(700, 80);
        // make the buttons do something when clicked by using ActionListener
        majorButton.addActionListener(this);
        studentButton.addActionListener(this);
        teacherButton.addActionListener(this);
        employeeButton.addActionListener(this);
        // set the frame to be visible
        frame.setVisible(true);
        // when the main menu is closed, the entire program will terminate
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // make it so the program can be run from this class
    public static void main(String[] args) {
        new MainMenu();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        // if major menu is clicked, a new window (major menu) will pop up
        if (actionEvent.getSource() == majorButton) {
            new MajorMenu(majors, true);
        }
        // if student menu is clicked, a new window (student menu) will pop up
        else if (actionEvent.getSource() == studentButton) {
            new StudentMenu(students, majors);
        }
        // if teacher menu is clicked, a new window (teacher menu) will pop up
        else if (actionEvent.getSource() == teacherButton) {
            new TeacherMenu(teachers, majors, students);
        }
        // if employee menu is clicked, a new window (employee menu) will pop up
        else if (actionEvent.getSource() == employeeButton) {
            new EmployeeMenu(employees);
        }

    }
}
