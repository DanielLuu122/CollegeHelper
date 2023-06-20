/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs20models;

/**
 *
 * @author danie
 */
public class InfoModel {
    public CollegeModel[] collegeList;
    public int numOfCollege;
    public InfoModel (){
        this.collegeList = new CollegeModel[100];
        this.numOfCollege = 0;
    }
    public void addCollege (CollegeModel aCollege){
        if (numOfCollege > 100){
            return;
        }
        this.collegeList[numOfCollege] = aCollege;
        numOfCollege++;
    }
    public CollegeModel getCollege(String name){
        for (int i = 0; i < numOfCollege; i++){
            if (name.equals(collegeList[i].getName())){
                return collegeList[i];
            }               
        }
        return null;
    } 
    public CollegeModel[] toArray(){
        CollegeModel [] unbufferedCollegeList = new CollegeModel [numOfCollege];
        for ( int i = 0; i < numOfCollege; i++){
            unbufferedCollegeList[i] = collegeList[i];
        }
        return unbufferedCollegeList;
    }
}
