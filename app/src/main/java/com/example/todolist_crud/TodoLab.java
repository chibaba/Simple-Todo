package com.example.todolist_crud;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class TodoLab {
    private List<Model> sListTodo;
    private LiveData<List<Model>> todos;
    private TodoDao todoDao;
    private static TodoLab dTodoLab;

    private TodoLab(Context context) {
        TodoDatabase database = TodoDatabase.getTodoDatabaseInstance(context.getApplicationContext());
        todoDao = database.todoDao();

        todos = todoDao.getAllTodos();
        sListTodo = new ArrayList<>();
    }

    public static TodoLab getsTodoLab(Context context) {
        if (dTodoLab == null) {
            dTodoLab = new TodoLab(context);
        }
        return dTodoLab;
    }

    public LiveData<List<Model>> getAllTodos() {
        return todos;
    }

    public void addTodo(Model todo) {
        insertTodos(todo);
    }

    private void insertTodos(Model todo) {
        addTodos(todo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Model>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onSuccess(@NonNull List<Model> todos) {
                        sListTodo = todos;
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });

    }

    public Single<List<Model>> addTodos(final Model todo){
        return Single.fromCallable(() -> {
            todoDao.insert(todo);
            return sListTodo;
        });

    }

    public void removeTodos(Model todo) {
        deleteTodo(todo);
    }

    private void deleteTodo(Model todo) {
        deleteTodos(todo).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Model>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onSuccess(@NonNull List<Model> todos) {
                        sListTodo = todos;
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }


    private Single<List<Model>> deleteTodos(final Model todo) {
        return Single.fromCallable(() -> {
            todoDao.delete(todo);
            return sListTodo;
        });

    }


    public void updateTask(Model task) {
        updateTaskInBackground(task)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Void>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull Void aVoid) {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {


                    }
                });

    }

    public Single<Void> updateTaskInBackground(Model todo) {
        return Single.fromCallable(() -> {
            todoDao.update(todo);
            Log.d("TaskLab", "" + todo.getDone());
            return null;
        });
    }

}
