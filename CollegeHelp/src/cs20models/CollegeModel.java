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
public class CollegeModel {

    public String name;
    public String program;
    public int essays;
    public String dueDate;
    public String requirements;
    public String oldDate;
    public boolean added;
    public CollegeModel() {
        this.name = "";
        this.program = "";
        this.essays = -1;
        this.dueDate = "";
        this.requirements = "";
        this.oldDate = "";
        this.added = false;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProgram() {
        return this.program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public int getEssays() {
        return this.essays;
    }

    public void setEssays(int essays) {
        this.essays = essays;
    }

    public String getDueDate() {
        return this.dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public void setAll(String name, String program, int essays, String dueDate) {
        this.name = name;
        this.program = program;
        this.essays = essays;
        this.dueDate = dueDate;
    }

    public String toString() {
        return this.name;
    }
    public void setRequirements(String x){
        this.requirements = x;
    }
    public String getRequirements(){
        return this.requirements;
    }
    public String getOldDate(){
        return this.oldDate;
    }
    public void setOldDate(String oldDate){
        this.oldDate = oldDate;
    }
    public void setAdded(boolean x){
        this.added = x;
    }
    public boolean getAdded(){
        return this.added;
    }
}
