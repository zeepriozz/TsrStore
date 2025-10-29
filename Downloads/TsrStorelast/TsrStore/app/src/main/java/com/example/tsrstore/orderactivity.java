package com.example.tsrstore;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.example.tsrstore.ui.notifications.NotificationsFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class orderactivity extends AppCompatActivity {


    EditText search;
    Button edit;
    String uid,cname;
    CardView wish;

    private String url = config.baseurl+"order.php";
    private ArrayList<ordermodell> dataModelArrayList;
    private orderadapter rvvAdapter;
    private RecyclerView recyclerView;
    private ProgressBar p;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderactivity);
        wish=findViewById(R.id.wishlist1);
        recyclerView = findViewById(R.id.cycle);
        p = findViewById(R.id.bar);
//        edit=findViewById(R.id.campbutton);




        //  edit=findViewById(R.id.prfbutton);
//
//
        search=findViewById(R.id.search);
        wish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent t=new Intent(orderactivity.this, NotificationsFragment.class);
                startActivity(t);
            }
        });
        search.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void afterTextChanged(Editable text) {
                //new array list that will hold the filtered data
                ArrayList<ordermodell> filteredSongs = new ArrayList<>();

                if (dataModelArrayList != null && !dataModelArrayList.isEmpty()) {
                    //looping through existing elements
                    for (ordermodell  s: dataModelArrayList) {
                        //if the existing elements contains the search input
                        if (s.getProductname().toLowerCase().contains(text.toString().toLowerCase())) {
                            //adding the element to filtered list
                            filteredSongs.add(s);
                        }
                    }
                }

                if (rvvAdapter != null) {
                    //calling a method of the adapter class and passing the filtered list
                    rvvAdapter.filterList(filteredSongs);
                }
            }
        });

        fetchingJSON();
    }




    private void fetchingJSON() {

        p.setVisibility(View.VISIBLE);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {



                        try {
                            p.setVisibility(View.GONE);

                            dataModelArrayList = new ArrayList<>();
                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {

                                JSONObject dataobj = array.getJSONObject(i);

                                dataModelArrayList.add(new ordermodell(
                                        dataobj.getString("id"),
                                        dataobj.getString("userid"),
                                        dataobj.getString("username"),
                                        dataobj.getString("usernumber"),
                                        dataobj.getString("productid"),
                                        dataobj.getString("productname"),
                                        dataobj.getString("productprice"),
                                        dataobj.getString("productimage"),
                                        dataobj.getString("shopid"),
                                        dataobj.getString("shopname"),
                                        dataobj.getString("totalquantity"),
                                        dataobj.getString("totalprice"),
                                        dataobj.getString("payment"),
                                        dataobj.getString("date")






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
                });



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


    private void setupRecycler(){
        rvvAdapter = new orderadapter(this,dataModelArrayList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(rvvAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
    }

}