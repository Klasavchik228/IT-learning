package com.example.itlearning;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import data.DataBaseHandler;
import model.PlayerBackground;

public class Settings_Activity extends AppCompatActivity {
    static SwitchCompat switchMusic;
    //MediaPlayer player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_settings);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        switchMusic = findViewById(R.id.switchMusic);
        SharedPreferences sharedPreferences=getSharedPreferences("save",MODE_PRIVATE);
        switchMusic.setChecked(sharedPreferences.getBoolean("valueSwitch",true));
        if(switchMusic.isChecked()){
            PlayerBackground.player.start();
            PlayerBackground.player.setLooping(true);
            SharedPreferences.Editor editor=getSharedPreferences("save",MODE_PRIVATE).edit();
            editor.putBoolean("valueSwitch",true);
            editor.apply();
            switchMusic.setChecked(true);
        }
        switchMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(switchMusic.isChecked()){
                    PlayerBackground.player.start();
                    PlayerBackground.player.setLooping(true);
                    SharedPreferences.Editor editor=getSharedPreferences("save",MODE_PRIVATE).edit();
                    editor.putBoolean("valueSwitch",true);
                    editor.apply();
                    switchMusic.setChecked(true);
                }else {
                    PlayerBackground.player.pause();
                    PlayerBackground.player.seekTo(0);
                    PlayerBackground.player.setLooping(false);

                    SharedPreferences.Editor editor=getSharedPreferences("save",MODE_PRIVATE).edit();
                    editor.putBoolean("valueSwitch",false);
                    editor.apply();
                    switchMusic.setChecked(false);

                }
            }
        });
    }

    public void OnClickBack(View v){
        finish();
    }
    public void onClickDelete(View v){
        DataBaseHandler db = new DataBaseHandler(this);
        db.deleteOllTerm();
        Toast.makeText(this,"Словарь очищен", Toast.LENGTH_SHORT).show();
    }
}