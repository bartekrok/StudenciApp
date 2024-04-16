package com.example.wojewodztwaapp;

import com.google.android.material.behavior.HideBottomViewOnScrollBehavior;

import java.text.Collator;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

//@RequiresApi(api = Build.VERSION_CODES.O)
public class Student implements Comparable<Student>{
    int zdjêcie;
    String Nazwisko;
    String Imie;
    String Wydzial;
    String dataUrodzenia="1999-09-14";
    double Srednia;
    public Student(String Imie,
                   String Nazwisko,
                   String Wydzial,
                   String Ur,
                   double Srednia,
                   int zdjêcie
    )
    {   this.zdjêcie = zdjêcie;
        this.Nazwisko = Nazwisko;
        this.Imie = Imie;
        this.Wydzial = Wydzial;
        this.dataUrodzenia=Ur;
        this.Srednia = Srednia; }
    public Student(){}
    public static ArrayList<Student> studenci=new ArrayList<Student>();
    public String toString()
    {return this.Nazwisko + " "+this.Imie+ "\nWydział: "+this.Wydzial;}

    static LocalDate dzisiaj=LocalDate.now();
    public double wiek(String data){
        LocalDate dataUrodzenia = LocalDate.parse(data);
        Period per=Period.between(dataUrodzenia,dzisiaj);
        return per.getYears()+per.getMonths()/12.0+per.getDays()/365.0;
    }
    public boolean equals(Student inny) {
        return this.Nazwisko.equals(inny.Nazwisko)
                && this.Imie.equals(inny.Imie)
                && this.dataUrodzenia.equals(inny.dataUrodzenia);
    }
    @Override
    public int compareTo( Student student) {
        Collator c = Collator.getInstance(new Locale("pl", "PL"));
        int porównanieNazwisk=c.compare(this.Nazwisko,student.Nazwisko);
        int porównanieImion=c.compare(this.Imie,student.Imie);
        if(porównanieNazwisk==0) return porównanieImion;
        return porównanieNazwisk;}


    public int compareTo(String student){
        Collator c = Collator.getInstance(new Locale("pl", "PL"));
        int porownanie=c.compare(this.toString(), student);
        return porownanie;
    }

    static Comparator<Student> kompSred = (s1, s2) -> {
        double roznica = s1.Srednia-s2.Srednia;
        if(roznica >0) return 1;
        else if( roznica < 0) return -1;
        else return 0;
    };



}
