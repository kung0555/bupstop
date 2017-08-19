package com.example.faster.test;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.akexorcist.googledirection.DirectionCallback;
import com.akexorcist.googledirection.GoogleDirection;
import com.akexorcist.googledirection.constant.Language;
import com.akexorcist.googledirection.constant.RequestResult;
import com.akexorcist.googledirection.constant.TransitMode;
import com.akexorcist.googledirection.constant.TransportMode;
import com.akexorcist.googledirection.constant.Unit;
import com.akexorcist.googledirection.model.Direction;
import com.akexorcist.googledirection.model.TransitDetail;
import com.akexorcist.googledirection.model.Route;
import com.akexorcist.googledirection.model.Step;
import com.akexorcist.googledirection.util.DirectionConverter;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;


public class Bus_details extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        intentBusline();

    }

    private void intentBusline() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String Busnum = bundle.getString("รถประจำทางสาย");
            String Sum = bundle.getString("ต้องผ่านทั้งหมด");
            String StartEnd = bundle.getString("วิ่งจาก");
            String KM = bundle.getString("อีก");
            String StopFirst = bundle.getString("ป้ายรถประจำทางเริ่มต้น");
            String StopEnd = bundle.getString("ป้ายรถประจำทางปลายทาง");


            TextView tg1 = (TextView) findViewById(R.id.textView2);
            TextView tg2 = (TextView) findViewById(R.id.textView3);
            TextView tg3 = (TextView) findViewById(R.id.textView4);
            TextView tg4 = (TextView) findViewById(R.id.textView5);
            TextView tg5 = (TextView) findViewById(R.id.textView6);
            TextView tg6 = (TextView) findViewById(R.id.textView7);
            tg1.setText("รถประจำทางสาย " + Busnum);
            tg2.setText("ต้องผ่านทั้งหมด " + Sum + " ป้าย");
            tg3.setText("วิ่งจาก " + StartEnd);
            tg4.setText("อีก " + KM);
            tg5.setText("ป้ายรถประจำทางเริ่มต้น " + StopFirst);
            tg6.setText("ป้ายรถประจำทางปลายทาง " + StopEnd);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.button1) {
            finish();
        }
    }
}






