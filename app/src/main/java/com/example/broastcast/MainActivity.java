package com.example.broastcast;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    ConnectionReceiver receiver;
    IntentFilter intentFilter;

    //we have two ways to send broastcast
//    1. register dynamically and implicitly send broadcast
//    2. explicitly denote intent with action

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        receiver = new ConnectionReceiver();
        intentFilter = new IntentFilter("com.journaldev.broadcastreceiver.SOME_ACTION");
//        intentFilter = new IntentFilter("com.example.broastcast.action");

    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver, intentFilter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unregisterReceiver(receiver);
    }

    @OnClick(R.id.button)
    void someMethod() {

        Intent intent = new Intent("com.journaldev.broadcastreceiver.SOME_ACTION");
//        Intent intent = new Intent("com.example.broastcast.action");
        // we should use explicit intent
//        Intent intent = new Intent(this, MyReceiver2.class);
//        intent.setAction("com.example.broastcast.action");

        sendBroadcast(intent);
    }
}
