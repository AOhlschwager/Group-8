package edu.wolf.smartmirror;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)){
            //boot has completed, now time to start our background service.
            Log.v("MyReceiver", "Got the boot one!");
            Intent i = new Intent(context, MainActivity.class);
            context.startService(i);
            Toast.makeText(context, "ON BOOT", Toast.LENGTH_LONG).show();
        }
    }
}
