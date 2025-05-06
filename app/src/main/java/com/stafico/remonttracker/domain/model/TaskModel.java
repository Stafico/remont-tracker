package com.stafico.remonttracker.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class TaskModel implements Parcelable {
    private final String title;
    private final double price; // Додано поле
    private boolean isDone;

    public TaskModel(String title, double price, boolean isDone) {
        this.title = title;
        this.price = price;
        this.isDone = isDone;
    }

    protected TaskModel(Parcel in) {
        title = in.readString();
        price = in.readDouble(); // читаємо price
        isDone = in.readByte() != 0;
    }

    public static final Creator<TaskModel> CREATOR = new Creator<TaskModel>() {
        @Override
        public TaskModel createFromParcel(Parcel in) {
            return new TaskModel(in);
        }

        @Override
        public TaskModel[] newArray(int size) {
            return new TaskModel[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public double getPrice() { // геттер
        return price;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int flags) {
        parcel.writeString(title);
        parcel.writeDouble(price); // записуємо price
        parcel.writeByte((byte) (isDone ? 1 : 0));
    }
}
