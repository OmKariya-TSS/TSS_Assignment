package com.tss.HomeAssignment.test;

import java.util.Scanner;
import com.tss.HomeAssignment.model.Course;
import com.tss.HomeAssignment.model.Student;
import com.tss.HomeAssignment.service.CourseService;
import com.tss.HomeAssignment.service.StudentService;

public class StudentTest {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int studentLimit = getValidatedInt(scanner, "Enter number of students (1 to 10): ", 1, 10);
        int courseLimit = getValidatedInt(scanner, "Enter number of courses (1 to 5): ", 1, 5);

        StudentService studentService = new StudentService(studentLimit);
        CourseService courseService = new CourseService(courseLimit);

        System.out.println("\nWelcome to Student Management System");

        boolean exit = false;

        while (!exit) {

            displayMenu();
            int choice = getValidatedInt(scanner, "Enter your choice: ", 1, 12);

            switch (choice) {

                case 1:
                    studentService.createStudent();
                    break;

                case 2:
                    courseService.createCourse();
                    break;

                case 3:
                    assignCourse(scanner, studentService, courseService);
                    break;

                case 4:
                    viewStudentProfile(scanner, studentService);
                    break;

                case 5:
                    studentService.displayAllStudents();
                    break;

                case 6:
                    viewCourse(scanner, courseService);
                    break;

                case 7:
                    courseService.displayAllCourses();
                    break;

                case 8:
                    payFees(scanner, studentService);
                    break;

                case 9:
                    viewPendingFees(scanner, studentService);
                    break;

                case 10:
                    removeCourse(scanner, studentService);
                    break;

                case 11:
                    replaceCourse(scanner, studentService, courseService);
                    break;

                case 12:
                    System.out.println("Exiting program...");
                    exit = true;
                    break;
            }
        }
    }


    private static void displayMenu() {
        System.out.println("\n-------- MENU --------");
        System.out.println("1. Add New Student");
        System.out.println("2. Add New Course");
        System.out.println("3. Assign Course to Student");
        System.out.println("4. Display Student Profile");
        System.out.println("5. Display All Students");
        System.out.println("6. Display a Course");
        System.out.println("7. Display All Courses");
        System.out.println("8. Pay Fees");
        System.out.println("9. View Pending Fees");
        System.out.println("10. Unenroll Course");
        System.out.println("11. Replace Course");
        System.out.println("12. Exit");
    }


    private static int getValidatedInt(Scanner scanner, String msg, int min, int max) {
        while (true) {
            System.out.print(msg);
            try {
                int value = Integer.parseInt(scanner.nextLine());
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.println("Enter number between " + min + " and " + max);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a number.");
            }
        }
    }


    private static void assignCourse(Scanner scanner, StudentService ss, CourseService cs) {

        ss.displayAvailableStudents();
        int sid = getValidatedInt(scanner, "Enter Student ID: ", 1, Integer.MAX_VALUE);

        Student student = ss.getStudentById(sid);
        if (student == null) return;

        cs.displayAvailableCourses();
        int cid = getValidatedInt(scanner, "Enter Course ID: ", 1, Integer.MAX_VALUE);

        Course course = cs.getCourseById(cid);
        if (course != null) {
            student.addCourse(course);
        }
    }

    private static void viewStudentProfile(Scanner scanner, StudentService ss) {
        ss.displayAvailableStudents();
        int sid = getValidatedInt(scanner, "Enter Student ID: ", 1, Integer.MAX_VALUE);

        Student student = ss.getStudentById(sid);
        if (student != null) {
            student.displayProfile();
        }
    }

    private static void viewCourse(Scanner scanner, CourseService cs) {
        cs.displayAvailableCourses();
        int cid = getValidatedInt(scanner, "Enter Course ID: ", 1, Integer.MAX_VALUE);

        Course course = cs.getCourseById(cid);
        if (course != null) {
            course.displayCourse();
        }
    }

    private static void payFees(Scanner scanner, StudentService ss) {
        ss.displayAvailableStudents();
        int sid = getValidatedInt(scanner, "Enter Student ID: ", 1, Integer.MAX_VALUE);

        Student student = ss.getStudentById(sid);
        if (student == null) return;

        System.out.print("Enter amount to pay: ");
        try {
            double amount = Double.parseDouble(scanner.nextLine());
            student.payFees(amount);
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount.");
        }
    }

    private static void viewPendingFees(Scanner scanner, StudentService ss) {
        ss.displayAvailableStudents();
        int sid = getValidatedInt(scanner, "Enter Student ID: ", 1, Integer.MAX_VALUE);

        Student student = ss.getStudentById(sid);
        if (student != null) {
            System.out.println("Pending Fees: " + student.getPendingFees());
        }
    }

    private static void removeCourse(Scanner scanner, StudentService ss) {
        ss.displayAvailableStudents();
        int sid = getValidatedInt(scanner, "Enter Student ID: ", 1, Integer.MAX_VALUE);

        Student student = ss.getStudentById(sid);
        if (student == null) return;

        for (Course c : student.getCourses()) {
            if (c != null) c.displayCourseOverview();
        }

        int cid = getValidatedInt(scanner, "Enter Course ID to remove: ", 1, Integer.MAX_VALUE);
        student.removeCourse(cid);
    }

    private static void replaceCourse(Scanner scanner, StudentService ss, CourseService cs) {

        ss.displayAvailableStudents();
        int sid = getValidatedInt(scanner, "Enter Student ID: ", 1, Integer.MAX_VALUE);

        Student student = ss.getStudentById(sid);
        if (student == null) return;

        for (Course c : student.getCourses()) {
            if (c != null) c.displayCourseOverview();
        }

        int oldCid = getValidatedInt(scanner, "Enter OLD Course ID: ", 1, Integer.MAX_VALUE);
        cs.displayAvailableCourses();
        int newCid = getValidatedInt(scanner, "Enter NEW Course ID: ", 1, Integer.MAX_VALUE);

        if (oldCid == newCid) {
            System.out.println("Old and new course cannot be same.");
            return;
        }

        Course newCourse = cs.getCourseById(newCid);
        if (newCourse != null) {
            student.replaceCourse(oldCid, newCourse);
        }
    }
}
