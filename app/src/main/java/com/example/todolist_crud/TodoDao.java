package com.example.todolist_crud;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TodoDao {
    @Query("SELECT * FROM Task_table ORDER BY date ASC")
    LiveData<List<Model>> getAllTasks();

    @Insert
    void insert(Model model);

    @Update
    void update(Model model);

    @Delete
    void delete(Model model);
}
