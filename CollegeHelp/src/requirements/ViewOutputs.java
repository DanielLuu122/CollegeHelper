package requirements;

import cs20models.AllModelsForView;
import cs20models.CollegeModel;
import cs20viewcontroller.*;
import requirements.DrawnView;

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
    public void updateRequirements (){
        collegeListR.setListData(theInfoModel.toArray());
    }
     public void updateList(String name){
        CollegeModel aCollegeModel = theInfoModel.getCollege(name);
       editableArea.setText(aCollegeModel.getRequirements());
    }
}
