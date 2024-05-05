package com.example.itlearning;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import data.DataBaseHandler;
import model.TermData;

public class DictionaryActivity extends AppCompatActivity {
    HashMap<Integer,Integer> map = new HashMap<Integer, Integer>();
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dictionary);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        listView = findViewById(R.id.listviwe1);
        ArrayList<String> termList = new ArrayList<>();
        try (DataBaseHandler baseHandler = new DataBaseHandler(this)) {
            List<TermData> termData = new ArrayList<>(baseHandler.getOllTerm());
            int i = 0;
            for(TermData obj: termData){
                map.put(i,obj.getId());
                termList.add(obj.getTerm());
                i++;
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_desing,R.id.head,termList);
            listView.setAdapter(adapter);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(DictionaryActivity.this,Term_activity.class);
                intent.putExtra("id",map.get(position));
                startActivity(intent);

            }
        });
    }
    protected void onResume() {
        super.onResume();
        listView = findViewById(R.id.listviwe1);
        ArrayList<String> termList = new ArrayList<>();
        try (DataBaseHandler baseHandler = new DataBaseHandler(this)) {
            List<TermData> termData = new ArrayList<>(baseHandler.getOllTerm());
            int i=0;
            map.clear();
            for(TermData obj: termData){
                map.put(i,obj.getId());
                termList.add(obj.getTerm());
                i++;
            }
            ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, R.layout.list_desing,R.id.head,termList);
            listView.setAdapter(adapter1);
        }
    }

    public void OnClickBack(View v){
        finish();

    }

}