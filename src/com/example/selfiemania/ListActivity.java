package com.example.selfiemania;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;


public class ListActivity extends Activity {

    private JSONObject jo;
    private ArrayList<HashMap<String, String>> al;
    private ListView lv;
    private Activity ac;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ac = this;
        new PostImages().execute(null, null, null);
    }


    

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class PostImages extends AsyncTask<Void, Void, ArrayList<HashMap<String,String>>>{

        @Override
        protected ArrayList<HashMap<String, String>> doInBackground(Void... v){
            al = new ArrayList<HashMap<String, String>>();
            jo = JSONGetter.getJSONfromURL("https://api.instagram.com/v1/tags/selfie/media/recent?client_id=7a48f8db622c41929624edd3216e83e9");
            try {
                // Locate the array name in JSON
                JSONArray ja = jo.getJSONArray("data");

                for (int i = 0; i < ja.length(); i++) {
                    JSONObject element = ja.getJSONObject(i);
                    JSONObject images = element.getJSONObject("images");
                    HashMap hm = new HashMap();

                    hm.put("standard_resolution", images.getJSONObject("standard_resolution").getString("url"));
                    hm.put("thumbnail", images.getJSONObject("thumbnail").getString("url"));
                    // Set the JSON Objects into the array
                    al.add(hm);
                }
            } catch (JSONException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }

            return al;

        }

        @Override
        protected void onPostExecute(ArrayList<HashMap<String, String>> al){
            lv = (ListView) findViewById(R.id.listview);
            FakeGridAdapter fga = new FakeGridAdapter(ac, al);
            lv.setAdapter(fga);

        }

    }
}
