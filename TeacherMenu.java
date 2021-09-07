package FinalProject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class TeacherMenu implements ActionListener{
    // creating the frame for the teacher menu
    JFrame frame = new JFrame("Teacher Menu");
    // creating the column of the teacher table
    String[] columnNames = {"Name", "Email", "Add Major", "View Majors", "Attendance", "Give Score", "Salary"};
    // creating a button to add a new student
    JButton addTeacherButton = new JButton("Add Teacher");

    // relevant components for teacher's attendance
    JComboBox dropDownAttendance = new JComboBox(new String[]{"Check-in", "Check-out"});
    JLabel dayLabel = new JLabel("Day:");
    JTextField inputDay = new JTextField();
    JLabel hourLabel = new JLabel("Hour:");
    JTextField inputHour = new JTextField();
    JButton saveAttendance = new JButton("Save");
    JFrame frameAttendance = new JFrame("View Attendance");

    // relevant components for giving score to a student
    JComboBox dropDownStudent;
    JLabel scoreLabel = new JLabel("Insert Score:");
    JTextField inputScore = new JTextField();
    JLabel courseLabel = new JLabel("Insert Course:");
    JTextField inputCourse = new JTextField();
    JButton saveScore = new JButton("Save");
    JFrame frameScore = new JFrame("Giving Score");

    // relevant components for teacher's salary
    JLabel salaryLabel = new JLabel("Insert Salary");
    JTextField inputSalary = new JTextField();
    JButton saveSalary = new JButton("Save");
    JFrame frameSalary = new JFrame("View Salary");

    // relevant components for adding a major to a teacher
    JComboBox dropDownMajor;
    JButton saveMajor = new JButton("Save");
    JFrame frameMajor = new JFrame("Add Major");

    ArrayList<Teacher> teachers;// list teachers at this school
    ArrayList<Major> majors;// list of majors at this school
    ArrayList<Student> students;// list of students at this school
    Teacher activeTeacher;// this is used as a pointer to the teacher that's chosen when the button inside the table is clicked

    TeacherMenu(ArrayList<Teacher> teachers, ArrayList<Major> majors, ArrayList<Student> students) {
        this.teachers = teachers;
        this.majors = majors;
        this.students = students;
        // using for loop to create list of teachers and its relevant information (name, email, etc)
        Object[][] teacherList = new Object[teachers.size()][columnNames.length];
        for (int i = 0; i < teachers.size(); i++) {
            // creating clickable buttons for each teachers
            NewButton btn1 = new NewButton("Add Major", teachers.get(i).getEmail(), "Add Major");
            NewButton btn2 = new NewButton("View Majors", teachers.get(i).getEmail(), "View Majors");
            NewButton btn4 = new NewButton("Attendance", teachers.get(i).getEmail(), "Attendance");
            NewButton btn5 = new NewButton("Give Score", teachers.get(i).getEmail(), "Give Score");
            NewButton btn6 = new NewButton("View Salary", teachers.get(i).getEmail(), "View Salary");
            // make the button do something when clicked by using ActionListener
            btn1.addActionListener(this);
            btn2.addActionListener(this);
            btn4.addActionListener(this);
            btn5.addActionListener(this);
            btn6.addActionListener(this);
            teacherList[i] = new Object[]{teachers.get(i).getName(), teachers.get(i).getEmail(), btn1, btn2, btn4, btn5, btn6};
        }
        // creating a table
        JTable jtable1 = new JTable(new JTableButtonModel(teacherList, columnNames));
        // to make those buttons inside the table (add major, view majors, etc.) clickable
        jtable1.setDefaultRenderer(JButton.class, new JTableButtonRenderer(jtable1.getDefaultRenderer(JButton.class)));
        jtable1.addMouseListener(new JTableButtonMouseListener(jtable1));

        Container pane1 = frame.getContentPane();
        addTeacherButton.addActionListener(this);// make the button do something when clicked by using ActionListener
        // using borderlayout as the frame
        pane1.add(new JScrollPane(jtable1), BorderLayout.CENTER);
        pane1.add(addTeacherButton, BorderLayout.PAGE_END);
        // set a specific size for the frame
        frame.setSize(500, 500);
        // to make the frame visible
        frame.setVisible(true);

        Container pane2 = frameAttendance.getContentPane();
        // add these components to the container
        pane2.add(dropDownAttendance);
        pane2.add(dayLabel);
        pane2.add(inputDay);
        pane2.add(hourLabel);
        pane2.add(inputHour);
        pane2.add(saveAttendance);
        saveAttendance.addActionListener(this);// make the button do something when clicked by using ActionListener
        // using boxlayout as the frame
        frameAttendance.setLayout(new BoxLayout(pane2, BoxLayout.PAGE_AXIS));
        // set a specific size for the frame
        frameAttendance.setSize(200, 300);
        // to make the frame not visible (will be visible if view attendance button is clicked)
        frameAttendance.setVisible(false);

        // using for loop to create list of students and put it into the dropdown option
        String[] studentOption = new String[students.size()];
        for(int i= 0; i < students.size(); i++){
            studentOption[i] = students.get(i).getName();
        }
        dropDownStudent = new JComboBox(studentOption);

        Container pane3 = frameScore.getContentPane();
        // add these components to the container
        pane3.add(dropDownStudent);
        pane3.add(scoreLabel);
        pane3.add(inputScore);
        pane3.add(courseLabel);
        pane3.add(inputCourse);
        pane3.add(saveScore);
        saveScore.addActionListener(this);// make the button do something when clicked by using ActionListener
        // using boxlayout as the frame
        frameScore.setLayout(new BoxLayout(pane3, BoxLayout.PAGE_AXIS));
        // set a specific size for the frame
        frameScore.setSize(200, 300);
        // to make the frame not visible (will be visible if give score button is clicked)
        frameScore.setVisible(false);

        Container pane4 = frameSalary.getContentPane();
        // add these components to the container
        pane4.add(salaryLabel);
        pane4.add(inputSalary);
        pane4.add(saveSalary);
        saveSalary.addActionListener(this);// make the button do something when clicked by using ActionListener
        // using boxlayout as the frame
        frameSalary.setLayout(new BoxLayout(pane4, BoxLayout.PAGE_AXIS));
        // set a specific size for the frame
        frameSalary.setSize(200, 100);
        // to make the frame not visible (will be visible if view salary button is clicked)
        frameSalary.setVisible(false);

        String[] majorOption = new String[majors.size()];
        for(int i= 0; i < majors.size(); i++){
            majorOption[i] = majors.get(i).getName();
        }
        dropDownMajor = new JComboBox(majorOption);

        Container pane5 = frameMajor.getContentPane();
        // add these components to the container
        pane5.add(dropDownMajor);
        pane5.add(saveMajor);
        saveMajor.addActionListener(this);// make the button do something when clicked by using ActionListener
        // using boxlayout as the frame
        frameMajor.setLayout(new BoxLayout(pane5, BoxLayout.PAGE_AXIS));
        // set a specific size for the frame
        frameMajor.setSize(200, 100);
        // to make the frame not visible (will be visible if add major button is clicked)
        frameMajor.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        // if add teacher button is clicked, a teacher add menu will pop up
        if (actionEvent.getSource() == addTeacherButton) {
            new TeacherAddMenu(teachers, this, majors, students);
        }

        // if the button that is clicked is in the table (add major, view majors, etc.)
        else if (actionEvent.getSource() instanceof NewButton){
            String email = ((NewButton) actionEvent.getSource()).getCode();// get the email of the teacher as a code gained from the button (code is an attribute of NewButton)
            String forWhat = ((NewButton) actionEvent.getSource()).getForWhat();// get the purpose of the button gained from the button (forwhat is an attribute of NewButton)
            switch (forWhat) {
                case "View Majors":// if the button's purpose (forwhat attribute) is to view majors
                    for (Teacher teacher : teachers) {
                        // to get the data from the correct teacher, the code/email gained from the button is used to find the teacher with said email
                        if (teacher.getEmail().equals(email)) {
                            new MajorMenu(teacher.getMajors(), false);
                            break;
                        }
                    }
                    break;
                case "Attendance":// if the button's purpose (forwhat attribute) is to view attendance
                    for (Teacher teacher : teachers) {
                        // to get the data from the correct teacher, the code/email gained from the button is used to find the teacher with said email
                        if (teacher.getEmail().equals(email)) {
                            this.activeTeacher = teacher;
                            break;
                        }
                    }
                    frameAttendance.setVisible(true);// to make the frame visible when clicked
                    break;
                case "Give Score":// if the button's purpose (forwhat attribute) is to give score
                    for (Teacher teacher : teachers) {
                        // to get the data from the correct teacher, the code/email gained from the button is used to find the teacher with said email
                        if (teacher.getEmail().equals(email)) {
                            this.activeTeacher = teacher;
                            break;
                        }
                    }
                    frameScore.setVisible(true);// to make the frame visible when clicked
                    break;
                case "View Salary":// if the button's purpose (forwhat attribute) is to view salary
                    for (Teacher teacher : teachers) {
                        // to get the data from the correct teacher, the code/email gained from the button is used to find the teacher with said email
                        if (teacher.getEmail().equals(email)) {
                            this.activeTeacher = teacher;
                            break;
                        }
                    }
                    frameSalary.setVisible(true);// to make the frame visible when clicked
                    break;
                case "Add Major":// if the button's purpose (forwhat attribute) is to add major to a teacher
                    for (Teacher teacher : teachers) {
                        // to get the data of the correct teacher, the code/email gained from the button is used to find the teacher with said email
                        if (teacher.getEmail().equals(email)) {
                            this.activeTeacher = teacher;
                            break;
                        }
                    }
                    frameMajor.setVisible(true);// to make the frame visible when clicked
                    break;
            }

        }
        // if the button to save the attendance after inputting the time is clicked
        else if (actionEvent.getSource() == saveAttendance) {
            frameAttendance.setVisible(false);// the previous frame will disappear
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
            newFrame.setVisible(true);// a new frame showing a text when the teacher check-in / check-out will be displayed
        }
        // if the button after giving a score to a student is clicked
        else if (actionEvent.getSource() == saveScore) {
            frameScore.setVisible(false);
            for (Student student : students) {
                if (student.getName().equals(dropDownStudent.getSelectedItem())) {
                    student.addGrade(Integer.parseInt(inputScore.getText()), inputCourse.getText());// a new grade is added to the chosen student
                    break;
                }
            }
        }
        // if the button after inputting the salary is clicked
        else if (actionEvent.getSource() == saveSalary) {
            frameSalary.setVisible(false);
            String wording = activeTeacher.getSalary(Integer.parseInt(inputSalary.getText()));

            JFrame newFrame = new JFrame("Salary");
            Container newPane = newFrame.getContentPane();
            newPane.add(new JLabel(wording));
            newFrame.setLayout(new BoxLayout(newPane, BoxLayout.PAGE_AXIS));
            newFrame.setSize(300, 100);
            newFrame.setVisible(true);// a new frame showing a text of that teacher's salary will be displayed
        }
        // if the button after picking which major to be added to a teacher is clicked
        else if (actionEvent.getSource() == saveMajor) {
            frameMajor.setVisible(false);
            for (Major major : majors) {
                if (major.getName().equals(dropDownMajor.getSelectedItem())) {
                    activeTeacher.addMajor(major);// the new major will be added to that teacher
                    break;
                }
            }
        }
    }

}
