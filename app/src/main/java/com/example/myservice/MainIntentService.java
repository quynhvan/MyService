package com.example.myservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainIntentService extends AppCompatActivity {
    private Button btnStart;
    private Button btnStop;
    private TextView tvViewPercent;
    private ProgressBar progressBar;

    //broadcast component
    public class ResponseReceiver extends BroadcastReceiver {

        //on broadcast received
        @Override
        public void onReceive(Context context, Intent intent) {
            //check action name
            if(intent.getAction().equals(MyIntentService.ACTION_1)){
                int value = intent.getIntExtra(MyIntentService.PARAM_PERCENT,0);
                new ShowProgressBarTask().excute(value);
            }
        }
    }

    //display value for the progressbar
    class ShowProgressBarTask extends
}
