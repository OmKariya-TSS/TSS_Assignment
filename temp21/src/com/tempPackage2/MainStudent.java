package com.tempPackage2;

import java.util.Scanner;

public class MainStudent {
    static Scanner scanner=new Scanner(System.in);
    public static void main(String[] args) {
        Student [] students =new Student[10];

        System.out.println("Enter how many students you want to enter:");
        int numOfStudent=scanner.nextInt();

        for(int i=0;i<numOfStudent;i++){
            students[i]=makeStudent();
        }



        StudentRegistory registory1=new StudentRegistory();



//        Student s1=new Student(id,name,dept,marks);

        System.out.println(s1);
        System.out.println(s1.average());
    }

    public static Student makeStudent(){
        System.out.println("Enter Id:");
        int id=scanner.nextInt();
        while(true){
            if(id>0){
                break;
            }else{
                System.out.println("Enter valid id:");
                id=scanner.nextInt();
            }
        }

        scanner.nextLine();

        System.out.println("Enter Student Name:");
        String name=scanner.nextLine();
        while (true){
            if(!name.equals("")){
                break;
            }else{
                System.out.println("Enter valid name:");
                name=scanner.nextLine();
            }
        }

        System.out.println("Choose department:");
        System.out.println("1.CSE");
        System.out.println("2. IT");
        System.out.println("3. ECE");
        System.out.println("4. MECH");

        Department dept=null;

        System.out.println("Enter department number:");
        int choice=scanner.nextInt();

        switch (choice){
            case 1:
                dept=Department.CSE;
                break;
            case 2:
                dept=Department.IT;
                break;
            case 3:
                dept=Department.ECE;
                break;
            case 4:
                dept=Department.MECH;
                break;
            default:
                System.out.println("Enter valid Choice!");
        }
        int[] marks=new int[3];

        for(int i=0;i<marks.length;i++){
            System.out.println("Enter marks:");
            marks[i]=scanner.nextInt();
            while (true){
                if(marks[i]>0 && marks[i]<100){
                    break;
                }else{
                    System.out.println("Enter valid value between 0 to 100:");
                    marks[i]=scanner.nextInt();
                }
            }
        }

        Student newStudent=new Student(id,name,dept,marks);
        return newStudent;
    }
}
