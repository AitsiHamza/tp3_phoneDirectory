package com.example.tp3_annuaire_01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    AppDatabase database;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database=AppDatabase.getInstance(this);
        List<Personne> contacts=database.personneDao().getAll();






        /*
        Intent intent=new Intent(this,EditPerson.class);
        startActivity(intent);*/
    }
}