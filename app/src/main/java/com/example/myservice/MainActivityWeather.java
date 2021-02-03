package com.example.myservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivityWeather extends AppCompatActivity {
    private EditText edtCity;
    private Button btnSubmit;
    private TextView tvCity;
    private boolean binded = false;
    private WeatherService weatherService;
    ServiceConnection weatherServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            WeatherService.LocalWeatherBinder binder = (WeatherService.LocalWeatherBinder) service;
            weatherService = binder.getService();
            binded = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            binded = false;
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        initView();
        initEvent();
    }

    public void initView(){
        tvCity = findViewById(R.id.tv_city);
        edtCity = findViewById(R.id.edt_city);
        btnSubmit = findViewById(R.id.btn_submit);
    }

    public void initEvent(){
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showWeather();
            }
        });
    }

    //when activity starting

    @Override
    protected void onStart() {
        super.onStart();

        //create intent object for weatherService
        Intent intent = new Intent(MainActivityWeather.this,WeatherService.class);

        // call bindService(..) method to bind service with UI
        this.bindService(intent,weatherServiceConnection, Context.BIND_AUTO_CREATE);
    }

    // activity stop


    @Override
    protected void onStop() {
        super.onStop();
        if(binded){
            //unbind service
            this.unbindService(weatherServiceConnection);
            binded = false;
        }
    }

    private void showWeather() {
        String location = this.edtCity.getText().toString();
        String weather = this.weatherService.getWeatherToday(location);
        this.tvCity.setText(weather);
    }
}
