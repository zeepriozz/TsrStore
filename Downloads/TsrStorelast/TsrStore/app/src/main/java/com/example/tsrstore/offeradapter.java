package com.example.tsrstore;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class offeradapter extends RecyclerView.Adapter<offeradapter.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<offermodel> dataModelArrayList;
    private ArrayList<offermodel> dataModelArrayListFiltered; // Copy for filtering
    private Context c;

    public offeradapter(Context c, ArrayList<offermodel> dataModelArrayList) {
        this.dataModelArrayList = dataModelArrayList;
        this.dataModelArrayListFiltered = new ArrayList<>(dataModelArrayList); // Copy for filtering
        this.inflater = LayoutInflater.from(c);
        this.c = c;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the XML layout for the product list
        View view = inflater.inflate(R.layout.offerlist, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // Calculate the actual position in the data list
        int actualPosition = position * 2;

        // Get the first product for the current pair
        final offermodel model1 = dataModelArrayListFiltered.get(actualPosition);

        // Load product image, name, and price for the first product
        Picasso.get().load(config.imgurl + model1.getImage()).into(holder.item01);
        holder.pname.setText(model1.getProductname());
        holder.prize.setText(model1.getPrice() + "/-");

        // Handle click on the first product card
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open the product full details when clicked
                Intent intent = new Intent(c, offerfullactivity.class);
                intent.putExtra("id", model1.getId());
                intent.putExtra("shopid", model1.getShopid());
                intent.putExtra("shopname", model1.getShopname());
                intent.putExtra("shopnumber", model1.getShopnumber());
                intent.putExtra("shopcategory", model1.getShopcategory());
                intent.putExtra("availableproducts", model1.getAvailableproducts());
                intent.putExtra("productname", model1.getProductname());
                intent.putExtra("price", model1.getPrice());
                intent.putExtra("location", model1.getLocation());
                intent.putExtra("description", model1.getDescription());
                intent.putExtra("image", model1.getImage());
                c.startActivity(intent);
            }
        });

        // Check if a second product exists for this pair
        if (actualPosition + 1 < dataModelArrayListFiltered.size()) {
            // Get the second product for the current pair
            final offermodel model2 = dataModelArrayListFiltered.get(actualPosition + 1);

            // Load product image, name, and price for the second product
            Picasso.get().load(config.imgurl + model2.getImage()).into(holder.item02);
            holder.pname1.setText(model2.getProductname());
            holder.prize1.setText(model2.getPrice() + "/-");

            // Handle click on the second product card
            holder.card1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Open the product full details when clicked
                    Intent intent = new Intent(c, offerfullactivity.class);
                    intent.putExtra("id", model2.getId());
                    intent.putExtra("shopid", model2.getShopid());
                    intent.putExtra("shopname", model2.getShopname());
                    intent.putExtra("shopnumber", model2.getShopnumber());
                    intent.putExtra("shopcategory", model2.getShopcategory());
                    intent.putExtra("availableproducts", model2.getAvailableproducts());
                    intent.putExtra("productname", model2.getProductname());
                    intent.putExtra("price", model2.getPrice());
                    intent.putExtra("location", model2.getLocation());
                    intent.putExtra("description", model2.getDescription());
                    intent.putExtra("image", model2.getImage());
                    c.startActivity(intent);
                }
            });

            // Make the second card and its contents visible
            holder.card1.setVisibility(View.VISIBLE);
        } else {
            // If there is no second product, hide the second card and its contents
            holder.card1.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        // Return half the size of the list (as each item displays two products)
        return (dataModelArrayListFiltered.size() + 1) / 2;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        CardView card, card1;
        TextView pname, prize, pname1, prize1;
        ImageView item01, item02;

        public MyViewHolder(View itemView) {
            super(itemView);
            pname = itemView.findViewById(R.id.offername);
            prize = itemView.findViewById(R.id.offerprize);
            pname1 = itemView.findViewById(R.id.offername1);
            prize1 = itemView.findViewById(R.id.offerprize1);
            item01 = itemView.findViewById(R.id.offerimg);
            item02 = itemView.findViewById(R.id.offerimg1);
            card = itemView.findViewById(R.id.offercard);
            card1 = itemView.findViewById(R.id.offercard1);
        }
    }
}
