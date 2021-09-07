package FinalProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class EmployeeAddMenu extends AddMenu {
    ArrayList<Employee> employees;// list of employees
    // creating the frame for the employees add menu
    JFrame frame = new JFrame("Employee Menu");
    EmployeeMenu employeeMenu;

    public EmployeeAddMenu(ArrayList<Employee> employees, EmployeeMenu employeeMenu) {
        this.employees = employees;
        this.employeeMenu = employeeMenu;

        Container pane1 = frame.getContentPane();
        // add these components to the container
        pane1.add(insertName);
        pane1.add(inputName);
        pane1.add(insertMail);
        pane1.add(inputMail);
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
            // creating new employee
            Employee employee = new Employee(inputName.getText(), inputMail.getText());
            employees.add(employee);
            // the employee frame will disappear
            frame.setVisible(false);
            employeeMenu.frame.setVisible(false);
            // the employee table will be recreated to show the new data (refresh) with the new student included
            new EmployeeMenu(employees);
        }
    }
}
