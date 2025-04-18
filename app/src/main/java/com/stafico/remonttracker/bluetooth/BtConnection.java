package com.stafico.remonttracker.bluetooth;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

import androidx.annotation.RequiresPermission;

public class BtConnection {

    private final BluetoothAdapter adapter;
    private ConnectThread cThread;

    public BtConnection(BluetoothAdapter adapter) {
        this.adapter = adapter;
    }

    @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
    public void connect(String mac) {
        if (adapter.isEnabled() && mac != null && !mac.isEmpty()) {
            BluetoothDevice device = adapter.getRemoteDevice(mac);
            if (device != null) {
                cThread = new ConnectThread(device);
                cThread.start();
            }
        }
    }

    public ConnectThread getConnectThread() {
        return cThread;
    }

    public void sendMessage(String message) {
        cThread.rThread.sendMessage(message.getBytes());
    }
}
