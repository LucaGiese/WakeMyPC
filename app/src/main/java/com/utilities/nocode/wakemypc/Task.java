package com.utilities.nocode.wakemypc;

import android.os.AsyncTask;
import android.widget.Toast;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;

public class Task extends AsyncTask {

    Socket s;
    ObjectOutputStream out;
    ObjectInputStream in;

    boolean running = true;

    @Override
    protected Object doInBackground(Object[] objects) {
        connect();
        return "";
    }

    public void connect(){
        s = new Socket();

        try {
            MainActivity.activity.append("Socket was created");
            s.setSoTimeout(1000 * 40);
            s.connect(new InetSocketAddress("noname.3utilities.com", 33599));
            MainActivity.activity.append("Socket has connected");

            out = new ObjectOutputStream(s.getOutputStream());
            in = new ObjectInputStream(s.getInputStream());

            MainActivity.activity.append("Streams are created");

            out.writeUTF(Key.key);
            out.flush();


            MainActivity.activity.append("Key was send");

            MainActivity.activity.append("Incoming: \n");

            String line = null;
            while(running && (line = in.readUTF()) != null){
                MainActivity.activity.append(line);

                if(line.equalsIgnoreCase("stop")){
                    break;
                }
            }

        }catch (IOException e){
            MainActivity.activity.append(e.getMessage());
        }
    }

    public void close(){
        try {
            out.flush();
            out.close();

            in.close();

            s.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
