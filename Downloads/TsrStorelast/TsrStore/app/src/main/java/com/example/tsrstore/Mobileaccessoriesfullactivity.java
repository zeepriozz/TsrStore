package com.example.tsrstore;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Mobileaccessoriesfullactivity extends AppCompatActivity {
    // UI components
    TextView pname, price, details, displayInteger, tamount;
    ImageView image;
    Button wishl, buy, cart;
    Button btndec, btnincr;

    // Quantity and total calculations
    int mininteger = 0;
    String am,packag1,shopname,avb,material1,add;
    String urlBuy = config.baseurl + "buy.php";
    String urlWishlist = config.baseurl + "wishlist.php";
    String urlCart = config.baseurl + "cartproduct.php";

    // Product and user details
    String uid, uname, uphone, id, pname1, price1, image1, fid;
    String selectedSize = ""; // Store the selected size

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobileaccessoriesfullactivity);

        // Initialize views
        initializeViews();

        // Set up button click listeners
        setupClickListeners();

        // Get user details from SessionManagerUser
        HashMap<String, String> userDetails = new SessionManagerUser(Mobileaccessoriesfullactivity.this).getUserDetails();
        uid = userDetails.get("id");
        uname = userDetails.get("username");
        uphone = userDetails.get("phone");
        add = userDetails.get("Address");
        // Get product details from the Intent
        getProductDetailsFromIntent();
    }

    private void initializeViews() {
        pname = findViewById(R.id.nameb1);
        cart = findViewById(R.id.cart5b1);
        price = findViewById(R.id.priceb1);
        details = findViewById(R.id.details1);
        displayInteger = findViewById(R.id.valueb1);
        tamount = findViewById(R.id.amountb1);
        image = findViewById(R.id.image1b1);
        buy = findViewById(R.id.buyb1);
        wishl = findViewById(R.id.wwish);
        btnincr = findViewById(R.id.btnincb1);
        btndec = findViewById(R.id.btndecb1);
    }

    private void setupClickListeners() {
        cart.setOnClickListener(view -> addtocart());

        btnincr.setOnClickListener(v -> {
            mininteger++;
            displayQuantity(mininteger);
        });

        btndec.setOnClickListener(v -> {
            if (mininteger > 0) {
                mininteger--;
                displayQuantity(mininteger);
            }
        });

        buy.setOnClickListener(v -> {
            if (mininteger == 0) {
                Toast.makeText(Mobileaccessoriesfullactivity.this, "Please Select Quantity", Toast.LENGTH_SHORT).show();
            } else {
                processPurchase();
            }
        });

        wishl.setOnClickListener(v -> {
            wishl.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFC0CB"))); // Pink color
            addToWishlist();
            wishl.postDelayed(() -> wishl.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE)), 2000); // Reset color after 2 seconds
        });
    }

    private void getProductDetailsFromIntent() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        pname1 = intent.getStringExtra("productname");
        pname.setText(pname1);
        price1 = intent.getStringExtra("price");
        price.setText(price1);
        packag1 = intent.getStringExtra("package");
        material1 = intent.getStringExtra("description");
        details.setText(material1);
        image1 = intent.getStringExtra("image");
        Picasso.get().load(config.imgurl + image1).into(image);
        fid = intent.getStringExtra("shopid");
        shopname = intent.getStringExtra("shopname");
        avb = intent.getStringExtra("availableproducts");
    }

    private void processPurchase() {
        int availableStock = Integer.parseInt(packag1);
        if (mininteger > availableStock) {
            Toast.makeText(this, "Given Stock is Unavailable", Toast.LENGTH_SHORT).show();
            return;
        }

        StringRequest request = new StringRequest(Request.Method.POST, urlBuy, response -> {
            try {
                JSONObject jsonObject = new JSONObject(response);
                String status = jsonObject.getString("status");
                String message = jsonObject.getString("message");




                if ("1".equals(status)) {
                    Toast.makeText(Mobileaccessoriesfullactivity.this, "Buying product", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Mobileaccessoriesfullactivity.this, paymentmethod.class);
                    intent.putExtra("productid", id);
                    intent.putExtra("totalprice", am);
                    intent.putExtra("userid", uid);
                    intent.putExtra("username", uname);
                    startActivity(intent);
                } else {
                    Toast.makeText(Mobileaccessoriesfullactivity.this, message, Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(Mobileaccessoriesfullactivity.this, "Error parsing response", Toast.LENGTH_SHORT).show();
            }
        }, error -> {
            error.printStackTrace();
            Toast.makeText(Mobileaccessoriesfullactivity.this, "Network Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("userid", uid);
                params.put("username", uname);
                params.put("usernumber", uphone);
                params.put("productid", id);
                params.put("productname", pname1);
                params.put("productprice", price1);
                params.put("productimage", image1);
                params.put("shopid", fid);
                params.put("shopname", shopname);
                params.put("size", selectedSize);
                params.put("totalquantity", String.valueOf(mininteger));
                params.put("totalprice", am);
                params.put("Address", add);


                return params;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }

    private void addToWishlist() {
        StringRequest request = new StringRequest(Request.Method.POST, urlWishlist, response -> {
            try {
                JSONObject jsonObject = new JSONObject(response);
                String status = jsonObject.getString("status");
                String message = jsonObject.getString("message");

                if ("1".equals(status)) {
                    Toast.makeText(Mobileaccessoriesfullactivity.this, "Added to Wishlist", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Mobileaccessoriesfullactivity.this, message, Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(Mobileaccessoriesfullactivity.this, "Error parsing response", Toast.LENGTH_SHORT).show();
            }
        }, error -> {
            error.printStackTrace();
            Toast.makeText(Mobileaccessoriesfullactivity.this, "Network Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("userid", uid);
                params.put("username", uname);
                params.put("usernumber", uphone);
                params.put("productid", id);
                params.put("productname", pname1);
                params.put("price", price1);
                params.put("package", packag1);
                params.put("description", material1);
                params.put("image", image1);
                params.put("shopid", fid);
                params.put("shopname", shopname);
                return params;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }

    private void addtocart() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlCart, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    String message = jsonObject.getString("message");

                    if ("1".equals(status)) {
                        Toast.makeText(Mobileaccessoriesfullactivity.this, "Added to Cart", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Mobileaccessoriesfullactivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(Mobileaccessoriesfullactivity.this, "Error parsing response", Toast.LENGTH_SHORT).show();
                }
            }
        }, error -> {
            error.printStackTrace();
            Toast.makeText(Mobileaccessoriesfullactivity.this, "Network Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("userid", uid);
                params.put("username", uname);
                params.put("usernumber", uphone);
                params.put("productid", id);
                params.put("productcategory", avb);
                params.put("productname", pname1);
                params.put("productprice", price1);
                params.put("productimage", image1);
                params.put("shopid", fid);
                params.put("shopname", shopname);
                params.put("totalquantity", String.valueOf(mininteger));
                params.put("totalprice", am);
                params.put("Address", add);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void displayQuantity(int quantity) {
        displayInteger.setText(String.valueOf(quantity));
        am = String.valueOf(quantity * Integer.parseInt(price1));
        tamount.setText(am);
    }
}
