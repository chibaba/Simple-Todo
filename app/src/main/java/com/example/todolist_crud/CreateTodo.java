package com.example.todolist_crud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class CreateTodo extends AppCompatActivity implements DatePickerDialog.DateKeysListener, View.OnClickListener {
    private static  final String DIALOG_DATE = "DialogDate";
    private static final String DIALOG_TIME = "DialogTime";

    private TextInputEditText dTitleField;
    private Button setTime;
    private Button btnAdd;
    private Button btn_setDate;
    private Task dtask = new Task();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        dTitleField = findViewById(R.id.et_title);
        setTime = findViewById(R.id.et_time);
        btnAdd = findViewById(R.id.et_addBtn);
         btn_setDate = findViewById(R.id.et_date);


        dTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                dtask.setTitle(s.toString());

            }
    
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        responder();
    }
    public void addTodo() {
        if (dTitleField.getText().toString().equals(""))  {
            return;
        }
        dtask.setTitle(dTitleField.getText().toString());
        TodoLab.get().addTodo(dtask);
        finish();

    }

    private void responder() {
        btn_setDate.setOnClickListener(this);
        setTime.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
    }
     @Override
    public void dateListerner(int year, int month, int day) {
         SimpleDateFormat formattedDate = new SimpleDateFormat("DD-MM-YYYY");
         Date date = new GregorianCalendar(year, month, day).getTime();
         String dateFormatted = formattedDate.format(date);
         dtask.setDate(dateFormatted);
     }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.et_dateStr:
                TimePickerDialog dialog = new TimePickerDialog();
                showPopUp(dialog,DIALOG_TIME);
                break;
            case R.id.et_date:
                DatePickerDialog dialog_date = new DatePickerDialog();
                showPopUp(dialog_date,DIALOG_DATE);
                break;
            case R.id.et_addBtn:
                addTodo();
                break;
            default:
                return ;
        }
    }
    private void showPopUp(DialogFragment frag, String str) {
        frag.show(getSupportFragmentManager(),str);
    }
}