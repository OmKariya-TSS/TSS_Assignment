package com.tss.test;

import com.tss.model.Student;

import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentsTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n;
        while(true) {
            System.out.println("enter number of students :");
            n = scanner.nextInt();
            if (n > 10) {
                System.out.println("please enter less than 10 . max 10 students allowed");
            }
            else{
                break;
            }
        }
        Student[] students = new Student[n];
        for(int i=0;i<n;i++){
            students[i]= new Student();
        }

        System.out.println("Welcome to Student OOP App");

        studentMenu(students);
    }
    public static Student createStudent(Student[] students) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter details of student:");

        int id;
        while (true) {
            try {
                System.out.println("Enter ID of student (positive number only):");
                id = scanner.nextInt();

                boolean exists = false;
                for (Student s : students) {
                    if (s != null && s.getId() == id) {
                        exists = true;
                        break;
                    }
                }

                if (exists) {
                    System.out.println("Student with this ID already exists. Enter a different ID.");
                    continue;
                }

                scanner.nextLine();
                break;

            } catch (InputMismatchException e) {
                System.out.println("Invalid input! ID must be a number.");
                scanner.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + " Try again.");
            }
        }

        String name;
        while (true) {
            try {
                System.out.println("Enter name of student:");
                name = scanner.nextLine();
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + " Try again.");
            }
        }

        String course;
        while (true) {
            try {
                System.out.println("Enter course of student:");
                course = scanner.nextLine();
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + " Try again.");
            }
        }

        long totalFees;
        while (true) {
            try {
                System.out.println("Enter total fees of student:");
                totalFees = scanner.nextLong();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Enter a number.");
                scanner.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + " Try again.");
            }
        }

        long feesPaid;
        while (true) {
            try {
                System.out.println("Enter fees paid by student:");
                feesPaid = scanner.nextLong();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Enter a number.");
                scanner.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + " Try again.");
            }
        }

        System.out.println("\nStudent created successfully!");
        return new Student(id);
    }

    public static void studentMenu(Student[] students){
        Scanner scanner = new Scanner(System.in);
        System.out.println("the menu is given below :");
        System.out.println("enter 1 for add student");
        System.out.println("enter 2 paying fees");
        System.out.println("enter 3 for viewing pending fees");
        System.out.println("enter 4 displaying a student");
        System.out.println("enter 5 for display all students");
        System.out.println("enter 6 for exit");
        int n = scanner.nextInt();
        int count = 0;
        switch(n){
            case 1:
                for (Student s : students) {
                    if (s != null && s.getId() > 0) {
                        count++;
                    }
                }

                if (count >= students.length) {
                    System.out.println("All students are already created");
                    studentMenu(students);
                    return;
                }

                Student newStudent = createStudent(students);
                if (newStudent == null) {
                    return;
                }
                for (int i = 0; i < students.length; i++) {
                    if (students[i] == null || students[i].getId() <= 0) {
                        students[i] = newStudent;
                        break;
                    }
                }
                count++;
                if (count == students.length) {
                    System.out.println("All students are created");
                }

                studentMenu(students);
                break;
            case 2:
                System.out.println("Enter student ID to pay fees:");
                int payFeesstudentId = scanner.nextInt();
                payFeesById(payFeesstudentId, students);
                studentMenu(students);
                break;
            case 3:
                System.out.println("Enter student ID to view pending fees:");
                int studentId = scanner.nextInt();
                showPendingFeesById(studentId, students);
                studentMenu(students);
                break;
            case 4:
                System.out.println("displaying all students");
                System.out.println("Available students:");
                for (Student s : students) {
                    if (s != null) {
                        System.out.println("ID: " + s.getId() + " | Name: " + s.getName());
                    }
                }
                System.out.println("Enter student ID to display details:");
                int displayStudentId = scanner.nextInt();
                displayStudentById(displayStudentId, students);
                studentMenu(students);
                break;
            case 5 :
                System.out.println("displaying all students");
                System.out.println("Available students:");
                for (Student s : students) {
                    if (s.getId() != 0) {
                        s.displayProfile(s);
                        System.out.println(" ---------- ");
                    }
                }
                studentMenu(students);
                break;
            case 6:
                System.out.println("you are on exit");
                break;
            default:
                System.out.println("please enter values within 1 to 5");
                studentMenu(students);
                break;
        }

    }
    public static void payFeesById(int id, Student[] students) {
        Scanner scanner = new Scanner(System.in);
        long amount;
        Student student = null;
        for (Student s : students) {
            if (s != null && s.getId() == id) {
                student = s;
                break;
            }
        }
        if (student == null) {
            System.out.println("Student with ID " + id + " not found.");
            return;
        }

        while (true) {
            try {
                System.out.println("\nEnter amount to pay:");
                amount = scanner.nextLong();
                student.payFees(amount);
                System.out.println("Fees paid successfully!");
                break;

            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Enter a number.");
                scanner.nextLine();

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());

            } catch (Exception e) {
                System.out.println(e.getMessage());
                break;
            }
        }
    }
    public static void displayStudentById(int id, Student[] students) {
        for (Student s : students) {
            if (s != null && s.getId() == id) {
                s.displayProfile(s);
                return;
            }
        }
        System.out.println("Student with ID " + id + " not found.");
    }
    public static void showPendingFeesById(int id, Student[] students) {

        for (Student s : students) {
            if (s != null && s.getId() == id) {
                long pending = s.getPendingFees();
                System.out.println("Pending fees: " + pending);
                return;
            }
        }

        System.out.println("Student with ID " + id + " not found.");
    }


}
