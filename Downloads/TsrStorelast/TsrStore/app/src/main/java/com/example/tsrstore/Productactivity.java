package com.example.tsrstore;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Productactivity extends AppCompatActivity {
    EditText search;
    CardView card,card1;
    Button s2;
    private String url = config.baseurl + "productlist.php", url1 = config.baseurl + "homeproduct.php";
    private ArrayList<ProductModel> dataModelArrayList;
    private ProductAdapter rvAdapter;
    private RecyclerView recyclerView;
    private ProgressBar p;
    String type, type1;

    String chair_n, chair_pr;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productactivity);
        recyclerView = findViewById(R.id.cycleg);
        p = findViewById(R.id.barg);

        Intent in = getIntent();
        type = in.getStringExtra("availableproducts");
        type1 = in.getStringExtra("category");

        fetchingJSON();
    }

    private void fetchingJSON() {
        p.setVisibility(View.VISIBLE);

        // Determine which URL to use (based on type or type1)
        String targetUrl = (type != null && !type.isEmpty()) ? url : url1;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, targetUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            p.setVisibility(View.GONE);

                            dataModelArrayList = new ArrayList<>();
                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject dataobj = array.getJSONObject(i);

                                dataModelArrayList.add(new ProductModel(
                                        dataobj.getString("id"),
                                        dataobj.getString("shopid"),
                                        dataobj.getString("shopname"),
                                        dataobj.getString("shopnumber"),
                                        dataobj.getString("shopcategory"),
                                        dataobj.getString("availableproducts"),
                                        dataobj.getString("productname"),
                                        dataobj.getString("price"),
                                        dataobj.getString("package"),
                                        dataobj.getString("location"),
                                        dataobj.getString("description"),
                                        dataobj.getString("image")
                                ));
                            }

                            setupRecycler();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        p.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                // Send different parameters depending on 'type' and 'type1'
                if (type != null && !type.isEmpty()) {
                    map.put("availableproducts", type);
                } else if (type1 != null && !type1.isEmpty()) {
                    map.put("category", type1);
                }
                return map;
            }
        };

        stringRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 20000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 20000;
            }

            @Override
            public void retry(VolleyError error) {
                p.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void setupRecycler() {
        rvAdapter = new ProductAdapter(this, dataModelArrayList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(rvAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
    }
}
