package com.myapplicationdev.android.taskmanager;

import java.io.Serializable;

public class Task implements Serializable {

    private int id;
    private String name;
    private String description;
    private int second;

    public Task( int id, String name, String description, int seconds){
        this.id = id;
        this.name = name;
        this.description = description;
        this.second = seconds;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getSecond() {
        return second;
    }
}
