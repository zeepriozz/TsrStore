package com.example.tsrstore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class paymentmethod extends AppCompatActivity {
    TextView total;
    Spinner p;
    Button Submit;
    String spin, date, orderid;
    String url = config.baseurl + "paymethod.php";
    //    String url1 = config.baseurl + "paymethod1.php"; // PHP script for placing orders from the cart
    String stotal, u_id, u_name;
    boolean isFromCart;

    String[] paymentMethods = {"Cash on delivery", "Internet banking"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymentmethod);

        total = findViewById(R.id.location00);
        p = findViewById(R.id.spinner);
        Submit = findViewById(R.id.submit);

        HashMap<String, String> map = new SessionManagerOrder(paymentmethod.this).getUserDetails();
        orderid = map.get("id");

        // Get data from Intent
        Intent intent = getIntent();
        stotal = intent.getStringExtra("totalprice");
        u_id = intent.getStringExtra("userid");
        u_name = intent.getStringExtra("username");
        isFromCart = intent.getBooleanExtra("isFromCart", false); // Get the flag indicating if the order is from the cart

        total.setText("Total Amount: " + stotal);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, paymentMethods);
        p.setAdapter(adapter);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFromCart) {
                    placeCartOrder(); // Process order from the cart
                } else {
                    pay(); // Regular order processing
                }
            }
        });
    }

    // Method for processing payment for regular orders
    private void pay() {
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        date = df.format(c);
        spin = p.getSelectedItem().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Toast.makeText(paymentmethod.this, response, Toast.LENGTH_SHORT).show();
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    String message = jsonObject.getString("message");

                    if ("1".equals(status)) {
                        if ("Cash on delivery".equals(spin)) {
                            Toast.makeText(paymentmethod.this, "Buying success", Toast.LENGTH_SHORT).show();
                        } else if ("Internet banking".equals(spin)) {
                            Toast.makeText(paymentmethod.this, "Buying success", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(paymentmethod.this, GpayActivity.class);
                            intent.putExtra("totalprice", stotal);
                            intent.putExtra("username", u_name);
                            startActivity(intent);
                        }
                    } else {
                        Toast.makeText(paymentmethod.this, "Payment failed", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle error
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("totalprice", stotal);
                params.put("payment", spin);
                params.put("username", u_name);
                params.put("userid", u_id);
                params.put("date", date);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    // Method for processing payment when the order is from the cart
    private void placeCartOrder() {
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        date = df.format(c);
        spin = p.getSelectedItem().toString();

        // Assuming you have a way to retrieve the cart items as an ArrayList<CartModel>
        ArrayList<CartModel> cartItems = getCartItems();

        for (CartModel item : cartItems) {
            placeOrderForCartItem(item);
        }
    }

    // Method to place order for each item in the cart
    private void placeOrderForCartItem(CartModel item) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    String message = jsonObject.getString("message");

                    if ("1".equals(status)) {
                        // Order placed successfully
                        Toast.makeText(paymentmethod.this, "Order placed for item: " + item.getProductname(), Toast.LENGTH_SHORT).show();
                    } else {
                        // Order placement failed
                        Toast.makeText(paymentmethod.this, message, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(paymentmethod.this, "Error parsing response", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(paymentmethod.this, "Network Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("totalprice", item.getTotalprice());
                params.put("payment", spin); // Store the payment method
                params.put("username", u_name);
                params.put("userid", u_id);
                params.put("date", date);
//                params.put("productid", item.getProductid()); // Include item-specific info if needed
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    // Method to retrieve the cart items (placeholder)
    private ArrayList<CartModel> getCartItems() {
        // Replace this with the actual method to get the list of cart items
        return new ArrayList<>(); // Return an empty list for now
    }
}
