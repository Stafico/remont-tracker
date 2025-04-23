package com.stafico.remonttracker.domain.model;

public class TaskModel {
    private String title;
    private boolean isDone;

    public TaskModel(String title, boolean isDone) {
        this.title = title;
        this.isDone = isDone;
    }

    public String getTitle() {
        return title;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
