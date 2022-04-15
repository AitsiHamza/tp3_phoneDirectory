package com.example.tp3_annuaire_01;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;
import androidx.room.RoomDatabase;

import java.util.ArrayList;
import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {

    // Initialize variable
    private List<Personne> personneList;
    private Activity context;
    private AppDatabase database;

    //Create constructor
    public ContactsAdapter(Activity context, List<Personne> dataList)
    {
        this.context=context;
        this.personneList=dataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ContactsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.list_row_main,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ContactsAdapter.ViewHolder holder, int position) {
        Personne personne=personneList.get(position);
        database= AppDatabase.getInstance(context);

        holder.firstName.setText(personne.getFirstName());
        holder.lastName.setText(personne.getLastName());
        holder.phone.setText(personne.getPhone());

        holder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri telephone = Uri.parse("tel:"+personne.getPhone());
                Intent SA = new Intent(Intent.ACTION_DIAL,telephone);
                context.startActivity(SA);
            }
        });

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Personne personne=personneList.get(holder.getAdapterPosition());
                final int id=personne.id;
                String firstname= personne.firstName;
                String lastname=personne.lastName;
                String phonenumber=personne.phone;
                final Dialog dialog=new Dialog(context);
                dialog.setContentView(R.layout.activity_edit_person);

                int width= WindowManager.LayoutParams.MATCH_PARENT;
                int height=WindowManager.LayoutParams.WRAP_CONTENT;
                dialog.getWindow().setLayout(width,height);
                dialog.show();

                final EditText firstName=dialog.findViewById(R.id.firstName);
                final EditText lastName=dialog.findViewById(R.id.lastName);
                final EditText phone=dialog.findViewById(R.id.phone);
                ImageView btUpdate=dialog.findViewById(R.id.save);

                firstName.setText(firstname);
                lastName.setText(lastname);
                phone.setText(phonenumber);

                btUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        String firstname=firstName.getText().toString().trim();
                        String lastname=lastName.getText().toString().trim();
                        String phonenumber=phone.getText().toString().trim();

                        database.personneDao().update(id,phonenumber,firstname,lastname);

                        personneList.clear();
                        personneList.addAll(database.personneDao().getAll());
                        notifyDataSetChanged();
                    }
                });

            }
        });

    }

    @Override
    public int getItemCount() {
        return personneList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView call,edit;
        TextView firstName,lastName,phone;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            call=itemView.findViewById(R.id.call);
            firstName=itemView.findViewById(R.id.firstName);
            lastName=itemView.findViewById(R.id.lastName);
            phone=itemView.findViewById(R.id.phone);
            edit=itemView.findViewById(R.id.editPerson);
        }
    }
}
