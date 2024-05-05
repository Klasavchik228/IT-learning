package com.example.itlearning;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import data.DataBaseHandler;
import model.TermData;

public class Term_activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_term);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        TextView term, description;
        term = findViewById(R.id.termList);
        description = findViewById(R.id.descriptionList);
        Bundle arguments = getIntent().getExtras();
        DataBaseHandler db = new DataBaseHandler(this);
        int id = arguments.getInt("id");
        TermData termData = db.getTerm(id);
        term.setText(termData.getTerm());
        description.setText(termData.getDescription());
        db.close();

    }
    public void onClickBack(View v){
        finish();
        //Intent intent = new Intent(this,DictionaryActivity.class);
        //startActivity(intent);
    }
    public void onClickSave(View v){
        Bundle arguments = getIntent().getExtras();
        TextView term,description;
        term = findViewById(R.id.termList);
        description = findViewById(R.id.descriptionList);
        DataBaseHandler db = new DataBaseHandler(this);
        TermData termData = db.getTerm(arguments.getInt("id"));
        termData.setTerm(term.getText().toString());
        termData.setDescription(description.getText().toString());
        db.updateTerm(termData);
        Toast.makeText(this,"Сохранено", Toast.LENGTH_SHORT).show();
        db.close();
    }
    public void onClickDelete(View v){
        Bundle arguments = getIntent().getExtras();
        TextView term;
        term = findViewById(R.id.termList);
        DataBaseHandler db = new DataBaseHandler(this);
        db.deleteTerm(arguments.getInt("id"));
        db.close();
        finish();

    }
}