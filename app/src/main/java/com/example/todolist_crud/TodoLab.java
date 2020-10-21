package com.example.todolist_crud;

import java.util.ArrayList;
import java.util.List;

public class TodoLab {
    private static  TodoLab dTodoLab;
    List<Model> sListTodo;

    private  TodoLab() {
        sListTodo = new ArrayList<>();
    }
    public static TodoLab get() {
        if (dTodoLab == null) {
            dTodoLab = new TodoLab();
        }
        return  dTodoLab;
    }
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
