package com.example.todolist_crud;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {
    private TodoLab todoLab;
    private LiveData<List<Model>> allTodos;

    public TaskViewModel(@NonNull Application application) {
        super(application);
        todoLab = TodoLab.getsTodoLab(application);
        allTodos = todoLab.getAllTodos();
    }


    public LiveData<List<Model>> getLiveTasks() {
        return allTodos;
    }

    public void deleteTodo(Model todo){
        todoLab.removeTodos(todo);
    }

    public void addTodo(Model task){
        // i Stopped here;
        todoLab.addTodo(task);
    }


}