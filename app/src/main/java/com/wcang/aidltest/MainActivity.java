package com.wcang.aidltest;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.ConnectionService;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private IServerUtility serverUtility;
    private Sepukku sepukku = new Sepukku();

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            serverUtility = IServerUtility.Stub.asInterface(iBinder);

            try {
                Log.i(TAG, "onServiceConnected");
                Log.i(TAG, "Server sum for us 5 + 6 = " + serverUtility.sum(5, 6));
                serverUtility.suicide(sepukku);
            } catch (RemoteException exc) {
                Log.e(TAG, "Having problem invoking remote sum " + exc.getLocalizedMessage());
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent i = new Intent(this, AidlServer.class);
        bindService(i, serviceConnection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        unbindService(serviceConnection);
        super.onDestroy();
    }

}
