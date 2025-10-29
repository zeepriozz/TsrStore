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

public class BeautySelector extends AppCompatActivity {
    CardView c1,c2,c3,c4,c5;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_beauty_selector);
        c1=findViewById(R.id.face);
        c2=findViewById(R.id.hair);
        c3=findViewById(R.id.fragrance);
        c4=findViewById(R.id.lip);
        c5=findViewById(R.id.daily);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="Skincare";
                Intent intent=new Intent(BeautySelector.this, Productactivity.class);
                intent.putExtra("availableproducts",type);
                startActivity(intent);
            }
        });
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="Haircare";
                Intent intent=new Intent(BeautySelector.this, Productactivity.class);
                intent.putExtra("availableproducts",type);
                startActivity(intent);
            }
        });
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="Fragrance";
                Intent intent=new Intent(BeautySelector.this, Productactivity.class);
                intent.putExtra("availableproducts",type);
                startActivity(intent);
            }
        });
        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="Other Beauty";
                Intent intent=new Intent(BeautySelector.this, Productactivity.class);
                intent.putExtra("availableproducts",type);
                startActivity(intent);
            }
        });
        c5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="Daily Essentials";
                Intent intent=new Intent(BeautySelector.this, Productactivity.class);
                intent.putExtra("availableproducts",type);
                startActivity(intent);
            }
        });
    }
}