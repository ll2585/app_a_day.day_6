package com.lukeli.appaday.day6;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class TabbedFragment extends Fragment  {
    String[] people;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_layout, container, false);
        people = getArguments().getStringArray("people");
        ListAdapter theAdapter = new ArrayAdapter<String>(getActivity(), R.layout.custom_person_row,
                R.id.textView1, people);
        ListView people_view = (ListView) v.findViewById(R.id.people_list_view);
        people_view.setAdapter(theAdapter);
        return v;
    }
}