package com.example.tsrstore;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FashionSelector extends AppCompatActivity {

    CardView c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12,c13,c14,c15,c16,c17,c18,c19,c20;
    String type,type2;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_fashion_selector);
        c1=findViewById(R.id.shirt);
        c2=findViewById(R.id.jeans);
        c3=findViewById(R.id.casual);
        c4=findViewById(R.id.sports);
        c5=findViewById(R.id.dress);
        c6=findViewById(R.id.saree);
        c7=findViewById(R.id.tops);
        c8=findViewById(R.id.lehenka);
        c9=findViewById(R.id.boyshirt);
        c10=findViewById(R.id.boysshort);
        c11=findViewById(R.id.Girlsethinic);
        c12=findViewById(R.id.girlsdress);
        c13=findViewById(R.id.meninner);
        c14=findViewById(R.id.womeninner);
        c15=findViewById(R.id.boysinner);
        c16=findViewById(R.id.jewellery);
        c17=findViewById(R.id.menacc);
        c18=findViewById(R.id.womenacces);
        c19=findViewById(R.id.bags);
        c20=findViewById(R.id.girlsinner);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="T-shirts and Shirts";
                Intent intent=new Intent(FashionSelector.this, Productactivity.class);
                intent.putExtra("availableproducts",type);
                startActivity(intent);
            }
        });
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type2="Jeans and Trousers";
                Intent intent=new Intent(FashionSelector.this, Productactivity.class);
                intent.putExtra("availableproducts",type2);
                startActivity(intent);
            }
        });
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="Casual shoe and sandels";
                Intent intent=new Intent(FashionSelector.this, Productactivity.class);
                intent.putExtra("availableproducts",type);
                startActivity(intent);
            }
        });
        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="Sports shoe";
                Intent intent=new Intent(FashionSelector.this, Productactivity.class);
                intent.putExtra("availableproducts",type);
                startActivity(intent);
            }
        });
        c5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="Dress";
                Intent intent=new Intent(FashionSelector.this, Productactivity.class);
                intent.putExtra("availableproducts",type);
                startActivity(intent);
            }
        });
        c6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="Sarees";
                Intent intent=new Intent(FashionSelector.this, Productactivity.class);
                intent.putExtra("availableproducts",type);
                startActivity(intent);
            }
        });
        c7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="Top Wear";
                Intent intent=new Intent(FashionSelector.this, Productactivity.class);
                intent.putExtra("availableproducts",type);
                startActivity(intent);
            }
        });
        c8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="Lehengas and Gowns";
                Intent intent=new Intent(FashionSelector.this, Productactivity.class);
                intent.putExtra("availableproducts",type);
                startActivity(intent);
            }
        });
        c9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="Boys T-shirt and Shirt";
                Intent intent=new Intent(FashionSelector.this, Productactivity.class);
                intent.putExtra("availableproducts",type);
                startActivity(intent);
            }
        });
        c10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="Boys Shorts and Jeans";
                Intent intent=new Intent(FashionSelector.this, Productactivity.class);
                intent.putExtra("availableproducts",type);
                startActivity(intent);
            }
        });
        c11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="Girls Ehinic and T-shirts";
                Intent intent=new Intent(FashionSelector.this, Productactivity.class);
                intent.putExtra("availableproducts",type);
                startActivity(intent);
            }
        });
        c12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="Girls Dress and Frocks";
                Intent intent=new Intent(FashionSelector.this, Productactivity.class);
                intent.putExtra("availableproducts",type);
                startActivity(intent);
            }
        });
        c13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="Mens inner wear";
                Intent intent=new Intent(FashionSelector.this, Productactivity.class);
                intent.putExtra("availableproducts",type);
                startActivity(intent);
            }
        });
        c14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="Womens inner wear";
                Intent intent=new Intent(FashionSelector.this, Productactivity.class);
                intent.putExtra("availableproducts",type);
                startActivity(intent);
            }
        });
        c15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="Boys inner wear";
                Intent intent=new Intent(FashionSelector.this, Productactivity.class);
                intent.putExtra("availableproducts",type);
                startActivity(intent);
            }
        });
        c16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="Girls inner wears";
                Intent intent=new Intent(FashionSelector.this, Productactivity.class);
                intent.putExtra("availableproducts",type);
                startActivity(intent);
            }
        });
        c17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="Mens Watches ,beslts and sunglasses";
                Intent intent=new Intent(FashionSelector.this, Productactivity.class);
                intent.putExtra("availableproducts",type);
                startActivity(intent);
            }
        });
        c18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="Womens watches ,belts and sunglasses";
                Intent intent=new Intent(FashionSelector.this, Productactivity.class);
                intent.putExtra("availableproducts",type);
                startActivity(intent);
            }
        });
        c19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="Bags and suitcase";
                Intent intent=new Intent(FashionSelector.this, Productactivity.class);
                intent.putExtra("availableproducts",type);
                startActivity(intent);
            }
        });
        c20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="jewellery earrings and bangles";
                Intent intent=new Intent(FashionSelector.this, Productactivity.class);
                intent.putExtra("availableproducts",type);
                startActivity(intent);
            }
        });
    }
}