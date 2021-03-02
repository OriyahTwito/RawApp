package com.example.rawapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

// View.OnClickListener - Implementation of the function "onClick"
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnAudio, btnVideo, btnText; // Set Button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // The design of MainActivity

        initUI();
        initListeners();
    }

    private void initUI() {
        // Id of Button
        btnAudio = findViewById(R.id.btnAudio);
        btnVideo = findViewById(R.id.btnVideo);
        btnText = findViewById(R.id.btnText);
    }

    private void initListeners() {
        // Give access to elements to be clicked
        btnAudio.setOnClickListener(this);
        btnVideo.setOnClickListener(this);
        btnText.setOnClickListener(this);
    }

    // Performs tasks after click on the elements
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, SecondActivity.class);
        switch (v.getId()) {
            // Intent to SecondActivity and pass to SecondActivity data
            case R.id.btnAudio:
                intent.putExtra("rawType", "audio");
                startActivity(intent);
                break;
            // Intent to SecondActivity and pass to SecondActivity data
            case R.id.btnVideo:
                intent.putExtra("rawType", "video");
                startActivity(intent);
                break;
            // Intent to SecondActivity and pass to SecondActivity data
            case R.id.btnText:
                intent.putExtra("rawType", "text");
                startActivity(intent);
                break;
        }
    }

}