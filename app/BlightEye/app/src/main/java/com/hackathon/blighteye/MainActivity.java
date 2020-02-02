package com.hackathon.blighteye;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
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
                goToTakeAPicture(v);
            }
        });

        toLibraryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                goToRequestActivity(v);
            }
        });
    }

    public void goToRequestActivity(View view) {
        Intent intent = new Intent(this, RequestsActivity.class);
        startActivity(intent);
    }

    public void goToTakeAPicture(View view){
        Intent intent = new Intent(this, TakeAPicture.class);
        int reqCode = 0;
        startActivityForResult(intent, reqCode);
    }
}
