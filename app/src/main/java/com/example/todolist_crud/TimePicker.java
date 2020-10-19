package com.example.todolist_crud;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class TimePicker  extends DialogFragment {

    private View view;
    @Override
    public Dialog onCreateDialog(Bundle savedInstance) {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.time_picker, null);
        return new AlertDialog.Builder(getActivity())
                .setTitle("please set the time")
                .setView(view)
                .setPositiveButton(android.R.string.ok, null)
                .create();
    }
}
