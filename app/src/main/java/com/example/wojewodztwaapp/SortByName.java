package com.example.wojewodztwaapp;

import java.util.Comparator;
import com.example.wojewodztwaapp.Student;

public class SortByName implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        return o1.Nazwisko.compareTo(o2.Nazwisko);
    }
}
