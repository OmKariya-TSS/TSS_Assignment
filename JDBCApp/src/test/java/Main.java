import com.tss.controller.AddressController;
import com.tss.controller.CourseController;
import com.tss.controller.StudentController;
import com.tss.controller.StudentCourseController;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        StudentCourseController studentCourseController = new StudentCourseController();
        StudentController studentController = new StudentController();
        CourseController courseController = new CourseController();
        AddressController addressController = new AddressController();
        while (!exit) {
            System.out.println("\n--- Student Management Menu ---");
            System.out.println("1. Add Student");
            System.out.println("2. Add Address");
            System.out.println("3: Read All Address");
            System.out.println("4. Add Course");
            System.out.println("5. Assign Course To Student");
            System.out.println("6. Read All Students");
            System.out.println("7. Read All Courses");
            System.out.println("8. Read Students Of Particular Course");
            System.out.println("9. Read Courses Of Particular Student");
            System.out.println("10. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    studentController.addStudent();
                }
                case 2 -> {
                    addressController.addNewAddress();
                }
                case 3 -> {
                    addressController.readAllAddresses();
                }
                case 4 -> {
                    courseController.addCourse();
                }
                case 5 -> {
                    studentCourseController.assignCourse();
                }
                case 6 -> {
                    studentController.readAllStudents();
                }
                case 7 -> {
                    courseController.readAllCourses();
                }
                case 8 -> {
                    studentCourseController.getStudentOfCourses();
                }
                case 9 -> {
                    studentCourseController.getCoursesOfStudent();
                }
                case 10 -> {
                    exit = true;
                    System.out.println("Exiting... Goodbye!");
                }
                default -> System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}