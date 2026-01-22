package com.tss.HomeAssignment.test;

import java.util.Scanner;
import com.tss.HomeAssignment.model.Course;
import com.tss.HomeAssignment.model.Student;
import com.tss.HomeAssignment.service.CourseService;
import com.tss.HomeAssignment.service.StudentService;

public class StudentTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int studentLimit = 0;
        while (true) {
            System.out.print("Enter number of students (1 to 10): ");
            String input = scanner.nextLine();
            try {
                studentLimit = Integer.parseInt(input);
                if (studentLimit >= 1 && studentLimit <= 10) {
                    break;
                } else {
                    System.out.println("Error: Number must be between 1 and 10.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid input! Please enter a valid integer.");
            }
        }
        int courseLimit = 0;
        while (true) {
            System.out.print("Enter number of courses (1 to 5): ");
            String input = scanner.nextLine();
            try {
                courseLimit = Integer.parseInt(input);
                if (courseLimit >= 1 && courseLimit <= 5) {
                    break;
                } else {
                    System.out.println("Error: Number must be between 1 and 5.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid input! Please enter a valid integer.");
            }
        }
        StudentService studentService = new StudentService(studentLimit);
        CourseService courseService = new CourseService(courseLimit);
        System.out.println("\nWelcome to student management system");
       StudentMenu(studentService,courseService);
        scanner.close();
    }
    public static void StudentMenu(StudentService studentService,CourseService courseService){
            Scanner scanner = new Scanner(System.in);
            int choice;
            System.out.println("1. Add New Student");
            System.out.println("2. Add New Course");
            System.out.println("3. Assign course to student");
            System.out.println("4. Display Student Profile");
            System.out.println("5. Display All Student Profiles");
            System.out.println("6. Display a Course");
            System.out.println("7. Display All Courses");
            System.out.println("8. Pay Fees (Student ID + Course ID)");
            System.out.println("9. View Pending Fees by Student ID");
            System.out.println("10. Unenroll Course from Student");
            System.out.println("11. Replace Student Course");
            System.out.println("12. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    studentService.createStudent();
                    StudentMenu(studentService, courseService);
                    break;
                case 2:
                    courseService.createCourse();
                    StudentMenu(studentService, courseService);
                    break;
                case 3:
                    System.out.println("Available Students :");
                    studentService.displayAvailableStudents();
                    System.out.print("Enter Student ID: ");
                    int stid = scanner.nextInt();
                    Student student;
                    student = studentService.getStudentById(stid);
                    if (student == null) {
                        System.out.println("Student not found.");
                        StudentMenu(studentService, courseService);
                        break;

                    }
                    System.out.println("Available courses :");
                    courseService.displayAvailableCourses();
                    System.out.print("Enter Course ID: ");
                    int courseId = scanner.nextInt();
                    Course course = courseService.getCourseById(courseId);
                    if (course == null) {
                        System.out.println("Course not found.");
                        StudentMenu(studentService, courseService);
                        break;
                    }
                    if (student.addCourse(course)) {
                        System.out.println("Course Assigned Successfully");
                    }else{
                        System.out.println("course was not added");
                    }
                    StudentMenu(studentService,courseService);
                    break;
                case 4:
                    System.out.println("Available Students :");
                    studentService.displayAvailableStudents();
                    System.out.print("Enter Student ID: ");
                    int sid = scanner.nextInt();
                    student = studentService.getStudentById(sid);
                    if (student != null) {
                        student.displayProfile();
                    } else {
                        System.out.println("Student not found.");
                    }
                    StudentMenu(studentService, courseService);
                    break;
                case 5:
                    studentService.displayAllStudents();
                    StudentMenu(studentService, courseService);
                    break;
                case 6:
                    System.out.println("Available courses :");
                    courseService.displayAvailableCourses();
                    System.out.print("Enter Course ID: ");
                    int cid = scanner.nextInt();
                    course = courseService.getCourseById(cid);
                    if (course != null) {
                        course.displayCourse();
                    } else {
                        System.out.println("Course not found.");
                    }
                    StudentMenu(studentService, courseService);
                    break;
                case 7:
                    courseService.displayAllCourses();
                    StudentMenu(studentService, courseService);
                    break;
                case 8:
                    System.out.println("Available Students :");
                    studentService.displayAvailableStudents();
                    System.out.print("Enter Student ID: ");
                    sid = scanner.nextInt();
                    student = studentService.getStudentById(sid);
                    if (student == null) {
                        System.out.println("Student not found.");
                        StudentMenu(studentService, courseService);
                        break;
                    }
                    System.out.println("Available courses :");
                    courseService.displayAvailableCourses();
                    System.out.print("Enter Course ID: ");
                    cid = scanner.nextInt();
                    course = courseService.getCourseById(cid);
                    if (course == null) {
                        System.out.println("Course not found.");
                        StudentMenu(studentService, courseService);
                        break;
                    }
                    else {
                        System.out.print("Enter fees amount to pay: ");
                        double amount = scanner.nextDouble();
                        student.payFees(amount);
                        System.out.println("Fees paid successfully.");
                    }
                    StudentMenu(studentService, courseService);
                    break;
                case 9:
                    System.out.println("Available Students :");
                    studentService.displayAvailableStudents();
                    System.out.print("Enter Student ID: ");
                    sid = scanner.nextInt();
                    student = studentService.getStudentById(sid);
                    if (student != null) {
                        System.out.println("Pending Fees: " + student.getPendingFees());
                    } else {
                        System.out.println("Student not found.");
                    }
                    StudentMenu(studentService, courseService);
                    break;
                case 10:
                    System.out.println("Available Students :");
                    studentService.displayAvailableStudents();
                    System.out.print("Enter Student ID: ");
                    sid = scanner.nextInt();
                    student = studentService.getStudentById(sid);
                    if (student == null) {
                        System.out.println("Student not found.");
                        StudentMenu(studentService, courseService);
                        break;
                    }
                    System.out.println("Courses enrolled by student:");
                    for (Course c : student.getCourses()) {
                        if (c != null) {
                            c.displayCourseOverview();
                        }
                    }
                    System.out.print("Enter Course ID to remove: ");
                    cid = scanner.nextInt();
                    student.removeCourse(cid);
                    StudentMenu(studentService, courseService);
                    break;
                case 11:
                    System.out.println("Available Students :");
                    studentService.displayAvailableStudents();
                    System.out.print("Enter Student ID: ");
                    sid = scanner.nextInt();
                    student = studentService.getStudentById(sid);
                    if (student == null) {
                        System.out.println("Student not found.");
                        StudentMenu(studentService, courseService);
                        break;
                    }
                    System.out.println("Student's current courses:");
                    for (Course c : student.getCourses()) {
                        if (c != null) {
                            c.displayCourseOverview();
                        }
                    }
                    System.out.print("Enter OLD Course ID: ");
                    int oldCourseId = scanner.nextInt();
                    System.out.println("Available New Courses:");
                    courseService.displayAvailableCourses();
                    System.out.print("Enter NEW Course ID: ");
                    int newCourseId = scanner.nextInt();
                    Course newCourse = courseService.getCourseById(newCourseId);
                    if(newCourseId==oldCourseId){
                        System.out.println("cant replace the course with the same course id");
                        StudentMenu(studentService,courseService);
                    }
                    if (newCourse == null) {
                        System.out.println("New course not found.");
                        StudentMenu(studentService, courseService);
                        break;
                    }
                    student.replaceCourse(oldCourseId, newCourse);
                    StudentMenu(studentService, courseService);
                    break;
                case 12:
                    System.out.println("Exiting program...");
                    //StudentMenu(studentService,courseService);
                    break;
                default:
                    System.out.println("Invalid choice.");
                    StudentMenu(studentService, courseService);
            }
    }
}

