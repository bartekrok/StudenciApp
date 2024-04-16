package com.example.wojewodztwaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import com.example.wojewodztwaapp.Student;

public class MainActivity extends AppCompatActivity {

    String[] wojewodztwa;
    String[] studenci;
    ListView lV;
    Toast placek;
    ArrayAdapter<String> adapter;
    ArrayAdapter<String> studentAdapter;

    ListAdapter listAdapter;
    Button showSelectedButton;
    TextView selectedText;
    ImageView studentImage;

    RadioButton nameRB;
    RadioButton avgRB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Student.studenci.isEmpty()) {
            Student.studenci.add(new Student("Stanisław", "Antczak", "Mechaniczny", "1998-01-11", 4.13,R.drawable.facet1));
            Student.studenci.add(new Student("Janusz", "Antkiewicz", "Mechaniczny", "1998-08-11", 4.19,R.drawable.facet2));
            Student.studenci.add(new Student("Paweł", "Bartnik", "Nawigacyjny", "1998-01-11", 4.13,R.drawable.facet3));
            Student.studenci.add(new Student("Adam", "Bartkowiak", "Elektryczny", "1996-07-11", 3.13,R.drawable.facet4));
            Student.studenci.add(new Student("Paweł", "Zagórski", "Elektryczny", "1995-01-11", 3.99,R.drawable.facet5));
            Student.studenci.add(new Student("Piotr", "Zawadzki", "PiT", "1997-03-21", 4.01,R.drawable.facet6));
            Student.studenci.add(new Student("Bartosz", "Kowalski", "Nawigacyjny", "1995-11-11", 3.99,R.drawable.facet7));
            Student.studenci.add(new Student("Bartosz", "Janowski", "Elektryczny", "1995-07-11", 3.29,R.drawable.progr_2));
            Student.studenci.add(new Student("Mikołaj", "Żurawski", "PiT", "1997-12-21", 4.89,R.drawable.facet8));
            Student.studenci.add(new Student("Paweł", "Filipiak", "PiT", "2001-04-29", 3.99,R.drawable.facet9));
            Student.studenci.add(new Student("Zdzisław", "Gutkowski", "Nawigacyjny", "2001-05-29", 4.05,R.drawable.progr_2));
        }
        studenci = new String[Student.studenci.size()];

        // Populate the wojewodztwa array
        wojewodztwa = getResources().getStringArray(R.array.województwa);
        for(int i = 0; i < Student.studenci.size();i++){
        studenci[i] = Student.studenci.get(i).toString();
        }

        // Initialize the adapter
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_checked, wojewodztwa);
        studentAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, studenci);

        // Find the ListView
        lV = findViewById(R.id.lView);
        listAdapter = new ListAdapter(this, Student.studenci);

        studentImage = findViewById(R.id.imageView);
        nameRB = findViewById(R.id.nameRadioButton);
        avgRB = findViewById(R.id.avgRadioButton);


        // Set the adapter to the ListView
        lV.setAdapter(adapter);
        lV.setAdapter(studentAdapter);
        lV.setAdapter(listAdapter);



        // Set choice mode
        lV.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        // Refresh the adapter
        adapter.notifyDataSetChanged();
        studentAdapter.notifyDataSetChanged();

        placek = Toast.makeText(this, "", Toast.LENGTH_LONG);

        // Find the button
        showSelectedButton = findViewById(R.id.showSelectedButton);

        // Find the TextView
        selectedText = findViewById(R.id.selectedText);

        // Set OnClickListener for the button
        showSelectedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSelectedItems();
            }
        });

        lV.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> studentAdapter, View v, int position,
                                    long arg3)
            {
                String value = (String)studentAdapter.getItemAtPosition(position);
                showSelectedStudent(value);
                // assuming string and if you want to get the value on click of list item
                // do what you intend to do on click of listview row
            }
        });

        nameRB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){sortArray(1);}
        });
        avgRB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){sortArray(2);}
        });
    }

    private void sortArray(int type){

        if(type == 1){
        Collections.sort(Student.studenci,new SortByName());
            for(int i = 0; i < Student.studenci.size();i++){
                studenci[i] = Student.studenci.get(i).toString();
            }

            studentAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, studenci);
            lV.setAdapter(studentAdapter);
            lV.setAdapter(listAdapter);


        } else if (type == 2) {
            Collections.sort(Student.studenci,new SortByAvg());
            for(int i = 0; i < Student.studenci.size();i++){
                studenci[i] = Student.studenci.get(i).toString();
            }

            studentAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, studenci);
            lV.setAdapter(studentAdapter);
            lV.setAdapter(listAdapter);

        }

    }

    private void showSelectedStudent(String clickedStudent){
        Student selectedStudent = new Student();
        for(int i = 0; i < Student.studenci.size(); i++){
            if(Student.studenci.get(i).compareTo(clickedStudent) == 0){
            selectedStudent = Student.studenci.get(i);
            }
        }
        studentImage.setImageResource(selectedStudent.zdjêcie);
    }

    private void showSelectedItems() {
        ArrayList<String> selectedItems = new ArrayList<>();
        // Iterate through all items in the ListView
        for (int i = 0; i < lV.getCount(); i++) {
            if (lV.isItemChecked(i)) { // Check if item at position i is checked
                selectedItems.add(studenci[i]); // Add checked item to the list
            }
        }
        // Convert the list of selected items to a string
        String selectedItemsString = "Selected items: " + Arrays.toString(selectedItems.toArray());

        // Set the text of the TextView to display selected items
        selectedText.setText(selectedItemsString);
    }
}
