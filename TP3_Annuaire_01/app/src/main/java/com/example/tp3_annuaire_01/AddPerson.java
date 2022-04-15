package com.example.tp3_annuaire_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class AddPerson extends AppCompatActivity {

    AppDatabase database;
    Personne personne;
    EditText firstName,lastName,phone;
    ImageView save,restart,returnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);

        database=AppDatabase.getInstance(this);
        firstName=findViewById(R.id.firstName);
        lastName=findViewById(R.id.lastName);
        phone=findViewById(R.id.phone);
        save=findViewById(R.id.save);
        restart=findViewById(R.id.restart);
        returnHome=findViewById(R.id.returnHome);
/*
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                personne.setFirstName(firstName.getText().toString());
                personne.setLastName(lastName.getText().toString());
                personne.setPhone(phone.getText().toString());
                database.personneDao().insert(personne);
                Toast.makeText(AddPerson.this,"Added", Toast.LENGTH_SHORT).show();

            }
        });*/

        returnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(AddPerson.this, MainActivity.class);
                startActivity(myIntent);
            }
        });

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firstName.setText("");
                lastName.setText("");
                phone.setText("");
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                database.personneDao().insert(new Personne(firstName.getText().toString(),
                        lastName.getText().toString(),phone.getText().toString()));

                Toast.makeText(AddPerson.this,"Added", Toast.LENGTH_SHORT).show();
            }
        });
    }
}