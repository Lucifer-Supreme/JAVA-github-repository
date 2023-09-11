import java.util.Scanner;
//This java program is a student grades calculator , it does the following
//Input: Take marks obtained (out of 100) in each subject.
//Calculate Total Marks: Sum up the marks obtained in all subjects.
//Calculate Average Percentage: Divide the total marks by the total number of subjects to get the
//average percentage.
//Grade Calculation: Assign grades based on the average percentage achieved.
//Display Results: Show the total marks, average percentage, and the corresponding grade to the user

public class student_grade_calculator_CodSoft {
    public static void main(String[] args) {
        System.out.println("\nWelcome To Student Grade Calculator\n");

        Scanner sc = new Scanner(System.in);


        System.out.println("Enter Student's name -");
        String name = sc.nextLine();

        System.out.println("Enter marks obtained in Maths out of 100-");
        int maths = sc.nextInt();
        if (maths>100 || maths<0){
            System.out.println("Invalid input");
            System.exit(1);
        }

        System.out.println("Enter marks obtained in Science out of 100-");
        int science = sc.nextInt();
        if (science>100 || science<0){
            System.out.println("Invalid input");
            System.exit(1);
        }

        System.out.println("Enter marks obtained in Social out of 100-");
        int social = sc.nextInt();
        if (social>100 || social<0){
            System.out.println("Invalid input");
            System.exit(1);
        }

        System.out.println("Enter marks obtained in English out of 100-");
        int english = sc.nextInt();
        if (english>100 || english<0){
            System.out.println("Invalid input");
            System.exit(1);
        }

        System.out.println("Enter marks obtained in Sanskrit out of 100-");
        int sanskrit = sc.nextInt();
        if (sanskrit>100 || sanskrit<0){
            System.out.println("Invalid input");
            System.exit(1);
        }

        System.out.println("Enter marks obtained in Extra Curriculum out of 100-");
        int ec = sc.nextInt();
        if (ec>100 || ec<0){
            System.out.println("Invalid input");
            System.exit(1);
        }

        //AVERAGE CALCULATION

        int average = (maths+science+social+english+sanskrit+ec)/6;
        int total = maths+science+social+english+sanskrit+ec

        //GRADE ALLOCATION

        String grade ;

        if (average>95){
            grade = "A++";
        }
        else if (average>90) {
            grade = "A";
        }
        else if (average>80) {
            grade = "B++";
        }
        else if (average>75) {
            grade = "B";
        }
        else if (average>60) {
            grade = "C++";
        }
        else if (average>=50) {
            grade = "C";
        }
        else {
            grade = "Fail";
        }

        System.out.println("\n\n**********************************************************************************\n\n");
        System.out.println("Student name -> "+name);
        System.out.println("Maths marks -> "+maths);
        System.out.println("Science marks -> "+science);
        System.out.println("Social marks -> "+social);
        System.out.println("English marks -> "+english);
        System.out.println("Sanskrit marks -> "+sanskrit);
        System.out.println("Extra Curriculum marks -> "+ec);
        System.out.println("Total marks out of 600 -> "+total);
        System.out.println("Average Parcentage -> "+average+"%");
        System.out.println("\nGrade acheived -> "+grade);
        System.out.println("\n\n**********************************************************************************\n\n");



    }
}
