package com.example.tsrstore.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tsrstore.orderactivity;
import com.example.tsrstore.wishlistadapter;
import com.example.tsrstore.wishlistmodel;
import com.example.tsrstore.R;
import com.example.tsrstore.config;
//import com.example.supere_mart.databinding.FragmentHomeBinding;
import com.example.tsrstore.databinding.FragmentNotificationsBinding;
//import com.example.supere_mart.registration;
//import com.example.supere_mart.student_adapter;
//import com.example.supere_mart.student_model;
//import com.example.tsrstore.viewdata;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NotificationsFragment extends Fragment {

    EditText search;

    CardView wishlist,order;

    private String url = config.baseurl+"wishlist1.php";
    private ArrayList<wishlistmodel> dataModelArrayList;
    private wishlistadapter rvAdapter;
    private RecyclerView recyclerView;
    private ProgressBar p;


    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        order=root.findViewById(R.id.order);
        recyclerView = root.findViewById(R.id.cycleg1);
        p = root.findViewById(R.id.barg1);
//        search=root.findViewById(R.id.search);

//        upload = root.findViewById(R.id.uploaddoc);
//        view1=root.findViewById(R.id.viewdoc);

//        upload.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(getActivity(), registration.class);
//                startActivity(intent);
//            }
//        });
//        view1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(getActivity(), viewdata.class);
//                startActivity(intent);
//            }
//        });


//        search.addTextChangedListener(new TextWatcher() {
//            @Override public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
//            @Override public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
//            @Override
//            public void afterTextChanged(Editable text) {
//                //new array list that will hold the filtered data
//                ArrayList<Companymodel> filteredSongs = new ArrayList<>();
//
//                if (dataModelArrayList != null && !dataModelArrayList.isEmpty()) {
//                    //looping through existing elements
//                    for (Companymodel  s: dataModelArrayList) {
//                        //if the existing elements contains the search input
//                        if (s.getEligibility().toLowerCase().contains(text.toString().toLowerCase())) {
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
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), orderactivity.class);
                startActivity(intent);
            }
        });
        fetchingJSON();

        return root;
    }

    private void fetchingJSON() {

        p.setVisibility(View.VISIBLE);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(getActivity(), response, Toast.LENGTH_SHORT).show();

                        try {
                            p.setVisibility(View.GONE);

                            dataModelArrayList = new ArrayList<>();
                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {

                                JSONObject dataobj = array.getJSONObject(i);

                                dataModelArrayList.add(new wishlistmodel(
                                        dataobj.getString("id"),
                                        dataobj.getString("userid"),
                                        dataobj.getString("username"),
                                        dataobj.getString("usernumber"),
                                        dataobj.getString("shopname"),
                                        dataobj.getString("shopid"),
                                        dataobj.getString("productname"),
                                        dataobj.getString("price"),
                                        dataobj.getString("image"),
                                        dataobj.getString("productid"),
                                        dataobj.getString("package"),
                                        dataobj.getString("description")

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
                        Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_SHORT).show();
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
                Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);


    }

    private void setupRecycler() {
        rvAdapter = new wishlistadapter(getActivity(), dataModelArrayList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(rvAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}