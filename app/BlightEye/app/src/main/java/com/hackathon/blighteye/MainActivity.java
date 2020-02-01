package com.hackathon.blighteye;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button toCameraButton;
    private Button toLibraryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toCameraButton = findViewById(R.id.GoToCamera);
        toLibraryButton = findViewById(R.id.GoToLibrary);

        toCameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                // fixme
            }
        });

        toLibraryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                // fixme
            }
        });
    }
}