import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) {
//=======================================================================================
        //IMPLEMENTING MAIN MENU


        //initializing quit variable to keep track of program status
        boolean quit = false;
        //implementing while loop to keep main menu running
        while (!quit) {

            //implementing main menu in single print statement
            System.out.println(BLUE+"MAIN MENU\n" +
                    "1. Check available seats\n" +
                    "2. Register Student\n" +
                    "3. Delete student\n" +
                    "4. Find student\n" +
                    "5. Store student details into file\n" +
                    "6. Load student details from file\n" +
                    "7. View student names\n" +
                    "8. Additional options\n"+
                    "9. Quit program\n"+RESET);


            //prompting user to enter a choice
            int choice = getPositiveInt("Enter your choice: ");

            //switch case to implement the main menu
            switch (choice) {
                case 1:
                    System.out.println("Checking available seats");
                    int availableSeats = checkAvailableSeats();
                    System.out.println("There are " + availableSeats + " seats available");
                    break;

                case 2:
                    System.out.println("Register Student");
                    registerStudent();
                    break;

                case 3:
                    System.out.println("Delete student");
                    deleteStudent();
                    break;

                case 4:
                    System.out.println("Find student");
                    findStudent();
                    break;
                case 5:
                    System.out.println("Store student details into file");
                    storeStudentDetailsIntoFile();
                    break;
                case 6:
                    System.out.println("Load student details from file");
                    loadStudentDetailsFromFile();
                    break;
                case 7:
                    System.out.println("View student names");
                    viewStudentNames();
                    break;
                //---------------------------------------------------------------------------
                //implementing sub menu
                case 8:
                    System.out.println("Other options");
                    //creating nested switch for additional options
                    System.out.println(BLUE+"SUB MENU\n" +
                            "1. Add student\n" +
                            "2. Generate summary\n" +
                            "3. Generate report\n" + RESET);
                    int choice2 = getPositiveInt("Enter your choice: ");
                    switch (choice2) {
                        case 1:
                            System.out.println("Add student");
                            registerStudentObj();
                            break;
                        case 2:
                            System.out.println("Generate summary");
                            generateSummary();
                            break;
                        case 3:
                            System.out.println("Generate report");
                            generateReport();
                            break;
                        default:
                            System.out.println("Invalid choice");
                            break;
                    }
                    break;
                //-------------------------------------------------------------------------
                case 9:
                    System.out.println("Quitting program");
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }

        }

    }

    //==================================================================================================================


    //defining ANSI codes for colours
    public static final String BLUE = "\033[0;34m";
    public static final String RED = "\033[0;31m";
    public static final String RESET = "\033[0m";


    //initializing student object array
    public static Student[] studentsObj = new Student[100];

    //creating scanner object
    static Scanner sc = new Scanner(System.in);

    //initiating student array
    //creating 2d array of size 100 with internal array of size 2
    public static String[][] students = new String[100][2];
    //students[i][0] = student id
    //students[i][1] = student name



    //=================================================================================================================
    //METHODS FOR TASK 1

    //method to check available seats
    public static int checkAvailableSeats() {
        //initializing available seats
        int availableSeats = 0;

        //iterating through students array and counting available seats
        //if student id is null, then seat is available
        for (int i = 0; i < students.length; i++) {
            if (students[i][0]==null) {
                availableSeats++;
            }
        }
        return availableSeats;
    }




    //implementing method to register student
    public static void registerStudent() {

        //checking if there are available seats
        int availableSeats = checkAvailableSeats();
        if(availableSeats<=0) {
            System.out.println("There are no more available seats left");
            return;
        }


        //prompting user for student ID
        String studentID = getString("Please enter student ID: ");

        //prompting user for student name
        String studentName = getString("Please enter name of student: ");

        //finding empty spot in array to fit new student
        for(int i=0; i< students.length; i++){
            if(students[i][0]==null){
                students[i][0] = studentID;
                students[i][1] = studentName;
                System.out.println("Student registered successfully!");
                break;
            }
        }

    }



    //implementing method to delete student
    public static void deleteStudent(){

        int index;
        //prompting user for student index to delete
        do {
            System.out.print("Enter index of student to delete: ");
            index = sc.nextInt();

            //using index+1 because array starts from 0, to make it more user-friendly
            if (students[index+1][0]==null) {
                System.out.println("No record found at this index");
                return;
            }
        }while(index<0 || index>100);

        //deleting student records of that index
        students[index][0] = null;
        students[index][1] = null;

        System.out.println("Record deleted successfully");
    }


    //implementing method to find student
    public static void findStudent() {

        //prompting user to enter student ID
        System.out.print("Enter student ID: ");
        String studentToFind = sc.nextLine();

        //finding details of student in array
        //implementing search in array
        //initializing counter
        int i = 0;
        //creating boolean variable to hold search status
        boolean notFound = true;
        while(i< students.length && notFound){
            if(students[i][0].equals(studentToFind)){
                System.out.println("Student number "+studentToFind+" found. Student name is "+students[i][1]);
                notFound = false;
            }
        }
    }

    //implementing method to store student details into txt file
    public static void storeStudentDetailsIntoFile() {
        //creating file object
        File file = new File("students.txt");
        //creating file writer object
        try {
            FileWriter writer = new FileWriter(file);
            //writing student details into file
            for (int i = 0; i < students.length; i++) {
                if (students[i][0] != null) {
                    writer.write(students[i][0] + " " + students[i][1] + "\n");
                }
            }
            writer.close();
            System.out.println("Student details stored into file successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //implementing method to load student details from txt file
    public static void loadStudentDetailsFromFile() {
        //creating file object
        File file = new File("students.txt");
        //creating file reader object
        try {
            FileReader reader = new FileReader(file);
            //creating buffered reader object
            BufferedReader bufferedReader = new BufferedReader(reader);
            //reading student details from file
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] student = line.split(" ");
                for (int i = 0; i < students.length; i++) {
                    if (students[i][0] == null) {
                        students[i][0] = student[0];
                        students[i][1] = student[1];
                        break;
                    }
                }
            }
            reader.close();
            System.out.println("Student details loaded from file successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //implementing method to view students by name
    public static void viewStudentNames() {

        //sorting students by name alphabetically
        //using bubble sort to sort students by name
        for (int i = 0; i < students.length; i++) {
            for (int j = i + 1; j < students.length; j++) {
                if (students[i][1] != null && students[j][1] != null && students[i][1].compareTo(students[j][1]) > 0) {
                    String tempID = students[i][0];
                    String tempName = students[i][1];
                    students[i][0] = students[j][0];
                    students[i][1] = students[j][1];
                    students[j][0] = tempID;
                    students[j][1] = tempName;
                }
            }
        }

        //printing sorted students by name
        for (int i = 0; i < students.length; i++) {
            if (students[i][0] != null) {
                System.out.println("Index: "+i+" Student ID: " + students[i][0] + " Student Name: " + students[i][1]);
            }
        }
    }


    //======================================================================================================================
    //METHODS FOR TASK 2

    //implementing method to register student in studentObj array
    public static void registerStudentObj() {
        // Iterate over the studentsObj array
        for (int i = 0; i < studentsObj.length; i++) {
            // Check if the current index is null (i.e., no Student object is stored at this index)
            if (studentsObj[i] == null) {
                // If it is null, assign the new Student object to this index
                studentsObj[i] = Student.registerStudent();
                // Break the loop as we've found a place to store our new Student
                break;
            }else if (studentsObj[99]!=null){
                System.out.println("No more available seats left");
                break;
            }
        }
    }

    //implementing method to generate summary
    public static void generateSummary() {

        //counting number of registered students
        int count = 0;
        //iterating through studentsObj array to count registered students
        for (int i = 0; i < studentsObj.length; i++) {
            if (studentsObj[i] != null) {
                count++;
            }
        }

        //checking number of students have marks over 40 per exam
        int countPass = 0;
        //iterating through studentsObj array to count students with marks over 40
        for (int i = 0; i < studentsObj.length; i++) {
            if (studentsObj[i] != null) {
                if (studentsObj[i].mod.getMark1() >= 40 && studentsObj[i].mod.getMark2() >= 40 && studentsObj[i].mod.getMark3() >= 40) {
                    countPass++;
                }
            }
        }

        //printing summary
        System.out.println("Number of registered students: " + count);
        System.out.println("Number of students with marks over 40 in all exams: " + countPass);
    }


    //implementing method to generate report
    //iterating through studentObj array to get values
    public static void generateReport() {

        //implementing bubble sort by average and rearranging studentsObj array
        for (int i = 0; i < studentsObj.length; i++) {
            for (int j = i + 1; j < studentsObj.length; j++) {
                if (studentsObj[i] != null && studentsObj[j] != null && studentsObj[i].mod.getAverage() < studentsObj[j].mod.getAverage()) {
                    Student temp = studentsObj[i];
                    studentsObj[i] = studentsObj[j];
                    studentsObj[j] = temp;
                }
            }
        }


        for (int i = 0; i < studentsObj.length; i++) {
            //checking if array slots are empty and skipping blanks
            if(studentsObj[i] != null) {
                //getting data from object and printing it within blocks
                System.out.println(RED+"========================================="+RESET);
                System.out.println("Name of student: "+studentsObj[i].getName());
                System.out.println("ID of student: "+studentsObj[i].getID());
                System.out.println("Mark 1: "+studentsObj[i].mod.getMark1());
                System.out.println("Mark 2: "+studentsObj[i].mod.getMark2());
                System.out.println("Mark 3: "+studentsObj[i].mod.getMark3());
                System.out.println("Total: "+studentsObj[i].mod.getTotal());
                System.out.println("Average: "+studentsObj[i].mod.getAverage());
                System.out.println("Grade: "+studentsObj[i].mod.getGrade());
                System.out.println(RED+"========================================="+RESET);

            }
        }
    }




//=================================================================================================
    //METHODS FOR ERROR HANDLING

    //implementing method to get positive integer from user
    public static int getPositiveInt(String text) {
        //initialising userInput variable to hold user prompt
        //assigning value of 0 to make program run for some reason
        int userInput = 0;
        do{
            try{
                //prompting user and scanning for input
                System.out.print(text);
                userInput = sc.nextInt();
                sc.nextLine();
            }catch(Exception e){
                System.out.println(RED+"Please enter a valid integer"+RESET);
                //clearing buffer
                sc.next();
                //resetting userInput to reprompt user
                userInput = 0;
            }
        }while(userInput<0 || userInput>100);
        return userInput;
    }

    //implementing method to prompt user for valid string input
    public static String getString(String text){
        String userInput;
        do{
            //prompting user and scanning for input
            System.out.print(text);
            userInput = sc.nextLine();
        }while(userInput.trim().isEmpty()); //checking if user input is blank and looping while it is blank
        return userInput;
    }


}