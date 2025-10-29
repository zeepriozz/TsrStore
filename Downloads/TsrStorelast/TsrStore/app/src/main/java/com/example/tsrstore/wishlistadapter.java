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

public class wishlistadapter extends RecyclerView.Adapter<wishlistadapter.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<wishlistmodel> dataModelArrayList;
    private ArrayList<wishlistmodel> dataModelArrayListFiltered; // Copy for filtering
    private Context c;

    public wishlistadapter(Context c, ArrayList<wishlistmodel> dataModelArrayList) {
        this.dataModelArrayList = dataModelArrayList;
        this.dataModelArrayListFiltered = new ArrayList<>(dataModelArrayList); // Copy for filtering
        this.inflater = LayoutInflater.from(c);
        this.c = c;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the XML layout for the product list
        View view = inflater.inflate(R.layout.wishlist, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // Calculate the actual position in the data list
        int actualPosition = position * 2;

        // Get the first product for the current pair
        final wishlistmodel model1 = dataModelArrayListFiltered.get(actualPosition);

        // Load product image, name, and price for the first product
        Picasso.get().load(config.imgurl + model1.getImage()).into(holder.item01);
        holder.pname.setText(model1.getProductname());
        holder.prize.setText(model1.getPrice() + "/-");

        // Handle click on the first product card
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open the product full details when clicked
                Intent intent = new Intent(c, Productfullactivity.class);
                intent.putExtra("id", model1.getId());
                intent.putExtra("userid", model1.getUserid());
                intent.putExtra("username", model1.getUsername());
                intent.putExtra("usernumber", model1.getUsernumber());
                intent.putExtra("productid", model1.getProductid());
                intent.putExtra("productname", model1.getProductname());
                intent.putExtra("price", model1.getPrice());
                intent.putExtra("package", model1.getProductpack());
                intent.putExtra("description", model1.getDescription());
                intent.putExtra("image", model1.getImage());
                intent.putExtra("shopid", model1.getShopid());
                intent.putExtra("shopname", model1.getShopname());
                c.startActivity(intent);
            }
        });

        // Check if a second product exists for this pair
        if (actualPosition + 1 < dataModelArrayListFiltered.size()) {
            // Get the second product for the current pair
            final wishlistmodel model2 = dataModelArrayListFiltered.get(actualPosition + 1);

            // Load product image, name, and price for the second product
            Picasso.get().load(config.imgurl + model2.getImage()).into(holder.item02);
            holder.pname1.setText(model2.getProductname());
            holder.prize1.setText(model2.getPrice() + "/-");

            // Handle click on the second product card
            holder.card1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Open the product full details when clicked
                    Intent intent = new Intent(c, Productfullactivity.class);
                    intent.putExtra("id", model2.getId());
                    intent.putExtra("userid", model2.getUserid());
                    intent.putExtra("username", model2.getUsername());
                    intent.putExtra("usernumber", model2.getUsernumber());
                    intent.putExtra("productid", model2.getProductid());
                    intent.putExtra("productname", model2.getProductname());
                    intent.putExtra("price", model2.getPrice());
                    intent.putExtra("package", model2.getProductpack());
                    intent.putExtra("description", model2.getDescription());
                    intent.putExtra("image", model2.getImage());
                    intent.putExtra("shopid", model2.getShopid());
                    intent.putExtra("shopname", model2.getShopname());


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
            pname = itemView.findViewById(R.id.pname2);
            prize = itemView.findViewById(R.id.prize2);
            pname1 = itemView.findViewById(R.id.pname3);
            prize1 = itemView.findViewById(R.id.prize3);
            item01 = itemView.findViewById(R.id.item012);
            item02 = itemView.findViewById(R.id.item023);
            card = itemView.findViewById(R.id.cardview11);
            card1 = itemView.findViewById(R.id.cardview23);
        }
    }
}
