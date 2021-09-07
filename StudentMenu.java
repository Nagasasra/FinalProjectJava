package FinalProject;
import Testing.ScoreMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StudentMenu implements ActionListener{
    JFrame frame = new JFrame("Student Menu");
    String[] columnNames = {"Name", "Email", "Major", "Average Score", "View Scores"};
    JButton addStudentButton = new JButton("Add Student");
    ArrayList<Student> students;
    ArrayList<Major> majors;

    JFrame frameScore = new JFrame("View Scores");
    JComboBox dropDownStudent;
    JButton comfirmViewScoreButton = new JButton("View Score");


    StudentMenu(ArrayList<Student> students, ArrayList<Major> majors) {
        this.majors = majors;
        this.students = students;
        Object[][] studentList = new Object[students.size()][columnNames.length];
        for (int i = 0; i < students.size(); i++) {
            NewButton btn = new NewButton("View Scores", students.get(i).getEmail(), "View Scores");
            btn.addActionListener(this);
            studentList[i] = new Object[]{students.get(i).getName(), students.get(i).getEmail(), students.get(i).getMajor().getName(), String.valueOf(students.get(i).getAvgGrade()), btn};
        }
        JTable jtable1 = new JTable(new JTableButtonModel(studentList, columnNames))
        {
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };
        jtable1.setDefaultRenderer(JButton.class, new JTableButtonRenderer(jtable1.getDefaultRenderer(JButton.class)));
        jtable1.addMouseListener(new JTableButtonMouseListener(jtable1));

        Container pane1 = frame.getContentPane();
        addStudentButton.addActionListener(this);

        pane1.add(new JScrollPane(jtable1), BorderLayout.CENTER);
        pane1.add(addStudentButton, BorderLayout.PAGE_END);
        //pane1.add(viewScoreButton);
        //pane1.add(viewScheduleButton);
        frame.setSize(500, 500);
        frame.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        if (actionEvent.getSource() == addStudentButton) {
            new StudentAddMenu(students, this, majors);
        }
        else if (actionEvent.getSource() == comfirmViewScoreButton) {
            for (Student student : students) {
                if (student.getName().equals(dropDownStudent.getSelectedItem())) {
                    new ScoreMenu(student.getScores());
                    break;
                }
            }
            frameScore.setVisible(false);
        }
        else if (actionEvent.getSource() instanceof NewButton){
            String email = ((NewButton) actionEvent.getSource()).getCode();
            String forWhat = ((NewButton) actionEvent.getSource()).getForWhat();
            if (forWhat.equals("View Scores")) {
                for (Student student : students) {
                    if (student.getEmail().equals(email)) {
                        new ScoreMenu(student.getScores());
                        break;
                    }
                }
            }

        }
    }
}

