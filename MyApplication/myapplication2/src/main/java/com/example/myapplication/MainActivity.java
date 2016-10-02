package com.example.myapplication;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.os.Parcel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import android.os.Parcelable;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import android.content.res.XmlResourceParser;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import com.example.myapplication.HandleXML;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

public class MainActivity extends AppCompatActivity {
    String rssResult = "";
    boolean item = false;
    private HandleXML obj;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RetrieveFeedTask obj = new RetrieveFeedTask();

        ArrayList<String> listItems=new ArrayList<String>();

        try {
            Log.i("Check","right");
                    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                    Document doc = dBuilder.parse(getResources().openRawResource(R.raw.abc));
                    doc.getDocumentElement().normalize();
            NodeList n = doc.getElementsByTagName("education");

            ListView rss = (ListView) findViewById(R.id.rss_feed);
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
    private class RssHandler extends DefaultHandler {

        public void startElement(String uri, String localName, String qName,
                                 Attributes attrs) throws SAXException {
            Log.i("Check","localname " + localName);
            if (localName.equals("item"))
                item = true;

            if (!localName.equals("item") && item == true)
                rssResult = rssResult + localName + ": ";

        }

        public void endElement(String namespaceURI, String localName,
                               String qName) throws SAXException {

        }

        public void characters(char[] ch, int start, int length)
                throws SAXException {
            String cdata = new String(ch, start, length);
            if (item == true)
                rssResult = rssResult +(cdata.trim()).replaceAll("\\s+", " ")+"\t";

        }

    }

    public void login(View view) {
        Intent intent = new Intent(MainActivity.this, login.class);
        startActivity(intent);
    }

    public void seeEvents(View view) {
        Intent intent = new Intent(MainActivity.this, calendar.class);
        startActivity(intent);
    }
}
