package com.stafico.remonttracker;

public class ProjectModel {
    public String title;
    public String description;

    public ProjectModel(String title, String description) {
        this.title = title;
        this.description = description;
    }

    // ГЕТТЕРИ
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    // можна додати сеттери пізніше, якщо буде потрібно змінювати значення
}
