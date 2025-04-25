package com.stafico.remonttracker.bluetooth;

import android.bluetooth.BluetoothSocket;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ReceiveThread extends Thread {

    private static final String TAG = "ReceiveThread";

    private final BluetoothSocket bSocket;
    private InputStream inStream;
    private OutputStream outStream;

    public ReceiveThread(BluetoothSocket bSocket) {
        this.bSocket = bSocket;
        try {
            this.inStream = bSocket.getInputStream();
        } catch (IOException e) {
            // Обробка помилки
        }
        try {
            this.outStream = bSocket.getOutputStream();
        } catch (IOException e) {
            // Обробка помилки
        }
    }

    @Override
    public void run() {
        byte[] buf = new byte[32];
        while (true) {
            try {
                int size = inStream.read(buf); // тут чекаємо поки дані не отримані
                // offset з якої позиції зліва читати
                String message = new String(buf, 0, size); // перетворюємо дані в рядок
                Log.d(TAG, "Message: " + message);
            } catch (IOException e) {
                break; // вийти з потоку
            }
        }
    }

    public void sendMessage(byte[] byteArray) {
        try {
            outStream.write(byteArray);
        } catch (IOException e) {

        }
    }

    public void test() {
    }
}
