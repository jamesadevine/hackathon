package com.nullpointerexception.hackathon.app;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.maps.android.heatmaps.HeatmapTileProvider;
import com.google.maps.android.heatmaps.WeightedLatLng;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity implements LocationListener {
    private GoogleMap map;
    private HeatmapTileProvider hmtp;
    private LocationManager locationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
                .getMap();
        //get the location manager
        this.locationManager=(LocationManager)getApplicationContext().getSystemService(Context.LOCATION_SERVICE);


        //check if location services are available
        if(!locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){


            //change
            //center on the university campus
            CameraUpdate center= CameraUpdateFactory.newLatLng(new LatLng(54.0084879, -2.7850552));
            CameraUpdate zoom=CameraUpdateFactory.zoomTo(16);

            map.moveCamera(center);
            map.animateCamera(zoom);
        }else{
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,10000, 5, this);
            //set the locationenabled boolean
            map.setMyLocationEnabled(true);

            //centre on current location
            CameraUpdate center= CameraUpdateFactory.newLatLng(getLocation());
            CameraUpdate zoom=CameraUpdateFactory.zoomTo(16);

            map.moveCamera(center);

            map.animateCamera(zoom);

        }
        addHeatMap();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
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
    private void addHeatMap() {

        AsyncPotholeCallback potholeCallback = new AsyncPotholeCallback() {
            @Override
            public void potholeCallback(ArrayList<PotHole> p) {
                List<WeightedLatLng> list = new ArrayList<WeightedLatLng>();
                Log.e("crap","HELP");
                for(PotHole potHole:p){

                    list.add(new WeightedLatLng(new LatLng(potHole.getLatitude(),potHole.getLongitude()),1));
                }
                // Create a heat map tile provider, passing it the latlngs of the police stations.
                hmtp = new HeatmapTileProvider.Builder()
                        .weightedData(list)
                        .build();
                // Add a tile overlay to the map, using the heat map tile provider.
                map.addTileOverlay(new TileOverlayOptions().tileProvider(hmtp));
            }
        };
        PotHoleASync async = new PotHoleASync(potholeCallback);
        async.execute();
        /*List<WeightedLatLng> list = new ArrayList<WeightedLatLng>();

        // Get the data: latitude/longitude positions of police stations.
        list.add(new WeightedLatLng(new LatLng(53.389818,-3.087004),1));
        list.add(new WeightedLatLng(new LatLng(53.187997,-2.376552),1));
        list.add(new WeightedLatLng(new LatLng(53.134525,-2.336175),1));
        list.add(new WeightedLatLng(new LatLng(53.36045,-2.508902),1));
        list.add(new WeightedLatLng(new LatLng(53.585445,-2.174717),1));
        list.add(new WeightedLatLng(new LatLng(53.743282,-2.650813),1));
        list.add(new WeightedLatLng(new LatLng(53.510633,-2.134239),1));

        map.addMarker(new MarkerOptions().title("POint").position(new LatLng(53.389818,-3.087004)));
        map.addMarker(new MarkerOptions().title("POint").position(new LatLng(53.187997,-2.376552)));
        map.addMarker(new MarkerOptions().title("POint").position(new LatLng(53.134525,-2.336175)));
        map.addMarker(new MarkerOptions().title("POint").position(new LatLng(53.36045,-2.508902)));
        map.addMarker(new MarkerOptions().title("POint").position(new LatLng(53.585445,-2.174717)));
        map.addMarker(new MarkerOptions().title("POint").position(new LatLng(53.743282,-2.650813)));
        map.addMarker(new MarkerOptions().title("POint").position(new LatLng(53.510633,-2.134239)));
        // Create a heat map tile provider, passing it the latlngs of the police stations.
        hmtp = new HeatmapTileProvider.Builder()
                .weightedData(list)
                .build();
        // Add a tile overlay to the map, using the heat map tile provider.
        map.addTileOverlay(new TileOverlayOptions().tileProvider(hmtp));*/
    }
    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }
    @Override
    public void onLocationChanged(Location location) {
        Log.e("location", "updating");
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(location.getLatitude(), location.getLongitude()), 17));

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(location.getLatitude(), location.getLongitude()))
                .zoom(17)
                .build();
        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public void onProviderEnabled(String provider) { }

    @Override
    public void onProviderDisabled(String provider) { }


    public LatLng getLocation(){
        //get the app location manager via context
        LocationManager locationManager=(LocationManager)getApplicationContext().getSystemService(Context.LOCATION_SERVICE);

        //initialise variables
        double latitude =0;
        double longitude =0;

        //check if the location isn't enabled, return null
        if(!locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER))
            return null;

        //otherwise get the location
        Location loc = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        if(loc!=null){
            longitude=loc.getLongitude();
            latitude=loc.getLatitude();
        }else{
            return new LatLng(54.0084879,-2.7850552);
        }

        //return LatLng
        return new LatLng(latitude,longitude);
    }
}
