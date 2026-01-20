package com.tss.HomeAssignment.model;

public class Student {
    int studentId;
    String name;
    Course[] courses = new Course[3];
    double totalFees;
    double feesPaid;

    public Student(int studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.courses = new Course[3];
        this.totalFees = 0;
        this.feesPaid = 0;
    }


    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Course[] getCourses() {
        return courses;
    }

    public void setCourses(Course[] courses) {
        this.courses = courses;
    }

    public double getTotalFees() {
        return totalFees;
    }

    public void setTotalFees(double totalFees) {
        this.totalFees = totalFees;
    }

    public double getFeesPaid() {
        return feesPaid;
    }

    public void setFeesPaid(double feesPaid) {
        this.feesPaid = feesPaid;
    }
    public boolean addCourse(Course course) {
        for (Course c : courses) {
            if (c != null && c.getCourseId() == course.getCourseId()) {
                System.out.println("This course is already assigned to the student.");
                return false;
            }
        }
        for (int i = 0; i < courses.length; i++) {
            if (courses[i] == null) {
                courses[i] = course;
                totalFees += course.getCourseFees();
                System.out.println("Course added successfully.");
                return true;
            }
        }
        System.out.println("Cannot add more than 3 courses.");
        return false;
    }

    public void payFees(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid payment amount.");
            return;
        }
        feesPaid += amount;
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
        System.out.println("--------------");
    }



}
