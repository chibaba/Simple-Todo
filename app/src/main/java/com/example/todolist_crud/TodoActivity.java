package com.example.todolist_crud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class TodoActivity extends AppCompatActivity {

    private RecyclerView myRecyclerView;
    private List<Model> dListOfModel;
    private TaskAdapter myAdapter;
    private FloatingActionButton btn_flt;
    private TextView empty_tv;
    private TextView textView;

    @Override
    protected void onResume() {
        super.onResume();
        setEmptyText();
        myAdapter.notifyDataSetChanged();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        defineViews();
        dListOfModel = new ArrayList<>();

    }
}