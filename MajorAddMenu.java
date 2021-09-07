package FinalProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MajorAddMenu implements ActionListener {
    // creating text fields
    JTextField inputName = new JTextField();
    JTextField inputCode = new JTextField();
    // creating labels
    JLabel insertName = new JLabel("Please Insert Name:");
    JLabel insertCode = new JLabel("Please Insert Code:");
    // creating save button
    JButton saveButton = new JButton("Save");
    ArrayList<Major> majors;
    // creating the frame for the major add menu
    JFrame frame = new JFrame("Major Menu");
    MajorMenu majorMenu;

    public MajorAddMenu(ArrayList<Major> majors, MajorMenu majorMenu) {
        this.majors = majors;
        this.majorMenu = majorMenu;
        Container pane1 = frame.getContentPane();
        // add these components to the container
        pane1.add(insertName);
        pane1.add(inputName);
        pane1.add(insertCode);
        pane1.add(inputCode);
        pane1.add(saveButton);
        // make the button do something when clicked by using ActionListener
        saveButton.addActionListener(this);
        // using boxlayout as the frame
        frame.setLayout(new BoxLayout(pane1, BoxLayout.PAGE_AXIS));
        // set a specific size for the frame
        frame.setSize(150, 150);
        // to make the frame visible
        frame.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        // if the button is clicked
        if (actionEvent.getSource() == saveButton) {
            // creating new major
            Major major = new Major(inputName.getText(), inputCode.getText());
            majors.add(major);
            // the major frame will disappear
            frame.setVisible(false);
            majorMenu.frame.setVisible(false);
            // the major table will be recreated to show the new data (refresh) with the new major included
            new MajorMenu(majors, true);
        }
    }
}
