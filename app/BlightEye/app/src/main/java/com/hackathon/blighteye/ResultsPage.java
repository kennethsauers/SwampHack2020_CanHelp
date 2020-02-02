package com.hackathon.blighteye;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ResultsPage extends AppCompatActivity {

    private Button understoodBbutton;
    private TextView resultsTexView;
    SomeData passedData;
    String disease;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        understoodBbutton = findViewById(R.id.understood_button);
        resultsTexView = findViewById(R.id.resultsView);

        passedData = (SomeData) getIntent().getSerializableExtra("SomeData");
        disease = passedData.getConditionName();

        resultsTexView.setText(disease);
    }
}
