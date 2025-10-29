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

public class FurnitureSelector extends AppCompatActivity {
    CardView c1,c2,c3,c4,c5,c6,c7;
    String type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_furniture_selector);
        c1=findViewById(R.id.bed);
        c2=findViewById(R.id.living);
        c3=findViewById(R.id.study);
        c4=findViewById(R.id.dinning);
        c5=findViewById(R.id.outdoor);
        c6=findViewById(R.id.kidroom);
        c7=findViewById(R.id.fstorage);



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="Bed Room Furniture";
                Intent intent=new Intent(FurnitureSelector.this, Productactivity.class);
                intent.putExtra("availableproducts",type);
                startActivity(intent);
            }
        });
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="Living Room Furniture";
                Intent intent=new Intent(FurnitureSelector.this, Productactivity.class);
                intent.putExtra("availableproducts",type);
                startActivity(intent);
            }
        });
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="Study and Office Furniture";
                Intent intent=new Intent(FurnitureSelector.this, Productactivity.class);
                intent.putExtra("availableproducts",type);
                startActivity(intent);
            }
        });
        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="Dining and Kitchen Furniture";
                Intent intent=new Intent(FurnitureSelector.this, Productactivity.class);
                intent.putExtra("availableproducts",type);
                startActivity(intent);
            }
        });
        c5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="Outdoor Furniture";
                Intent intent=new Intent(FurnitureSelector.this, Productactivity.class);
                intent.putExtra("availableproducts",type);
                startActivity(intent);
            }
        });
        c6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="Kids room Furniture";
                Intent intent=new Intent(FurnitureSelector.this, Productactivity.class);
                intent.putExtra("availableproducts",type);
                startActivity(intent);
            }
        });
        c7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="Storage Furniture";
                Intent intent=new Intent(FurnitureSelector.this, Productactivity.class);
                intent.putExtra("availableproducts",type);
                startActivity(intent);
            }
        });
    }
}