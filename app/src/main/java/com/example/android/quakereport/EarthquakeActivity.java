package com.example.android.quakereport;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity {

    static final String LOG_TAG = EarthquakeActivity.class.getName();
    // url is set to fetch data
    private static final String USGS_REQUEST_URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=6&limit=10";

    //custom adapter name is set
    private EarthquakeAdapter mAdapter;

    @Override
    // on creation of app this method is execyted
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // which xml file is to be used is mentioned
        setContentView(R.layout.earthquake_activity);

        //adapter object is created
        mAdapter = new EarthquakeAdapter(this, new ArrayList<Earthquake>());

        //id is set for the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        //list view is given an adapter is set so that its views could be set
        earthquakeListView.setAdapter(mAdapter);

        //when list item is clicked it intent to browser is sent
        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //Earthquake objet is created named "currentEarthquake"
                Earthquake currentEarthquake = mAdapter.getItem(position);

                //Uri obejt named "earthquakeUri"
                Uri earthquakeUri = Uri.parse(currentEarthquake.getUrl());

                //intent is sent to open more info of that perticular location
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);

                //starts the intent
                startActivity(websiteIntent);
            }
        });

        //new thread task is given the object named "task"
        EarthquakeAsyncTask task = new EarthquakeAsyncTask();
        task.execute(USGS_REQUEST_URL);// a new tread is said to vist the the url
    }

    //asyncTask is extended to suite our needs
    private class EarthquakeAsyncTask extends AsyncTask<
            String,//input for backgroung process
            Void,  //give progerss on screen
            List<Earthquake> //what needs to be done after theard process is completed     List<> Creates a list view for a class
            > {

        //as everything is being accepted from the URL we set everything by creating a new {@link #Earthquake} object
        @Override
        protected List<Earthquake> doInBackground(String... urls) { //takes the input from AsyncTask < doInBackgroung, ... , ... >

            if (urls.length < 1 || urls[0] == null) {   //check if there is a valid string
                return null;
            }

            List<Earthquake> result = QueryUtils.fetchEarthquakeData(urls[0]);  //new list object is created which as type of Earthquake class
                                                                                            // QueryUtils sets the the data for us
            return result;
        }


        @Override
        protected void onPostExecute(List<Earthquake> data) {   //new EarthQuake obj is created named data
            // Clear the adapter of previous earthquake data
            mAdapter.clear();


            if (data != null && !data.isEmpty()) {
                mAdapter.addAll(data);
            }
        }
    }
}