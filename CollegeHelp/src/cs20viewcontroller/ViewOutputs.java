package cs20viewcontroller;

import cs20models.AllModelsForView;
import java.awt.Container;
import cs20models.CollegeModel;

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
    public void updateInfoList (){
        collegeList.setListData(theInfoModel.toArray());
    }
    public void updateFields(String name){
        CollegeModel aCollegeModel = theInfoModel.getCollege(name);
       
        collegeNameField.setText(aCollegeModel.getName());
        programField.setText(aCollegeModel.getProgram());
        essaysField.setText(aCollegeModel.getEssays() + "");
        deadlineField.setText(aCollegeModel.getDueDate());
    }
    public void clearFields(){
        collegeNameField.setText("");
        programField.setText("");
        essaysField.setText("");
        deadlineField.setText("");
    }
    
}
