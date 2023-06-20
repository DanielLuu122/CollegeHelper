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
package cs20viewcontroller;

import cs20models.AllModelsForView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import cs20models.CollegeModel;

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
    private class GoToScreen implements ActionListener {

        public String location;

        public GoToScreen(String x) {
            this.location = x;
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            AllModelsForView.goToScreen(location);
        }
    }

    private class ClearAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            clearFields();
        }
    }

    private class GetCollegeInfoAction implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent arg0) {
            if (collegeList.getSelectedValue() != null) {
                updateFields(collegeList.getSelectedValue().toString());
                //System.out.println(collegeList.getSelectedValue().toString());
            }
        }

    }

    private class SetCollegeInfoAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            String name = collegeNameField.getText();
            if (name.equals("")) {
                return;
            }
            CollegeModel aCollegeModel = theInfoModel.getCollege(name);
            if (aCollegeModel != null) {
                aCollegeModel.setName(name);

                String program = programField.getText();
                aCollegeModel.setProgram(program);

                String essaysStr = essaysField.getText();
                if (essaysStr.equals("")) {
                    essaysStr = "0";
                }
                try {
                    Integer.parseInt(essaysStr);
                    int essays = Integer.parseInt(essaysStr);
                    aCollegeModel.setEssays(essays);
                } catch (final NumberFormatException e) {
                    essaysField.setText("invalid int");
                }

                String dueDate = deadlineField.getText();
                boolean isNum = true;
                for (char ch : dueDate.toCharArray()) {
                    if (Character.isLetter(ch)) {
                        isNum = false;
                    }
                }
                if (dueDate.length() == 10) {
                    if (dueDate.charAt(2) == '/' && dueDate.charAt(5) == '/' && isNum == true) {
                        if (aCollegeModel.getDueDate() != dueDate) {
                            aCollegeModel.setOldDate(aCollegeModel.getDueDate());
                            aCollegeModel.setAdded(false);
                        }
                        aCollegeModel.setDueDate(dueDate);
                    } else {
                        deadlineField.setText("invalid format");
                        aCollegeModel.setDueDate("invalid format");
                    }
                } else {
                    deadlineField.setText("invalid format");
                    aCollegeModel.setDueDate("invalid format");
                }

            } else {
                CollegeModel newCollege = new CollegeModel();
                newCollege.setName(collegeNameField.getText());
                newCollege.setProgram(programField.getText());
                String essaysStr = essaysField.getText();
                if (essaysStr.equals("")) {
                    essaysStr = "0";
                }
                try {
                    Integer.parseInt(essaysStr);
                    int essays = Integer.parseInt(essaysStr);
                    newCollege.setEssays(essays);
                } catch (final NumberFormatException e) {
                    essaysField.setText("invalid int");
                }
                String dueDate = deadlineField.getText();
                boolean isNum = true;
                for (char ch : dueDate.toCharArray()) {
                    if (Character.isLetter(ch)) {
                        isNum = false;
                    }
                }
                if (dueDate.length() == 10) {
                    if (dueDate.charAt(2) == '/' && dueDate.charAt(5) == '/' && isNum == true) {
                        newCollege.setDueDate(dueDate);
                    } else {
                        deadlineField.setText("invalid format");
                        newCollege.setDueDate("invalid format");
                    }
                } else {
                    deadlineField.setText("invalid format");
                    newCollege.setDueDate("invalid format");
                }
                theInfoModel.addCollege(newCollege);
            }
            updateInfoList();
        }
    }

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
        goToCalendarBtn.addActionListener(new GoToScreen("calendar"));
        goToRequirementsBtn1.addActionListener(new GoToScreen("requirements"));
        setBtn.addActionListener(new SetCollegeInfoAction());
        collegeList.addListSelectionListener(new GetCollegeInfoAction());
        clearBtn.addActionListener(new ClearAction());

        // main frame built with the master ViewUserActions constructor has to 
        // record a reference to the newly constructed object in AllModelsForView
        AllModelsForView.mainFrame = this;
        AllModelsForView.mainContentPane = this.getContentPane(); // because we'll be swaping mainFrame's content pane around, we need to store a reference to the original one!
    }

}
