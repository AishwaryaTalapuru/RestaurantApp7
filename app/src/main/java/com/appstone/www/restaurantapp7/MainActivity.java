package com.appstone.www.restaurantapp7;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.MalformedURLException;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Intent;

import java.io.BufferedInputStream;

public class MainActivity extends Activity {
    ListView lv;
    ArrayAdapter<String> adapter;
    String address="http://192.168.0.3/Android/intermediate.php";
    InputStream is=null;
    String line =null;
    String result=null;
    String[] data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv=(ListView) findViewById(R.id.Listview1);
        StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));
        //getting data sir
        getData();
        //adapter
        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,data);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for(position=0; position<=100; position++) {

                    if (position == 0) {
                        Intent myIntent = new Intent(view.getContext(), MenuActivity.class);
                        startActivityForResult(myIntent, 0);
                    }
                    if (position == 1) {
                        Intent myIntent = new Intent(view.getContext(), MenuActivity.class);
                        startActivityForResult(myIntent, 1);
                    }
                    if (position == 2) {
                        Intent myIntent = new Intent(view.getContext(), MenuActivity.class);
                        startActivityForResult(myIntent, 2);
                    }
                }
            }
        });



    }

    private void getData()
    {
        try {
            URL url = new URL(address);
            HttpURLConnection con=(HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            is=new BufferedInputStream(con.getInputStream());





        }catch(Exception e)
        {
            e.printStackTrace();
        }

        //reading "is" content into string sir
        try{

            BufferedReader br=new BufferedReader(new InputStreamReader(is));
            StringBuilder sb=new StringBuilder();
            while((line=br.readLine()) != null)
            {
                sb.append(line+"\n");
            }
            is.close();
            result=sb.toString();

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        //Parsing JSON DATA sir
        try{
            JSONArray ja=new JSONArray(result);
            JSONObject jo=null;
            data=new String[ja.length()];
            for(int i=0;i<ja.length();i++)
            {
                jo=ja.getJSONObject(i);
                data[i]=jo.getString("id");
            }

        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}

