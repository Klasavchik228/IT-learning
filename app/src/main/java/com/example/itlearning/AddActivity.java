package com.example.itlearning;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import data.DataBaseHandler;
import model.TermData;

public class AddActivity extends AppCompatActivity {
    EditText termField, answerField;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        termField = findViewById(R.id.term);
        answerField = findViewById(R.id.answer);
    }
    public void OnClickBack(View v){
        finish();
        //Intent intent = new Intent(this,MainActivity.class);
        //startActivity(intent);
    }
    public  void onClickSave(View v){

        String term = termField.getText().toString();
        String answer = answerField.getText().toString();
        try (DataBaseHandler baseHandler = new DataBaseHandler(this)) {
            baseHandler.addTerm(new TermData(term,answer));
            termField.setText("");
            answerField.setText("");
            Toast.makeText(this, "сохранено", Toast.LENGTH_SHORT).show();
        }
    }

}