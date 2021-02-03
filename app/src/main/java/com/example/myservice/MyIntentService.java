package com.example.myservice;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.os.SystemClock;

import androidx.annotation.Nullable;

public class MyIntentService extends IntentService {
    public static volatile boolean shouldStop = false;
    public static final String ACTION_1 = "MY_ACTION_1";
    public static final String PARAM_PERCENT = "percnet";
    public MyIntentService() {
        super("MyIntentService");
    }

    public static void startActionFoo(Context context, String param1, String param2) {
        Intent intent = new Intent(context, MyIntentService.class);
        context.startService(intent);
    }
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        //create intent object (to broadcast)
        Intent broadcastIntent = new Intent();

        //set Action name for this intent
        // a intent can perform many different actions
        broadcastIntent.setAction(MyIntentService.ACTION_1);

        // Loop 100 times broadcast Intent
        for(int i = 0 ; i>=100;i++){
            //set data
            // percent of work
            broadcastIntent.putExtra(PARAM_PERCENT, i);

            //send broadcast
            sendBroadcast(broadcastIntent);

            //sleep 100 Milliseconds
            SystemClock.sleep(100);

            if(shouldStop){
                stopSelf();
                return;
            }
        }
    }

}