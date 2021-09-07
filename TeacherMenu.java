package FinalProject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class TeacherMenu implements ActionListener{
    JFrame frame = new JFrame("Teacher Menu");
    String[] columnNames = {"Name", "Email", "Add Major", "View Majors", "Attendance", "Give Score", "Salary"};
    JButton viewMajorsButton = new JButton("View Majors");
    JButton viewScheduleButton = new JButton("View Schedules");
    JButton addTeacherButton = new JButton("Add Teacher");
    JButton addMajorButton = new JButton("Add Major");

    JComboBox dropDownAttendance = new JComboBox(new String[]{"Check-in", "Check-out"});
    JLabel dayLabel = new JLabel("Day:");
    JTextField inputDay = new JTextField();
    JLabel hourLabel = new JLabel("Hour:");
    JTextField inputHour = new JTextField();
    JButton saveAttendance = new JButton("Save");
    JFrame frameAttendance = new JFrame("View Attendance");

    JComboBox dropDownStudent;
    JLabel scoreLabel = new JLabel("Insert Score:");
    JTextField inputScore = new JTextField();
    JLabel courseLabel = new JLabel("Insert Course:");
    JTextField inputCourse = new JTextField();
    JButton saveScore = new JButton("Save");
    JFrame frameScore = new JFrame("Giving Score");

    JLabel salaryLabel = new JLabel("Insert Salary");
    JTextField inputSalary = new JTextField();
    JButton saveSalary = new JButton("Save");
    JFrame frameSalary = new JFrame("View Salary");

    JComboBox dropDownMajor;
    JButton saveMajor = new JButton("Save");
    JFrame frameMajor = new JFrame("Add Major");

    ArrayList<Teacher> teachers;
    ArrayList<Major> majors;
    ArrayList<Student> students;
    Teacher activeTeacher;

    TeacherMenu(ArrayList<Teacher> teachers, ArrayList<Major> majors, ArrayList<Student> students) {
        this.teachers = teachers;
        this.majors = majors;
        this.students = students;
        Object[][] teacherList = new Object[teachers.size()][columnNames.length];
        for (int i = 0; i < teachers.size(); i++) {
            NewButton btn1 = new NewButton("Add Major", teachers.get(i).getEmail(), "Add Major");
            NewButton btn2 = new NewButton("View Majors", teachers.get(i).getEmail(), "View Majors");
            NewButton btn4 = new NewButton("Attendance", teachers.get(i).getEmail(), "Attendance");
            NewButton btn5 = new NewButton("Give Score", teachers.get(i).getEmail(), "Give Score");
            NewButton btn6 = new NewButton("View Salary", teachers.get(i).getEmail(), "View Salary");
            btn1.addActionListener(this);
            btn2.addActionListener(this);
            btn4.addActionListener(this);
            btn5.addActionListener(this);
            btn6.addActionListener(this);
            teacherList[i] = new Object[]{teachers.get(i).getName(), teachers.get(i).getEmail(), btn1, btn2, btn4, btn5, btn6};
        }
        JTable jtable1 = new JTable(new JTableButtonModel(teacherList, columnNames));
        jtable1.setDefaultRenderer(JButton.class, new JTableButtonRenderer(jtable1.getDefaultRenderer(JButton.class)));
        jtable1.addMouseListener(new JTableButtonMouseListener(jtable1));

        Container pane1 = frame.getContentPane();
        viewMajorsButton.addActionListener(this);
        viewScheduleButton.addActionListener(this);
        addTeacherButton.addActionListener(this);

        pane1.add(new JScrollPane(jtable1), BorderLayout.CENTER);
        pane1.add(addTeacherButton, BorderLayout.PAGE_END);
        frame.setSize(500, 500);
        frame.setVisible(true);

        Container pane2 = frameAttendance.getContentPane();
        pane2.add(dropDownAttendance);
        pane2.add(dayLabel);
        pane2.add(inputDay);
        pane2.add(hourLabel);
        pane2.add(inputHour);
        pane2.add(saveAttendance);
        saveAttendance.addActionListener(this);

        frameAttendance.setLayout(new BoxLayout(pane2, BoxLayout.PAGE_AXIS));
        frameAttendance.setSize(200, 300);
        frameAttendance.setVisible(false);

        String[] studentOption = new String[students.size()];
        for(int i= 0; i < students.size(); i++){
            studentOption[i] = students.get(i).getName();
        }
        dropDownStudent = new JComboBox(studentOption);
        Container pane3 = frameScore.getContentPane();
        pane3.add(dropDownStudent);
        pane3.add(scoreLabel);
        pane3.add(inputScore);
        pane3.add(courseLabel);
        pane3.add(inputCourse);
        pane3.add(saveScore);
        saveScore.addActionListener(this);

        frameScore.setLayout(new BoxLayout(pane3, BoxLayout.PAGE_AXIS));
        frameScore.setSize(200, 300);
        frameScore.setVisible(false);

        Container pane4 = frameSalary.getContentPane();
        pane4.add(salaryLabel);
        pane4.add(inputSalary);
        pane4.add(saveSalary);
        saveSalary.addActionListener(this);

        frameSalary.setLayout(new BoxLayout(pane4, BoxLayout.PAGE_AXIS));
        frameSalary.setSize(200, 100);
        frameSalary.setVisible(false);

        String[] majorOption = new String[majors.size()];
        for(int i= 0; i < majors.size(); i++){
            majorOption[i] = majors.get(i).getName();
        }
        dropDownMajor = new JComboBox(majorOption);

        Container pane5 = frameMajor.getContentPane();
        pane5.add(dropDownMajor);
        pane5.add(saveMajor);
        saveMajor.addActionListener(this);

        frameMajor.setLayout(new BoxLayout(pane5, BoxLayout.PAGE_AXIS));
        frameMajor.setSize(200, 100);
        frameMajor.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        if (actionEvent.getSource() == addTeacherButton) {
            new TeacherAddMenu(teachers, this, majors, students);
        }

        else if (actionEvent.getSource() instanceof NewButton){
            String email = ((NewButton) actionEvent.getSource()).getCode();
            String forWhat = ((NewButton) actionEvent.getSource()).getForWhat();
            switch (forWhat) {
                case "View Majors":
                    for (Teacher teacher : teachers) {
                        if (teacher.getEmail().equals(email)) {
                            new MajorMenu(teacher.getMajors(), false);
                            break;
                        }
                    }
                    break;
                case "Attendance":
                    for (Teacher teacher : teachers) {
                        if (teacher.getEmail().equals(email)) {
                            this.activeTeacher = teacher;
                            break;
                        }
                    }
                    frameAttendance.setVisible(true);
                    break;
                case "Give Score":
                    for (Teacher teacher : teachers) {
                        if (teacher.getEmail().equals(email)) {
                            this.activeTeacher = teacher;
                            break;
                        }
                    }
                    frameScore.setVisible(true);
                    break;
                case "View Salary":
                    for (Teacher teacher : teachers) {
                        if (teacher.getEmail().equals(email)) {
                            this.activeTeacher = teacher;
                            break;
                        }
                    }
                    frameSalary.setVisible(true);
                    break;
                case "Add Major":
                    for (Teacher teacher : teachers) {
                        if (teacher.getEmail().equals(email)) {
                            this.activeTeacher = teacher;
                            break;
                        }
                    }
                    frameMajor.setVisible(true);
                    break;
            }

        }
        else if (actionEvent.getSource() == saveAttendance) {
            frameAttendance.setVisible(false);
            String wording = "";
            if (Objects.equals(dropDownAttendance.getSelectedItem(), "Check-in")){
                wording = activeTeacher.attendanceIn(inputDay.getText(), inputHour.getText());
            }
            else{
                wording = activeTeacher.attendanceOut(inputDay.getText(), inputHour.getText());
            }
            JFrame newFrame = new JFrame("Attendance");
            Container newPane = newFrame.getContentPane();
            newPane.add(new JLabel(wording));
            newFrame.setLayout(new BoxLayout(newPane, BoxLayout.PAGE_AXIS));
            newFrame.setSize(300, 100);
            newFrame.setVisible(true);
        }
        else if (actionEvent.getSource() == saveScore) {
            frameScore.setVisible(false);
            for (Student student : students) {
                if (student.getName().equals(dropDownStudent.getSelectedItem())) {
                    student.addGrade(Integer.parseInt(inputScore.getText()), inputCourse.getText());
                    break;
                }
            }
        }
        else if (actionEvent.getSource() == saveSalary) {
            frameSalary.setVisible(false);
            String wording = activeTeacher.getSalary(Integer.parseInt(inputSalary.getText()));

            JFrame newFrame = new JFrame("Salary");
            Container newPane = newFrame.getContentPane();
            newPane.add(new JLabel(wording));
            newFrame.setLayout(new BoxLayout(newPane, BoxLayout.PAGE_AXIS));
            newFrame.setSize(300, 100);
            newFrame.setVisible(true);
        }
        else if (actionEvent.getSource() == saveMajor) {
            frameMajor.setVisible(false);
            for (Major major : majors) {
                if (major.getName().equals(dropDownMajor.getSelectedItem())) {
                    activeTeacher.addMajor(major);
                    break;
                }
            }
        }
    }

}
