package com.lukeli.appaday.day6;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Luke on 3/2/2015.
 */
public class Person implements Parcelable{
    private static final String KEY_NAME = "name";
    private static final String KEY_MARRIED = "married";
    private static final String KEY_DECEASED = "deceased";
    private static final String KEY_AGE = "age";
    private static final String KEY_SPOUSE_ID = "spouse_id";
    private static final String KEY_ID = "id";


    private String name;
    private boolean married;
    private boolean deceased;
    private int age;
    private int spouse_id;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSpouse_id() {
        return spouse_id;
    }

    public void setSpouse_id(int spouse_id) {
        this.spouse_id = spouse_id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMarried() {
        return married;
    }

    public void setMarried(boolean married) {
        this.married = married;
    }

    public boolean isDeceased() {
        return deceased;
    }

    public void setDeceased(boolean deceased) {
        this.deceased = deceased;
    }

    public Person(int id, String name, int age, boolean married, boolean deceased, int spouse_id){
        setName(name);
        setMarried(married);
        setDeceased(deceased);
        setId(id);
        setAge(age);
        setSpouse_id(spouse_id);
    }

    public Person(){

    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        Bundle bundle = new Bundle();

        bundle.putString(KEY_NAME, name);
        bundle.putBoolean(KEY_MARRIED, married);
        bundle.putBoolean(KEY_DECEASED, deceased);
        bundle.putInt(KEY_AGE, age);
        bundle.putInt(KEY_SPOUSE_ID, spouse_id);
        bundle.putInt(KEY_ID, id);
        dest.writeBundle(bundle);
    }

    public static final Parcelable.Creator<Person> CREATOR = new Creator<Person>() {

        @Override
        public Person createFromParcel(Parcel source) {
            // read the bundle containing key value pairs from the parcel
            Bundle bundle = source.readBundle();
            // instantiate a person using values from the bundle
            return new Person(bundle.getInt(KEY_ID),
                    bundle.getString(KEY_NAME),
                    bundle.getInt(KEY_AGE),
                    bundle.getBoolean(KEY_MARRIED),
                    bundle.getBoolean(KEY_DECEASED),
                    bundle.getInt(KEY_SPOUSE_ID));
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }

    };
}
