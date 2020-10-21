package com.example.todolist_crud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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

        removalListener();

    }

    private void removalListener() {
        myAdapter.setListenerForAdapter(new TaskAdapter.TaskAdapterListener() {
            @Override
            public void onClickdelete(int position) {
                TodoLab.get().deleteTask(position);
                myAdapter.notifyItemRemoved(position);
                setEmptyText();
            }
        });
    }


    private void startAnother() {
        Intent intent = new Intent(TodoActivity.this, CreateTodo.class);
        startActivity(intent);
    }

    private void defineViews() {
        btn_flt = findViewById(R.id.et_FAB);
        myRecyclerView = findViewById(R.id.et_cv);
        empty_tv = findViewById(R.id.empty_tv);
        textView = findViewById(R.id.et_tv);
    }
    private void setEmptyText() {
        if (myAdapter.getItemCount() > 0) {
            Log.d("TodActivity", "" + myAdapter.getItemCount());
            empty_tv.setVisibility(View.GONE);
        } else {
            empty_tv.setVisibility(View.VISIBLE);
        }

    }
}