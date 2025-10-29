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

public class AutoSelector extends AppCompatActivity {
    CardView c1,c2,c3,c4;
    String  type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_auto_selector);
        c1=findViewById(R.id.bike);
        c2=findViewById(R.id.fit);
        c3=findViewById(R.id.engine);
        c4=findViewById(R.id.clean);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="Bike accessories";
                Intent intent=new Intent(AutoSelector.this, Productactivity.class);
                intent.putExtra("availableproducts",type);
                startActivity(intent);
            }
        });
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="Car accessories";
                Intent intent=new Intent(AutoSelector.this, Productactivity.class);
                intent.putExtra("availableproducts",type);
                startActivity(intent);
            }
        });
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="Tyers and engin oil";
                Intent intent=new Intent(AutoSelector.this, Productactivity.class);
                intent.putExtra("availableproducts",type);
                startActivity(intent);
            }
        });
        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="Cleaning and Grooming";
                Intent intent=new Intent(AutoSelector.this, Productactivity.class);
                intent.putExtra("availableproducts",type);
                startActivity(intent);
            }
        });
    }
}