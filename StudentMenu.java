package FinalProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StudentMenu implements ActionListener{
    // creating the frame for the student menu
    JFrame frame = new JFrame("Student Menu");
    // creating the column of the student table
    String[] columnNames = {"Name", "Email", "Major", "Average Score", "View Scores"};
    // creating a button to add a new student
    JButton addStudentButton = new JButton("Add Student");
    ArrayList<Student> students;// list of student to be shown in the table
    ArrayList<Major> majors;// list of majors available at this school


    StudentMenu(ArrayList<Student> students, ArrayList<Major> majors) {
        this.majors = majors;
        this.students = students;
        // using for loop to create list of students and its relevant information (name, email, etc)
        Object[][] studentList = new Object[students.size()][columnNames.length];
        for (int i = 0; i < students.size(); i++) {
            // creating clickable button for each students to view their score
            NewButton btn = new NewButton("View Scores", students.get(i).getEmail(), "View Scores");
            btn.addActionListener(this);// make the button do something when clicked by using ActionListener
            studentList[i] = new Object[]{students.get(i).getName(), students.get(i).getEmail(), students.get(i).getMajor().getName(), String.valueOf(students.get(i).getAvgGrade()), btn};
        }
        // creating a table
        JTable jtable1 = new JTable(new JTableButtonModel(studentList, columnNames))
        {
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }// the cells shouldn't be editable
        };
        // to make those buttons inside the table (view scores) clickable
        jtable1.setDefaultRenderer(JButton.class, new JTableButtonRenderer(jtable1.getDefaultRenderer(JButton.class)));
        jtable1.addMouseListener(new JTableButtonMouseListener(jtable1));

        Container pane1 = frame.getContentPane();
        addStudentButton.addActionListener(this);// make the button do something when clicked by using ActionListener
        // using borderlayout as the frame
        pane1.add(new JScrollPane(jtable1), BorderLayout.CENTER);
        pane1.add(addStudentButton, BorderLayout.PAGE_END);
        // set a specific size for the frame
        frame.setSize(500, 500);
        // to make the frame visible
        frame.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        // if add student button is clicked, a student add menu will pop up
        if (actionEvent.getSource() == addStudentButton) {
            new StudentAddMenu(students, this, majors);
        }
        // if the button that is clicked is in the table (view scores)
        else if (actionEvent.getSource() instanceof NewButton){
            String email = ((NewButton) actionEvent.getSource()).getCode();// get the email of the student as a code gained from the button (code is an attribute of NewButton)
            String forWhat = ((NewButton) actionEvent.getSource()).getForWhat();// get the purpose of the button gained from the button (forwhat is an attribute of NewButton)
            // if the button's purpose (forwhat attribute) is indeed to view score
            if (forWhat.equals("View Scores")) {
                for (Student student : students) {
                    // to get the score of the correct student, the code/email gained from the button is used to find the student with said email
                    if (student.getEmail().equals(email)) {
                        new ScoreMenu(student.getScores());
                        break;
                    }
                }
            }

        }
    }
}

