package FinalProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class EmployeeMenu implements ActionListener {
    JFrame frame = new JFrame("Employee Menu");
    String[] columnNames = {"Name", "Email", "Attendance", "Salary"};
    JButton addEmployeeButton = new JButton("Add Employee");

    JComboBox dropDownAttendance = new JComboBox(new String[]{"Check-in", "Check-out"});
    JLabel dayLabel = new JLabel("Day:");
    JTextField inputDay = new JTextField();
    JLabel hourLabel = new JLabel("Hour:");
    JTextField inputHour = new JTextField();
    JButton saveAttendance = new JButton("Save");
    JFrame frameAttendance = new JFrame("View Attendance");

    JLabel salaryLabel = new JLabel("Insert Salary");
    JTextField inputSalary = new JTextField();
    JButton saveSalary = new JButton("Save");
    JFrame frameSalary = new JFrame("View Salary");

    ArrayList<Employee> employees;
    Employee activeEmployee;

    public EmployeeMenu(ArrayList<Employee> employees) {
        this.employees = employees;

        Object[][] employeeList = new Object[employees.size()][columnNames.length];
        for (int i = 0; i < employees.size(); i++) {
            NewButton btn1 = new NewButton("Attendance", employees.get(i).getEmail(), "Attendance");
            NewButton btn2 = new NewButton("View Salary", employees.get(i).getEmail(), "View Salary");
            btn1.addActionListener(this);
            btn2.addActionListener(this);
            employeeList[i] = new Object[]{employees.get(i).getName(), employees.get(i).getEmail(), btn1, btn2};
        }

        JTable jtable1 = new JTable(new JTableButtonModel(employeeList, columnNames));
        jtable1.setDefaultRenderer(JButton.class, new JTableButtonRenderer(jtable1.getDefaultRenderer(JButton.class)));
        jtable1.addMouseListener(new JTableButtonMouseListener(jtable1));

        Container pane1 = frame.getContentPane();
        addEmployeeButton.addActionListener(this);

        pane1.add(new JScrollPane(jtable1), BorderLayout.CENTER);
        pane1.add(addEmployeeButton, BorderLayout.PAGE_END);
        frame.setSize(500, 500);
        frame.setVisible(true);

        Container pane2 = frameAttendance.getContentPane();
        pane2.add(dropDownAttendance);
        pane2.add(dayLabel);
        pane2.add(inputDay);
        pane2.add(hourLabel);
        pane2.add(inputHour);
        pane2.add(saveAttendance);
        saveAttendance.addActionListener(this);

        frameAttendance.setLayout(new BoxLayout(pane2, BoxLayout.PAGE_AXIS));
        frameAttendance.setSize(200, 300);
        frameAttendance.setVisible(false);

        Container pane3 = frameSalary.getContentPane();
        pane3.add(salaryLabel);
        pane3.add(inputSalary);
        pane3.add(saveSalary);
        saveSalary.addActionListener(this);

        frameSalary.setLayout(new BoxLayout(pane3, BoxLayout.PAGE_AXIS));
        frameSalary.setSize(200, 100);
        frameSalary.setVisible(false);


    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == addEmployeeButton) {
            new EmployeeAddMenu(employees, this);
        }
        else if (actionEvent.getSource() instanceof NewButton) {
            String email = ((NewButton) actionEvent.getSource()).getCode();
            String forWhat = ((NewButton) actionEvent.getSource()).getForWhat();
            if (forWhat.equals("Attendance")) {
                for (Employee employee : employees) {
                    if (employee.getEmail().equals(email)) {
                        this.activeEmployee = employee;
                        break;
                    }
                }
                frameAttendance.setVisible(true);
            }
            else if (forWhat.equals("View Salary")) {
                for (Employee employee : employees) {
                    if (employee.getEmail().equals(email)) {
                        this.activeEmployee = employee;
                        break;
                    }
                }
                frameSalary.setVisible(true);
            }
        }
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
            newFrame.setVisible(true);
        }
        else if (actionEvent.getSource() == saveSalary) {
            frameSalary.setVisible(false);
            String wording = activeEmployee.getSalary(Integer.parseInt(inputSalary.getText()));

            JFrame newFrame = new JFrame("Salary");
            Container newPane = newFrame.getContentPane();
            newPane.add(new JLabel(wording));
            newFrame.setLayout(new BoxLayout(newPane, BoxLayout.PAGE_AXIS));
            newFrame.setSize(300, 100);
            newFrame.setVisible(true);
        }
    }
}
