package com.tss.test;

import com.tss.model.StudentCompare;
import com.tss.model.StudentIdComparator;
import com.tss.model.StudentNameComparator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CompareTest {
    public static void main(String[] args) {
        List<StudentCompare> list = new ArrayList<>();
        StudentCompare s1 = new StudentCompare(6,"OM");
        StudentCompare s2 = new StudentCompare(2,"OM2");
        StudentCompare s3 = new StudentCompare(5,"OM3");
        StudentCompare s4 = new StudentCompare(1,"OM4");
        list.add(s1);
        list.add(s2);
        list.add(s3);
        list.add(s4);
        Collections.sort(list);
        for(StudentCompare s : list){
            System.out.println(s);
        }
        Collections.sort(list,new StudentIdComparator());
        for(StudentCompare s : list){
            System.out.println(s);
        }
        Collections.sort(list,new StudentNameComparator());
        for(StudentCompare s : list){
            System.out.println(s);
        }



    }
}
