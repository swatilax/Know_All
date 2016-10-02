package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class Main2Activity extends AppCompatActivity {
    String rssResult = "";
    boolean item = false;
    private HandleXML obj;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        String s= getIntent().getStringExtra("user");
        TextView tv = (TextView)findViewById(R.id.textView3);
        tv.setText("Welcome "+s+"! Hope you are having a good time" );
        ArrayList<String> listItems=new ArrayList<String>();
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(getResources().openRawResource(R.raw.abc));
            doc.getDocumentElement().normalize();
            NodeList n = doc.getElementsByTagName("education");

            ListView rss = (ListView) findViewById(R.id.rss_feed2);
            adapter=new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1,
                    listItems);
            rss.setAdapter(adapter);

            //adapter.add("Tryyy");
            for (int i=0; i < n.getLength(); i++) {
                Node an = n.item(i);

                //Log.i("check",an.getNodeName() + ": type (" + an.getNodeType() + "):" + an.getNodeValue());
                if(an.getNodeType()!=Node.TEXT_NODE) {
                    NodeList nl2 = an.getChildNodes();
                    Log.i("check",an.getNodeName() + ": type (" + an.getNodeType() + "):" + an.getTextContent().toString());

                    listItems.add(an.getTextContent().toString());
                    adapter.notifyDataSetChanged();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void logout(View view) {
        Intent intent = new Intent(Main2Activity.this, MainActivity.class);
        startActivity(intent);
    }

    public void seeEvents(View view) {
        Intent intent = new Intent(Main2Activity.this, calendar.class);
        startActivity(intent);
    }

    public void starComm(View view) {
        Intent intent = new Intent(Main2Activity.this, starred.class);
        startActivity(intent);
    }
}
