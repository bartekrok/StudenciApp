package com.example.wojewodztwaapp;

import java.util.Comparator;

public class SortByAvg implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {

        return (int)o1.Srednia - (int)o2.Srednia;
    }
}
