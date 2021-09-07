package FinalProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class EmployeeAddMenu extends AddMenu {
    ArrayList<Employee> employees;
    JFrame frame = new JFrame("Employee Menu");

    EmployeeMenu employeeMenu;

    public EmployeeAddMenu(ArrayList<Employee> employees, EmployeeMenu employeeMenu) {
        this.employees = employees;
        this.employeeMenu = employeeMenu;

        Container pane1 = frame.getContentPane();
        pane1.add(insertName);
        pane1.add(inputName);
        pane1.add(insertMail);
        pane1.add(inputMail);
        pane1.add(saveButton);

        saveButton.addActionListener(this);

        frame.setLayout(new BoxLayout(pane1, BoxLayout.PAGE_AXIS));
        frame.setSize(150, 150);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == saveButton) {
            Employee employee = new Employee(inputName.getText(), inputMail.getText());
            employees.add(employee);
            frame.setVisible(false);
            employeeMenu.frame.setVisible(false);
            new EmployeeMenu(employees);
        }
    }
}
