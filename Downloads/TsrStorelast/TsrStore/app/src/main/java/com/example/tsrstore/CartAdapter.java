package com.example.tsrstore;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<CartModel> dataModelArrayList;
    private Context context;
    private String url = config.baseurl + "cartremove.php";
    String urlBuy = config.baseurl + "buy.php"; // URL for product purchase
    private String status, message; // Response status and message
    private String sid; // Holds the current product's ID for removal

    public CartAdapter(Context context, ArrayList<CartModel> dataModelArrayList) {
        this.dataModelArrayList = dataModelArrayList;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the XML layout for each cart item
        View view = inflater.inflate(R.layout.cart, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // Get the product for the current position
        final CartModel model = dataModelArrayList.get(position);

        // Load product image, name, and price using Picasso
        Picasso.get().load(config.imgurl + model.getImage()).into(holder.itemImage);
        holder.productName.setText(model.getProductname());
        holder.productPrice.setText(model.getPrice() + "/-");

        // Handle product removal from the cart
        holder.removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sid = model.getId(); // Set product ID to be removed
                showRemoveDialog(holder.getAdapterPosition()); // Pass the item position for removal
            }
        });

        // Handle Buy Now button click
        holder.buyNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest request = new StringRequest(Request.Method.POST, urlBuy, response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String status = jsonObject.getString("status");
                        String message = jsonObject.getString("message");

                        if (status.equals("1")) {
                            Toast.makeText(context, "Buying product", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(context, paymentmethod.class);
                            intent.putExtra("id", model.getId());
                            intent.putExtra("userid", model.getUserid());
                            intent.putExtra("username", model.getUsername());
                            intent.putExtra("usernumber", model.getUsernumber());
                            intent.putExtra("shopname", model.getShopname());
                            intent.putExtra("shopid", model.getShopid());
                            intent.putExtra("productname", model.getProductname());
                            intent.putExtra("productprice", model.getPrice());
                            intent.putExtra("productimage", model.getImage());
                            intent.putExtra("productid", model.getProductid());
                            intent.putExtra("size", model.getSize());
                            intent.putExtra("totalquantity", model.getTotalquantity());
                            intent.putExtra("totalprice", model.getTotalprice());
                            intent.putExtra("Address", model.getAddress());
                            context.startActivity(intent);
                        } else {
                            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(context, "Error parsing response", Toast.LENGTH_SHORT).show();
                    }
                }, error -> {
                    error.printStackTrace();
                    Toast.makeText(context, "Network Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("userid", model.getUserid());
                        params.put("username", model.getUsername());
                        params.put("usernumber", model.getUsernumber());
                        params.put("productid", model.getProductid());
                        params.put("productcategory", model.getProductcategory());
                        params.put("productname", model.getProductname());
                        params.put("productprice", model.getPrice());
                        params.put("productimage", model.getImage());
                        params.put("shopid", model.getShopid());
                        params.put("shopname", model.getShopname());
                        params.put("size", model.getSize());
                        params.put("totalquantity", model.getTotalquantity());
                        params.put("totalprice", model.getTotalprice());
                        params.put("Address", model.getAddress());

                        return params;
                    }
                };

                RequestQueue queue = Volley.newRequestQueue(context);
                queue.add(request);
            }
        });

        // Handle clicking on the product to view its details
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Productfullactivity.class);
                intent.putExtra("id", model.getId());
                intent.putExtra("userid", model.getUserid());
                intent.putExtra("username", model.getUsername());
                intent.putExtra("usernumber", model.getUsernumber());
                intent.putExtra("shopname", model.getShopname());
                intent.putExtra("shopid", model.getShopid());
                intent.putExtra("productcategory", model.getProductcategory());
                intent.putExtra("productname", model.getProductname());
                intent.putExtra("productprice", model.getPrice());
                intent.putExtra("productimage", model.getImage());
                intent.putExtra("productid", model.getProductid());
                intent.putExtra("size", model.getSize());
                intent.putExtra("totalquantity", model.getTotalquantity());
                intent.putExtra("totalprice", model.getTotalprice());
                intent.putExtra("Address", model.getAddress());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size(); // Return the total number of cart items
    }

    // Show a dialog to confirm removal of the product
    private void showRemoveDialog(int position) {
        new AlertDialog.Builder(context)
                .setTitle("Delete")
                .setMessage("Are you sure you want to delete this item?")
                .setPositiveButton("Yes", (dialog, which) -> deleteProduct(sid, position)) // Pass the product ID and position for deletion
                .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss()) // Just dismiss the dialog
                .show();
    }

    // Delete the product from the cart using a POST request
    private void deleteProduct(String id, int position) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    status = jsonObject.getString("status");
                    message = jsonObject.getString("message");

                    if ("0".equals(status)) {
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Product removed successfully", Toast.LENGTH_SHORT).show();
                        refreshCart(position); // Refresh the cart after successful removal
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(context, "Error parsing response", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error: " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id", id); // Pass product ID for deletion
                return params;
            }
        };

        // Execute the request
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    // Method to refresh the cart after an item is removed
    private void refreshCart(int position) {
        dataModelArrayList.remove(position); // Remove the item from the list
        notifyItemRemoved(position); // Notify the adapter that an item is removed
        notifyItemRangeChanged(position, dataModelArrayList.size()); // Notify that the remaining items need to be re-arranged
    }

    // ViewHolder class to hold the views for each cart item
    class MyViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView productName, productPrice;
        ImageView itemImage;
        Button removeButton, buyNowButton;

        public MyViewHolder(View itemView) {
            super(itemView);
            removeButton = itemView.findViewById(R.id.remove);
            buyNowButton = itemView.findViewById(R.id.buynow);
            productName = itemView.findViewById(R.id.cartname);
            productPrice = itemView.findViewById(R.id.cartprize);
            itemImage = itemView.findViewById(R.id.cartimg);
            cardView = itemView.findViewById(R.id.cartcard);
        }
    }
}
