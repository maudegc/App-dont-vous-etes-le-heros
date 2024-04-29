package com.example.tp2.ui;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tp2.R;
import com.example.tp2.présentation.modèle.Modèle;

public class MainActivity extends AppCompatActivity {
    TextView test;

    @Override
    protected void onDestroy() {
        Modèle.getModèle().updateAventure();

        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}