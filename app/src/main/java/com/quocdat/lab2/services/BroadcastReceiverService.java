package com.quocdat.lab2.services;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;


public class BroadcastReceiverService extends IntentService {

    public static final String ACTION ="BroadcastReceiverService";

    public BroadcastReceiverService(){
        super("BroadcastReceiverService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String name = intent.getStringExtra("name");

        Intent i = new Intent(ACTION);
        i.putExtra("myName", name);

        LocalBroadcastManager.getInstance(this).sendBroadcast(i);

    }
}