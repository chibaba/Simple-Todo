package com.example.todolist_crud;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Entity;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private List<Model> dTask = new ArrayList<>();
    private Context context ;
    private TaskAdapterListener listener;

    public TaskAdapter(Context context) {
        this.context = context;
    }

    @Override
     public int getItemViewType(int index) {
        if(dTask.get(index).getImage() == null) {
            return 0;
        }
        return 1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(viewType == 0) {
            // when image is absent
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.noimage, parent, false);
            return new TaskHolder(view);
        }

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.present_view, parent, false);
        return new TaskHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull  RecyclerView.ViewHolder holder, int position) {
        final Model dBindTask = dTask.get(position);

        // with no image
        if(dBindTask.getImage() == null) {
            TaskHolderNoImage taskHolder = (TaskHolderNoImage) holder;
            taskHolder.spectitle.setText(dBindTask.getTitle());
            if(dBindTask.getDate() != null){
                taskHolder.dateStr.setText(dBindTask.getDate());

        }
            taskHolder.box.setChecked((dBindTask.getDone()== 1));
            taskHolder.box.setOnCheckedChangeListener((compoundButton, b) -> {
                if(dBindTask.getDone() == 0){
                    dBindTask.setDone(1);
                    taskHolder.box.setChecked(true);
                } else{
                    dBindTask.setDone(0);
                    taskHolder.box.setChecked(false);
                }
                taskHolder.box.isChecked();
                TodoLab.getsTodoLab(context).updateTask(dBindTask);
            });

        } else {
            //With image
            TaskHolder taskHolder = (TaskHolder) holder;
            taskHolder.spectitle.setText(dBindTask.getTitle());
            if(dBindTask.getDate() != null){
                taskHolder.dateStr.setText(dBindTask.getDate());
            }

            taskHolder.box.setChecked((dBindTask.getDone()== 1));
            taskHolder.box.setOnCheckedChangeListener((compoundButton, b) -> {
                if(dBindTask.getDone() == 0){
                    dBindTask.setDone(1);
                } else{
                    dBindTask.setDone(0);
                }
                TodoLab.getsTodoLab(context).updateModel(dBindTask);
            });


            Glide.with(context)
                    .asBitmap()
                    .circleCrop()
                    .load(Uri.parse(dBindTask.getImage()))
                    .into(taskHolder.imag);

        }

    }



    @Override
    public int getItemCount() {
        return dTask.size();
    }


    public void setListenerForAdapter(TaskAdapterListener listerner){
        this.listener =listerner;
    }

    public class TaskHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView imag;
        TextView specTitle, dateStr;
        ImageView imageView;
        CheckBox box;

        public TaskHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.et_cardPv);
            specTitle = itemView.findViewById(R.id.et_realTitle);
            dateStr = itemView.findViewById(R.id.date_str);
            box = itemView.findViewById(R.id.et_checkBox);
            imag = itemview.findViewById(R.id.taskimaage);
            imageView = itemView.findViewById(R.id.imageView);

            imageView.setOnClickListener(view -> {
                    if(listener != null) {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onClickdelete(position);
                        }
                    }
                 });
        }
    }

    public class TaskHolderNoImage extends RecyclerView.ViewHolder{
        CardView cardView ;
        TextView spectitle ,dateStr;
        ImageView imageView;
        CheckBox box;
        public TaskHolderNoImage(@NonNull View itemview) {
            super(itemview);
            cardView = itemview.findViewById(R.id.cardw);
            spectitle = itemview.findViewById(R.id.realtitlew);
            dateStr = itemview.findViewById(R.id.date_strw);
            box = itemview.findViewById(R.id.checkBoxw);
            imageView = itemview.findViewById(R.id.imageVieww);

            imageView.setOnClickListener(view -> {
                if(listener != null){
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        listener.onClickdelete(position);
                    }
                }
            });

        }
    }

public interface  TaskAdapterListener{
        void onClickdelete(int position);
}
}
