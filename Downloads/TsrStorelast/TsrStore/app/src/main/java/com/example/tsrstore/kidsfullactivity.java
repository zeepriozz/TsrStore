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

public class kidsfullactivity extends AppCompatActivity {

    // UI components
    private TextView pname, price, material, pattern, color, packag, displayInteger, tamount, returntime, details;
    private ImageView image;
    private Button wishl, buy, cart, btndec, btnincr;
    private RatingBar rating;
    private CardView xs, s, m, l, xl, xxl,six,seven,eight,nine;

    // Quantity and total calculations
    private int mininteger = 0;
    private String am, avb, idd;
    private final String urlBuy = config.baseurl + "buy.php";
    private final String urlWishlist = config.baseurl + "wishlist.php";
    private final String urlCart = config.baseurl + "cartproduct.php";

    // Product and user details
    private String uid, uname, uphone, id, pname1, price1, material1, pattern1, color1, packag1, image1, fid;
    private String selectedSize = ""; // Store the selected size

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kidsfullactivity);

        // Initialize views
        initializeViews();

        // Set up button click listeners
        setupClickListeners();

        // Get user details from SessionManagerUser
        HashMap<String, String> userDetails = new SessionManagerUser(kidsfullactivity.this).getUserDetails();
        uid = userDetails.get("id");
        uname = userDetails.get("username");
        uphone = userDetails.get("phone");

        // Get product details from the Intent
        getProductDetailsFromIntent();
    }

    private void initializeViews() {
        pname = findViewById(R.id.kidsname);
        cart = findViewById(R.id.kidcart);
        price = findViewById(R.id.kidsprice);
//        material = findViewById(R.id.materialb1);
        btnincr = findViewById(R.id.btnincbkid);
        btndec = findViewById(R.id.btndecbkid);
        details = findViewById(R.id.kidsdetails);
        displayInteger = findViewById(R.id.valuebkid);
        tamount = findViewById(R.id.amountbkid);
//        pattern = findViewById(R.id.patternb1);
//        color = findViewById(R.id.colorb1);
//        returntime = findViewById(R.id.returnb1);
//        packag = findViewById(R.id.packagb1);
        image = findViewById(R.id.kidsimg);
        buy = findViewById(R.id.kidbuy);
        wishl = findViewById(R.id.kidwish);
        xs = findViewById(R.id.zero);
        s = findViewById(R.id.one);
        m = findViewById(R.id.two);
        l = findViewById(R.id.three);
        xl = findViewById(R.id.four);
        xxl = findViewById(R.id.five);
        six = findViewById(R.id.sixx);
        seven = findViewById(R.id.sevenn);
        eight = findViewById(R.id.eightt);
        nine = findViewById(R.id.ninee);
    }

    private void setupClickListeners() {
        // Set click listeners for size selection
        setupSizeClickListener(xs, "0-1 year");
        setupSizeClickListener(s, "1-2 year");
        setupSizeClickListener(m, "2-3 year");
        setupSizeClickListener(l, "3-4 year");
        setupSizeClickListener(xl, "4-5 year");
        setupSizeClickListener(xxl, "5-6 year");
        setupSizeClickListener(six, "6-7 year");
        setupSizeClickListener(seven, "7-8 year");
        setupSizeClickListener(eight, "8-9 year");
        setupSizeClickListener(nine, "9-100 year");

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
                Toast.makeText(kidsfullactivity.this, "Please Select Quantity", Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(selectedSize)) {
                Toast.makeText(kidsfullactivity.this, "Please Select Size", Toast.LENGTH_SHORT).show();
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
        six.setCardBackgroundColor(Color.WHITE);
        seven.setCardBackgroundColor(Color.WHITE);
        eight.setCardBackgroundColor(Color.WHITE);
        nine.setCardBackgroundColor(Color.WHITE);
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
        image1 = intent.getStringExtra("image");
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
                    Toast.makeText(kidsfullactivity.this, "Buying product", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(kidsfullactivity.this, paymentmethod.class);
                    intent.putExtra("productid", id);
                    intent.putExtra("totalprice", am);
                    intent.putExtra("userid", uid);
                    intent.putExtra("username", uname);
                    startActivity(intent);
                } else {
                    Toast.makeText(kidsfullactivity.this, message, Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(kidsfullactivity.this, "Error parsing response", Toast.LENGTH_SHORT).show();
            }
        }, error -> {
            error.printStackTrace();
            Toast.makeText(kidsfullactivity.this, "Network Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(kidsfullactivity.this, "Added to wishlist", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(kidsfullactivity.this, message, Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
            Toast.makeText(kidsfullactivity.this, "Network Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(kidsfullactivity.this, "Product added to cart", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(kidsfullactivity.this, message, Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(kidsfullactivity.this, "Error adding to cart", Toast.LENGTH_SHORT).show();
            }
        }, error -> {
            Toast.makeText(kidsfullactivity.this, "Network Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
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

                return params;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }
}
