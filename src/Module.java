import java.util.Scanner;

public class Module {
    //creating scanner object
    static Scanner sc = new Scanner(System.in);
    protected Module mod;

    //Member variables
    private int mark1;
    private int mark2;
    private int mark3;
    private double average;
    private String grade;

    //Constructor
    public Module() {
        this.mark1 = mark1;
        this.mark2 = mark2;
        this.mark3 = mark3;
        this.average = (mark1 + mark2 + mark3) / 3.0;
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
        System.out.print("Please enter mark 1: ");
        int mark1 = sc.nextInt();
        sc.nextLine();

        //prompting user for mark 2
        System.out.print("Please enter mark 2: ");
        int mark2 = sc.nextInt();
        sc.nextLine();

        //prompting user for mark 3
        System.out.print("Please enter mark 3: ");
        int mark3 = sc.nextInt();
        sc.nextLine();

        //creating new module object
        return new Module();
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



}
