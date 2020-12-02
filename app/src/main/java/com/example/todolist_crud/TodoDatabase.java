package com.example.todolist_crud;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Model.class}, version = 1, exportSchema = false)
public abstract class TodoDatabase extends RoomDatabase {

    public abstract TodoDao todoDao();
    public static TodoDatabase todoDatabaseInstance;

    public static synchronized TodoDatabase getTodoDatabaseInstance(Context context){
        if(todoDatabaseInstance == null){
            todoDatabaseInstance = Room.databaseBuilder(context, TodoDatabase.class, "todo_database")
                    .build();
        }
        return todoDatabaseInstance;
    }
}
