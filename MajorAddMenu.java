package FinalProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MajorAddMenu implements ActionListener {
    JTextField inputName = new JTextField();
    JTextField inputCode = new JTextField();
    JLabel insertName = new JLabel("Please Insert Name:");
    JLabel insertCode = new JLabel("Please Insert Code:");
    JButton saveButton = new JButton("Save");
    ArrayList<Major> majors;
    JFrame frame = new JFrame("Major Menu");
    MajorMenu majorMenu;

    public MajorAddMenu(ArrayList<Major> majors, MajorMenu majorMenu) {
        this.majors = majors;
        this.majorMenu = majorMenu;
        Container pane1 = frame.getContentPane();
        pane1.add(insertName);
        pane1.add(inputName);
        pane1.add(insertCode);
        pane1.add(inputCode);
        pane1.add(saveButton);
        saveButton.addActionListener(this);

        frame.setLayout(new BoxLayout(pane1, BoxLayout.PAGE_AXIS));
        frame.setSize(150, 150);
        frame.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == saveButton) {
            Major major = new Major(inputName.getText(), inputCode.getText());
            majors.add(major);
            frame.setVisible(false);
            majorMenu.frame.setVisible(false);
            new MajorMenu(majors, true);
        }
    }
}
