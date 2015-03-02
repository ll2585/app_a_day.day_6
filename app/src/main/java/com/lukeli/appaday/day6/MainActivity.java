package com.lukeli.appaday.day6;

import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class MainActivity extends FragmentActivity {
    private FragmentTabHost mTabHost;
    private String[] people = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        Bundle args = new Bundle();
        args.putStringArray("people", people);
        mTabHost.addTab(
                mTabHost.newTabSpec("tab1").setIndicator("Tab 1", null),
                TabbedFragment.class, args);
        mTabHost.addTab(
                mTabHost.newTabSpec("tab2").setIndicator("Tab 2", null),
                TabbedFragment.class, args);
        mTabHost.addTab(
                mTabHost.newTabSpec("tab3").setIndicator("Tab 3", null),
                TabbedFragment.class, args);

    }

    protected void reloadTabs(){
        mTabHost.clearAllTabs();
        Bundle args = new Bundle();
        args.putStringArray("people", people);
        mTabHost.addTab(
                mTabHost.newTabSpec("tab1").setIndicator("Tab 1", null),
                TabbedFragment.class, args);
        mTabHost.addTab(
                mTabHost.newTabSpec("tab2").setIndicator("Tab 2", null),
                TabbedFragment.class, args);
        mTabHost.addTab(
                mTabHost.newTabSpec("tab3").setIndicator("Tab 3", null),
                TabbedFragment.class, args);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onGeneratePeopleClick(View view) {

        new GeneratePeople().execute();
    }

    private class GeneratePeople extends AsyncTask<Void, Void, Void> {
        String jsonString = "";
        String result = "";
        @Override
        protected Void doInBackground(Void... params) {

            String num_people = ((EditText) findViewById(R.id.num_people_edit_text)).getText().toString();
            String marriage_rate = ((EditText) findViewById(R.id.marriage_percent_edit_text)).getText().toString();
            String death_rate = ((EditText) findViewById(R.id.death_percent_edit_text)).getText().toString();

            boolean selectJSON = (RadioButton) findViewById(((RadioGroup) findViewById(R.id.JSON_or_XML_group)).getCheckedRadioButtonId()) == (RadioButton) findViewById(R.id.set_JSON_button);
            String is_JSON = selectJSON ? "json" : "xml";
            DefaultHttpClient httpClient = new DefaultHttpClient(new BasicHttpParams());

            HttpGet httpGet = new HttpGet("https://arcane-depths-3989.herokuapp.com/random_game_info?type=" + is_JSON + "&num=" +num_people + "&death=" +death_rate + "&marry=" + marriage_rate);

            if(selectJSON){
                httpGet.setHeader("Content-type", "application/json");
            }else{
                httpGet.setHeader("Content-type", "application/xml");
            }

            InputStream inputStream = null;

            try{
                HttpResponse response = httpClient.execute(httpGet);

                HttpEntity entity = response.getEntity();

                inputStream = entity.getContent();

                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);

                StringBuilder sb = new StringBuilder();

                String line = null;

                while((line = reader.readLine()) != null){
                    sb.append(line + "\n");

                }

                jsonString = sb.toString();
                System.out.println(jsonString);
                if(selectJSON){
                    JSONObject jsonObject = new JSONObject(jsonString);

                    JSONArray people_arr = jsonObject.getJSONArray("people");

                    makePeopleStringArray(people_arr);
                }


            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            reloadTabs();
        }

        protected void makePeopleStringArray(JSONArray jArray) {
            people = new String[jArray.length()];
            try {
                for(int i = 0 ; i < jArray.length(); i++){
                    JSONObject person = jArray.getJSONObject(i);
                    people[i] = person.getString("name");
                    System.out.println(people[i]);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
