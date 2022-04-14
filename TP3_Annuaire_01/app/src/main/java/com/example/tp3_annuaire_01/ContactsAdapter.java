package com.example.tp3_annuaire_01;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ContactsAdapter extends BaseAdapter {
    private Context context;
    private List<Personne> listPerson;

    public ContactsAdapter(Context context, List<Personne> listPerson) {
        this.context = context;
        this.listPerson = listPerson;
    }

    @Override
    public int getCount() {
        return listPerson.size();
    }

    @Override
    public Object getItem(int i) {
        return listPerson.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view==null){
            view= LayoutInflater.from(context)
                    .inflate(R.layout.list_row_main,viewGroup,false);
        }

        Personne currentItem = (Personne) getItem(i);

        TextView textView_firstName=(TextView) view.findViewById(R.id.editText_firstName);
        TextView textView_lastName=(TextView) view.findViewById(R.id.editText_lastName);
        TextView textView_phone=(TextView) view.findViewById(R.id.editText_phone);
        //ImageButton btn_save=(ImageButton) view.findViewById(R.id.call);

        textView_firstName.setText(currentItem.firstName);
        textView_lastName.setText(currentItem.lastName);
        textView_phone.setText(currentItem.phone);



        return null;
    }
}
