package com.tss.HomeAssignment.model;

public class Student {

    private int studentId;
    private String name;
    private Course[] courses;
    private double totalFees;
    private double feesPaid;

    public Student(int studentId, String name) {

        if (studentId <= 0) {
            throw new IllegalArgumentException("Student ID must be positive.");
        }

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Student name cannot be empty.");
        }

        if (!name.matches("[a-zA-Z ]+")) {
            throw new IllegalArgumentException("Student name must contain only letters.");
        }

        this.studentId = studentId;
        this.name = name.trim();
        this.courses = new Course[3];
        this.totalFees = 0;
        this.feesPaid = 0;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        if (studentId <= 0) {
            System.out.println("Invalid Student ID.");
            return;
        }
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Name cannot be empty.");
            return;
        }

        if (!name.matches("[a-zA-Z ]+")) {
            System.out.println("Name must contain only letters.");
            return;
        }

        this.name = name.trim();
    }

    public Course[] getCourses() {
        return courses;
    }

    public double getTotalFees() {
        return totalFees;
    }

    public double getFeesPaid() {
        return feesPaid;
    }


    public boolean addCourse(Course course) {

        if (course == null) {
            System.out.println("Invalid course.");
            return false;
        }

        for (Course c : courses) {
            if (c != null && c.getCourseId() == course.getCourseId()) {
                System.out.println("This course is already assigned.");
                return false;
            }
        }

        for (int i = 0; i < courses.length; i++) {
            if (courses[i] == null) {
                courses[i] = course;
                totalFees += course.getCourseFees();
                return true;
            }
        }

        System.out.println("Cannot add more than 3 courses.");
        return false;
    }

    public void removeCourse(int courseId) {

        if (courseId <= 0) {
            System.out.println("Invalid course ID.");
            return;
        }

        for (int i = 0; i < courses.length; i++) {
            if (courses[i] != null && courses[i].getCourseId() == courseId) {
                totalFees -= courses[i].getCourseFees();
                courses[i] = null;

                if (feesPaid > totalFees) {
                    feesPaid = totalFees;
                }

                System.out.println("Course removed successfully.");
                return;
            }
        }

        System.out.println("Course not found for this student.");
    }

    public boolean replaceCourse(int oldCourseId, Course newCourse) {

        if (oldCourseId <= 0 || newCourse == null) {
            System.out.println("Invalid input.");
            return false;
        }

        for (Course c : courses) {
            if (c != null && c.getCourseId() == newCourse.getCourseId()) {
                System.out.println("Student already enrolled in new course.");
                return false;
            }
        }

        for (int i = 0; i < courses.length; i++) {
            if (courses[i] != null && courses[i].getCourseId() == oldCourseId) {

                double oldFees = courses[i].getCourseFees();
                double newFees = newCourse.getCourseFees();

                courses[i] = newCourse;
                totalFees = totalFees - oldFees + newFees;

                double diff = newFees - oldFees;
                if (diff > 0) {
                    System.out.println("Pay extra amount: " + diff);
                } else if (diff < 0) {
                    System.out.println("Refund amount: " + Math.abs(diff));
                } else {
                    System.out.println("No fee difference.");
                }

                if (feesPaid > totalFees) {
                    feesPaid = totalFees;
                }

                System.out.println("Course replaced successfully.");
                return true;
            }
        }

        System.out.println("Old course not found.");
        return false;
    }

    public void payFees(double amount) {

        if (amount <= 0) {
            System.out.println("Payment amount must be positive.");
            return;
        }

        if (feesPaid + amount > totalFees) {
            System.out.println("Payment exceeds total fees.");
            return;
        }

        feesPaid += amount;
        System.out.println("Payment successful.");
    }

    public double getPendingFees() {
        return totalFees - feesPaid;
    }

    public void displayProfile() {

        System.out.println("Student ID : " + studentId);
        System.out.println("Name       : " + name);
        System.out.println("Courses Opted:");

        boolean hasCourse = false;
        for (Course course : courses) {
            if (course != null) {
                course.displayCourse();
                hasCourse = true;
            }
        }

        if (!hasCourse) {
            System.out.println("No courses enrolled.");
        }

        System.out.println("Total Fees  : " + totalFees);
        System.out.println("Fees Paid   : " + feesPaid);
        System.out.println("Pending Fees: " + getPendingFees());
        System.out.println("-------------------");
    }

    public void displayOverviewOfStudent() {
        System.out.println(name + " : " + studentId);
    }
}
