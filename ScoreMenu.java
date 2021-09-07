package FinalProject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ScoreMenu {
    // creating the frame for the score menu
    JFrame frame = new JFrame("Score Menu");
    // creating the column of the score table
    String[] columnNames = {"Subject", "Score"};
    ArrayList<Grade> scores;// list of courses with its scores

    public ScoreMenu(ArrayList<Grade> scores) {
        this.scores = scores;
        // using for loop to create list of courses and its relevant information (name of course, grade)
        String[][] scoreList = new String[scores.size()][columnNames.length];
        for (int i = 0; i < scores.size(); i++) {
            scoreList[i] = new String[]{scores.get(i).getCourse(), String.valueOf(scores.get(i).getGrade())};
        }
        // creating a table
        JTable jtable1 = new JTable(scoreList, columnNames) {
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };
        Container pane1 = frame.getContentPane();
        // using borderlayout as the frame
        pane1.add(new JScrollPane(jtable1), BorderLayout.CENTER);
        // set a specific size for the frame
        frame.setSize(400, 400);
        // to make the frame visible
        frame.setVisible(true);
    }



}
