package com.example.faster.test;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ListViewCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.akexorcist.googledirection.DirectionCallback;
import com.akexorcist.googledirection.GoogleDirection;
import com.akexorcist.googledirection.constant.Language;
import com.akexorcist.googledirection.constant.RequestResult;
import com.akexorcist.googledirection.constant.TransitMode;
import com.akexorcist.googledirection.constant.TransportMode;
import com.akexorcist.googledirection.constant.Unit;
import com.akexorcist.googledirection.model.Direction;
import com.akexorcist.googledirection.model.Step;
import com.akexorcist.googledirection.util.DirectionConverter;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Bus_line extends AppCompatActivity implements OnMapReadyCallback,DirectionCallback
{
    private GoogleMap googleMap;
    private LocationManager locationManager;
    private Criteria criteria;
    double latStartADouble;
    double lngStartADouble;
    double latSearch;
    double lngSearch;
    private LatLng camera = new LatLng(13.777570, 100.509166);
    private String serverKey = "AIzaSyANztP01h4SRxFBJjiKLrxm5uWP1yCQr4E";
    String x1;
    String x2;
    String x3;
    String x4;
    String x5;
    String x6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_line);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapTest)).getMapAsync(this);

        //รับข้อมูล
        intentBusStop();

        //setup Location
        setupLocation();




    }//Main Method

    private void requestDirection() {
        LatLng origin = new LatLng(13.778512, 100.507915);
        LatLng destination = new LatLng(13.787855, 100.490267);
        GoogleDirection.withServerKey(serverKey)
                .from(new LatLng(latStartADouble,lngStartADouble))
                //.from(origin)
                //.to(destination)
                .to(new LatLng(latSearch, lngSearch))
                .language(Language.THAI)
                .transportMode(TransportMode.TRANSIT)
                .unit(Unit.METRIC)
                .transitMode(TransitMode.BUS)
                .execute(this);

    }

    private void intentBusStop() {
        Bundle LatLng = getIntent().getExtras();
        if (LatLng != null) {
            latSearch = LatLng.getDouble("LatSearch");
            lngSearch = LatLng.getDouble("LngSearch");
            //Toast.makeText(getApplicationContext(), "LatSearch  "+latSearch +"\nLngSearch "+lngSearch, Toast.LENGTH_SHORT).show();


        }
    }




    public Location myFindLocation(String strProvider) {

        Location location = null;
        if (locationManager.isProviderEnabled(strProvider)) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return null;
            }
            locationManager.requestLocationUpdates(strProvider, 1000, 10, locationListener);
            location = locationManager.getLastKnownLocation(strProvider);
        }
        return location;
    }
    @Override
    protected void onResume() {
        super.onResume();
        //for Network
        Location networkLocation = myFindLocation(LocationManager.NETWORK_PROVIDER);
        if (networkLocation != null) {
            latStartADouble = networkLocation.getLatitude();
            lngStartADouble = networkLocation.getLongitude();
        }
        //for GPS
        Location gpsLocation = myFindLocation(LocationManager.GPS_PROVIDER);
        if (gpsLocation != null) {
            latStartADouble = gpsLocation.getLatitude();
            lngStartADouble = gpsLocation.getLongitude();
            Toast.makeText(getApplicationContext(), "latStartADouble11  "+latStartADouble +"\nlngStartADouble11 "+lngStartADouble, Toast.LENGTH_SHORT).show();
            Log.d("14JulV1", "current lat  " + latStartADouble);
            Log.d("14JulV1", "current lng   " + lngStartADouble);
            requestDirection();

        }
        if (gpsLocation == null) {
            Toast.makeText(getApplicationContext(), "ไม่มี GPS", Toast.LENGTH_SHORT).show();

        }


    }
    @Override
    protected void onStop() {
        super.onStop();
        locationManager.removeUpdates(locationListener);
    }



    public LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            latStartADouble = location.getLatitude();
            lngStartADouble = location.getLongitude();
            Toast.makeText(getApplicationContext(), "LatChang  "+latStartADouble +"\nlngChang "+lngStartADouble, Toast.LENGTH_SHORT).show();
            Log.d("14JulV1", "GPS" + latStartADouble);
            Log.d("14JulV1", "GPS" + lngStartADouble);

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    private void setupLocation() {
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);

    }


    @Override
    public void onDirectionSuccess(Direction direction, String rawBody) {
        String status = direction.getStatus();
        if (status.equals(RequestResult.OK)) {
            Toast toast = Toast.makeText(this, "OK", Toast.LENGTH_LONG);
            toast.show();


            ArrayList<LatLng> sectionPositionList = direction.getRouteList().get(0).getLegList().get(0).getSectionPoint();
            for (LatLng position : sectionPositionList) {
                googleMap.addMarker(new MarkerOptions().position(position));
            }

            List<Step> stepList = direction.getRouteList().get(0).getLegList().get(0).getStepList();
            ArrayList<PolylineOptions> polylineOptionList = DirectionConverter.createTransitPolyline(this, stepList, 5, Color.RED, 3, Color.BLUE);
            for (PolylineOptions polylineOption : polylineOptionList) {
                googleMap.addPolyline(polylineOption);
            }

            Button b1 = (Button) findViewById(R.id.buttOnline);
            String z = direction.getRouteList().get(0).getLegList().get(0).getStepList().get(0).getTravelMode();
            String a = "WALKING";
            boolean gg = z.equals(a);
            int i = 0;
            if (gg==true) {
                i=1;
                x1 = direction.getRouteList().get(0).getLegList().get(0).getStepList().get(i).getTransitDetail().getLine().getShortName();
                x2 = direction.getRouteList().get(0).getLegList().get(0).getStepList().get(i).getTransitDetail().getStopNumber();
                x3 = direction.getRouteList().get(0).getLegList().get(0).getStepList().get(i).getTransitDetail().getLine().getName();
                x4 = direction.getRouteList().get(0).getLegList().get(0).getStepList().get(i).getDistance().getText();
                x5 = direction.getRouteList().get(0).getLegList().get(0).getStepList().get(i).getTransitDetail().getDepartureStopPoint().getName();
                x6 = direction.getRouteList().get(0).getLegList().get(0).getStepList().get(i).getTransitDetail().getArrivalStopPoint().getName();
                b1.setText("รถประจำทางสาย " + x1);
                /*tg1.setText("รถประจำทางสาย " + x1);
                tg2.setText("ต้องผ่านทั้งหมด " + x2 + " ป้าย");
                tg3.setText("วิ่งจาก " + x3);
                tg4.setText("อีก " + x4);
                tg5.setText("ป้ายรถประจำทางเริ่มต้น " +x5);
                tg6.setText("ป้ายรถประจำทางปลายทาง "+x6);*/

            }
            else {
                x1 = direction.getRouteList().get(0).getLegList().get(0).getStepList().get(i).getTransitDetail().getLine().getShortName();
                x2 = direction.getRouteList().get(0).getLegList().get(0).getStepList().get(i).getTransitDetail().getStopNumber();
                x3 = direction.getRouteList().get(0).getLegList().get(0).getStepList().get(i).getTransitDetail().getLine().getName();
                x4 = direction.getRouteList().get(0).getLegList().get(0).getStepList().get(i).getDistance().getText();
                x5 = direction.getRouteList().get(0).getLegList().get(0).getStepList().get(i).getTransitDetail().getDepartureStopPoint().getName();
                x6 = direction.getRouteList().get(0).getLegList().get(0).getStepList().get(i).getTransitDetail().getArrivalStopPoint().getName();
                b1.setText("รถประจำทางสาย " + x1);

            }
        }
        else if (status.equals(RequestResult.NOT_FOUND)) {
            Toast toast = Toast.makeText(this, "NOT_FOUND", Toast.LENGTH_LONG);
            toast.show();
        } else if (status.equals(RequestResult.ZERO_RESULTS)) {
            Toast toast = Toast.makeText(this, "ไม่มีเส้นทาง", Toast.LENGTH_LONG);
            toast.show();
        } else if (status.equals(RequestResult.MAX_WAYPOINTS_EXCEEDED)) {
            Toast toast = Toast.makeText(this, "MAX_WAYPOINTS_EXCEEDE", Toast.LENGTH_LONG);
            toast.show();
        } else if (status.equals(RequestResult.REQUEST_DENIED)) {
            Toast toast = Toast.makeText(this, "REQUEST_DENIED", Toast.LENGTH_LONG);
            toast.show();
        } else if (status.equals(RequestResult.UNKNOWN_ERROR)) {
            Toast toast = Toast.makeText(this, "UNKNOWN_ERROR", Toast.LENGTH_LONG);
            toast.show();
        }
    }
    public void Onclick1(View view) {
        Intent intentt = new Intent(Bus_line.this, Bus_details.class);
        intentt.putExtra("รถประจำทางสาย", x1);
        intentt.putExtra("ต้องผ่านทั้งหมด", x2);
        intentt.putExtra("วิ่งจาก", x3);
        intentt.putExtra("อีก", x4);
        intentt.putExtra("ป้ายรถประจำทางเริ่มต้น", x5);
        intentt.putExtra("ป้ายรถประจำทางปลายทาง", x6);

        startActivity(intentt);
    }

    @Override
    public void onDirectionFailure(Throwable t) {
        Toast toast = Toast.makeText(this, "No", Toast.LENGTH_LONG);
        toast.show();

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latSearch, lngSearch), 16));
    }
}

