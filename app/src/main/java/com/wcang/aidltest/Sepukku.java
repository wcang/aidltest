package com.wcang.aidltest;

import android.os.RemoteException;
import android.util.Log;

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
    }
}
