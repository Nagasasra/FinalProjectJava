package FinalProject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class StudentAddMenu extends AddMenu {
    ArrayList<Student> students;// list of students
    // creating the frame for the student add menu
    JFrame frame = new JFrame("Student Menu");
    StudentMenu studentMenu;
    JComboBox dropDownMajor;// drop down option of available majors in this school
    ArrayList<Major> majors;// list of majors

    public StudentAddMenu(ArrayList<Student> students, StudentMenu studentMenu, ArrayList<Major> majors) {
        this.students = students;
        this.studentMenu = studentMenu;
        this.majors = majors;
        // using for loop to create list of majors and put it into the dropdown option
        String[] majorOptions = new String[majors.size()];
        for (int i = 0; i < majors.size(); i++) {
            majorOptions[i] = majors.get(i).getName();
        }
        dropDownMajor = new JComboBox(majorOptions);
        Container pane1 = frame.getContentPane();
        // add these components to the container
        pane1.add(insertName);
        pane1.add(inputName);
        pane1.add(insertMail);
        pane1.add(inputMail);
        pane1.add(dropDownMajor);
        pane1.add(saveButton);
        // make the button do something when clicked by using ActionListener
        saveButton.addActionListener(this);
        // using boxlayout as the frame
        frame.setLayout(new BoxLayout(pane1, BoxLayout.PAGE_AXIS));
        // set a specific size for the frame
        frame.setSize(150, 175);
        // to make the frame visible
        frame.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        // if the button is clicked
        if (actionEvent.getSource() == saveButton) {
            Major major = null;
            for (Major value : majors) {
                // if at least one major is already created, the dropdown option won't be empty
                if (value.getName().equals(dropDownMajor.getSelectedItem())) {
                    major = value;
                }
            }
            // creating new student
            Student student = new Student(inputName.getText(), inputMail.getText(), major);
            students.add(student);
            // the student frame will disappear
            frame.setVisible(false);
            studentMenu.frame.setVisible(false);
            // the student table will be recreated to show the new data (refresh) with the new student included
            new StudentMenu(students, majors);
        }

    }
}
