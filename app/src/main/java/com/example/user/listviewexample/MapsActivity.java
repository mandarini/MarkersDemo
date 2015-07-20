package com.example.user.listviewexample;

        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;

        import com.google.android.gms.maps.CameraUpdateFactory;
        import com.google.android.gms.maps.GoogleMap;
        import com.google.android.gms.maps.model.LatLng;
        import com.google.android.gms.maps.model.MarkerOptions;
        import com.google.android.gms.maps.MapFragment;
        import com.google.android.gms.maps.model.Marker;

/**
 * Created by katerinaskroumpelou on 19/7/2015.
 */

//this class extends AppCompatActivity (new ActionBarActivity) because we want the action bar to display over the map

public class MapsActivity extends AppCompatActivity  {

    //here we are declaring our variables that will be initialized in the onCreate method, fed by the extras

    double lat, lon;
    String point;
    private GoogleMap googleMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //feeding the extras

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            lat=extras.getDouble("lat");
            lon=extras.getDouble("lon");
            point=extras.getString("name");
        }

        //determining the layout

        setContentView(R.layout.activity_maps);

        //creating a new position object which we will use to set the marker position and the camera position

        LatLng Pos = new LatLng(lat , lon);

        //creating the map

        try {
            if (googleMap == null) {
                googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
            }

            googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

            Marker marker = googleMap.addMarker(new MarkerOptions().
                    position(Pos).title(point));

            //I chose zoom level 10 that shows approximately the whole of attica when centered to one of the attica located places
            //Choosing another level would either show a very wide view that would change very little when choosing different places
            //or it would contain few landmarks, making it difficult for the user to get a quick gist of where the location is
            //relative to known landmarks (center of athens, rest of crete)

            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Pos, 10));



        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}