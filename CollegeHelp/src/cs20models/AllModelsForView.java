/*
 * The classes representing the model is organized into the cs20models package.
 * 
 * These are classes that represent (i.e. "models") the underlying data needed
 * for your program to run; as well, these classes model what can be done to
 * the data (e.g. an Elevator class may model an object with information of 
 * which floor the car is on, and contains methods for moving the car to 
 * another floor.  There may be other classes to help model other aspects of
 * the problem or situation too).
 * 
 * Model classes shouldn't know anything about the View or Controller classes.
 * It's a "pure" representation of the underlying problem or situation your
 * program solves or handles.
 * 
 * The Model classes are manipulated by the Controller classes.  Data stored
 * in Model classes are displayed whenever the View classes decides to do so.
 * So Model classes are passive (active Model classes would need some kind of
 * event or notification system, which you are not expected to know how to use).
 * 
 * The View and Controller classes are in the cs20viewcontroller package.
 * 
 * A sample model class is provided in the form of the DeepThoughtModel.
 * Go ahead and modify that class.
 * 
 * You can also make more classes in the cs20models package to better model
 * the problem or situation for your program.  But if you do, you will need
 * to list them below, inside the AllModelsForView class.  That's to inform
 * the View and Controller classes that these models are available to be used
 * by them.
 */
package cs20models;

import java.awt.Container;
import javax.swing.JFrame;

/**
 * List out every model class you create here that will be directly used by your
 * GUI (i.e. the View and Controller classes) as instance variables.
 *
 * The list is in the form of a public instance variable assigned with the class
 * constructed as your GUI requires.
 *
 * HINT: Chances are, if you have a "container" class that contains a hundred
 * (or whatever amount) of other individual objects, you'll just need to list
 * the "container" class and not the individual objects.
 *
 * @author cheng
 */
public class AllModelsForView extends javax.swing.JFrame {

    public static cs20viewcontroller.ViewUserActions mainFrame; // NO "new ViewUserActions()" constructor here - mainFrame is SPECIAL!
    public static Container mainContentPane;

    public static calendar.ViewUserActions calendar = new calendar.ViewUserActions();
    public static requirements.ViewUserActions requirements = new requirements.ViewUserActions();

    public static void goToScreen(String screen) {
        if (screen.equals("calendar")) {
            // one way is:
            //this.setContentPane(new screen2.DrawnView().getContentPane());
            // but problem here is that every time you go to screen 2,
            // you get a BRAND NEW one.

            // so instead, save a single new one as an instance variable
            // in this class, then use the saved one, e.g.:
            AllModelsForView.mainFrame.setContentPane(AllModelsForView.calendar.getContentPane());
            //AllModelsForView.screen2.updateThoughtDisplayed(); // go to screen, and auto update data displayed
            AllModelsForView.mainFrame.pack();
        }
        if (screen.equals("input")) {
            AllModelsForView.mainFrame.setContentPane(AllModelsForView.mainContentPane);
            AllModelsForView.mainFrame.pack();
        }
        if (screen.equals("requirements")) {
            AllModelsForView.mainFrame.setContentPane(AllModelsForView.requirements.getContentPane());
            //AllModelsForView.screen2.updateThoughtDisplayed(); // go to screen, and auto update data displayed
            AllModelsForView.mainFrame.pack();
        }
    }

    // big caveat here is that every DrawnView in every package
    // gets a DIFFERENT copy of the instance variables here,
    // so if you want any data shared globally, the variable
    // here must be static
    public static CollegeModel theCollegeModel = new CollegeModel();
    public static InfoModel theInfoModel = new InfoModel();
    public static DayModel theDayModel = new DayModel();
    public static CalendarModel theCalendarModel = new CalendarModel();
}
