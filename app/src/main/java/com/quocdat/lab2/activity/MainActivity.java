package com.quocdat.lab2.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.quocdat.lab2.R;

public class MainActivity extends AppCompatActivity {

    Button btnBai2, btnBai3, btnBai4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnBai2 = findViewById(R.id.btnBai2);

        btnBai2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SendBroadcastActivity.class);
                startActivity(i);
            }
        });
    }



    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        registerReceiver(receiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)){
                int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR);
                String msg = "";
                switch (state){
                    case BluetoothAdapter.STATE_OFF:
                        msg = "Bluetooth STATE_OFF";
                        break;
                    case BluetoothAdapter.STATE_TURNING_OFF:
                        msg = "Bluetooth STATE_TURNING_OFF";
                        break;
                    case BluetoothAdapter.STATE_ON:
                        msg = "Bluetooth STATE_ON";
                        break;
                    case BluetoothAdapter.STATE_TURNING_ON:
                        msg = "Bluetooth STATE_TURNING_ON";
                        break;
                }
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        }
    };
}