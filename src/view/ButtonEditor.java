package view;


import model.features.Course;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class ButtonEditor extends DefaultCellEditor {
    protected JButton button;
    private String label;
    private boolean isPushed;
    private JTable table;
    private ArrayList<Course> coursesList;
    private View view;

    public ButtonEditor(View view,JCheckBox checkBox, JTable table, ArrayList<Course> coursesList) {
        super(checkBox);
        this.view = view;
        this.table = table;
        this.coursesList = coursesList;
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(e -> fireEditingStopped());
    }

    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        label = (value == null) ? "" : value.toString();
        button.setText(label);
        isPushed = true;
        return button;
    }

    public Object getCellEditorValue() {
        if (isPushed) {
            // Handle "View" button click logic here
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                Course selectedCourse = coursesList.get(selectedRow);
                view.ShowCourseTopicsPanel(selectedCourse, selectedCourse.getTopics().get(0), 0);
            }
        }
        isPushed = false;
        return label;
    }

    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }

    protected void fireEditingStopped() {
        super.fireEditingStopped();
    }
}