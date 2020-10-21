package com.example.todolist_crud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
        dListOfModel = TodoLab.get().getTodos();
        myAdapter = new TaskAdapter(dListOfModel, this);

        myRecyclerView.setAdapter(myAdapter);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        Log.d("TaskListActivity",""+myAdapter.getItemCount());
        btn_flt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnother();
            }
        });
        removalListerner();

    }
}