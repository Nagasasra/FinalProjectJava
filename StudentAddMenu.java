package FinalProject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class StudentAddMenu extends AddMenu {
    ArrayList<Student> students;
    JFrame frame = new JFrame("Student Menu");
    StudentMenu studentMenu;
    JComboBox dropDownMajor;
    ArrayList<Major> majors;

    public StudentAddMenu(ArrayList<Student> students, StudentMenu studentMenu, ArrayList<Major> majors) {
        this.students = students;
        this.studentMenu = studentMenu;
        this.majors = majors;
        String[] majorOptions = new String[majors.size()];
        for (int i = 0; i < majors.size(); i++) {
            majorOptions[i] = majors.get(i).getName();
        }
        dropDownMajor = new JComboBox(majorOptions);
        Container pane1 = frame.getContentPane();
        pane1.add(insertName);
        pane1.add(inputName);
        pane1.add(insertMail);
        pane1.add(inputMail);
        pane1.add(dropDownMajor);
        pane1.add(saveButton);

        saveButton.addActionListener(this);

        frame.setLayout(new BoxLayout(pane1, BoxLayout.PAGE_AXIS));
        frame.setSize(150, 175);
        frame.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == saveButton) {
            Major major = null;
            for (Major value : majors) {
                if (value.getName().equals(dropDownMajor.getSelectedItem())) {
                    major = value;
                }
            }

            Student student = new Student(inputName.getText(), inputMail.getText(), major);
            students.add(student);
            frame.setVisible(false);
            studentMenu.frame.setVisible(false);
            new StudentMenu(students, majors);
        }

    }
}
