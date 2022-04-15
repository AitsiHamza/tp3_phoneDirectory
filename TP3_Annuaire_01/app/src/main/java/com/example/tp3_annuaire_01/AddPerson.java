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

        returnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(AddPerson.this, MainActivity.class);
                Toast.makeText(AddPerson.this,"Return Home", Toast.LENGTH_SHORT).show();
                startActivity(myIntent);
            }
        });

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firstName.setText("");
                lastName.setText("");
                phone.setText("");
                Toast.makeText(AddPerson.this,"Restart", Toast.LENGTH_SHORT).show();
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