package edu.wolf.mirror;

import android.app.Service;
import android.graphics.Color;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MainActivity extends AppCompatActivity implements Button.OnClickListener {

    String TAG = "TCPServer", message = "State of Mirror";
    Thread myNet;

    TextView ip;
    ViewSwitcher switcher;
    Button a1;

    String a1String;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switcher = (ViewSwitcher) findViewById(R.id.activity_main);
        switcher.showNext();

        WifiManager wm = (WifiManager) getSystemService(Service.WIFI_SERVICE);
        WifiInfo info = wm.getConnectionInfo();
        int ipint = info.getIpAddress();
        String ipAddress = Formatter.formatIpAddress(ipint);
        //String ipAddress = Integer.toString(wm.getConnectionInfo().getIpAddress());

        Log.d("IP", ipAddress);
        ip = (TextView) findViewById(R.id.ip);
        ip.setTextColor(Color.parseColor("#ffffff"));
        String display = "IP: " + ipAddress;
        ip.setText(display);

        a1 = (Button) findViewById(R.id.A1);

        doNetwork stuff = new doNetwork();
        myNet = new Thread(stuff);
        myNet.start();

    }

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            Log.d("Server", msg.getData().getString("msg"));
            return true;
        }

    });

    @Override
    public void onClick(View v) {
        //Toast.makeText(MainActivity.this, "OnClick Happened", Toast.LENGTH_SHORT).show();
        doNetwork stuff = new doNetwork();
        myNet = new Thread(stuff);
        myNet.start();
    }

    public void mkmsg(String str) {
        //handler junk, because threads can't update screen!
        Message msg = new Message();
        Bundle b = new Bundle();
        b.putString("msg", str);
        msg.setData(b);
        handler.sendMessage(msg);
    }

    class doNetwork implements Runnable {
        public void run() {

            int p = 3012;

            while(!message.equals("Done"))
            {
                try {
                    mkmsg("Waiting on Connecting...\n");
                    Log.v(TAG,"S: Connecting...");
                    ServerSocket serverSocket = new ServerSocket(p);

                    //socket created, now wait for a coonection via accept.
                    Socket client = serverSocket.accept();
                    Log.v(TAG,"S: Receiving...");

                    //switcher.showNext();

                    try {
                        //setup send/receive streams.
                        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())), true);

                        while(!message.equals("Closed"))
                        {
                            //receive the message first.
                            mkmsg("Attempting to receive a message ...\n");
                            //Toast.makeText(MainActivity.this, "I'm TRYING", Toast.LENGTH_SHORT).show();
                            String str = in.readLine();
                            mkmsg("received a message:\n" + str + "\n");

                            if(str.equals("A1 clock"))
                            {
                                //message = "Closed";
                                out.println("close");
                                a1.setText("Clock");
                                in.close();
                                out.close();
                                break;
                            }
                            else if(str.equals("A1 weather"))
                            {
                                //message = "Closed";
                                out.println("close");
                                a1.setText("Weather");
                                in.close();
                                out.close();
                                break;
                            }
                            else if(str.equals("A1 reminder"))
                            {
                                //message = "Closed";
                                out.println("close");
                                a1.setText("Reminder");
                                in.close();
                                out.close();
                                break;
                            }
                            else if(str.equals("A1 alarm"))
                            {
                                //message = "Closed";
                                out.println("close");
                                a1.setText("Alarm");
                                in.close();
                                out.close();
                                break;
                            }
                            else
                            {
                                message = "State of Mirror";
                                out.println(message);
                            }
                        } // end of while

                        //now close down the send/receive streams.

                        in.close();
                        out.close();

                    } catch (Exception e) {
                        mkmsg("Error happened sending/receiving\n");
                        client.close();
                        serverSocket.close();
                        run();

                    } finally {
                        //Toast.makeText(MainActivity.this, "The world ended", Toast.LENGTH_SHORT).show();
                        //mkmsg("We are done, closing connection\n");
                        //message = "Done";
                        client.close();  //close the client connection
                        serverSocket.close();  //finally close down the server side as well.
                    }

                } catch (Exception e) {
                    mkmsg("Unable to connect...\n");
                    //message = "Done";
                    //run();
                }
            }

        }
    }
}
