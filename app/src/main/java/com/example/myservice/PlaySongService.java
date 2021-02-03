package com.example.myservice;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class PlaySongService extends Service {
    private MediaPlayer mediaPlayer;
    public PlaySongService() {
    }

    // return the communication channel to the service
    @Override
    public IBinder onBind(Intent intent) {
        // this service is unbounded
        // so this method is never called
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.tet);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //play song
        mediaPlayer.start();
        //START_STICKY nói với các hệ điều hành để tạo lại các dịch vụ sau khi đã có đủ bộ nhớ
        // và gọi onStartCommand() một lần nữa với một Intent null.
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        mediaPlayer.release();
        // release the resources
        super.onDestroy();

    }
}