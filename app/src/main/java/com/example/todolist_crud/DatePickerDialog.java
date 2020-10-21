package com.example.todolist_crud;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class DatePickerDialog extends DialogFragment {
    private View view;
    private DatePicker sDatePicker;
    private DateKeysListener dateKeysListener;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            dateKeysListener = (DateKeysListener)context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "implement Dialog");
        }
    }

    @Override
    public Dialog onCreateDialog (Bundle savedInstanceState) {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.date_dialog_picker, null);
        sDatePicker = view.findViewById(R.id.et_date_picker);
        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                   int year = sDatePicker.getYear();
                   int month = sDatePicker.getMonth();
                   int day = sDatePicker.getDayOfMonth();
                   dateKeysListener.dateListener(year, month, day);
            }
        })
        .create();
    }

      public interface DateKeysListener {
        void dateListener(int year, int month, int day );
      }

}
