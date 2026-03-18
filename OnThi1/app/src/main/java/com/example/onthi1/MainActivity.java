package com.example.onthi1;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private StudentAdapter studentAdapter;
    private RecyclerView recyclerViewStudent;
    private DBHelper dbHelper;
    SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        InitWidgets();

        AddListeners();

    }
    private void InitWidgets(){

        recyclerViewStudent = findViewById(R.id.recyclerViewStudent);
        recyclerViewStudent.setLayoutManager(new LinearLayoutManager(this));

        searchView = findViewById(R.id.searchView);

        dbHelper = new DBHelper(this);

        List<Student> studentList = dbHelper.getAllStudents();

        studentAdapter = new StudentAdapter(studentList);

        recyclerViewStudent.setAdapter(studentAdapter);

        DividerItemDecoration divider =
                new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);

        recyclerViewStudent.addItemDecoration(divider);
    }

    private void AddListeners(){
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                studentAdapter.getFilter().filter(newText);
                return true;
            }
        });

    }
}