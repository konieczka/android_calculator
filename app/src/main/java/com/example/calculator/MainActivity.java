package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button buttonSimple;
    private Button buttonAdvanced;
    private Button buttonAbout;
    private Button buttonExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindMenuButtons();

        buttonSimple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToSimple = new Intent(MainActivity.this, SimpleActivity.class);
                startActivity(goToSimple);
            }
        });

        buttonAdvanced.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToSimple = new Intent(MainActivity.this, AdvancedActivity.class);
                startActivity(goToSimple);
            }
        });

        buttonAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToSimple = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(goToSimple);
            }
        });

        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.finish();
                System.exit(0);
            }
        });
    }

    private void bindMenuButtons(){
        buttonSimple = (Button) findViewById(R.id.buttonSimple);
        buttonAdvanced = (Button) findViewById(R.id.buttonAdvanced);
        buttonAbout = (Button) findViewById(R.id.buttonAbout);
        buttonExit = (Button) findViewById(R.id.buttonExit);
    }
}
