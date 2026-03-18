package com.example.contactadvancedemotry1;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    private ListView lvContact;
    private EditText etSearch;
    private Button btnAdd, btnDelete;

    // temp
    ArrayList<String> contacts;
    ArrayAdapter<String> lvAdapter;
    MyDB myDB;

    ArrayList<User> listUser;
    UserAdapter listUserAdapter;
    int seletedId = -1;

    private ConnectivityManager connectivityManager;
    private ConnectivityManager.NetworkCallback networkCallback;

    // creating an option menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.example_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()){
            case R.id.mniSort:
                Toast.makeText(MainActivity.this, "Sort", Toast.LENGTH_SHORT).show();
                Collections.sort(listUser, (u1, u2) -> {
                    return (u1.getName().toLowerCase().compareTo(u2.getName().toLowerCase()));
                });
                listUserAdapter.notifyDataSetChanged();
                lvContact.setAdapter(listUserAdapter);
                break;
            case R.id.mniAdd:
                // create an Intent to call to "Add new" activity
                ActivityResultLauncher<Intent> launcher =
                        registerForActivityResult(
                                new ActivityResultContracts.StartActivityForResult(),
                                result -> {}
                        );
                Intent intent = new Intent(this, AddUser.class);
                // TODO: add user activity
                launcher.launch(intent);

                break;
            default:
                break;
        }
        return true;
    }


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
    }

    private void InitWidgets(){
        lvContact = findViewById(R.id.lvContact);
        etSearch = findViewById(R.id.etSearch);
        btnAdd = findViewById(R.id.btnAdd);
        btnDelete = findViewById(R.id.btnRemove);

    }
}