package FinalProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class EmployeeMenu implements ActionListener {
    // creating the frame for the employee menu
    JFrame frame = new JFrame("Employee Menu");
    // creating the column of the employee table
    String[] columnNames = {"Name", "Email", "Attendance", "Salary"};
    // creating a button to add a new employee
    JButton addEmployeeButton = new JButton("Add Employee");

    // relevant components for teacher's attendance
    JComboBox dropDownAttendance = new JComboBox(new String[]{"Check-in", "Check-out"});
    JLabel dayLabel = new JLabel("Day:");
    JTextField inputDay = new JTextField();
    JLabel hourLabel = new JLabel("Hour:");
    JTextField inputHour = new JTextField();
    JButton saveAttendance = new JButton("Save");
    JFrame frameAttendance = new JFrame("View Attendance");

    // relevant components for teacher's salary
    JLabel salaryLabel = new JLabel("Insert Salary");
    JTextField inputSalary = new JTextField();
    JButton saveSalary = new JButton("Save");
    JFrame frameSalary = new JFrame("View Salary");

    ArrayList<Employee> employees;// list teachers at this school
    Employee activeEmployee;// this is used as a pointer to the employee that's chosen when the button inside the table is clicked

    public EmployeeMenu(ArrayList<Employee> employees) {
        this.employees = employees;
        // using for loop to create list of employees and its relevant information (name, email, etc)
        Object[][] employeeList = new Object[employees.size()][columnNames.length];
        for (int i = 0; i < employees.size(); i++) {
            // creating clickable buttons for each employees
            NewButton btn1 = new NewButton("Attendance", employees.get(i).getEmail(), "Attendance");
            NewButton btn2 = new NewButton("View Salary", employees.get(i).getEmail(), "View Salary");
            // make the button do something when clicked by using ActionListener
            btn1.addActionListener(this);
            btn2.addActionListener(this);
            employeeList[i] = new Object[]{employees.get(i).getName(), employees.get(i).getEmail(), btn1, btn2};
        }
        // creating a table
        JTable jtable1 = new JTable(new JTableButtonModel(employeeList, columnNames));
        // to make those buttons inside the table (add major, view majors, etc.) clickable
        jtable1.setDefaultRenderer(JButton.class, new JTableButtonRenderer(jtable1.getDefaultRenderer(JButton.class)));
        jtable1.addMouseListener(new JTableButtonMouseListener(jtable1));

        Container pane1 = frame.getContentPane();
        addEmployeeButton.addActionListener(this);// make the button do something when clicked by using ActionListener
        // using borderlayout as the frame
        pane1.add(new JScrollPane(jtable1), BorderLayout.CENTER);
        pane1.add(addEmployeeButton, BorderLayout.PAGE_END);
        // set a specific size for the frame
        frame.setSize(500, 500);
        // to make the frame visible
        frame.setVisible(true);

        Container pane2 = frameAttendance.getContentPane();
        // add these components to the container
        pane2.add(dropDownAttendance);
        pane2.add(dayLabel);
        pane2.add(inputDay);
        pane2.add(hourLabel);
        pane2.add(inputHour);
        pane2.add(saveAttendance);
        saveAttendance.addActionListener(this);// make the button do something when clicked by using ActionListener
        // using boxlayout as the frame
        frameAttendance.setLayout(new BoxLayout(pane2, BoxLayout.PAGE_AXIS));
        // set a specific size for the frame
        frameAttendance.setSize(200, 300);
        // to make the frame not visible (will be visible if view attendance button is clicked)
        frameAttendance.setVisible(false);

        Container pane3 = frameSalary.getContentPane();
        // add these components to the container
        pane3.add(salaryLabel);
        pane3.add(inputSalary);
        pane3.add(saveSalary);
        saveSalary.addActionListener(this);// make the button do something when clicked by using ActionListener
        // using boxlayout as the frame
        frameSalary.setLayout(new BoxLayout(pane3, BoxLayout.PAGE_AXIS));
        // set a specific size for the frame
        frameSalary.setSize(200, 100);
        // to make the frame not visible (will be visible if view salary button is clicked)
        frameSalary.setVisible(false);


    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        // if add employee button is clicked, an employee add menu will pop up
        if (actionEvent.getSource() == addEmployeeButton) {
            new EmployeeAddMenu(employees, this);
        }

        // if the button that is clicked is in the table (add major, view majors, etc.)
        else if (actionEvent.getSource() instanceof NewButton) {
            String email = ((NewButton) actionEvent.getSource()).getCode();// get the email of the employee as a code gained from the button (code is an attribute of NewButton)
            String forWhat = ((NewButton) actionEvent.getSource()).getForWhat();// get the purpose of the button gained from the button (forwhat is an attribute of NewButton)
            if (forWhat.equals("Attendance")) {// if the button's purpose (forwhat attribute) is to view attendance
                for (Employee employee : employees) {
                    // to get the data from the correct employee, the code/email gained from the button is used to find the employee with said email
                    if (employee.getEmail().equals(email)) {
                        this.activeEmployee = employee;
                        break;
                    }
                }
                frameAttendance.setVisible(true);// to make the frame visible when clicked
            }
            else if (forWhat.equals("View Salary")) {// if the button's purpose (forwhat attribute) is to view salary
                for (Employee employee : employees) {
                    if (employee.getEmail().equals(email)) {// to get the data from the correct employee, the code/email gained from the button is used to find the employee with said email
                        this.activeEmployee = employee;
                        break;
                    }
                }
                frameSalary.setVisible(true);// to make the frame visible when clicked
            }
        }
        // if the button to save the attendance after inputting the time is clicked
        else if (actionEvent.getSource() == saveAttendance) {
            frameAttendance.setVisible(false);
            String wording = "";
            if (Objects.equals(dropDownAttendance.getSelectedItem(), "Check-in")){
                wording = activeEmployee.attendanceIn(inputDay.getText(), inputHour.getText());
            }
            else{
                wording = activeEmployee.attendanceOut(inputDay.getText(), inputHour.getText());
            }
            JFrame newFrame = new JFrame("Attendance");
            Container newPane = newFrame.getContentPane();
            newPane.add(new JLabel(wording));
            newFrame.setLayout(new BoxLayout(newPane, BoxLayout.PAGE_AXIS));
            newFrame.setSize(300, 100);
            newFrame.setVisible(true);// a new frame showing a text when the employee check-in / check-out will be displayed
        }
        // if the button after inputting the salary is clicked
        else if (actionEvent.getSource() == saveSalary) {
            frameSalary.setVisible(false);
            String wording = activeEmployee.getSalary(Integer.parseInt(inputSalary.getText()));

            JFrame newFrame = new JFrame("Salary");
            Container newPane = newFrame.getContentPane();
            newPane.add(new JLabel(wording));
            newFrame.setLayout(new BoxLayout(newPane, BoxLayout.PAGE_AXIS));
            newFrame.setSize(300, 100);
            newFrame.setVisible(true);// a new frame showing a text of that employee's salary will be displayed
        }
    }
}
