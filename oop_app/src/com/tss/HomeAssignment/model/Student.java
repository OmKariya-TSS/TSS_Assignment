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
    public void displayOverviewOfStudent(){
        System.out.println(name+ " : " +studentId);
    }
    public void removeCourse(int courseId) {
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
        for (Course c : courses) {
            if (c != null && c.getCourseId() == newCourse.getCourseId()) {
                System.out.println("Student is already enrolled in the new course.");
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

}
