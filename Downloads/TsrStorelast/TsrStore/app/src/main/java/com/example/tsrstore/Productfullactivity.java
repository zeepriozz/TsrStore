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
import android.widget.RatingBar;
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

public class Productfullactivity extends AppCompatActivity {

    // UI components
    private TextView pname, price, material, pattern, color, packag, displayInteger, tamount, returntime, details;
    private ImageView image;
    private Button wishl, buy, cart, btndec, btnincr;
    private RatingBar rating;
    private CardView xs, s, m, l, xl, xxl;

    // Quantity and total calculations
    private int mininteger = 0;
    private String am, add, idd;
    private final String urlBuy = config.baseurl + "buy.php";
    private final String urlWishlist = config.baseurl + "wishlist.php";
    private final String urlCart = config.baseurl + "cartproduct.php";

    // Product and user details
    private String uid, uname, uphone, avb,id, pname1, price1, material1, pattern1, color1, packag1, image1, fid;
    private String selectedSize = ""; // Store the selected size

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productfullactivity);

        // Initialize views
        initializeViews();

        // Set up button click listeners
        setupClickListeners();

        // Get user details from SessionManagerUser
        HashMap<String, String> userDetails = new SessionManagerUser(Productfullactivity.this).getUserDetails();
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
//        material = findViewById(R.id.materialb1);
        btnincr = findViewById(R.id.btnincb1);
        btndec = findViewById(R.id.btndecb1);
        details = findViewById(R.id.details);
        displayInteger = findViewById(R.id.valueb1);
        tamount = findViewById(R.id.amountb1);
//        pattern = findViewById(R.id.patternb1);
//        color = findViewById(R.id.colorb1);
//        returntime = findViewById(R.id.returnb1);
//        packag = findViewById(R.id.packagb1);
        image = findViewById(R.id.image1b1);
        buy = findViewById(R.id.buyb1);
        wishl = findViewById(R.id.wwish);
        xs = findViewById(R.id.xs);
        s = findViewById(R.id.s);
        m = findViewById(R.id.m);
        l = findViewById(R.id.l);
        xl = findViewById(R.id.xl);
        xxl = findViewById(R.id.xxl);
    }

    private void setupClickListeners() {
        // Set click listeners for size selection
        setupSizeClickListener(xs, "XS");
        setupSizeClickListener(s, "S");
        setupSizeClickListener(m, "M");
        setupSizeClickListener(l, "L");
        setupSizeClickListener(xl, "XL");
        setupSizeClickListener(xxl, "XXL");

        // Add to cart button
        cart.setOnClickListener(view -> addtocart());

        // Increment quantity
        btnincr.setOnClickListener(v -> {
            mininteger++;
            displayQuantity(mininteger);
        });

        // Decrement quantity
        btndec.setOnClickListener(v -> {
            if (mininteger > 0) {
                mininteger--;
                displayQuantity(mininteger);
            }
        });

        // Buy button
        buy.setOnClickListener(v -> {
            if (mininteger == 0) {
                Toast.makeText(Productfullactivity.this, "Please Select Quantity", Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(selectedSize)) {
                Toast.makeText(Productfullactivity.this, "Please Select Size", Toast.LENGTH_SHORT).show();
            } else {
                processPurchase();
            }
        });

        // Wishlist button
        wishl.setOnClickListener(v -> {
            wishl.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFC0CB"))); // Pink color
            addToWishlist();
            wishl.postDelayed(() -> wishl.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE)), 2000); // Reset color after 2 seconds
        });
    }

    private void setupSizeClickListener(CardView cardView, String size) {
        cardView.setOnClickListener(v -> {
            // Reset all CardView colors
            resetCardViewColors();

            // Set selected CardView color
            cardView.setCardBackgroundColor(Color.parseColor("#CA4585"));
            selectedSize = size;
        });
    }

    private void resetCardViewColors() {
        xs.setCardBackgroundColor(Color.WHITE);
        s.setCardBackgroundColor(Color.WHITE);
        m.setCardBackgroundColor(Color.WHITE);
        l.setCardBackgroundColor(Color.WHITE);
        xl.setCardBackgroundColor(Color.WHITE);
        xxl.setCardBackgroundColor(Color.WHITE);
    }

    private void getProductDetailsFromIntent() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        pname1 = intent.getStringExtra("productname");
        pname.setText(pname1);
        price1 = intent.getStringExtra("price");
        price.setText("Prize :" + price1);
        packag1 = intent.getStringExtra("package");
        material1 = intent.getStringExtra("description");
        details.setText(material1);
        pattern1 = intent.getStringExtra("shopcategory");
        image1 = intent.getStringExtra("image");
        Picasso.get().load(config.imgurl + image1).into(image);
        fid = intent.getStringExtra("shopid");
        color1 = intent.getStringExtra("shopname");
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

                if (status.equals("1")) {
                    Toast.makeText(Productfullactivity.this, "Buying product", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Productfullactivity.this, paymentmethod.class);
                    intent.putExtra("productid", id);
                    intent.putExtra("totalprice", am);
                    intent.putExtra("userid", uid);
                    intent.putExtra("username", uname);
                    startActivity(intent);
                } else {
                    Toast.makeText(Productfullactivity.this, message, Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(Productfullactivity.this, "Error parsing response", Toast.LENGTH_SHORT).show();
            }
        }, error -> {
            error.printStackTrace();
            Toast.makeText(Productfullactivity.this, "Network Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
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
                params.put("shopname", color1);
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

                if (status.equals("1")) {
                    Toast.makeText(Productfullactivity.this, "Added to wishlist", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Productfullactivity.this, message, Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
            Toast.makeText(Productfullactivity.this, "Network Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
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
                params.put("shopname", color1);
                return params;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }

    private void displayQuantity(int number) {
        displayInteger.setText(String.valueOf(number));
        calculateTotalPrice();
    }

    private void calculateTotalPrice() {
        int pricePerItem = Integer.parseInt(price1);
        int totalAmount = mininteger * pricePerItem;
        am = String.valueOf(totalAmount);
        tamount.setText("₹" + am);
    }

    private void addtocart() {
        StringRequest request = new StringRequest(Request.Method.POST, urlCart, response -> {
            try {
                JSONObject jsonObject = new JSONObject(response);
                String status = jsonObject.getString("status");
                String message = jsonObject.getString("message");

                if (status.equals("1")) {
                    Toast.makeText(Productfullactivity.this, "Product added to cart", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Productfullactivity.this, message, Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(Productfullactivity.this, "Error adding to cart", Toast.LENGTH_SHORT).show();
            }
        }, error -> {
            Toast.makeText(Productfullactivity.this, "Network Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
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
                params.put("shopname", color1);
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
}
