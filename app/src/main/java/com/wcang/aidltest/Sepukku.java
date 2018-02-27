package com.wcang.aidltest;

import android.app.Activity;
import android.os.RemoteException;
import android.util.Log;

import java.lang.ref.WeakReference;

/**
 * Created by Ang Way Chuang on 2/5/2018.
 */

public class Sepukku extends IDie.Stub {
    private static final String TAG = "Sepukku";


    @Override
    public String getName() throws RemoteException {
        return "sepukku";
    }


    @Override
    public void commit() throws RemoteException {
        Log.i(TAG, "I've shamed myself. Commit sepukku: process id " + android.os.Process.myPid() + " thread id " + Thread.currentThread().getId());
        Runnable suicide = () -> {
            try {
                Thread.sleep(1000);
                android.os.Process.killProcess(android.os.Process.myPid());
            } catch (InterruptedException exc) {
                Log.e(TAG, "I've failed to commit suicide because someone disturbs my sleep " + exc.getLocalizedMessage() );
            }
        };
        new Thread(suicide).start();
    }
}
