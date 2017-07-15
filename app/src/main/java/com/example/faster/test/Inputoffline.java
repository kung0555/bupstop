package com.example.faster.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Inputoffline extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inputoffline);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Toast.makeText(this,"ใส่ป้ายรถประจำทางที่ต้องการ",Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void OnclickOK_off(View view) {
        EditText inputOffline = (EditText) findViewById(R.id.offeditText);
        String inputOff = inputOffline.getText().toString();
        if (inputOff.matches("")) {
            Toast.makeText(this, "กรุณาใส่ป้ายรถประจำทางเป้าหมาย", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(Inputoffline.this, Bus_line_offline.class);
            intent.putExtra("inputOff", inputOff);
            startActivity(intent);


        }
    }

    public void OnclickCancel_off(View view) {
        finish();
    }
}