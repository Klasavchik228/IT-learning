package com.example.itlearning;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import model.PlayerBackground;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        PlayerBackground.player = MediaPlayer.create(this,R.raw.lofi);
        SharedPreferences sharedPreferences=getSharedPreferences("save",MODE_PRIVATE);

        if(sharedPreferences.getBoolean("valueSwitch",true)) {
            PlayerBackground.player.start();
            PlayerBackground.player.setLooping(true);
        }
    }
    public void OnClickDictionary(View v){
        Intent intent = new Intent(this,DictionaryActivity.class);
        startActivity(intent);
    }
    public void OnClickAdd(View v){
        Intent intent = new Intent(this,AddActivity.class);
        startActivity(intent);
    }
    public void OnClickLearning(View v){
        Intent intent = new Intent(this,LearningActivty.class);
        startActivity(intent);
    }
    public void OnClickSettings(View v){
        Intent intent = new Intent(this,Settings_Activity.class);
        startActivity(intent);
    }
    public void OnClickImage(View v){
        Toast.makeText(this,"Мяяяуу", Toast.LENGTH_LONG).show();
    }

}