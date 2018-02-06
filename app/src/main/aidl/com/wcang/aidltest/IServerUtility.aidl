// IServerUtility.aidl
package com.wcang.aidltest;
import com.wcang.aidltest.IDie;

// Declare any non-default types here with import statements

interface IServerUtility {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    int sum(int a, int b);

    void suicide(IDie method);
}
