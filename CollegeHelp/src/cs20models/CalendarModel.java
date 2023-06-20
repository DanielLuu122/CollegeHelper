/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs20models;

import java.awt.Component;
import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;

/**
 *
 * @author danie
 */
public class CalendarModel {

    public int days[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    public String months[] = {"January", "Febuary", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December",};
    public int yearStart = LocalDate.of(Calendar.getInstance().get(Calendar.YEAR), Month.JANUARY, 1).getDayOfWeek().getValue();
    public int dow[] = new int[12];
    public DayModel[] dayList;
    public boolean init = false;

    public CalendarModel() {
        this.dow[0] = yearStart - 1;
//        System.out.println(yearStart);
//        System.out.println(this.dow[0]);
        for (int i = 1; i < 12; i++) {
            if (i != 0) {
                this.dow[i] = (this.dow[i - 1] + days[i - 1]) % 7;
            }
        }
        this.dayList = new DayModel[365];
        this.init = false;
    }

    public void setDay(int index, DayModel model) {
        dayList[index] = model;
    }

    public int getIndex(int month, int day) {
        int index = -1;
        String strMonth = getMonth(month);
        for (int i = 0; i < dayList.length; i++) {
            if (dayList[i].day == day && dayList[i].month.equals(strMonth)) {
                index = i;
            }
        }
        return index;
    }

    public DayModel getDay(String month, int day) {
        int index = -1;
        for (int i = 0; i < dayList.length; i++) {
            if (dayList[i].day == day && dayList[i].month.equals(month)) {
                index = i;
            }
        }
        if (index != -1) {
            return dayList[index];
        }
        return null;
    }

    public int getTotalDays(int i) {
        return this.days[i];
    }

    public String getMonth(int i) {
        if (i > 11) {
            return this.months[11];
        }
        if (i < 0) {
            return this.months[0];
        }
        return this.months[i];
    }

    public boolean getInit() {
        return this.init;
    }

    public void setInit(boolean x) {
        this.init = x;
    }

    public int getDow(int month) {
        return this.dow[month];
    }

}
