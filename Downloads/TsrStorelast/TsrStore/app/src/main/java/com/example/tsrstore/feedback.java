package com.example.tsrstore;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class feedback extends AppCompatActivity {

    private RatingBar ratingBar;
    private Button button;
    private EditText feedback;
    private TextView p, w;
    private String rating;

    private String feed, pa, wa, username, status, error;
    private final String URL = config.baseurl + "feedback.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        ratingBar = findViewById(R.id.app_rating_bar);
        feedback = findViewById(R.id.feed1);
        p = findViewById(R.id.pf);
        w = findViewById(R.id.wno1);
        button = findViewById(R.id.btn_rate_app);

        // Retrieve user details using SessionManagerUser
        SessionManagerUser sessionManager = new SessionManagerUser(this);
        pa = sessionManager.getUserDetails().get("id");
        username = sessionManager.getUserDetails().get("username");
        wa = sessionManager.getUserDetails().get("phone");

        p.setText(username);
        w.setText(wa);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitFeedback();
            }
        });
    }

    private void submitFeedback() {
        // Retrieve the feedback text and validate input
        feed = feedback.getText().toString();
        if (TextUtils.isEmpty(feed)) {
            feedback.requestFocus();
            feedback.setError("Required field");
            return;
        }

        // Get rating value
        float ratingValue = ratingBar.getRating();
        rating = Float.toString(ratingValue);

        // Create the request to submit feedback
        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    // Parse response to JSON object
                    JSONObject jsonObject = new JSONObject(response);
                    status = jsonObject.getString("status");
                    error = jsonObject.optString("error", "Unknown error");

                    // Handle the response
                    if (status.equals("1")) {
                        Toast.makeText(feedback.this, "Thank you for the feedback", Toast.LENGTH_SHORT).show();
                        // Redirect to Home
                        startActivity(new Intent(feedback.this, Home.class));
                        finish();
                    } else {
                        Toast.makeText(feedback.this, "Failed! Error: " + error, Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(feedback.this, "Error parsing response", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(feedback.this, "Request failed: " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // Create the map of parameters to be sent with the request
                Map<String, String> map = new HashMap<>();
                map.put("rating", rating);
                map.put("name", username);
                map.put("phoneno", wa);
                map.put("feedback", feed);
                return map;
            }
        };

        // Add the request to the Volley queue
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }
}
