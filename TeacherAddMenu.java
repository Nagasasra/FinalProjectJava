package FinalProject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class TeacherAddMenu extends AddMenu {
    ArrayList<Teacher> teachers;
    JFrame frame = new JFrame("Teacher Menu");
    TeacherMenu teacherMenu;
    JComboBox dropDownMajor;
    ArrayList<Major> majors;
    ArrayList<Student> students;

    public TeacherAddMenu(ArrayList<Teacher> teachers, TeacherMenu teacherMenu, ArrayList<Major> majors, ArrayList<Student> students) {
        this.teachers = teachers;
        this.teacherMenu = teacherMenu;
        this.majors = majors;
        this.students = students;
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



            Teacher teacher = new Teacher(inputName.getText(), inputMail.getText());
            if (major != null) {
                teacher.addMajor(major);
            }
            teachers.add(teacher);
            frame.setVisible(false);
            teacherMenu.frame.setVisible(false);
            new TeacherMenu(teachers, majors, students);
        }
    }
}
