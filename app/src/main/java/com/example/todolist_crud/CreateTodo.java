package com.example.todolist_crud;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputEditText;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class    CreateTodo extends AppCompatActivity implements DatePickerDialog.DateKeysListener, View.OnClickListener {
    private static  final String DIALOG_DATE = "DialogDate";
    private static final String DIALOG_TIME = "DialogTime";

    private TextInputEditText dTitleField;
    private Button setTime;
    private Button btnAdd;
    private Button btn_setDate;
    private Model dtask = new Model();
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private ImageView imageView;
    private Uri photoURI;
    private static final String TAG = "CreateTodo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_todos);
        dTitleField = findViewById(R.id.et_title);
        setTime = findViewById(R.id.et_timesette);
        btnAdd = findViewById(R.id.et_addBtn);
         btn_setDate = findViewById(R.id.btn_date);


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
        if(photoURI != null) {
            dtask.setImage(photoURI.toString());
        }
        dtask.setTitle(dTitleField.getText().toString());
        new TaskViewModel(getApplication()).addTodo(dtask);
        finish();

    }

    private void responder() {
        btn_setDate.setOnClickListener(this);
        setTime.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
    }
     @Override
    public void dateListener (int year, int month, int day) {

         SimpleDateFormat formattedDate = new SimpleDateFormat("dd-MMM-yyyy");
         Date date = new GregorianCalendar(year, month, day).getTime();
         String dateFormatted = formattedDate.format(date);
         dtask.setDate(dateFormatted);
     }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.et_timesette  :
                dispatchTakePictureIntent();
                break;
            case R.id.btn_date:
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

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Log.d(TAG,"started 1");
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            Log.d(TAG,"started 2");
            try {
                photoFile = createImageFile();
                Log.d(TAG,"started 3");
            } catch (IOException ex) {
            }
            if(photoFile != null){
                photoURI = FileProvider.getUriForFile(this, "com.mystic.todolistapp.fileprovider", photoFile);
                Log.d(TAG,"started 4");
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                Log.d(TAG,"started 5");
                Log.d("Create",""+photoURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                Log.d(TAG,"started 6");
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Glide.with(getApplicationContext()).load(photoURI).into(imageView);
        }

    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        String currentPhotoPath = image.getAbsolutePath();
        return image;
    }
}