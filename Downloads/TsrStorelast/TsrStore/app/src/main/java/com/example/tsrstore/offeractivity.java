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

public class offeractivity extends AppCompatActivity {
    EditText search;
    CardView card,card1;
    Button s2;
    private String url = config.baseurl+"listproduct.php";
    private ArrayList<offermodel> dataModelArrayList;
    private offeradapter rvAdapter;
    private RecyclerView recyclerView;
    private ProgressBar p;
    String sno;

    String chair_n,chair_pr;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offeractivity);
        recyclerView = findViewById(R.id.cycleg);
        p = findViewById(R.id.barg);
        HashMap<String, String> data = new SessionManager(this).getUserDetails();


        sno = data.get("shopphonenumber");


//        Intent in=getIntent();
//        type=in.getStringExtra("availableproducts");
        // s2=findViewById(R.id.s2);

//        Intent in=getIntent();
//        chair_n=in.getStringExtra("Chairman_Name");
//        chair_pr=in.getStringExtra("Chairman_Party");
//        Toast.makeText(this, "Voting Succesful for Chairperson", Toast.LENGTH_SHORT).show();
        // search=findViewById(R.id.search);
//        search.addTextChangedListener(new TextWatcher() {
//            @Override public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
//            @Override public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
//            @Override
//            public void afterTextChanged(Editable text) {
//                //new array list that will hold the filtered data
//                ArrayList<productmodel> filteredSongs = new ArrayList<>();
//
//                if (dataModelArrayList != null && !dataModelArrayList.isEmpty()) {
//                    //looping through existing elements
//                    for (productmodel  s: dataModelArrayList) {
//                        //if the existing elements contains the search input
//                        if (s.getCategory().toLowerCase().contains(text.toString().toLowerCase())) {
//                            //adding the element to filtered list
//                            filteredSongs.add(s);
//                        }
//                    }
//                }
//
//                if (rvAdapter != null) {
//                    //calling a method of the adapter class and passing the filtered list
//                    rvAdapter.filterList(filteredSongs);
//                }
//            }
//        });
//

//        s2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(GSActivity.this,Magazine.class);
//
//                intent.putExtra("c_n",chair_n);
//                intent.putExtra("c_p",chair_pr);
//                startActivity(intent);
//            }
//        });
//        card.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(Productactivity.this,Productfullactivity.class);
//                startActivity(intent);
//            }
//        });


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

                                dataModelArrayList.add(new offermodel(
                                        dataobj.getString("id"),
                                        dataobj.getString("shopid"),
                                        dataobj.getString("shopname"),
                                        dataobj.getString("shopnumber"),
                                        dataobj.getString("shopcategory"),
                                        dataobj.getString("availableproducts"),
                                        dataobj.getString("productname"),
                                        dataobj.getString("price"),
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
                }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>map=new HashMap<>();

                map.put("shopnumber",sno);
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


    private void setupRecycler(){
        rvAdapter = new offeradapter(this, dataModelArrayList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(rvAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
    }

}




