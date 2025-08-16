package j17.student;

import java.util.Scanner;

public class StudentHello {

    public static void main(String[] args) {

        int studentAge = 15;
        double studentGpa = 3.5;

        boolean hasPerfectAttendance = true;
        String studentName = "Somendu Maiti";
        String firstNameString = "Somendu";
        String secondNameString = "Maiti";

        char firstName = firstNameString.charAt(0);
        char secondName = secondNameString.charAt(0);

        System.out.println("Student Age: " + studentAge);
        System.out.println("Student GPA: " + studentGpa);
        System.out.println("First Name: " + firstName);
        System.out.println("Second Name: " + secondName);
        System.out.println("Has Perfect Attendance: " + hasPerfectAttendance);
        System.out.println("Student Name: " + studentName);
        System.out.println("First Name String: " + firstNameString);
        System.out.println("Second Name String: " + secondNameString);

        System.out.println(firstNameString + " " + secondNameString + " has GPA of : "+studentGpa);


        System.out.print("Enter new GPA: ");

        Scanner input = new Scanner(System.in);
        studentGpa = input.nextDouble();

        System.out.println(firstNameString + " " + secondNameString + " has Updated GPA of : "+studentGpa);
    }

}
