package com.android.androidcrudjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    private void cadastrarTela(Intent i){

        //startActivity(new Intent(MainActivity.this, R.layout.create_layout));


    }

}

