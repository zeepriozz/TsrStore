package com.example.tsrstore;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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

public class listfullactivity extends AppCompatActivity {
    CardView c1;
    ImageView img1, img2, img3;
    TextView t1, t2, t3;
    String sname, simg, sprice, sdis, sid, url = config.baseurl + "delete.php", status, message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_listfullactivity);

        // Initialize UI components
        img1 = findViewById(R.id.fimg);
        img2 = findViewById(R.id.fdelete);
        img3 = findViewById(R.id.fedit);
        t1 = findViewById(R.id.fname);
        t2 = findViewById(R.id.fprice);
        t3 = findViewById(R.id.fdis);
        c1 = findViewById(R.id.fcard);

        // Get data from Intent
        Intent intent = getIntent();
        simg = intent.getStringExtra("image");
        Picasso.get().load(config.imgurl + simg).into(img1);

        sname = intent.getStringExtra("productname");
        t1.setText("Product Name: " + sname);

        sprice = intent.getStringExtra("price");
        t2.setText("Product Price: " + sprice);

        sdis = intent.getStringExtra("description");
        t3.setText("Description: " + sdis);

        sid = intent.getStringExtra("id");

        // Handle Window Insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Edit Product Click Listener
        img3.setOnClickListener(view -> {
            Intent editIntent = new Intent(listfullactivity.this, Editproduct.class);
            // Pass data to the next activity
            editIntent.putExtra("productname", sname);
            editIntent.putExtra("price", sprice);
            editIntent.putExtra("description", sdis);
            editIntent.putExtra("image", simg);
            editIntent.putExtra("id", sid);
            startActivity(editIntent);
        });

        // Delete Product Click Listener
        img2.setOnClickListener(view ->
                confirmDelete());
    }

    private void confirmDelete() {
        new AlertDialog.Builder(this)
                .setTitle("Delete")
                .setMessage("Are you sure you want to delete this item?")
                .setPositiveButton("Yes", (dialog, which) -> deleteProduct(sid)) // Pass the product ID for deletion
                .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss()) // Just dismiss the dialog
                .show();
    }

    private void deleteProduct(String id) {
        StringRequest str = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsnb = new JSONObject(response);
                    status = jsnb.getString("status");
                    message = jsnb.getString("message");

                    if ("0".equals(status)) {
                        Toast.makeText(listfullactivity.this, message, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(listfullactivity.this, "Product removed successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(listfullactivity.this, Sellerhome.class));
                        finish(); // Close the current activity
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(listfullactivity.this, "Error parsing response", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(listfullactivity.this, "Error: " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id", id);
                return params;
            }
        };

        // Execute the request
        RequestQueue rq = Volley.newRequestQueue(listfullactivity.this);
        rq.add(str);
    }
}
