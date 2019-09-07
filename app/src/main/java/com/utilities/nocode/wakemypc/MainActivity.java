package com.utilities.nocode.wakemypc;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    public static MainActivity activity;

    private Task task;

    public TextView console;

    public String test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity = this;
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Activity activity = this;

        console = (TextView) findViewById(R.id.console);
        console.setMovementMethod(new ScrollingMovementMethod());

        append("Start was Successfully...");

        Button btn = findViewById(R.id.wake);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task = new Task();
                task.execute();
                append("Starting to connect now");
            }
        });
    }

    public void append(final String text){


        try {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    console.append(text + "\n");
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
