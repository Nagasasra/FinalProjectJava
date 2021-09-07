package FinalProject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class TeacherAddMenu extends AddMenu {
    ArrayList<Teacher> teachers;// list of teachers
    // creating the frame for the teacher add menu
    JFrame frame = new JFrame("Teacher Menu");
    TeacherMenu teacherMenu;
    JComboBox dropDownMajor;// drop down option of available majors in this school
    ArrayList<Major> majors;// list of majors
    ArrayList<Student> students;// list of students

    public TeacherAddMenu(ArrayList<Teacher> teachers, TeacherMenu teacherMenu, ArrayList<Major> majors, ArrayList<Student> students) {
        this.teachers = teachers;
        this.teacherMenu = teacherMenu;
        this.majors = majors;
        this.students = students;
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
            // creating new teacher
            Teacher teacher = new Teacher(inputName.getText(), inputMail.getText());
            if (major != null) {// if at least one major is already created, the selected major from the dropdown option will be assigned to that teacher
                teacher.addMajor(major);
            }
            teachers.add(teacher);
            // the student frame will disappear
            frame.setVisible(false);
            teacherMenu.frame.setVisible(false);
            // the student table will be recreated to show the new data (refresh) with the new student included
            new TeacherMenu(teachers, majors, students);
        }
    }
}
