package com.example.tsrstore.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tsrstore.CartAdapter;
import com.example.tsrstore.CartModel;
import com.example.tsrstore.R;
import com.example.tsrstore.SessionManagerUser;
import com.example.tsrstore.config;
import com.example.tsrstore.paymentmethod;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CartFragment extends Fragment {

    private String urlFetchCart = config.baseurl + "cart1.php";
    private String urlPlaceOrder = config.baseurl + "cartorder.php";  // PHP script for placing the order
    private ArrayList<CartModel> cartModelArrayList;
    private CartAdapter rvAdapter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private TextView total;
    private Button place;
    private String userid, username;

    public CartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_cart, container, false);

        // Initialize views
        recyclerView = root.findViewById(R.id.cyclegcart);
        progressBar = root.findViewById(R.id.bargcart);
        total = root.findViewById(R.id.totalLabel);
        place = root.findViewById(R.id.place);

        // Retrieve user details from session
        HashMap<String, String> map = new SessionManagerUser(getActivity()).getUserDetails();
        userid = map.get("id");
        username = map.get("username");

        // Set click listener on place order button
        place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                placeOrder();  // Method to place the order
            }
        });

        // Fetch cart data
        fetchingCartData();

        return root;
    }

    // Method to place the order
    private void placeOrder() {
        if (cartModelArrayList != null && !cartModelArrayList.isEmpty()) {
            for (CartModel item : cartModelArrayList) {
                int itemTotalPrice = Integer.parseInt(item.getPrice()) * Integer.parseInt(item.getTotalquantity());
                placeOrderForItem(item, itemTotalPrice);
            }
        } else {
            Toast.makeText(getActivity(), "Your cart is empty!", Toast.LENGTH_SHORT).show();
        }
    }

    private void placeOrderForItem(CartModel item, int itemTotalPrice) {
        StringRequest request = new StringRequest(Request.Method.POST, urlPlaceOrder, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    String message = jsonObject.getString("message");

                    if (status.equals("1")) {
                        Toast.makeText(getActivity(), "Order placed for: " + item.getProductname(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                    }

                    if (item.equals(cartModelArrayList.get(cartModelArrayList.size() - 1))) {
                        Intent intent = new Intent(getActivity(), paymentmethod.class);
                        intent.putExtra("username", username);
                        intent.putExtra("userid", userid);
                        intent.putExtra("totalprice", total.getText().toString().replace("Total Amount: ₹", "").trim());
                        startActivity(intent);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Error parsing response", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Network Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("userid", userid);
                params.put("username", item.getUsername());
                params.put("usernumber", item.getUsernumber());
                params.put("productid", item.getProductid());
                params.put("productname", item.getProductname());
                params.put("productprice", String.valueOf(itemTotalPrice));
                params.put("productimage", item.getImage());
                params.put("shopid", item.getShopid());
                params.put("shopname", item.getShopname());
                params.put("size", item.getSize());
                params.put("totalquantity", item.getTotalquantity());
                params.put("totalprice", String.valueOf(itemTotalPrice));
                params.put("Address", item.getAddress());


                return params;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.add(request);
    }

    // Method to fetch cart data from the server
    private void fetchingCartData() {
        progressBar.setVisibility(View.VISIBLE);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlFetchCart,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressBar.setVisibility(View.GONE);
                        cartModelArrayList = new ArrayList<>();

                        try {
                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject dataobj = array.getJSONObject(i);

                                cartModelArrayList.add(new CartModel(
                                        dataobj.optString("id", ""),
                                        dataobj.optString("userid", ""),
                                        dataobj.optString("username", ""),
                                        dataobj.optString("usernumber", ""),
                                        dataobj.optString("shopname", ""),
                                        dataobj.optString("shopid", ""),
                                        dataobj.optString("productcategory", "General"),
                                        dataobj.optString("productname", ""),
                                        dataobj.optString("productprice", "0"),
                                        dataobj.optString("productimage", ""),
                                        dataobj.optString("productid", ""),
                                        dataobj.optString("size", ""),
                                        dataobj.optString("totalquantity", "1"),
                                        dataobj.optString("totalprice", "0"),
                                        dataobj.optString("Address", "")


                                        ));
                            }

                            if (!cartModelArrayList.isEmpty()) {
                                setupRecycler();
                                updateTotalAmount();
                            } else {
                                Toast.makeText(getActivity(), "Your cart is empty!", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(), "Error parsing cart data", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getActivity(), "Error: " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("userid", userid);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    // Set up RecyclerView
    private void setupRecycler() {
        rvAdapter = new CartAdapter(getActivity(), cartModelArrayList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(rvAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
    }

    // Update the total amount for the items in the cart
    private void updateTotalAmount() {
        int totalAmount = 0;
        for (CartModel item : cartModelArrayList) {
            totalAmount += Integer.parseInt(item.getPrice()) * Integer.parseInt(item.getTotalquantity());
        }
        total.setText("Total Amount: ₹" + totalAmount);
    }
}
