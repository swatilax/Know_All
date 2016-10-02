package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;

public class starred extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starred);
        CheckedTextView simpleCheckedTextView = (CheckedTextView) findViewById(R.id.starcomm); // initiate a CheckedTextView
        Boolean checkedValue = simpleCheckedTextView.isChecked(); // check current state of CheckedTextView
       // CustomAdapter mAdapter = new CustomAdapter(this, R.layout.mLayout, arrayList);
        String lv_items[] = { "Android", "iPhone", "BlackBerry",
                "AndroidPeople", "J2ME", "Listview", "ArrayAdapter", "ListItem",
                "Us", "UK", "India" };
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), lv_items);
       // simpleCheckedTextView
       // simpleCheckedTextView.setada
    }

}
