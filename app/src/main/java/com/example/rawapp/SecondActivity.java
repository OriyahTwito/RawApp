package com.example.rawapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

// View.OnClickListener - Implementation of the function "onClick"
public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textViewText; // Set TextView
    private Button btnPlay, btnPause, btnStop; // Set Button
    private String rawType, all = ""; // Set String
    private MediaPlayer mediaPlayer; // Set MediaPlayer
    private VideoView videoView; // Set VideoView
    private InputStream inputStream; // Set InputStream
    private InputStreamReader inputStreamReader; // Set InputStreamReader
    private BufferedReader bufferedReader; // Set BufferedReader

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second); // The design of SecondActivity

        initUI();
        initListeners();
    }

    private void initUI() {
        // Id of TextView
        textViewText = findViewById(R.id.textViewText);
        // Id of Button
        btnPlay = findViewById(R.id.btnPlay);
        btnPause = findViewById(R.id.btnPause);
        btnStop = findViewById(R.id.btnStop);
        // Id of VideoView
        videoView = findViewById(R.id.videoView);

        // Get data from MainActivity
        rawType = getIntent().getStringExtra("rawType");

        // Initialize audio
        if (rawType.equals("audio")) {
            mediaPlayer = MediaPlayer.create(this, R.raw.audio);
        // Initialize video
        } else if (rawType.equals("video")) {
            // Set videoView VISIBLE
            videoView.setVisibility(View.VISIBLE);

            // Set urlPath into videoView
            int idVideo = R.raw.video;
            String urlPath = "android.resource://" + getPackageName() + "/" + idVideo;
            Uri uri = Uri.parse(urlPath);
            videoView.setVideoURI(uri);

            // Set controller into videoView
            MediaController mediaController = new MediaController(this);
            videoView.setMediaController(mediaController);
            mediaController.setAnchorView(videoView);
        // Initialize text
        } else if (rawType.equals("text")) {
            inputStream = getResources().openRawResource(R.raw.text);
            // Parse text
            inputStreamReader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(inputStreamReader);

            // Put text into textViewText
            try {
                String t;
                while ((t = bufferedReader.readLine()) != null) {
                    all += t;
                }

                bufferedReader.close();

                textViewText.setText(all);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void initListeners() {
        // Give access to elements to be clicked
        btnPlay.setOnClickListener(this);
        btnPause.setOnClickListener(this);
        btnStop.setOnClickListener(this);
    }

    // Show AlertDialog
    private void startAlertDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Are you sure?")

                .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                    // Stop audio / video
                    if (rawType.equals("audio")) {
                        mediaPlayer.stop();
                    } else if (rawType.equals("video")) {
                        videoView.stopPlayback();
                    }
                })

                .setNegativeButton(android.R.string.no, (dialog, which) -> {

                })

                .show();
    }

    // Performs tasks after click on the elements
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // Play audio / video
            case R.id.btnPlay:
                if (rawType.equals("audio")) {
                    mediaPlayer.start();
                } else if (rawType.equals("video")) {
                    videoView.start();
                }
                break;
            // Pause audio / video
            case R.id.btnPause:
                if (rawType.equals("audio")) {
                    mediaPlayer.pause();
                } else if (rawType.equals("video")) {
                    videoView.pause();
                }
                break;
            // Stop audio / video
            case R.id.btnStop:
                startAlertDialog();
                break;
        }
    }

}