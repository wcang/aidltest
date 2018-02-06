package com.wcang.aidltest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class AidlServer extends Service {
    private static final String TAG = "AidlServer";
    private final ServerUtility serverUtility = new ServerUtility();

    public AidlServer() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return serverUtility;
    }

    public class ServerUtility extends IServerUtility.Stub {
        @Override
        public int sum(int a, int b) throws RemoteException {
            Log.i(TAG, "sum: " + (a + b) + " process id " + android.os.Process.myPid() + " thread id " + Thread.currentThread().getId());
            return a + b;
        }

        @Override
        public void suicide(IDie method) throws RemoteException {
            Log.i(TAG, "method to die: " + method.getName() + " process id " + android.os.Process.myPid() + " thread id " + Thread.currentThread().getId());
            method.commit();
        }
    }
}
