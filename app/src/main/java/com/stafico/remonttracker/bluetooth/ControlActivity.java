package com.stafico.remonttracker.bluetooth;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.stafico.remonttracker.databinding.ActivityControlBinding;
import com.stafico.remonttracker.domain.model.TaskModel;

import java.util.ArrayList;

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

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.BLUETOOTH_CONNECT}, 1);

        ArrayList<TaskModel> taskList = getIntent().getParcelableArrayListExtra("taskList");

        if (taskList == null) {
            Log.d(TAG, "Ошибка передачи списка через Intent");
            finish();
            return;
        }

        onBtListResult();
        init(); //bt connection


        binding.bCheckout.setOnClickListener(v -> {
            if (btConnection != null) {


                btConnection.sendMessage("JOB RECEIPT");

                btConnection.sendMessage(new byte[] {0x0A, 0x0A, 0x0A});
                for (TaskModel e : taskList) {
                    btConnection.sendMessage(e.getTitle() + "      1.00 UAH");
                    btConnection.sendMessage(new byte[]{0x0A, 0x0A});
                }

                btConnection.sendMessage(new byte[] {0x0A, 0x0A, 0x0A});
            }
        });

        binding.idList.setOnClickListener(v -> {
            // Тут очікуєм результат
            actListLauncher.launch(new Intent(this, BtListActivity.class));
        });
    }

    @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
    private void onBtListResult() {
        actListLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    listItem = (ListItem) result.getData().getSerializableExtra(BtListActivity.DEVICE_KEY);

                    if (listItem.getMac() != null) {
                        btConnection.connect(listItem.getMac());
                    }
                }
            });
    }
}