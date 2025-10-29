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

public class searchadapter extends RecyclerView.Adapter<searchadapter.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<searchmodel> dataModelArrayList;
    private ArrayList<searchmodel> dataModelArrayListFiltered;
    private Context c;

    public searchadapter(Context c, ArrayList<searchmodel> dataModelArrayList) {
        this.dataModelArrayList = dataModelArrayList;
        this.dataModelArrayListFiltered = new ArrayList<>(dataModelArrayList);
        this.inflater = LayoutInflater.from(c);
        this.c = c;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.search, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        int actualPosition = position * 2;

        // Get the first product for the current pair
        final searchmodel model1 = dataModelArrayListFiltered.get(actualPosition);

        Picasso.get().load(config.imgurl + model1.getImage()).into(holder.item01);
        holder.pname.setText(model1.getProductname());
        holder.prize.setText(model1.getPrice() + "/-");

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                if ("fashion".equalsIgnoreCase(model1.getShopcategory())) {
                    intent = new Intent(c, Productfullactivity.class);
                } else {
                    intent = new Intent(c, Mobileaccessoriesfullactivity.class);
                }
                intent.putExtra("id", model1.getId());
                intent.putExtra("shopid", model1.getShopid());
                intent.putExtra("shopname", model1.getShopname());
                intent.putExtra("shopcategory", model1.getShopcategory());
                intent.putExtra("availableproducts", model1.getAvailableproducts());
                intent.putExtra("productname", model1.getProductname());
                intent.putExtra("price", model1.getPrice());
                intent.putExtra("package", model1.getPackagee());
                intent.putExtra("description", model1.getDescription());
                intent.putExtra("image", model1.getImage());
                c.startActivity(intent);
            }
        });

        if (actualPosition + 1 < dataModelArrayListFiltered.size()) {
            final searchmodel model2 = dataModelArrayListFiltered.get(actualPosition + 1);

            Picasso.get().load(config.imgurl + model2.getImage()).into(holder.item02);
            holder.pname1.setText(model2.getProductname());
            holder.prize1.setText(model2.getPrice() + "/-");

            holder.card1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent;
                    if ("fashion".equalsIgnoreCase(model2.getShopcategory())) {
                        intent = new Intent(c, Productfullactivity.class);
                    } else {
                        intent = new Intent(c, Mobileaccessoriesfullactivity.class);
                    }
                    intent.putExtra("id", model2.getId());
                    intent.putExtra("shopid", model2.getShopid());
                    intent.putExtra("shopname", model2.getShopname());
                    intent.putExtra("shopcategory", model2.getShopcategory());
                    intent.putExtra("availableproducts", model2.getAvailableproducts());
                    intent.putExtra("productname", model2.getProductname());
                    intent.putExtra("price", model2.getPrice());
                    intent.putExtra("package", model2.getPackagee());
                    intent.putExtra("description", model2.getDescription());
                    intent.putExtra("image", model2.getImage());
                    c.startActivity(intent);
                }
            });

            holder.card1.setVisibility(View.VISIBLE);
        } else {
            holder.card1.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return (dataModelArrayListFiltered.size() + 1) / 2;
    }

    public void filterList(ArrayList<searchmodel> filteredProducts) {
        this.dataModelArrayListFiltered = filteredProducts;
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        CardView card, card1;
        TextView pname, prize, pname1, prize1;
        ImageView item01, item02;

        public MyViewHolder(View itemView) {
            super(itemView);
            pname = itemView.findViewById(R.id.searchname);
            prize = itemView.findViewById(R.id.searchprice);
            pname1 = itemView.findViewById(R.id.searchname1);
            prize1 = itemView.findViewById(R.id.searchprice1);
            item01 = itemView.findViewById(R.id.searchimg);
            item02 = itemView.findViewById(R.id.searchimg1);
            card = itemView.findViewById(R.id.searchcard1);
            card1 = itemView.findViewById(R.id.searchcard2);
        }
    }
}
