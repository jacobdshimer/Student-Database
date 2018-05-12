/*
This is the Student.java file, it is the contains the logic behind the student database.  It contains two methods and two
sets of getters and setters.

Author: Shimer, Jacob D.

*/

import java.text.DecimalFormat;

public class Student {
    private String name, major;
    private double numOfCredits, totalQualityPoints;
    DecimalFormat numberFormat = new DecimalFormat("#.00");

    //When a Student object is initialized, only name and major gets set by the user.  Everything else gets
    //automatically set to 0
    Student(String name, String major) {
        this.name = name;
        this.major = major;
        this.numOfCredits = 0;
        this.totalQualityPoints = 0;
    }

    //This method contains the logic for when updating a student record
    void courseCompleted (String grade, String hours){
        setNumOfCredits(getNumOfCredits() + Double.parseDouble(hours));
        int numericGrade;
        //This switch is needed in order to change the letter grade from the GUI to a numerical grade
        switch (grade) {
            case "A":
                numericGrade = 4;
                break;
            case "B":
                numericGrade = 3;
                break;
            case "C":
                numericGrade = 2;
                break;
            case "D":
                numericGrade = 1;
                break;
            default:
                numericGrade = 0;
                break;
        }
        //This sets the total quality points
        setTotalQualityPoints(getTotalQualityPoints() + (Integer.parseInt(hours) * numericGrade));
    }

    private double getNumOfCredits() {
        return numOfCredits;
    }

    private void setNumOfCredits(double numOfCredits) {
        this.numOfCredits = numOfCredits;
    }

    private double getTotalQualityPoints() {
        return totalQualityPoints;
    }

    private void setTotalQualityPoints(double totalQualityPoints) {
        this.totalQualityPoints = totalQualityPoints;
    }

    //This overrides toString
    @Override
    public String toString() {
        //If the student DB is essentially blank (i.e. you just created a student) it sets the GPA to 4.0
        if (getTotalQualityPoints() == 0 && getNumOfCredits() == 0) {
            return("Name: " + name + "\nMajor: " + major + "\nGPA: 4.0");
        } else {
            return("Name: " + name + "\nMajor: " + major + "\nGPA: " + (numberFormat.format(getTotalQualityPoints()/getNumOfCredits())));
        }
    }
}
