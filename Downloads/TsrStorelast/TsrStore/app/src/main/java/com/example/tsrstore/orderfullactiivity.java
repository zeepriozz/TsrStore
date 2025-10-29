package com.example.tsrstore;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.squareup.picasso.Picasso;

public class orderfullactiivity extends AppCompatActivity {
    ImageView orimg;
    TextView t1,t2,t3,t4;
    String img,pname,pqt,pprize,pdate;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_orderfullactiivity);
        orimg=findViewById(R.id.orderimg);
        t1=findViewById(R.id.orderpname);
        t2=findViewById(R.id.ordertquantity);
        t3=findViewById(R.id.ordertprize);
        t4=findViewById(R.id.orderdate);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Intent intent = getIntent();
        img = intent.getStringExtra("productimage");
        Picasso.get().load(config.imgurl + img).into(orimg);
        pname = intent.getStringExtra("productname");
        t1.setText("Product name:"+pname);
        pqt = intent.getStringExtra("totalquantity");
        t2.setText("Total quantity:"+pqt);
        pprize = intent.getStringExtra("totalprice");
        t3.setText("Total price:"+pprize);
        pdate = intent.getStringExtra("date");
        t4.setText("Date of Order:"+pdate);




    }
}