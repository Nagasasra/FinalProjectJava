package FinalProject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MajorMenu implements ActionListener {
    JFrame frame = new JFrame("Major Menu");
    String[] columnNames = {"Code", "Major"};
    JButton addMajorButton = new JButton("Add Major");
    ArrayList<Major> majors;
    boolean isAdding = true;

    public MajorMenu(ArrayList<Major> majors, boolean isAdding) {
        this.majors = majors;
        String[][] majorList = new String[majors.size()][columnNames.length];
        for (int i = 0; i < majors.size(); i++) {
            majorList[i] = new String[]{majors.get(i).getCode(), majors.get(i).getName()};
        }

        JTable jtable1 = new JTable(majorList, columnNames) {
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };
        Container pane1 = frame.getContentPane();

        pane1.add(new JScrollPane(jtable1), BorderLayout.CENTER);
        if (isAdding) {
            pane1.add(addMajorButton, BorderLayout.PAGE_END);
        }

        frame.setSize(800, 400);
        frame.setVisible(true);

        addMajorButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == addMajorButton) {
            new MajorAddMenu(majors, this);
        }
    }
}
