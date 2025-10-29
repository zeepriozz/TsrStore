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

public class orderadapter extends RecyclerView.Adapter<orderadapter.MyViewHolder> {


    private LayoutInflater inflater;
    private ArrayList<ordermodell> dataModelArrayList;
    private Context c;


    public orderadapter(Context ctx, ArrayList<ordermodell> dataModelArrayList) {
        c = ctx;
        inflater = LayoutInflater.from(c);
        this.dataModelArrayList = dataModelArrayList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.order, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {

//        Picasso.get().load(dataModelArrayList.get(position).getImage()).into(holder.img);
        final ordermodell omodel = dataModelArrayList.get(position);
        // Load product image, name, and price for the first product
        Picasso.get().load(config.imgurl + omodel.getProductimage()).into(holder.item01);
        holder.pname.setText(omodel.getProductname());
        holder.pdetails.setText(omodel.getTotalquantity());
        holder.prize.setText(omodel.getTotalprice() + "/-");




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


        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(c, orderfullactiivity.class);
                intent.putExtra("id", omodel.getId());
                intent.putExtra("userid", omodel.getUserid());
                intent.putExtra("username", omodel.getUsername());
                intent.putExtra("usernumber", omodel.getUsernumber());
                intent.putExtra("productid", omodel.getProductid());
                intent.putExtra("productname", omodel.getProductname());
                intent.putExtra("productprice", omodel.getProductprice());
                intent.putExtra("productimage", omodel.getProductimage());
                intent.putExtra("shopid", omodel.getShopid());
                intent.putExtra("shopname", omodel.getShopname());
                intent.putExtra("totalquantity", omodel.getTotalquantity());
                intent.putExtra("totalprice", omodel.getTotalprice());
                intent.putExtra("payment", omodel.getPayment());
                intent.putExtra("date", omodel.getDate());
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


    public void filterList(ArrayList<ordermodell> filteredSongs) {
        this.dataModelArrayList = filteredSongs;
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {


        CardView card;
        TextView pname,prize,pdetails;
        ImageView item01;

        public MyViewHolder(View itemView) {
            super(itemView);
            pname = itemView.findViewById(R.id.ordername);
            prize = itemView.findViewById(R.id.orderprice);
            pdetails=itemView.findViewById(R.id.orderdetails);
            item01 = itemView.findViewById(R.id.orderimage);
            card = itemView.findViewById(R.id.ordercard);



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