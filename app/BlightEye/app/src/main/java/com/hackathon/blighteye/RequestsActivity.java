package com.hackathon.blighteye;

import android.app.DownloadManager;
import android.graphics.BitmapFactory;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class RequestsActivity extends AppCompatActivity {

    private Button sendRequestButton;
    private EditText editText1;
    private RequestQueue rQ;
    private TextView textView;
    private ImageView imageView;

    String url = "https://cool-phalanx-266913.appspot.com/calc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests);

        rQ = Volley.newRequestQueue(this);
        textView = findViewById(R.id.RequestsText);
        editText1 = findViewById(R.id.Edit1);
        imageView = findViewById(R.id.imageView);
        sendRequestButton = findViewById(R.id.sendRequestButton);

        // Add listener for request function Button
        sendRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    sendCalcJSONRequest();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        try {
            byte[] bytes = fullyReadFileToBytes();
            imageView.setImageBitmap(BitmapFactory.decodeByteArray(bytes, 0, bytes.length));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean sendCalcJSONRequest() throws JSONException {
        String e1Str = editText1.getText().toString();

        JSONObject jsonObject;
        jsonObject = new JSONObject();

        try {
            byte[] bytes = fullyReadFileToBytes();
            jsonObject.put("value", new String(bytes));

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        Log.i("^^^^^^^Preparing to send the request now ^^^^^^^^^^^^^^", "Packing jsonObject into JsonRequest");

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("^^^^^^^^^^^GOT A VALID RESPONSE^^^^^^^^^^", " PRINTING IT");
                        Log.i("RESPONSE AS A STRING: ", response.toString());
                        Toast.makeText(getApplicationContext(), "Got an OK", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("^^^^^^^^^^ Errored on tryna get response", error.toString());
                    }
                });

        rQ.add(jsonObjectRequest);
        return true;
    }

    byte[] fullyReadFileToBytes() throws IOException {
        File f = new File(getFilesDir() + "/pic.jpg");
        int size = (int) f.length();
        byte bytes[] = new byte[size];
        byte tmpBuff[] = new byte[size];
        FileInputStream fis= new FileInputStream(f);
        try {

            int read = fis.read(bytes, 0, size);
            if (read < size) {
                int remain = size - read;
                while (remain > 0) {
                    read = fis.read(tmpBuff, 0, remain);
                    System.arraycopy(tmpBuff, 0, bytes, size - remain, read);
                    remain -= read;
                }
            }
        }  catch (IOException e){
            throw e;
        } finally {
            fis.close();
        }

        return bytes;
    }
}
