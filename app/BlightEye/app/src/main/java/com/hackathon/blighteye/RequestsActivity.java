package com.hackathon.blighteye;

import android.app.DownloadManager;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class RequestsActivity extends AppCompatActivity {

    private Button sendRequestButton;
    private EditText editText1;
    private EditText editText2;
    private RequestQueue rQ;
    private TextView textView;
    String url = "https://cool-phalanx-266913.appspot.com/calc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests);

        rQ = Volley.newRequestQueue(this);
        textView = findViewById(R.id.RequestsText);
        editText1 = findViewById(R.id.Edit1);
        editText2 = findViewById(R.id.Edit2);
        sendRequestButton = findViewById(R.id.sendRequestButton);

        // Add listener for request function Button
        sendRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendCalcJSONRequest();
            }
        });
    }

    void sendStringRequest() {
        // Get values from editTexts
        String e1 = editText1.getText().toString();
        String e2 = editText2.getText().toString();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(RequestsActivity.this, jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(RequestsActivity.this, "e" + e.toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RequestsActivity.this, "err"+error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );

        rQ.add(stringRequest);
    }

    public boolean sendCalcJSONRequest() {
        String e1Str = editText1.getText().toString();
        Integer value = Integer.parseInt(e1Str);

        JSONObject jsonRequestObject;

        try {
            jsonRequestObject = new JSONObject()
                    .put("value", value);
        } catch (JSONException e){
            e.printStackTrace();
            return false;
        }

        Log.i("Sending a req", "req");

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                jsonRequestObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Integer resInt = response.getInt("data");
                            Toast.makeText(getApplicationContext(), resInt.toString(), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Errored", error.toString());
                    }
                });

        rQ.add(jsonObjectRequest);

        return true;
    }
}
