package com.example.tsrstore.ui.dashboard;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tsrstore.R;
import com.example.tsrstore.config;
import com.example.tsrstore.searchadapter;
import com.example.tsrstore.searchmodel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DashboardFragment extends Fragment {

    private EditText searchField;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private String url = config.baseurl + "searchproduct.php";
    private ArrayList<searchmodel> productList;
    private searchadapter searchAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        initializeViews(root);
        setupSearchListener();
        fetchProductData();
        return root;
    }

    private void initializeViews(View root) {
        recyclerView = root.findViewById(R.id.searchcycle);
        progressBar = root.findViewById(R.id.searchbar);
        searchField = root.findViewById(R.id.search1);
    }

    private void setupSearchListener() {
        searchField.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable text) {
                filterProducts(text.toString());
            }
        });
    }

    private void filterProducts(String query) {
        ArrayList<searchmodel> filteredProducts = new ArrayList<>();

        if (productList != null && !productList.isEmpty()) {
            for (searchmodel product : productList) {
                if (product.getProductname().toLowerCase().contains(query.toLowerCase())) {
                    filteredProducts.add(product);
                }
            }
        }

        if (searchAdapter != null) {
            searchAdapter.filterList(filteredProducts);
        }
    }

    private void fetchProductData() {
        progressBar.setVisibility(View.VISIBLE);

        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressBar.setVisibility(View.GONE);
                        parseProductData(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                String searchQuery = searchField.getText().toString().trim();
                params.put("productname", searchQuery);
                return params;
            }
        };

        setupRetryPolicy(request);
        RequestQueue requestQueue = Volley.newRequestQueue(requireActivity());
        requestQueue.add(request);
    }

    private void parseProductData(String response) {
        productList = new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONArray(response);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject productObj = jsonArray.getJSONObject(i);

                productList.add(new searchmodel(
                        productObj.getString("id"),
                        productObj.getString("shopid"),
                        productObj.getString("shopname"),
                        productObj.getString("shopnumber"),
                        productObj.getString("shopcategory"),
                        productObj.getString("category"),
                        productObj.getString("availableproducts"),
                        productObj.getString("productname"),
                        productObj.getString("price"),
                        productObj.getString("package"),
                        productObj.getString("location"),
                        productObj.getString("description"),
                        productObj.getString("image")
                ));
            }

            setupRecyclerView();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void setupRecyclerView() {
        searchAdapter = new searchadapter(getActivity(), productList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(searchAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
    }

    private void setupRetryPolicy(StringRequest request) {
        request.setRetryPolicy(new RetryPolicy() {
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
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
