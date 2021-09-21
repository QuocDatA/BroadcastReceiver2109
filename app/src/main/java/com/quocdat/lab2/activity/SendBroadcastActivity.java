package com.quocdat.lab2.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.quocdat.lab2.R;
import com.quocdat.lab2.services.BroadcastReceiverService;

public class SendBroadcastActivity extends AppCompatActivity {

    EditText edtInput;
    Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_broadcast);
        edtInput = findViewById(R.id.edtInput);
        btnSend = findViewById(R.id.btnSend);
    }

    public void onSendBroadcast(View view){
        String name = edtInput.getText().toString();

        Intent i = new Intent(this, BroadcastReceiverService.class);
        i.putExtra("name", name);
        i.setAction("com");
        startService(i);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter(BroadcastReceiverService.ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String myName = intent.getStringExtra("myName");
            Log.d("BBB", myName);
            Toast.makeText(SendBroadcastActivity.this, myName, Toast.LENGTH_SHORT).show();
        }
    };
}