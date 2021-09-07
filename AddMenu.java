package FinalProject;

import javax.swing.*;
import java.awt.event.ActionListener;

abstract public class AddMenu implements ActionListener {
    JTextField inputName = new JTextField();
    JTextField inputMail = new JTextField();
    JButton saveButton = new JButton("Save");
    JLabel insertName = new JLabel("Please Insert Name:");
    JLabel insertMail = new JLabel("Please Insert Mail:");

}
