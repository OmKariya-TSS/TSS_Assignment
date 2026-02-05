package com.tss.model;

import java.util.Comparator;

public class StudentIdComparator implements Comparator<StudentCompare> {

    @Override
    public int compare(StudentCompare o1, StudentCompare o2) {
        return o1.getId()-o2.getId();
    }
}
