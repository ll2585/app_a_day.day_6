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

import java.util.ArrayList;

public class TabbedFragment extends Fragment  {
    ArrayList<String> people;
    protected ArrayAdapter<String> adapter;
    ListAdapter myAdapter;
    ListView myListView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_layout, container, false);
        people = getArguments().getStringArrayList("people");
        adapter = new ArrayAdapter<String>(getActivity(), R.layout.custom_person_row,
                R.id.textView1, people);
        myListView = (ListView) v.findViewById(R.id.people_list_view);
        myListView.setAdapter(adapter);
        return v;
    }

    public void setPeople(ArrayList<String> newPeople){
        adapter.notifyDataSetChanged();
        people = getArguments().getStringArrayList("people");
        adapter = new ArrayAdapter(getActivity(), R.layout.custom_person_row,
                R.id.textView1, people);
        myListView = (ListView) getView().findViewById(R.id.people_list_view);
        myListView.setAdapter(adapter);

    }
}