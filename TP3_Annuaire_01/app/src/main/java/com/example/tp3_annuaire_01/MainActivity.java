package com.example.tp3_annuaire_01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView firstName,lastName,phone;
    RecyclerView recyclerView;
    ImageView add,deleteAll;
    List<Personne> contacts;
    LinearLayoutManager linearLayoutManager;
    ContactsAdapter adapter;
    AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstName=findViewById(R.id.firstName);
        lastName=findViewById(R.id.lastName);
        phone=findViewById(R.id.phone);
        recyclerView=findViewById(R.id.recycler_view);
        add=findViewById(R.id.addPerson);
        deleteAll=findViewById(R.id.deleteAll);

        database=AppDatabase.getInstance(this);
        contacts=database.personneDao().getAll();
        linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter=new ContactsAdapter(MainActivity.this,contacts);
        recyclerView.setAdapter(adapter);

        deleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database.personneDao().deleteAll();
                contacts.clear();
                adapter=new ContactsAdapter(MainActivity.this,contacts);
                recyclerView.setAdapter(adapter);
                Toast.makeText(MainActivity.this,"Delete all",Toast.LENGTH_SHORT);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,AddPerson.class);
                startActivity(intent);
            }
        });
    }

}