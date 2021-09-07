package FinalProject;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// a custom button class to be used inside table and make it clickable
class NewButton extends JButton {
    private String code;// this is the email of said person (useful for finding the information of the right person)
    private String forWhat;// this is the purpose of the button (is this button to view scores? view salary? etc.)

    public NewButton(String text, String code, String forWhat) {
        super(text);
        this.code = code;
        this.forWhat = forWhat;
    }

    public String getCode() {
        return code;
    }
    public String getForWhat() {
        return forWhat;
    }
}

// a lot of the code in these classes are taken from https://www.tutorialspoint.com/how-can-we-add-insert-a-jbutton-to-jtable-cell-in-java
class JTableButtonRenderer implements TableCellRenderer {
    private TableCellRenderer defaultRenderer;
    public JTableButtonRenderer(TableCellRenderer renderer) {
        defaultRenderer = renderer;
    }
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if(value instanceof Component)
            return (Component)value;
        return defaultRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}
class JTableButtonModel extends AbstractTableModel {

    private Object[][] rows;
    private String[] columns;

    public JTableButtonModel(Object[][] rows, String[] columns) {
        this.rows = rows;
        this.columns = columns;
    }

    public String getColumnName(int column) {
        return columns[column];
    }

    public int getRowCount() {
        return rows.length;
    }
    public int getColumnCount() {
        return columns.length;
    }
    public Object getValueAt(int row, int column) {
        return rows[row][column];
    }
    public boolean isCellEditable(int row, int column) {
        return false;
    }
    public Class getColumnClass(int column) {
        return getValueAt(0, column).getClass();
    }
}

class JTableButtonMouseListener extends MouseAdapter {
    private final JTable table;

    public JTableButtonMouseListener(JTable table) {
        this.table = table;
    }

    @Override public void mouseClicked(MouseEvent e) {
        int column = table.getColumnModel().getColumnIndexAtX(e.getX());
        int row    = e.getY()/table.getRowHeight();
        System.out.println("Col :"+column + "row:"+row);

        if (row < table.getRowCount() && row >= 0 && column < table.getColumnCount() && column >= 0) {
            Object value = table.getValueAt(row, column);
            System.out.println("Value :"+value.getClass().getName());
            if (value instanceof NewButton) {
                //System.out.println("Value :"+((NewButton) value).getCode());
                ((NewButton)value).doClick();
            }

        }
    }
}
