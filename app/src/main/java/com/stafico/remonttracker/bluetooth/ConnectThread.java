package com.stafico.remonttracker.bluetooth;

import android.Manifest;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

import androidx.annotation.RequiresPermission;

import java.io.IOException;
import java.util.UUID;

public class ConnectThread extends Thread {
    private static final String TAG = "ConnectThread";
    private static final String UUID_STRING = "00001101-0000-1000-8000-00805F9B34FB";

    private BluetoothSocket mSocket;
    public ReceiveThread rThread;

    @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
    public ConnectThread(BluetoothDevice device) {
         try {
            mSocket = device.createRfcommSocketToServiceRecord(UUID.fromString(UUID_STRING));
        } catch (IOException e) {
            Log.e(TAG, "Socket creation failed", e);
        }
    }

    @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
    @Override
    public void run() {
        try {
            Log.d(TAG, "Connecting...");
            if (mSocket != null) {
                mSocket.connect();
                // запускаємо receiveThread
                rThread = new ReceiveThread(mSocket);
                rThread.start();
                Log.d(TAG, "Connected");
            }
        } catch (IOException e) {
            Log.d(TAG, "Can not connect to device", e);
            closeConnection();
        }
    }

    public void closeConnection() {
        try {
            if (mSocket != null) {
                mSocket.close();
            }
        } catch (IOException e) {
            Log.e(TAG, "Error closing socket", e);
        }
    }

    public BluetoothSocket getSocket() {
        return mSocket;
    }
}
