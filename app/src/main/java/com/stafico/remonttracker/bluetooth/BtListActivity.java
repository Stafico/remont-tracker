package com.stafico.remonttracker.bluetooth;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.stafico.remonttracker.databinding.ActivityBluetoothBinding;

import java.util.ArrayList;
import java.util.Set;

// https://developer.android.com/develop/connectivity/bluetooth/setup
// https://www.youtube.com/watch?v=y8R2C86BIUc&list=PLgCYzUzKIBE8KHMzpp6JITZ2JxTgWqDH2
// https://www.youtube.com/watch?v=A2y3fSXjVMo
public class BtListActivity extends AppCompatActivity implements RcAdapter.Listener {

    private static final String TAG = "BtListActivity";

    public static final String DEVICE_KEY = "device_key";

    private BluetoothAdapter btAdapter;
    private ActivityBluetoothBinding binding;
    private RcAdapter adapter;

    @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBluetoothBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init();
    }

    @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
    private void init() {
        BluetoothManager btManager = getSystemService(BluetoothManager.class);
        btAdapter = btManager.getAdapter();
        adapter = new RcAdapter(this);
        binding.rcView.setLayoutManager(new LinearLayoutManager(this));
        binding.rcView.setAdapter(adapter);
        getPairedDevices();
    }

    @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
    private void getPairedDevices() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.BLUETOOTH_CONNECT},
                        1001); // Код запиту, будь-який
                return;
            }
        }
        Set<BluetoothDevice> pairedDevices = btAdapter.getBondedDevices();
        ArrayList<ListItem> tempList = new ArrayList<>();
        if (pairedDevices != null) {
            for (BluetoothDevice device : pairedDevices) {
                tempList.add(new ListItem(device.getName(), device.getAddress()));
            }
        }
        adapter.submitList(tempList);
    }

    @Override
    public void onClick(ListItem item) {
        Intent i = new Intent().putExtra(DEVICE_KEY, item);
        setResult(RESULT_OK, i);
        finish(); // закриваємо
    }
}
