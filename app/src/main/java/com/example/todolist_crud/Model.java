package com.example.todolist_crud;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


import java.util.UUID;

import io.reactivex.rxjava3.annotations.NonNull;

@Entity(tableName = "Task_table")

public class Model {


    @PrimaryKey
    @NonNull

    private String title    ;
    private String uuid;
    private int done;
    private String date;
    private String image;


    public Model() {
        this.done = 0;
        uuid = UUID.randomUUID().toString();
    }

    public int getDone() {
        return done;
    }

    public void setDone(int done) {
        this.done = done;
    }

    public String getUuid() {
        return  uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }



    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}
