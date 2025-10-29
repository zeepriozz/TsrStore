package com.example.tsrstore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ElectronicSelector extends AppCompatActivity {
    CardView c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12,c13,c14,c15,c16;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_electronic_selector);
        c1=findViewById(R.id.mobile1);
//        c2=findViewById(R.id.mobileacces);
//        c3=findViewById(R.id.case1);
//        c4=findViewById(R.id.headphone);
        c2=findViewById(R.id.laptop);
//        c6=findViewById(R.id.monitor);
//        c7=findViewById(R.id.keyboard);
//        c8=findViewById(R.id.storage);
//        c9=findViewById(R.id.swatch);
//        c10=findViewById(R.id.dryer);
//        c11=findViewById(R.id.tablet);
//        c12=findViewById(R.id.camera);
//        c13=findViewById(R.id.powerbank);
//        c14=findViewById(R.id.smart);
        c3=findViewById(R.id.helth);
        c4=findViewById(R.id.wash);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type="Mobile";
                Intent intent=new Intent(ElectronicSelector.this, Productactivity.class);
                intent.putExtra("availableproducts",type);
                startActivity(intent);
            }
        });
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type="Laptops";
                Intent intent=new Intent(ElectronicSelector.this, Productactivity.class);
                intent.putExtra("availableproducts",type);
                startActivity(intent);
            }
        });
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type="Other electronic items";
                Intent intent=new Intent(ElectronicSelector.this, Productactivity.class);
                intent.putExtra("availableproducts",type);
                startActivity(intent);
            }
        });
        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type="Home electronics";
                Intent intent=new Intent(ElectronicSelector.this, Productactivity.class);
                intent.putExtra("availableproducts",type);
                startActivity(intent);
            }
        });

    }
}