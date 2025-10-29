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

public class Sellerlogin extends AppCompatActivity {

    EditText e1, e2;
    Button b1;
    TextView t1, t2;
    String url = config.baseurl + "shoplogin.php";

    String se1, se2, status, message, id, phone, add, img, category;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sellerlogin);

        e1 = findViewById(R.id.loginname);
        e2 = findViewById(R.id.loginpass);
        b1 = findViewById(R.id.loginbutton);
        t1 = findViewById(R.id.loginregister);
        t2 = findViewById(R.id.loginforgot);
        final Animation buttonClickAnimation = AnimationUtils.loadAnimation(this, R.anim.button_click_animation);

        // Initialize SessionManager
        SessionManager sessionManager = new SessionManager(Sellerlogin.this);

        // Check if the user is already logged in
        if (sessionManager.isLoggedIn()) {
            // Redirect to Sellerhome activity
            Intent intent = new Intent(Sellerlogin.this, Sellerhome.class);
            startActivity(intent);
            finish();
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intn = new Intent(Sellerlogin.this, Forgotpass1.class);
                startActivity(intn);
            }
        });

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(Sellerlogin.this, Sellerregistration.class);
                startActivity(in);
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(buttonClickAnimation);
                login1();
            }
        });
    }

    private void login1() {
        se1 = e1.getText().toString();
        se2 = e2.getText().toString();

        if (TextUtils.isEmpty(se1)) {
            e1.requestFocus();
            e1.setError("enter shop name");
            return;
        }
        if (TextUtils.isEmpty(se2)) {
            e2.requestFocus();
            e2.setError("enter password");
            return;
        } else if (se2.length() < 6) {
            e2.requestFocus();
            e2.setError("Password must be at least 6 characters");
        }

        StringRequest StringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject c = new JSONObject(response);
                            status = c.getString("status");
                            message = c.getString("message");
                            id = c.getString("id");
                            se1 = c.getString("shopname");
                            add = c.getString("address");
                            phone = c.getString("shopphonenumber");
                            se2 = c.getString("password");
                            img = c.getString("logo");
                            checklogin();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Sellerlogin.this, String.valueOf(error), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("shopname", se1);
                params.put("password", se2);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(StringRequest);
    }

    private void checklogin() {
        if (status.equals("0")) {
            Toast.makeText(this, "Invalid", Toast.LENGTH_SHORT).show();
        } else {
            new SessionManager(Sellerlogin.this).createLoginSession(id, se1, category, add, phone, se2, img);
            Toast.makeText(this, "Login successfully", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(Sellerlogin.this, Sellerhome.class);
            startActivity(i);
            finish();
        }
    }
}
