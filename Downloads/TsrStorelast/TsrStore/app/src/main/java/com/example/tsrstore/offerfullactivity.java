package com.example.tsrstore;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class  offerfullactivity extends AppCompatActivity {
    ImageView image;
    TextView text;
    EditText edit;
    Button b;
    String sid,simg,stext,sedit,u1,status,message,url= config.baseurl+"offer.php";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_offerfullactivity);
        image = findViewById(R.id.offereditimg);
        text = findViewById(R.id.offerproductname);
        edit = findViewById(R.id.offereditprize);
        b = findViewById(R.id.offerbutton);

        Intent intent = getIntent();
        simg = intent.getStringExtra("image");
        Picasso.get().load(config.imgurl + simg).into(image);

        stext = intent.getStringExtra("productname");
        text.setText(stext);

        sedit = intent.getStringExtra("price");
        edit.setText(sedit);

        sid = intent.getStringExtra("id");

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                offer();
            }
        });
    }

            private void offer() {

                u1 = edit.getText().toString();

                if (TextUtils.isEmpty(u1)) {
                    edit.requestFocus();
                    edit.setError("required field");
                    return;
                }
                StringRequest str = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(offerfullactivity.this, response, Toast.LENGTH_SHORT).show();


                        try {
                            JSONObject json = new JSONObject(response);
                            status = json.getString("status");
                            message = json.getString("message");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if ("0".equals(status)) {
                            Toast.makeText(offerfullactivity.this, message, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(offerfullactivity.this, "updation successful", Toast.LENGTH_SHORT).show();
                            new notify(offerfullactivity.this).showNotification(offerfullactivity.this,stext,"New offer");
                            startActivity(new Intent(offerfullactivity.this, offeractivity.class));
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(offerfullactivity.this, error.toString(), Toast.LENGTH_SHORT).show();

                    }
                }) {

                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<>();
                        params.put("id", sid);
                        params.put("price", u1);



                        return params;
                    }
                };

                RequestQueue rq = Volley.newRequestQueue(offerfullactivity.this);
                rq.add(str);
            }

        }