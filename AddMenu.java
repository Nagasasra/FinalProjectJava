package FinalProject;

import javax.swing.*;
import java.awt.event.ActionListener;

// this class is to be used by Person Add Menu class (StudentAddMenu, TeacherAddMenu, EmployeeAddMenu)
abstract public class AddMenu implements ActionListener {
    // creating text fields
    JTextField inputName = new JTextField();
    JTextField inputMail = new JTextField();
    // creating save button
    JButton saveButton = new JButton("Save");
    // creating labels
    JLabel insertName = new JLabel("Please Insert Name:");
    JLabel insertMail = new JLabel("Please Insert Mail:");

}
