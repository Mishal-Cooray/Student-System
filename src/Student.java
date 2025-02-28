import org.w3c.dom.ls.LSOutput;

import java.util.Scanner;

public class Student{

    //creating scanner object
    static Scanner sc = new Scanner(System.in);

    //creating module objects
    Module mod;

    //declaring member variables
    private String name;
    private String ID;


    //constructor for custom data type of Student
    public Student(){
        this.ID = ID;
        this.name = name;
        //adding module to student
        this.mod = mod;
    }


    //method to prompt user for student name
    public static String promptStudentName() {
        //prompting user for student name
        String name;
        //do-while loop added to prevent user from leaving entry blank
        do {
            System.out.print("Please enter name of student: ");
            name = sc.nextLine();
        }while(name.trim().isEmpty());
        return name;
    }


    //method to register student
    public static Student registerStudent() {

        //prompting user for student ID
        String studentID = Main.getString("Please enter student ID: ");

        //prompting user for student name
        String studentName = Main.getString("Please enter name of student: ");

        //getting marks from module class
        Module mod = Module.promptModule();

        //creating new student with given inputs
        Student newStudent = new Student();
        newStudent.ID = studentID;
        newStudent.name = studentName;
        newStudent.mod = mod;

        System.out.println("Student registered successfully!");

        //creating new student object
        return newStudent;
    }

    //creating getters for name and ID
    public String getName() {
        return name;
    }
    public String getID() {
        return ID;
    }

}
