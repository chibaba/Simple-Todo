package com.example.todolist_crud;

import android.content.Context;
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

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskHolder> {

    private List<Model> dTask;
    private Context context ;
    private TaskAdapterListener listener;

    public TaskAdapter(List<Model>dTask, Context context) {
        this.dTask = dTask;
        this.context = context;
    }

    @NonNull
    @Override
    public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.present_view, parent, false);
        return new TaskHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull  TaskHolder holder, int position) {
        final Model dBindTask = dTask.get(position);
        holder.specTitle.setText(dBindTask.getTitle());
        if(dBindTask.getDate() != null) {
            holder.dateStr.setText(dBindTask.getDate().toString());
        }
        if(holder.box.isChecked()) {
            dBindTask.setChecked(true);
        }

        holder.box.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener() {
            @Override
                    public  void onCheckedChanged(CompoundButton  compoundButton, boolean b) {

            }
        });

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
        TextView specTitle, dateStr;
        ImageView imageView;
        CheckBox box;
        public TaskHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.et_cardPv);
            specTitle = itemView.findViewById(R.id.et_realTitle);
            dateStr = itemView.findViewById(R.id.date_str);
            box = itemView.findViewById(R.id.et_checkBox);
            imageView = itemView.findViewById(R.id.imageView);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null) {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onClickdelete(position);
                        }
                    }
                }
            });
        }
    }

public interface  TaskAdapterListener{
        void onClickdelete(int position);
}
}
