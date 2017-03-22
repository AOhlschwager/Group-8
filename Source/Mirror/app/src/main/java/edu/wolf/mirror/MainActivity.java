package edu.wolf.mirror;

import android.app.Service;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    String TAG = "TCPServer";
    Thread myNet;

    Button a1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WifiManager wm = (WifiManager) getSystemService(Service.WIFI_SERVICE);
        String ip = Integer.toString(wm.getConnectionInfo().getIpAddress());
        Log.d("IP", ip);

        doNetwork stuff = new doNetwork();
        myNet = new Thread(stuff);
        myNet.start();

        a1 = (Button) findViewById(R.id.A1);
        a1.setText(ip);
    }

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            //output.append(msg.getData().getString("msg"));
            return true;
        }

    });

    public void mkmsg(String str) {
        //handler junk, because threads can't update screen!
        Message msg = new Message();
        Bundle b = new Bundle();
        b.putString("msg", str);
        msg.setData(b);
        handler.sendMessage(msg);
    }

    public void onClick(View v) {
        doNetwork stuff = new doNetwork();
        myNet = new Thread(stuff);
        myNet.start();
    }

    class doNetwork implements Runnable {
        public void run() {

            int p = 3012;

            try {
                mkmsg("Waiting on Connecting...\n");
                Log.v(TAG,"S: Connecting...");
                ServerSocket serverSocket = new ServerSocket(p);

                //socket created, now wait for a coonection via accept.
                Socket client = serverSocket.accept();
                Log.v(TAG,"S: Receiving...");

                try {
                    //setup send/receive streams.
                    BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())), true);

                    //receive the message first.
                    mkmsg("Attempting to receive a message ...\n");
                    String str = in.readLine();
                    mkmsg("received a message:\n" + str + "\n");

                    if(str.equals("A1 clock"))
                    {
                        a1.setText("Clock");
                    }

                    //now send a message.
                    String message = "Hello from server android emulator";
                    mkmsg("Attempting to send message ...\n");
                    out.println(message);
                    mkmsg("Message sent...\n");

                    //now close down the send/receive streams.
                    in.close();
                    out.close();

                } catch (Exception e) {
                    mkmsg("Error happened sending/receiving\n");

                } finally {
                    mkmsg("We are done, closing connection\n");
                    client.close();  //close the client connection
                    serverSocket.close();  //finally close down the server side as well.
                }

            } catch (Exception e) {
                mkmsg("Unable to connect...\n");
            }

        }
    }
}
