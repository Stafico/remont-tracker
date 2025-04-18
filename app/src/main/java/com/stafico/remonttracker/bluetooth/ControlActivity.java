package com.stafico.remonttracker.bluetooth;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AppCompatActivity;

import com.stafico.remonttracker.R;
import com.stafico.remonttracker.databinding.ActivityControlBinding;

public class ControlActivity extends AppCompatActivity {

    private static final String TAG = "ControlActivity";

    private ActivityControlBinding binding;
    private ActivityResultLauncher<Intent> actListLauncher;
    private BtConnection btConnection;
    private ListItem listItem = null;

    private void init() {
        BluetoothManager btManager = getSystemService(BluetoothManager.class);
        BluetoothAdapter btAdapter = btManager.getAdapter();

        btConnection = new BtConnection(btAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityControlBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        onBtListResult();
        init();

        binding.bA.setOnClickListener(v -> {
            if (btConnection != null) {
                btConnection.sendMessage("Crocodilio Bombardilio");
                btConnection.sendMessage(String.valueOf(0x0A));
            }
        });

        binding.bB.setOnClickListener(v -> {
            if (btConnection != null) {
                btConnection.sendMessage("Test B");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.control_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // ClickListener

    @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.id_list) {
            // Тут очікуєм результат
            actListLauncher.launch(new Intent(this, BtListActivity.class));
        } else if (item.getItemId() == R.id.id_connect) {
            if (listItem != null && listItem.getMac() != null) {
                btConnection.connect(listItem.getMac());
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void onBtListResult() {
        actListLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    listItem = (ListItem) result.getData().getSerializableExtra(BtListActivity.DEVICE_KEY);
                }
            });
    }
}