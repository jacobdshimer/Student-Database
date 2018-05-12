/*
This is the GUI.java file, it is the main file for the program.  It creates the GUI and contains the logic behind
the process button.  It also contains the main() method.

Author: Shimer, Jacob D.

*/

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class GUI {
    //Creating the hashmap for the database
    private HashMap<String,Student> studentDB = new HashMap();

    class GuiFrame extends JFrame {
        GuiFrame(String title, int width, int height) {
            super(title);
            setFrame(width, height);
        }

        void display() {
            setVisible(true);
        }

        void setFrame(int width, int height) {
            setSize(width, height);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }

    }

    private class Panel extends JPanel {
        //All of the parts for the gui
        private JLabel idLabel = new JLabel("ID:");
        private JTextField id = new JTextField();
        private JLabel nameLabel = new JLabel("Name:");
        private JTextField name = new JTextField();
        private JLabel majorLabel = new JLabel("Major:");
        private JTextField major = new JTextField();
        private JLabel chooseLabel = new JLabel("Choose Selection:");
        private String[] choices = { "Insert","Delete","Find","Update" };
        private JComboBox chooseBox = new JComboBox(choices);
        private JButton process = new JButton("Process Request");

        //The choices for when updating a student record
        private String[] grade = {"A","B","C","D","F"};
        private String[] credits = {"3","4","6"};

        private Panel() {
            //Creating the panel for the Labels and the Process button
            JPanel labelPanel = new JPanel();
            labelPanel.setLayout(new GridLayout(5,1));
            labelPanel.add(idLabel);
            labelPanel.add(nameLabel);
            labelPanel.add(majorLabel);
            labelPanel.add(chooseLabel);
            labelPanel.add(process);

            //Creating the panel for the inputs
            JPanel inputPanel = new JPanel();
            inputPanel.setLayout(new GridLayout(5,1));
            inputPanel.add(id);
            inputPanel.add(name);
            inputPanel.add(major);
            inputPanel.add(chooseBox);

            //Creates a third panel for putting everything together
            JPanel allTogether = new JPanel();
            allTogether.setLayout(new GridLayout(1,2,1,4));
            allTogether.add(labelPanel);
            allTogether.add(inputPanel);
            add(allTogether);

            //Adding the actionlistener for the process button
            process.addActionListener(e -> actionPerformed());
        }

        private void actionPerformed() {
            //Creating a new student
            if (chooseBox.getSelectedIndex()==0){
                //Check if ID already exists
                if (!studentDB.containsKey(id.getText())) {
                    //If student doesn't exist:
                    //Creates a mapping of the ID field to the Student object, initialising it with the Name field and the Major field
                    studentDB.put(id.getText(), new Student(name.getText(), major.getText()));
                    JOptionPane.showMessageDialog(null,"Student: " + id.getText() + " was successfully created.",
                            "Record Created",JOptionPane.PLAIN_MESSAGE);
                } else {
                    //If student does exist, display an error
                    JOptionPane.showMessageDialog(null, "Error this student ID already exists.",
                            "Key Error", JOptionPane.ERROR_MESSAGE);
                }
            //Deleting a student
            } else if (chooseBox.getSelectedIndex()==1){
                //Check whether ID exists or not
                if (studentDB.containsKey(id.getText())) {
                    //If it does, remove the key from the map, deleting the values
                    studentDB.remove(id.getText());
                    JOptionPane.showMessageDialog(null,id.getText() + " was successfully deleted.",
                            "Deletion Completed",JOptionPane.PLAIN_MESSAGE);
                } else {
                    //If it doesn't, display error
                    JOptionPane.showMessageDialog(null, "Error this student ID does not exists.",
                            "Key Error", JOptionPane.ERROR_MESSAGE);
                }
            //Finding a student
            } else if (chooseBox.getSelectedIndex()==2) {
                //Check whether ID exists or not
                if (studentDB.containsKey(id.getText())) {
                    //If it does, display information about student
                    JOptionPane.showMessageDialog(null,"Student ID: " + id.getText() + "\n" + String.valueOf(studentDB.get(id.getText())),
                            "Find Successful",JOptionPane.PLAIN_MESSAGE);
                } else {
                    //If it doesn't, display error
                    JOptionPane.showMessageDialog(null, "Error this student ID does not exists.",
                            "Key Error", JOptionPane.ERROR_MESSAGE);
                }
            //Updating a student record
            } else if (chooseBox.getSelectedIndex()==3) {
                //Check whether ID exists or not
                if (studentDB.containsKey(id.getText())){
                    //If the student does exist, get input from user
                    //First, create a JOptionPane asking for the grade, calling the grade variable declared earlier
                    String selectedGrade = String.valueOf(JOptionPane.showInputDialog(null, "Select Grade:",
                            "Select Grade", JOptionPane.QUESTION_MESSAGE, null, grade, grade[0]));
                    //Second, create a JOptionPane asking for the amount of credits, calling the credits variable declared earlier
                    //I decided to use 3, 4, and 6 credits for the possible choices.  You can add more possibilities by editing the earlier variable
                    String selectedCredits = String.valueOf(JOptionPane.showInputDialog(null, "Select Credits:",
                            "Select Credits", JOptionPane.QUESTION_MESSAGE, null, credits, credits[0]));
                    //Creating an instance of the ID's value, which is the Student object.  Then calling the courseCompleted method
                    Student record = studentDB.get(id.getText());
                    record.courseCompleted(selectedGrade,selectedCredits);
                    JOptionPane.showMessageDialog(null,"Update Complete",
                            "Update Successful", JOptionPane.PLAIN_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Error this student ID does not exists.",
                            "Key Error", JOptionPane.ERROR_MESSAGE);
                }

            }

        }
    }

    //Adds the Panel to the Frame
    class Application extends GuiFrame {
        Application(String title, int width, int height) {
            super(title, width, height);
            add(new Panel());
        }

    }

    //the start of the program.  Sets the name, width, and height of the frame before displaying the frame
    public static void main(String[] args){
        Application app = new GUI().new Application("Project 4",300,200);
        app.display();

    }
}
