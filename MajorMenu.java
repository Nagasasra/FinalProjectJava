package FinalProject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MajorMenu implements ActionListener {
    // creating the frame for the major menu
    JFrame frame = new JFrame("Major Menu");
    // creating the column of the major table
    String[] columnNames = {"Code", "Major"};
    // creating a button to add a new major
    JButton addMajorButton = new JButton("Add Major");
    ArrayList<Major> majors;// list of majors to be shown in the table
    boolean isAdding = true;

    public MajorMenu(ArrayList<Major> majors, boolean isAdding) {
        this.majors = majors;
        // using for loop to create list of majors and its relevant information (name, code)
        String[][] majorList = new String[majors.size()][columnNames.length];
        for (int i = 0; i < majors.size(); i++) {
            majorList[i] = new String[]{majors.get(i).getCode(), majors.get(i).getName()};
        }
        // creating a table
        JTable jtable1 = new JTable(majorList, columnNames) {
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }// the cells shouldn't be editable
        };
        Container pane1 = frame.getContentPane();
        // using borderlayout as the frame
        pane1.add(new JScrollPane(jtable1), BorderLayout.CENTER);
        if (isAdding) {
            pane1.add(addMajorButton, BorderLayout.PAGE_END);
        }
        // set a specific size for the frame
        frame.setSize(800, 400);
        // to make the frame visible
        frame.setVisible(true);
        // make the button do something when clicked by using ActionListener
        addMajorButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        // if add major button is clicked, a major add menu will pop up
        if (actionEvent.getSource() == addMajorButton) {
            new MajorAddMenu(majors, this);
        }
    }
}
