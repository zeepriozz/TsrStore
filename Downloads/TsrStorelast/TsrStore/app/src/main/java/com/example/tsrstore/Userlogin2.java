package com.example.tsrstore;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Userlogin2 extends AppCompatActivity {
    EditText e;
    Button b;
    TextView t;
    String s, status, message, url = config.baseurl + "userlogin.php", name, phone, email, password, id;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize session manager and check login status
        SessionManagerUser sessionManager = new SessionManagerUser(this);
        if (sessionManager.isLoggedIn()) {
            // If already logged in, go to Home screen directly
            Intent intent = new Intent(Userlogin2.this, Home.class);
            startActivity(intent);
            finish();
            return;
        }

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_userlogin2);
        e = findViewById(R.id.logotp);
        b = findViewById(R.id.loginb);
        t = findViewById(R.id.lregi);
        final Animation buttonClickAnimation = AnimationUtils.loadAnimation(this, R.anim.button_click_animation);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(Userlogin2.this, Userlogin.class);
                startActivity(in);
            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(buttonClickAnimation);
                login();
            }
        });
    }

    private void login() {
        s = e.getText().toString();

        if (TextUtils.isEmpty(s)) {
            e.requestFocus();
            e.setError("enter otp");
            return;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject c = new JSONObject(response);
                            status = c.getString("status");
                            message = c.getString("message");
                            id = c.getString("id");
                            name = c.getString("username");
                            email = c.getString("email");
                            phone = c.getString("phone");
                            password = c.getString("Address");
                            checklogin();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Userlogin2.this, String.valueOf(error), Toast.LENGTH_SHORT).show();
                    }
                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("otp", s);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void checklogin() {
        if (status.equals("0")) {
            Toast.makeText(this, "Invalid", Toast.LENGTH_SHORT).show();
        } else {
            new SessionManagerUser(Userlogin2.this).createLoginSession(id, name, email, phone, password);
            Toast.makeText(this, "Login successfully", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(Userlogin2.this, Home.class);
            startActivity(i);
            finish();
        }
    }
}
