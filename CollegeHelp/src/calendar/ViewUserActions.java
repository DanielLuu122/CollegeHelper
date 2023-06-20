/*
 * The controller classes (like the ViewUserActions class) provides actions
 * that the user can trigger through the view classes.  Those actions are 
 * written in this class as private inner classes (i.e. classes 
 * declared inside another class).
 *
 * You can use all the public instance variables you defined in AllModelsForView, 
 * DrawnView, and ViewOutputs as though they were part of this class! This is 
 * due to the magic of subclassing (i.e. using the extends keyword).
 */
package calendar;

import cs20models.AllModelsForView;
import static cs20models.AllModelsForView.theCalendarModel;
import cs20models.DayModel;
import cs20viewcontroller.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import javax.swing.Action;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * ViewUserActions is a class that contains actions users can trigger.
 *
 * User actions are written as private inner classes that implements the
 * ActionListener interface. These actions must be "wired" into the DrawnView in
 * the ViewUserActions constructor, or else they won't be triggered by the user.
 *
 * Actions that the user can trigger are meant to manipulate some model classes
 * by sending messages to them to tell them to update their data members.
 *
 * Actions that the user can trigger can also be used to manipulate the GUI by
 * sending messages to the view classes (e.g. the DrawnView class) to tell them
 * to update themselves (e.g. to redraw themselves on the screen).
 *
 * @author cheng
 */
public class ViewUserActions extends ViewOutputs {

    /*
     * Step 1 of 2 for writing user actions.
     * -------------------------------------
     *
     * User actions are written as private inner classes that implement
     * ActionListener, and override the actionPerformed method.
     *
     * Use the following as a template for writting more user actions.
     */
    private class FillCalendar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            initCalendar(monthLabelToInt());

        }
    }

    private class AddEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (!dayDisplay.getText().equals("")) {
                Scanner s = new Scanner(dayDisplay.getText());
                int date = s.nextInt();
                DayModel aDayModel = theCalendarModel.getDay(monthLabel.getText(), date);
                aDayModel.addEvents(addEventField.getText());
                setDisplay(aDayModel);
                if (!aDayModel.getColour().equals("red")) {
                    aDayModel.setColour("orange");
                }
                updateCalendar();
                addEventField.setText("");
            }

        }
    }

    private class ClearEvents implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (!dayDisplay.getText().equals("")) {
                Scanner s = new Scanner(dayDisplay.getText());
                int date = s.nextInt();
                DayModel aDayModel = theCalendarModel.getDay(monthLabel.getText(), date);
                aDayModel.setEvents("");
                setDisplay(aDayModel);
                aDayModel.setColour("-");
            }

        }
    }

    private class ChangeMonth implements ActionListener {

        public char v;

        public ChangeMonth(char c) {
            this.v = c;
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            String newMonth = monthLabel.getText();
            for (int i = 0; i < 12; i++) {
                if (monthLabel.getText().equals(theCalendarModel.getMonth(i))) {
                    switch (this.v) {
                        case 'r':
                            newMonth = theCalendarModel.getMonth(i + 1);
                            break;
                        case 'l':
                            newMonth = theCalendarModel.getMonth(i - 1);
                            break;
                    }
                }
            }
            monthLabel.setText(newMonth);
            updateCalendar();
        }
    }

    private class GetDayChangeAction implements ListSelectionListener { // change to table cell listner

        public int row;
        public int column;

        public GetDayChangeAction() {
            this.row = -1;
            this.column = -1;
        }

        @Override
        public void valueChanged(ListSelectionEvent arg0) {
            if (arg0.getValueIsAdjusting()) {
                return;
            }
            if ((this.row != table.getSelectedRow() || this.column != table.getSelectedColumn()) && table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()) != null) {
                DayModel s = (DayModel) table.getValueAt(table.getSelectedRow(), table.getSelectedColumn());
                setDisplay(s);
            }
            this.row = table.getSelectedRow();
            this.column = table.getSelectedColumn();
        }

    }

    private class GoToScreen implements ActionListener {

        public String location;

        public GoToScreen(String location) {
            this.location = location;
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            AllModelsForView.goToScreen(location);
        }
    }
//    
//    private class CopyTxtFromScrn1Action implements ActionListener{
//        @Override
//        public void actionPerformed(ActionEvent ae){
//            userTextField.setText( AllModelsForView.aDeepThoughtModel.getThought() );
//        }
//    }


    /*
     * ViewUserActions constructor used for wiring user actions to GUI elements
     */
    public ViewUserActions() {
        /*
         * Step 2 of 2 for writing user actions.
         * -------------------------------------
         *
         * Once a private inner class has been written for a user action, you
         * can wire it to a GUI element (i.e. one of GUI elements you drew in
         * the DrawnView class and which you gave a memorable public variable
         * name!).
         *
         * Use the following as a template for wiring more user actions.
         */
        goToInputBtn.addActionListener(new GoToScreen("input"));
        goToRequirementsBtn.addActionListener(new GoToScreen("requirements"));
        startBtn.addActionListener(new FillCalendar());
        ListSelectionModel cellSelectionModel = table.getSelectionModel();
        ListSelectionModel colM = table.getColumnModel().getSelectionModel();
        GetDayChangeAction g = new GetDayChangeAction();
        colM.addListSelectionListener(g);
        cellSelectionModel.addListSelectionListener(g);
        backBtn.addActionListener(new ChangeMonth('l'));
        forwardBtn.addActionListener(new ChangeMonth('r'));
        addBtn.addActionListener(new AddEvent());
        clearBtn.addActionListener(new ClearEvents());
//        copyTxtFromScn1.addActionListener(new CopyTxtFromScrn1Action());
    }
}
