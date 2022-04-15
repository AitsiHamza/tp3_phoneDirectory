package com.example.tp3_annuaire_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class EditPerson extends AppCompatActivity {

    AppDatabase database;
    Personne personne;
    EditText firstName,lastName,phone;
    ImageView save,restart,returnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_person);

/*
        database=AppDatabase.getInstance(this);
        firstName=findViewById(R.id.firstName_edit);
        lastName=findViewById(R.id.lastName_edit);
        phone=findViewById(R.id.phone_edit);
        save=findViewById(R.id.save_edit);
        restart=findViewById(R.id.restart_edit);
        returnHome=findViewById(R.id.returnHome_edit);

        returnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(EditPerson.this, MainActivity.class);
                startActivity(myIntent);
                Toast.makeText(EditPerson.this,"Return Home", Toast.LENGTH_SHORT).show();
            }
        });

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firstName.setText("");
                lastName.setText("");
                phone.setText("");
                Toast.makeText(EditPerson.this,"Restart", Toast.LENGTH_SHORT).show();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database.personneDao().insert(new Personne(firstName.getText().toString(),
                        lastName.getText().toString(),phone.getText().toString()));
                Toast.makeText(EditPerson.this,"Updated", Toast.LENGTH_SHORT).show();
            }
        });

 */
    }
}