import java.util.Scanner;

public class Module {
    //creating scanner object
    static Scanner sc = new Scanner(System.in);

    //creating student object
    Student student = new Student();

    //Member variables
    private int mark1;
    private int mark2;
    private int mark3;
    private int total;
    private double average;
    private String grade;

    //Constructor for module
    public Module(int mark1, int mark2, int mark3) {
        this.mark1 = mark1;
        this.mark2 = mark2;
        this.mark3 = mark3;
        this.total = this.mark1 + this.mark2 + this.mark3;
        this.average = total/ 3.0;
        this.grade = calculateGrade();
    }

    //method to calculate grade
    public String calculateGrade() {
        if (average >= 80) {
            return "Distinction";
        } else if (average >= 70) {
            return "Merit";
        } else if (average >= 40) {
            return "Pass";
        } else {
            return "Fail";
        }
    }

    //prompting user for inputs
    public static Module promptModule() {
        //prompting user for mark 1
        int mark1 = Main.getPositiveInt("Please enter mark 1: ");

        //prompting user for mark 2
        int mark2 = Main.getPositiveInt("Please enter mark 2: ");

        //prompting user for mark 3
        int mark3 = Main.getPositiveInt("Please enter mark 3: ");

        //creating new module object
        return new Module(mark1, mark2, mark3);
    }

    //implementing getter methods for average and grade
    public int getMark1() {
        return mark1;
    }

    public int getMark2() {
        return mark2;
    }

    public int getMark3() {
        return mark3;
    }

    public double getAverage() {
        return average;
    }

    public String getGrade() {
        return grade;
    }

    public int getTotal() {
        return total;
    }

}
