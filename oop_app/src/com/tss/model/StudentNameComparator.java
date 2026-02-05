package com.tss.model;

import java.util.Comparator;

public class StudentNameComparator implements Comparator<StudentCompare> {
    @Override
    public int compare(StudentCompare o1, StudentCompare o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
