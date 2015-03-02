package com.lukeli.appaday.day6;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class DeceasedAdapter extends ArrayAdapter<Person>{

    public DeceasedAdapter(Context context, ArrayList<Person> people) {
        super(context, R.layout.custom_person_row, people);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater theInflater = LayoutInflater.from(getContext());

        View theView = theInflater.inflate(R.layout.custom_person_row, parent, false);

        Person person = getItem(position);
        String personName = person.getName();
        TextView personNameTextView = (TextView) theView.findViewById(R.id.person_name_text_view);
        personNameTextView.setText(personName);

        ImageView theImageView = (ImageView) theView.findViewById(R.id.deceased_image_view);
        if(person.isDeceased()) {
            theImageView.setImageResource(R.drawable.gravestone);
        }

        ImageView marriedImageView = (ImageView) theView.findViewById(R.id.married_image_view);
        if(person.isMarried()) {
            marriedImageView.setImageResource(R.drawable.married);
        }

        return theView;

    }
}
