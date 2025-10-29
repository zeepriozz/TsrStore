package com.example.tsrstore;

import static android.provider.SyncStateContract.Helpers.update;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class Editproduct extends AppCompatActivity {

    EditText e1,e2,e3;
    String s1,s2,s3,sid;
    Button b1;
    String u1,u2,u3,sname,url= config.baseurl+"editproduct.php",status,message;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_editproduct);
        e1=findViewById(R.id.ename);
        e2=findViewById(R.id.eprice);
         e3=findViewById(R.id.edis);
         b1=findViewById(R.id.ebutton);

        Intent intent = getIntent();
        s1 = intent.getStringExtra("productname");
        e1.setText( s1);

        s2 = intent.getStringExtra("price");
        e2.setText( s2);

        s3 = intent.getStringExtra("description");
        e3.setText(s3);

        sid = intent.getStringExtra("id");

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                updateproduct();
            }
        });


    }

            private void updateproduct() {

                u1 = e1.getText().toString();
                u2 = e2.getText().toString();
                u3 = e3.getText().toString();


                if (TextUtils.isEmpty(u1)) {
                    e1.requestFocus();
                    e1.setError("required field");
                    return;
                }


                if (TextUtils.isEmpty(u2)) {
                    e2.requestFocus();
                    e2.setError("required field");
                    return;
                }

                if (TextUtils.isEmpty(u3)) {
                    e3.requestFocus();
                    e3.setError("required field");
                    return;
                }
                StringRequest str = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(Editproduct.this, response, Toast.LENGTH_SHORT).show();


                        try {
                            JSONObject json = new JSONObject(response);
                            status = json.getString("status");
                            message = json.getString("message");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if ("0".equals(status)) {
                            Toast.makeText(Editproduct.this, message, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Editproduct.this, "updation successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Editproduct.this, Sellerhome.class));
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(Editproduct.this, error.toString(), Toast.LENGTH_SHORT).show();

                    }
                }) {

                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<>();
                        params.put("id", sid);
                        params.put("productname", u1);
                        params.put("price", u2);
                        params.put("description", u3);


                        return params;
                    }
                };

                RequestQueue rq = Volley.newRequestQueue(Editproduct.this);
                rq.add(str);
            }

        }
