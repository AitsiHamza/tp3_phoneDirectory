package com.example.tp3_annuaire_01;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
                Personne personne=personneList.get(holder.getAbsoluteAdapterPosition());
                final int id=personne.id;
                final Dialog dialog=new Dialog(context);
                dialog.setContentView(R.layout.activity_edit_person);

                int width= WindowManager.LayoutParams.MATCH_PARENT;
                int height=WindowManager.LayoutParams.MATCH_PARENT;
                dialog.getWindow().setLayout(width,height);
                dialog.show();

                final EditText firstName=dialog.findViewById(R.id.firstName_edit);
                final EditText lastName=dialog.findViewById(R.id.lastName_edit);
                final EditText phone=dialog.findViewById(R.id.phone_edit);
                ImageView btUpdate=dialog.findViewById(R.id.save_edite),
                returnHome=dialog.findViewById(R.id.returnHome_edit),
                restart=dialog.findViewById(R.id.restart_edit);

                firstName.setText(personne.firstName);
                lastName.setText(personne.lastName);
                phone.setText(personne.phone);

                returnHome.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(context,MainActivity.class);
                        Toast.makeText(context,"Return Home",Toast.LENGTH_SHORT);
                        context.startActivity(intent);
                    }
                });
                restart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        firstName.setText("");
                        lastName.setText("");
                        phone.setText("");
                        Toast.makeText(context,"Reset",Toast.LENGTH_SHORT);
                    }
                });
                btUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        personne.setFirstName(firstName.getText().toString().trim());
                        personne.setLastName(lastName.getText().toString().trim());
                        personne.setPhone(phone.getText().toString().trim());

                        database.personneDao().update(personne);

                        personneList.clear();
                        personneList.addAll(database.personneDao().getAll());
                        notifyDataSetChanged();
                    }
                });

            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Personne personneDelete=personneList.get(holder.getAbsoluteAdapterPosition());
                database.personneDao().delete(personneDelete);
                personneList.clear();
                personneList.addAll(database.personneDao().getAll());
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return personneList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView call,edit,delete;
        TextView firstName,lastName,phone;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            call=itemView.findViewById(R.id.call);
            firstName=itemView.findViewById(R.id.firstName);
            lastName=itemView.findViewById(R.id.lastName);
            phone=itemView.findViewById(R.id.phone);
            edit=itemView.findViewById(R.id.editPerson);
            delete=itemView.findViewById(R.id.deletePerson);
        }
    }
}
