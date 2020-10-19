package com.example.todolist_crud;

import java.util.ArrayList;
import java.util.List;

public class TodoLab<Todo> {
    private static  TodoLab dTodoLab;
    List<Todo> sListTodo;

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
    public void addTodo (Todo todo) {
        sListTodo.add(todo);
    }
    public List<Todo>getTodos() {
        return sListTodo;
    }
}
