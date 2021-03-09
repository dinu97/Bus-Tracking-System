package com.example.busmanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomePage extends AppCompatActivity {
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        btn = (Button) findViewById(R.id.btn_busSchedule);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            OpenBusSchedule();
            }
        });
    }
    public void  OpenBusSchedule()
    {
        Intent intent = new Intent(this,BusSchedule.class);
        startActivity(intent);
    }
}
