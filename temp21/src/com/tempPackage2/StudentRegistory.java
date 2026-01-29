package com.tempPackage2;

public class StudentRegistory {
    private Student [] students=new Student[10];
    static int count=0;
    public StudentRegistory(Student[] students){
        this.students=students;
    }
    public StudentRegistory(){

    }

    public Student maxMarks(){
        int max=0;
        Student ans=null;

        for(Student st:this.students){
            if(st.average()>max){
                max= (int) st.average();
                ans=st;
            }
        }
        return ans;
    }

    public void addStudent(Student newStudent){
        students[count]=newStudent;
        count++;
    }
}
