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

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<ProductModel> dataModelArrayList;
    private ArrayList<ProductModel> dataModelArrayListFiltered;
    private Context c;

    public ProductAdapter(Context c, ArrayList<ProductModel> dataModelArrayList) {
        this.dataModelArrayList = dataModelArrayList;
        this.dataModelArrayListFiltered = new ArrayList<>(dataModelArrayList);
        this.inflater = LayoutInflater.from(c);
        this.c = c;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.productlist, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        int actualPosition = position * 2;

        final ProductModel model1 = dataModelArrayListFiltered.get(actualPosition);

        Picasso.get().load(config.imgurl + model1.getImage()).into(holder.item01);
        holder.pname.setText(model1.getProductname());
        holder.prize.setText(model1.getPrice() + "/-");

        holder.card.setOnClickListener(view -> {
            Intent intent;
            if ("fashion".equalsIgnoreCase(model1.getShopcategory())) {
                if (isHomeAppliances(model1.getAvailableproducts())) {
                    intent = new Intent(c, Homeapplincesfullactivity.class);
                }
                else if(isElder(model1.getAvailableproducts()))
                {
                    intent = new Intent(c, Productfullactivity.class);
                }
                else if (isKids(model1.getAvailableproducts())) {
                    intent = new Intent(c, kidsfullactivity.class);
                }
                else{
                    intent = new Intent(c, Mobileaccessoriesfullactivity.class);
                }
            } else {
                intent = new Intent(c, Mobileaccessoriesfullactivity.class);
            }
            addIntentExtras(intent, model1);
            c.startActivity(intent);
        });

        if (actualPosition + 1 < dataModelArrayListFiltered.size()) {
            final ProductModel model2 = dataModelArrayListFiltered.get(actualPosition + 1);

            Picasso.get().load(config.imgurl + model2.getImage()).into(holder.item02);
            holder.pname1.setText(model2.getProductname());
            holder.prize1.setText(model2.getPrice() + "/-");

            holder.card1.setOnClickListener(view -> {
                Intent intent;
                if ("fashion".equalsIgnoreCase(model2.getShopcategory())) {
                    if (isHomeAppliances(model2.getAvailableproducts())) {
                        intent = new Intent(c, Homeapplincesfullactivity.class);
                    }
                    else if(isElder(model2.getAvailableproducts()))
                    {
                        intent = new Intent(c, Productfullactivity.class);
                    }
                    else if (isKids(model2.getAvailableproducts())) {
                        intent = new Intent(c, kidsfullactivity.class);
                    }
                    else{
                        intent = new Intent(c, Mobileaccessoriesfullactivity.class);
                    }
                }
                else {
                    intent = new Intent(c, Mobileaccessoriesfullactivity.class);
                }
                addIntentExtras(intent, model2);
                c.startActivity(intent);
            });

            holder.card1.setVisibility(View.VISIBLE);
        } else {
            holder.card1.setVisibility(View.GONE);
        }
    }

    private boolean isHomeAppliances(String availableProducts) {
        return availableProducts.equalsIgnoreCase("Casual shoe and sandels")
                || availableProducts.equalsIgnoreCase("Womens Casual shoe and sandels")
                || availableProducts.equalsIgnoreCase("Sports shoe");
    }
    private boolean isKids(String availableProducts) {
        return availableProducts.equalsIgnoreCase("Boys T-shirt and Shirt")
                || availableProducts.equalsIgnoreCase("Boys Shorts and Jeans")
                || availableProducts.equalsIgnoreCase("Girls Ethnic and T-shirts")
                || availableProducts.equalsIgnoreCase("Girls Dress and Frocks")
                || availableProducts.equalsIgnoreCase("Boys inner wear")
                || availableProducts.equalsIgnoreCase("Girls inner wear")
                || availableProducts.equalsIgnoreCase("Boys Ethnic")
                || availableProducts.equalsIgnoreCase("Womens belts and sunglasses");
    }
    private boolean isElder(String availableProducts) {
        return availableProducts.equalsIgnoreCase("T-shirts and Shirts")
                || availableProducts.equalsIgnoreCase("Jeans and Trousers")
                || availableProducts.equalsIgnoreCase("Mens inner wear")
                || availableProducts.equalsIgnoreCase("Dress")
                || availableProducts.equalsIgnoreCase("Top Wear")
                || availableProducts.equalsIgnoreCase("Lehengas and Gowns")
                || availableProducts.equalsIgnoreCase("Womens inner wear");
    }

    private void addIntentExtras(Intent intent, ProductModel model) {
        intent.putExtra("id", model.getId());
        intent.putExtra("shopid", model.getShopid());
        intent.putExtra("shopname", model.getShopname());
        intent.putExtra("shopcategory", model.getShopcategory());
        intent.putExtra("availableproducts", model.getAvailableproducts());
        intent.putExtra("productname", model.getProductname());
        intent.putExtra("price", model.getPrice());
        intent.putExtra("package", model.getPackagee());
        intent.putExtra("description", model.getDescription());
        intent.putExtra("image", model.getImage());
    }

    @Override
    public int getItemCount() {
        return (dataModelArrayListFiltered.size() + 1) / 2;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        CardView card, card1;
        TextView pname, prize, pname1, prize1;
        ImageView item01, item02;

        public MyViewHolder(View itemView) {
            super(itemView);
            pname = itemView.findViewById(R.id.pname);
            prize = itemView.findViewById(R.id.prize);
            pname1 = itemView.findViewById(R.id.pname1);
            prize1 = itemView.findViewById(R.id.prize1);
            item01 = itemView.findViewById(R.id.item01);
            item02 = itemView.findViewById(R.id.item02);
            card = itemView.findViewById(R.id.cardview1);
            card1 = itemView.findViewById(R.id.cardview2);
        }
    }
}
