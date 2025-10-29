package com.example.tsrstore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Homeproductadapter extends RecyclerView.Adapter<Homeproductadapter.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<Homeproductmodel> dataModelArrayList;
    private ArrayList<Homeproductmodel> dataModelArrayListFiltered;
    private Context c;

    public Homeproductadapter(Context c, ArrayList<Homeproductmodel> dataModelArrayList) {
        this.dataModelArrayList = dataModelArrayList;
        this.dataModelArrayListFiltered = new ArrayList<>(dataModelArrayList); // Copy for filtering
        this.inflater = LayoutInflater.from(c);
        this.c = c;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.activity_homeproductactivity, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // Calculate the actual position in the data list
        int actualPosition = position * 2;

        // Get the first product for the current pair
        final Homeproductmodel model1 = dataModelArrayListFiltered.get(actualPosition);

        // Load product image, name, and price for the first product
        Picasso.get().load(config.imgurl + model1.getImage()).into(holder.item01);
        holder.pname.setText(model1.getProductname());
        holder.prize.setText(model1.getPrice() + "/-");

        // Set heart icon for the first product
//        holder.heartIcon.setImageResource(model1.isFavorite() ? R.drawable.bw : R.drawable.heart);
//
//        // Handle heart icon click for the first product
//        holder.heartIcon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                model1.setFavorite(!model1.isFavorite());
//                holder.heartIcon.setImageResource(model1.isFavorite() ? R.drawable.bw : R.drawable.heart);
//                Toast.makeText(c, model1.isFavorite() ? "Added to Wishlist" : "Removed from Wishlist", Toast.LENGTH_SHORT).show();
//            }
//        });

        // Check if a second product exists for this pair
        if (actualPosition + 1 < dataModelArrayListFiltered.size()) {
            final Homeproductmodel model2 = dataModelArrayListFiltered.get(actualPosition + 1);

            // Load product image, name, and price for the second product
            Picasso.get().load(config.imgurl + model2.getImage()).into(holder.item02);
            holder.pname1.setText(model2.getProductname());
            holder.prize1.setText(model2.getPrice() + "/-");

            // Set heart icon for the second product
//            holder.heartIcon1.setImageResource(model2.isFavorite() ? R.drawable.bw : R.drawable.heart);
//
//            // Handle heart icon click for the second product
//            holder.heartIcon1.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    model2.setFavorite(!model2.isFavorite());
//                    holder.heartIcon1.setImageResource(model2.isFavorite() ? R.drawable.bw : R.drawable.heart);
//                    Toast.makeText(c, model2.isFavorite() ? "Added to Wishlist" : "Removed from Wishlist", Toast.LENGTH_SHORT).show();
//                }
//            });

            // Make second product visible
            holder.item02.setVisibility(View.VISIBLE);
            holder.pname1.setVisibility(View.VISIBLE);
            holder.prize1.setVisibility(View.VISIBLE);
//            holder.heartIcon1.setVisibility(View.VISIBLE);
        } else {
            // Hide the second product views if there's no second product
            holder.item02.setVisibility(View.GONE);
            holder.pname1.setVisibility(View.GONE);
            holder.prize1.setVisibility(View.GONE);
//            holder.heartIcon1.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        // Return half the size of the list (as each item displays two products)
        return (dataModelArrayListFiltered.size() + 1) / 2;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView pname, prize, pname1, prize1;
        ImageView item01, item02, heartIcon, heartIcon1;

        public MyViewHolder(View itemView) {
            super(itemView);
            pname = itemView.findViewById(R.id.ppname);
            prize = itemView.findViewById(R.id.pprize);
            pname1 = itemView.findViewById(R.id.ppname1);
            prize1 = itemView.findViewById(R.id.pprize1);
            item01 = itemView.findViewById(R.id.pitem01);
            item02 = itemView.findViewById(R.id.pitem02);
//            heartIcon = itemView.findViewById(R.id.pheart_icon);
//            heartIcon1 = itemView.findViewById(R.id.pheart_icon1);
        }
    }
}
