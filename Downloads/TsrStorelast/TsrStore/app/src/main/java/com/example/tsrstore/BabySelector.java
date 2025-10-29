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

public class BabySelector extends AppCompatActivity {
    CardView c1,c2,c3;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_baby_selector);
        c1= findViewById(R.id.toys);
        c2=findViewById(R.id.bcare);
        c3=findViewById(R.id.school);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="Toys";
                Intent intent=new Intent(BabySelector.this, Productactivity.class);
                intent.putExtra("availableproducts",type);
                startActivity(intent);
            }
        });
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="Baby care";
                Intent intent=new Intent(BabySelector.this, Productactivity.class);
                intent.putExtra("availableproducts",type);
                startActivity(intent);
            }
        });
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="School Supplies";
                Intent intent=new Intent(BabySelector.this, Productactivity.class);
                intent.putExtra("availableproducts",type);
                startActivity(intent);
            }
        });
    }
}