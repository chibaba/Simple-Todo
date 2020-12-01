package com.example.todolist_crud;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

public class TodoLab {
    private List<Model> sListTodo;
    private LiveData<List<Model>> models;
    private TodoDao todoDao;
    private static  TodoLab dTodoLab;

    private  TodoLab(Context context) {
        TodoDatabase database = TodoDatabase.getTodoDatabaseInstance(context.getApplicationContext());
        todoDao = database.todoDao();

        todos = todoDao.getAllTasks();
        sListTodo = new ArrayList<>();
    }
    public static TodoLab getTaskLab(Context context) {
        if (dTodoLab == null) {
            dTodoLab = new TodoLab(context);
        }
        return  dTodoLab;
    }

    public LiveData<List<Model>>

    public void deleteTask(int index) {
        sListTodo.remove(index);
    }
    public void addTodo (Model todo) {
        sListTodo.add(todo);
    }
    public List<Model>getTodos() {
        return sListTodo;
    }
}
