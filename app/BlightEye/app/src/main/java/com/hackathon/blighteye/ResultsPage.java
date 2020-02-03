package com.hackathon.blighteye;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class ResultsPage extends AppCompatActivity {

    private Button understoodBbutton;
    private Button linkB;
    private TextView resultsTexView;
    SomeData passedData;
    String disease;
    String link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        understoodBbutton = findViewById(R.id.understood_button);
        linkB = findViewById(R.id.linkB);
        resultsTexView = findViewById(R.id.resultsView);

        passedData = (SomeData) getIntent().getSerializableExtra("SomeData");
        disease = passedData.getConditionName();

        resultsTexView.setText(disease);

        understoodBbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        linkB.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(link));
                startActivity(intent);
            }
        });

        HashMap<String, String> diseasesList = new HashMap<String, String>();
        diseasesList.put("Bowen's disease", "https://www.webmd.com/cancer/what-is-bowens-disease");
        diseasesList.put("basal cell carcinoma", "https://www.webmd.com/melanoma-skin-cancer/basal-cell-carcinoma");
        diseasesList.put("benign keratosis-like", "https://www.mayoclinic.org/diseases-conditions/seborrheic-keratosis/symptoms-causes/syc-20353878");
        diseasesList.put("dermatofibroma", "https://www.webmd.com/g00/skin-problems-and-treatments/picture-of-dermatofibroma?i10c.ua=1&i10c.encReferrer=aHR0cHM6Ly93d3cuZ29vZ2xlLmNvbS8%3d&i10c.dv=24");
        diseasesList.put("melanoma", "https://www.mayoclinic.org/diseases-conditions/melanoma/symptoms-causes/syc-20374884");
        diseasesList.put("melanocytic nevi ", "https://www.mayoclinic.org/diseases-conditions/moles/symptoms-causes/syc-20375200");
        diseasesList.put("vascular lesions vasc.","https://www.mayoclinic.org/departments-centers/vascular-anomalies-clinic/overview/ovc-20421863");

        link = diseasesList.get(disease);
    }
}
