package com.example.myservice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnPlay;
    private Button btnStop;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnPlay = findViewById(R.id.btn_play);
        btnStop = findViewById(R.id.btn_stop);
        initEvent();
    }

    private void initEvent(){
        btnStop.setOnClickListener(this);
        btnPlay.setOnClickListener(this);
    }

    private void PlaySong(){
        // create intent object for PlaySongService
        Intent intent = new Intent(MainActivity.this, PlaySongService.class);

        // call startService with Intent parameter
        this.startService(intent);
    }

    private void StopSong(){
        // create intent object
        Intent intent = new Intent(MainActivity.this, PlaySongService.class);
        this.stopService(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_play:
                PlaySong();
                break;
            case R.id.btn_stop:
                StopSong();
                break;
            default:
                break;
        }
    }
}
