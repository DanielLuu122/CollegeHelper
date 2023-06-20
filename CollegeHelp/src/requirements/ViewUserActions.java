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
package requirements;

import cs20models.AllModelsForView;
import static cs20models.AllModelsForView.theInfoModel;
import cs20models.CollegeModel;
import cs20viewcontroller.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Action;
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
    private class startBtnAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            updateRequirements();
        }
    }

    private class updateBtnAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            String text = editableArea.getText();
            if (collegeListR.getSelectedValue() != null) {
                CollegeModel aCollegeModel = theInfoModel.getCollege(collegeListR.getSelectedValue().toString());
                aCollegeModel.setRequirements(text);
            }
        }
    }

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

    private class GetCollegeInfoAction implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent arg0) {
            updateList(collegeListR.getSelectedValue().toString());
        }

    }
//    
//    private class CopyTxtFromScrn1Action implements ActionListener{
//        @Override
//        public void actionPerformed(ActionEvent ae){
//            userTextField.setText( AllModelsForView.aDeepThoughtModel.getThought() );
//        }
//    }

    private class setRequirements {

    }

    private class getRequirements {

    }

    private class getCollege {

    }

    private class setCollege {

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
        goToInputBtn.addActionListener(new GoToScreen("input"));
        startBtn.addActionListener(new startBtnAction());
        collegeListR.addListSelectionListener(new GetCollegeInfoAction());
        updateBtn.addActionListener(new updateBtnAction());
//        copyTxtFromScn1.addActionListener(new CopyTxtFromScrn1Action());
    }
}
