package com.example.user.listviewexample;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //This is my main activity, it takes the layout off the .xml file activity_main

        setContentView(R.layout.activity_main);

        //here I am creating three string arrays with the names of the places and the coordinates in two separate arrays for latitude and longitude

        String[] places= {"Agios Dimitrios", "Athens International Airport", "Heraklion", "NCSR Demokritos", "Ietionia Akti", "Terra Vibe Park"};

        String[] longitude = {"23.727823", "23.937302", "25.142762", "23.818233", "23.636827", "23.804169"};

        String[] latitude = {"37.934538", "37.928700", "35.341585", "37.998911", "37.948184", "38.230284"};

        //here I am inserting the names into a list with a list adapter that will feed the arrays into a ListView
        //this is done with MyAdapter class that extends the ArrayAdapter class

        final ListAdapter theAdapter = new MyAdapter(this, places);

        //here I am feeding the list into the ListView in order to display it

        ListView theListView = (ListView) findViewById(R.id.theListView);

        theListView.setAdapter(theAdapter);

        //I am following the same procedure (with MyAdapter) to create lists from the longitude and latitude string arrays

        final ListAdapter LonAdapter = new MyAdapter(this, longitude);

        final ListAdapter LatAdapter = new MyAdapter(this, latitude);

        //here I am telling android what to do when a list item is clicked

        theListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //here I am reading which place was picked. literally, I am reading the position of it
                //the position integer is the array position of the cliked item
                //I am using this position in order to access the coordinates of the place,
                //that are in the corresponding position in the latitude-longitude arrays

                String placePicked = String.valueOf(theAdapter.getItem(position));
                String lati = String.valueOf(LatAdapter.getItem(position));
                String longi = String.valueOf(LonAdapter.getItem(position));

                //the latitude and longitude are string arrays, so I am converting the data into double floats (coordinates need double float due to many decimal places)

                Double latOf = Double.valueOf(lati);
                Double lonOf = Double.valueOf(longi);

                //this method initializes the map. I am feeding the method with my data, the location and the name of the picked place

                addMapFragment(latOf, lonOf, placePicked);

                //I am displaying a toast of the same data just for cross-checking/validating

                Toast.makeText(MainActivity.this, placePicked + "\n" + latOf + ", " +lonOf, Toast.LENGTH_SHORT).show();
            }
        });

    }


    //this initializes the options menu with the menu_main template

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //this tells android what to do when a menu item is selected

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //this dipslays a dialog with app info. It uses the extended class DialogFragent

        if (id == R.id.appinfo) {

            DialogFragment myFragment = new MyDialogFragment();

            myFragment.show(getFragmentManager(), "theDialog");

            return true;
        }

        //this option terminates the app, using the exit_the_app layout item, that also contains a picture. It displays in the action bar

        else if (id == R.id.exit_the_app) {

            finish();
            return true;
        }

        //this option returns to the main activity

        else if (id == R.id.go_back) {

            Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
            startActivityForResult(myIntent, 0);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    //this is the addMapFragment method that points to the MapsActivity and feeds it with the location and name data of the selected place

    public void addMapFragment(double lat, double lon, String name) {

        Intent intent = new Intent(this, MapsActivity.class);

        intent.putExtra("lon", lon);
        intent.putExtra("lat", lat);
        intent.putExtra("name", name);

        startActivity(intent);
    }

}
