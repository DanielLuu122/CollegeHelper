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
public class DayModel {

    public int day;
    public String month;
    public String events;
    public String defaults;
    public String colour;
    public DayModel() {
        this.day = -1;
        this.events = "";
        this.defaults = "";
        this.colour = "-";
    }

    public int getDay() {
        return this.day;
    }

    public void setDay(int day) {
        this.day = day;
    }
    public String getEvents(){
        return this.events;
    }
    public void setEvents(String events){
        this.events = events;
    }
    public void addEvents (String events){
        this.events += "\n" + events;
    }
    public String getDefaults(){
        return this.defaults;
    }
    public void addDefaults(String defaults){
        this.defaults += "\n" + defaults;
    }
    public void setDefaults(String x){
        this.defaults = x;
    }
    public String getMonth(){
        return this.month;
    }
    public void setMonth(String month){
        this.month = month;
    }
    @Override
    public String toString(){
        if(this.day == -1){
            return "";
        }
        return this.day + "";
    }
    public String display() {
        if (this.day == -1){
            return "";
        }
        return this.day + "\nDeadlines:" + this.defaults + "\nEvents:" + this.events;
    }
    public String getColour (){
        return this.colour;
    }
    public void setColour (String colour){
        this.colour = colour;
    }
        
}
