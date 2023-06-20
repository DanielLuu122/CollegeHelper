package calendar;

import cs20models.AllModelsForView;
import static cs20models.AllModelsForView.theCalendarModel;
import cs20models.CollegeModel;
import cs20models.DayModel;
import cs20viewcontroller.*;
import java.awt.Color;
import java.awt.Component;
import java.util.Calendar;
import java.util.Scanner;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

/**
 * Write methods in this class for displaying data in the DrawnView.
 *
 * You can use all the public instance variables you defined in AllModelsForView
 * and DrawnView as though they were part of this class! This is due to the
 * magic of subclassing (i.e. using the extends keyword).
 *
 * The methods for displaying data in the DrawnView are written as methods in
 * this class.
 *
 * Make sure to use these methods in the ViewUserActions class though, or else
 * they will be defined but never used!
 *
 * @author cheng
 */
public class ViewOutputs extends DrawnView {

    public void updateCalendar() {
        if (theCalendarModel.getInit() == true) {
            addCollageToCalendar();
            DayModel aDayModel = new DayModel();
            int month = monthLabelToInt();
            for (int row = 0; row < table.getRowCount(); row++) {
                for (int column = 0; column < table.getColumnCount(); column++) {
                    table.setValueAt(aDayModel, row, column);
                }
            }
            int row = 0;
            int column = theCalendarModel.getDow(month);
            for (int day = 1; day <= theCalendarModel.getTotalDays(month); day++) {
                DayModel aDay = theCalendarModel.getDay(theCalendarModel.getMonth(month), day);
                table.setValueAt(aDay, row, column);
                table.getColumnModel().getColumn(column).setCellRenderer(new ChangeColour());
                column++;
                if (column > 6) {
                    row++;
                    column = 0;
                }
            }
            if (!dayDisplay.getText().equals("")) {
                Scanner s = new Scanner(dayDisplay.getText());
                int date = s.nextInt();
                DayModel bDayModel = theCalendarModel.getDay(monthLabel.getText(), date);
                setDisplay(bDayModel);
            }
        }
    }

    public void setDisplay(DayModel aDayModel) {
        SimpleAttributeSet day = new SimpleAttributeSet();
        StyleConstants.setFontFamily(day, "Arial");
        StyleConstants.setFontSize(day, 20);
        Scanner s = new Scanner(aDayModel.display());
        SimpleAttributeSet defaults = new SimpleAttributeSet();
        StyleConstants.setFontFamily(defaults, "Calibri Light");
        StyleConstants.setFontSize(defaults, 14);
        SimpleAttributeSet other = new SimpleAttributeSet();
        StyleConstants.setFontFamily(other, "Calibri Light");
        StyleConstants.setFontSize(other, 16);
        StyleConstants.setBold(other, true);
        String displayArray[] = new String[100000];
        int count = 0;
        while (s.hasNextLine()) {
            displayArray[count] = s.nextLine();
            count++;
        }
        dayDisplay.setText("");
        Document doc = dayDisplay.getDocument();
        for (int i = 0; i < count; i++) {
            try {
                if (i == 0) {
                    dayDisplay.getDocument().insertString(0, displayArray[0], day);
                }
                if (i != 0) {
                    if (displayArray[i].equals("Deadlines:") || displayArray[i].equals("Events:")) {
                        dayDisplay.getDocument().insertString(doc.getLength(), "\n" + displayArray[i], other);
                    } else {
                        dayDisplay.getDocument().insertString(doc.getLength(), "\n" + displayArray[i], defaults);

                    }
                }
            } catch (Exception e) {
            }
        }
        //dayDisplay.setText(aDayModel.display());
    }

    public int monthLabelToInt() {
        for (int i = 0; i < 12; i++) {
            if (monthLabel.getText().equals(theCalendarModel.getMonth(i))) {
                return i;
            }
        }
        return -1;
    }

    public void initCalendar(int x) {
        if (theCalendarModel.getInit() == false) {
            int dayCount = 0;
            for (int month = 0; month < 12; month++) {
                for (int day = 1; day <= theCalendarModel.getTotalDays(month); day++) {
                    DayModel aDayModel = new DayModel();
                    aDayModel.setDay(day);
                    aDayModel.setMonth(theCalendarModel.getMonth(month));
                    theCalendarModel.setDay(dayCount, aDayModel);
                    dayCount++;

                }

            }
            addCollageToCalendar();
            theCalendarModel.setInit(true);
            updateCalendar();
        } else {
            updateCalendar();
        }
    }

    public void addCollageToCalendar() {
        CollegeModel[] aCollegeList = theInfoModel.toArray();
        for (int i = 0; i < aCollegeList.length; i++) {
            CollegeModel aCollegeModel = aCollegeList[i];
            String dueDate[] = aCollegeModel.getDueDate().split("/");
            DayModel aDayModel;
            if (!aCollegeModel.getOldDate().equals("") && !aCollegeModel.getOldDate().equals(aCollegeModel.getDueDate())) {
                String oldDate[] = aCollegeModel.getOldDate().split("/");
                if (oldDate[2].equals(Calendar.getInstance().get(Calendar.YEAR) + "")) {
                    aDayModel = theCalendarModel.getDay(theCalendarModel.getMonth(Integer.parseInt(oldDate[1]) - 1), Integer.parseInt(oldDate[0]));
                    if (aDayModel.getDefaults().equals("\n" + aCollegeModel.name + " Submission due")) {
                        aDayModel.setColour("-");
                    }
                    aDayModel.setDefaults(aDayModel.getDefaults().replaceAll("\n" + aCollegeModel.name + " Submission due", ""));
                    aCollegeModel.setOldDate(aCollegeModel.getDueDate());
                }
            }
            if (dueDate[2].equals(Calendar.getInstance().get(Calendar.YEAR) + "")) {
                if (!aCollegeModel.getAdded()) {
                    aDayModel = theCalendarModel.getDay(theCalendarModel.getMonth(Integer.parseInt(dueDate[1]) - 1), Integer.parseInt(dueDate[0]));
                    aDayModel.addDefaults(aCollegeModel.name + " Submission due");
                    aDayModel.setColour("red");
                    // theCalendarModel.setDay(theCalendarModel.getIndex(Integer.parseInt(dueDate[1]) - 1, Integer.parseInt(dueDate[0])), aDayModel);
                    //System.out.println(aDayModel.getDefaults());
                    aCollegeModel.setAdded(true);
                }
            }
        }
    }

//    public void updateMonth(String month){
//        int row = 0;
//        int column = 0;
//        int intMonth = Integer.parseInt(month);
//        for(int i = 0; i < theCalendarModel.getTotalDays(intMonth); i++){
//            table.setValueAt();
//        }
//    }
}

class ChangeColour extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {

        //Cells are by default rendered as a JLabel.
        Component d = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
        JLabel l = (JLabel) d;
        //Get the status for the current row.
        DayModel aDayModel = (DayModel) table.getValueAt(row, col);
        //System.out.println(aDayModel.getColour());
        Border border = BorderFactory.createLineBorder(Color.BLUE, 3);
        if (aDayModel.getColour().equals("red")) {
            l.setBackground(Color.RED);
            l.setForeground(new java.awt.Color(255, 255, 255));
            if (isSelected == true) {
                l.setBorder(border);
            }
            return l;
        } else if (aDayModel.getColour().equals("orange")) {
            l.setBackground(Color.ORANGE);
            l.setForeground(new java.awt.Color(255, 255, 255));
            if (isSelected == true) {
                l.setBorder(border);
            }
            return l;
        } else if (aDayModel.getColour().equals("-")) {
            l.setBackground(new java.awt.Color(255, 255, 255));
            l.setForeground(new java.awt.Color(102, 0, 204));
            if (isSelected == true) {
                l.setBackground(new java.awt.Color(0, 0, 255));
                l.setForeground(new java.awt.Color(255, 255, 255));
            }
            return l;
        }
        //Return the JLabel which renders the cell.
        return l;
    }
}

class defaultColour extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {

        //Cells are by default rendered as a JLabel.
        JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

        //Get the status for the current row.
        l.setBackground(new java.awt.Color(69, 73, 74));
        //Return the JLabel which renders the cell.
        return l;
    }
}
