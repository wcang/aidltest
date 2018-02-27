package com.wcang.aidltest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class AidlServer extends Service {
    private static final String TAG = "AidlServer";
    private final ServerUtility serverUtility = new ServerUtility();
    private IBinder clientBinder;

    public AidlServer() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return serverUtility;
    }

    /* refer to https://www.androiddesignpatterns.com/2013/08/binders-death-recipients.html
       Android service use this to perform proper clean up
     */
    private class Reaper implements IBinder.DeathRecipient {
        @Override
        public void binderDied() {
            Log.i(TAG, "Remote client died");
        }
    }

    public class ServerUtility extends IServerUtility.Stub {
        @Override
        public int sum(int a, int b) throws RemoteException {
            Log.i(TAG, "sum: " + (a + b) + " process id " + android.os.Process.myPid() + " thread id " + Thread.currentThread().getId());
            return a + b;
        }

        @Override
        public void deliverBinder(IBinder binder) throws RemoteException {
            clientBinder = binder;

            clientBinder.linkToDeath(new Reaper(), 0);
            return;
        }

        @Override
        public void suicide(IDie method) throws RemoteException {
            Log.i(TAG, "method to die: " + method.getName() + " process id " + android.os.Process.myPid() + " thread id " + Thread.currentThread().getId());
            method.commit();
        }
    }
}
