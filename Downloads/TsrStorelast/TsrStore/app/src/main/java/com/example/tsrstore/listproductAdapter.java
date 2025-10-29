package com.example.tsrstore;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

//import com.mashood.kaudisorders.R;
//import com.mashood.kaudisorders.disorder.DisorderListActivity;
//import com.squareup.picasso.Picasso;

//import com.example.wecan.ui.dashboard.DashboardFragment;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class listproductAdapter extends RecyclerView.Adapter<listproductAdapter.MyViewHolder> {


    private LayoutInflater inflater;
    private ArrayList<listproductModel> dataModelArrayList;
    private Context c;


    public listproductAdapter(Context ctx, ArrayList<listproductModel> dataModelArrayList) {
        c = ctx;
        inflater = LayoutInflater.from(c);
        this.dataModelArrayList = dataModelArrayList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.listproduct, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {

//        Picasso.get().load(dataModelArrayList.get(position).getImage()).into(holder.img);
        final listproductModel omodel = dataModelArrayList.get(position);
        Picasso.get().load(config.imgurl + omodel.getImage()).into(holder.img);

        holder.ritem.setText("productname :  "+ dataModelArrayList.get(position).getProductname());
//        holder.workingexperience.setText("Working Experience:  "+dataModelArrayList.get(position).getWorkingexperience());
//        holder.district.setText("District:  "+dataModelArrayList.get(position).getDistrict());
//        holder.email.setText("Email:  "+dataModelArrayList.get(position).getEmail());
        holder.rprice.setText("price:  "+dataModelArrayList.get(position).getPrice());


        //call function
//        holder.rphonenumber.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                call(dataModelArrayList.get(position).getPhonenumber());
//            }
//
//            private void call(String contactnumber) {
//                Intent intent=new Intent(Intent.ACTION_DIAL);
//                intent.setData(Uri.parse("tel:" +contactnumber));
//                c.startActivity(intent);
//            }
//        });

        //button click cheyumbo data kittan
//
//        holder.edit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent = new Intent(c, RequestformforAshaWorker.class);
//
//                intent.putExtra("name",dataModelArrayList.get(position).getName());
//
//                c.startActivity(intent);
//
////                        if (!dataModelArrayList.get(position).getImage().equals("")) {
////            Picasso.get.load(config.imgurl+dataModelArrayList.get(position).getImage()).into(holder.image);
//            }
//
//        });


        holder.receivercardiew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(c, listfullactivity.class);
                intent.putExtra("id",dataModelArrayList.get(position).getId());
                intent.putExtra("shopid",dataModelArrayList.get(position).getShopid());
                intent.putExtra("shopname",dataModelArrayList.get(position).getShopname());
                intent.putExtra("shopnumber",dataModelArrayList.get(position).getShopnumber());
                intent.putExtra("shopcategory", dataModelArrayList.get(position).getShopcategory());
                intent.putExtra("availableproducts", dataModelArrayList.get(position).getAvailableproducts());
                intent.putExtra("productname", dataModelArrayList.get(position).getProductname());
                intent.putExtra("price",dataModelArrayList.get(position).getPrice());
                intent.putExtra("location",dataModelArrayList.get(position).getLocation());
                intent.putExtra("description",dataModelArrayList.get(position).getDescription());
                intent.putExtra("image",dataModelArrayList.get(position).getImage());

                c.startActivity(intent);

//                        if (!dataModelArrayList.get(position).getImage().equals("")) {
//            Picasso.get.load(config.imgurl+dataModelArrayList.get(position).getImage()).into(holder.image);
            }

        });

//        holder.edit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent = new Intent(c, Nuraeasharequestform.class);
//                intent.putExtra("name",dataModelArrayList.get(position).getName());
//                intent.putExtra("phonenumber", dataModelArrayList.get(position).getPhonenumber());
//                c.startActivity(intent);
//
////                        if (!dataModelArrayList.get(position).getImage().equals("")) {
////            Picasso.get.load(config.imgurl+dataModelArrayList.get(position).getImage()).into(holder.image);
//            }
//
//        });


        //sms nte code




//
//      holder.cardView.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View view) {
//               BookingDataModel p = dataModelArrayList.get(position);
//             String crop = p.getBloodgp();
//           //    String img = p.getImage();
//              Intent i = new Intent(c, Bookingdonorslist.class);
//               i.putExtra("crop", crop);
//               c.startActivity(i);
//            }
//       });

    }


    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }


    public void filterList(ArrayList<listproductModel> filteredSongs) {
        this.dataModelArrayList = filteredSongs;
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {


        CardView receivercardiew;
        TextView ritem,rprice;
        ImageView img;
        //        Button edit;
        String id,cname;


        public MyViewHolder(View itemView) {
            super(itemView);
            img= itemView.findViewById(R.id.rimage);
            ritem = itemView.findViewById(R.id.ritem);
            rprice = itemView.findViewById(R.id.rprice);
            receivercardiew =itemView.findViewById(R.id.receivercardview);

//            edit.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent in=new Intent(c, Doctorsbookingform.class);
//                    in.putExtra("id",id);
//                    in.putExtra("name",cname);
//
//                }
//            });



        }

    }
}