package com.example.tsrstore;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
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
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Userlogin extends AppCompatActivity {
    EditText name, phone, email, Address;
    Button submit;
    TextView login;
    String name1, phone1, email1, password1, status, message, otpnew;

    String url = config.baseurl + "userregister.php";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_userlogin);
        name = findViewById(R.id.Userregistername2);
        phone = findViewById(R.id.Userregisterphone2);
        email = findViewById(R.id.Userregisteremail2);
        Address = findViewById(R.id.Userregisterpassword2);
        submit = findViewById(R.id.Userregitersubmit2);
        login = findViewById(R.id.rlog);
        final Animation buttonClickAnimation = AnimationUtils.loadAnimation(this, R.anim.button_click_animation);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(Userlogin.this,Userlogin2.class);
                startActivity(in);
            }
        });

        submit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                v.startAnimation(buttonClickAnimation);
                submit2();
            }
        });
    }

    private void submit2() {
        name1 = name.getText().toString();
        phone1 = phone.getText().toString();
        email1 = email.getText().toString();
        password1 = Address.getText().toString();

        if (TextUtils.isEmpty(name1)) {
            name.requestFocus();
            name.setError("*required");
            return;
        }
        if (TextUtils.isEmpty(phone1)) {
            phone.requestFocus();
            phone.setError("*required");
            return;
        } else if (phone1.length() != 10) {
            phone.requestFocus();
            phone.setError("Phone number must be 10 digits");
            return;
        }
        if (TextUtils.isEmpty(email1)) {
            email.requestFocus();
            email.setError("*required");
            return;
        }
        if (TextUtils.isEmpty(password1)) {
            Address.requestFocus();
            Address.setError("Enter your Address");
            return;
        }

        Random r = new Random();
        int otp = r.nextInt((9999 - 1000) + 1) + 1000;
        otpnew = "IC" + otp;

        StringRequest string = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject json = new JSONObject(response);
                    status = json.getString("status");
                    message = json.getString("message");

                    if (status.equals("1")) {
                        SmsManager sms = SmsManager.getDefault();
                        Intent in = new Intent();
                        PendingIntent pi = PendingIntent.getActivity(Userlogin.this, 0, in, PendingIntent.FLAG_IMMUTABLE);
                        sms.sendTextMessage(phone1, null, "Your Verification has been submitted successfully. Your security code is given please remember this for login in future " + otpnew, pi, null);
                        Toast.makeText(Userlogin.this, "OTP sent via SMS successfully", Toast.LENGTH_SHORT).show();
                        Intent object = new Intent(Userlogin.this, Userlogin2.class);
                        startActivity(object);
                    } else {
                        Toast.makeText(Userlogin.this, message, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Userlogin.this, String.valueOf(error), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("username", name1);
                map.put("email", email1);
                map.put("phone", phone1);
                map.put("Address", password1);
                map.put("otp", otpnew);

                return map;
            }
        };
        RequestQueue req = Volley.newRequestQueue(Userlogin.this);
        req.add(string);
    }
}


