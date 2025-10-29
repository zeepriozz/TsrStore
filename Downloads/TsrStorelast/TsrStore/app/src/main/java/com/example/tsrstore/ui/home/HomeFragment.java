package com.example.tsrstore.ui.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tsrstore.AutoSelector;
import com.example.tsrstore.BabySelector;
import com.example.tsrstore.BeautySelector;
import com.example.tsrstore.ElectronicSelector;
import com.example.tsrstore.FashionSelector;
import com.example.tsrstore.FurnitureSelector;
import com.example.tsrstore.HomeSelector;
import com.example.tsrstore.Homeproductactivity;
import com.example.tsrstore.Productactivity;
import com.example.tsrstore.R;
import com.example.tsrstore.SportsSelector;
import com.example.tsrstore.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    CardView c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12,c13,c14,c15,c16,c17,c18,c19,c20,c21,c22,c23,c24,c25,c26,c27,c28,c29,c30,c31,c32,c33,c34,c35,c36,c37;
    String type;

    @SuppressLint("WrongViewCast")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        c1=root.findViewById(R.id.fashion);
        c2=root.findViewById(R.id.electronic);
        c3=root.findViewById(R.id.home1);
        c4=root.findViewById(R.id.beauty);
        c5=root.findViewById(R.id.toys);
        c6=root.findViewById(R.id.fitness);
        c7=root.findViewById(R.id.auto1);
        c8=root.findViewById(R.id.furniture);
        c9=root.findViewById(R.id.homemen);
        c10=root.findViewById(R.id.homewomen);
        c11=root.findViewById(R.id.homekid);
        c12=root.findViewById(R.id.homeotherfashion);
        c13=root.findViewById(R.id.homemobile);
        c14=root.findViewById(R.id.homelap);
        c15=root.findViewById(R.id.homeelec);
        c16=root.findViewById(R.id.homeair);
        c17=root.findViewById(R.id.homekitchen);
        c18=root.findViewById(R.id.homelamp);
        c19=root.findViewById(R.id.homebed);
        c20=root.findViewById(R.id.homecur);
        c21=root.findViewById(R.id.homeldbuty);
        c22=root.findViewById(R.id.homemenbuty);
        c23=root.findViewById(R.id.homebabybuty);
        c24=root.findViewById(R.id.homeotherbuty);
        c25=root.findViewById(R.id.hometoy);
        c26=root.findViewById(R.id.homeschool);
        c27=root.findViewById(R.id.homesports);
        c28=root.findViewById(R.id.homegym);
        c29=root.findViewById(R.id.homenutri);
        c30=root.findViewById(R.id.homebike);
        c31=root.findViewById(R.id.homegroom);
        c32=root.findViewById(R.id.homecar);
        c33=root.findViewById(R.id.homeengin);
        c34=root.findViewById(R.id.homebedroom);
        c35=root.findViewById(R.id.homeliving);
        c36=root.findViewById(R.id.homeoffice);
        c37=root.findViewById(R.id.homedinning);






        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), FashionSelector.class);
                startActivity(intent);
            }
        });
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inte=new Intent(getActivity(),ElectronicSelector.class);
                startActivity(inte);
            }
        });
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inte=new Intent(getActivity(), HomeSelector.class);
                startActivity(inte);
            }
        });
        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inte=new Intent(getActivity(), BeautySelector.class);
                startActivity(inte);
            }
        });
        c5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inte=new Intent(getActivity(), BabySelector.class);
                startActivity(inte);
            }
        });
        c6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inte=new Intent(getActivity(), SportsSelector.class);
                startActivity(inte);
            }
        });
        c7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inte=new Intent(getActivity(), AutoSelector.class);
                startActivity(inte);
            }
        });
        c8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inte=new Intent(getActivity(), FurnitureSelector.class);
                startActivity(inte);
            }
        });
        c9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="Mens";
                Intent intent=new Intent(getActivity(), Productactivity.class);
                intent.putExtra("category",type);
                startActivity(intent);
            }
        });
        c10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="Womens";
                Intent intent=new Intent(getActivity(), Productactivity.class);
                intent.putExtra("category",type);
                startActivity(intent);
            }
        });
        c11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="Kids";
                Intent intent=new Intent(getActivity(), Productactivity.class);
                intent.putExtra("category",type);
                startActivity(intent);
            }
        });
        c12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="Other fashion items";
                Intent intent=new Intent(getActivity(), Productactivity.class);
                intent.putExtra("category",type);
                startActivity(intent);
            }
        });
        c13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="Mobiles";
                Intent intent=new Intent(getActivity(), Productactivity.class);
                intent.putExtra("category",type);
                startActivity(intent);
            }
        });
        c14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="Laptops";
                Intent intent=new Intent(getActivity(), Productactivity.class);
                intent.putExtra("category",type);
                startActivity(intent);
            }
        });
        c15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="Home electronics";
                Intent intent=new Intent(getActivity(), Productactivity.class);
                intent.putExtra("category",type);
                startActivity(intent);
            }
        });
        c16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="Other electronic items";
                Intent intent=new Intent(getActivity(), Productactivity.class);
                intent.putExtra("category",type);
                startActivity(intent);
            }
        });
        c17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="Kitchen items";
                Intent intent=new Intent(getActivity(), Productactivity.class);
                intent.putExtra("category",type);
                startActivity(intent);
            }
        });
        c18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="Decor and lighting";
                Intent intent=new Intent(getActivity(), Productactivity.class);
                intent.putExtra("category",type);
                startActivity(intent);
            }
        });
        c19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="Home Furnishings";
                Intent intent=new Intent(getActivity(), Productactivity.class);
                intent.putExtra("category",type);
                startActivity(intent);
            }
        });
        c20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="Home improvement Tools";
                Intent intent=new Intent(getActivity(), Productactivity.class);
                intent.putExtra("category",type);
                startActivity(intent);
            }
        });
        c21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="Haircare";
                Intent intent=new Intent(getActivity(), Productactivity.class);
                intent.putExtra("category",type);
                startActivity(intent);
            }
        });
        c22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="Skincare";
                Intent intent=new Intent(getActivity(), Productactivity.class);
                intent.putExtra("category",type);
                startActivity(intent);
            }
        });
        c23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="Fragrance";
                Intent intent=new Intent(getActivity(), Productactivity.class);
                intent.putExtra("category",type);
                startActivity(intent);
            }
        });
        c24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="Other Beauty";
                Intent intent=new Intent(getActivity(), Productactivity.class);
                intent.putExtra("category",type);
                startActivity(intent);
            }
        });
        c25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="Toys";
                Intent intent=new Intent(getActivity(), Productactivity.class);
                intent.putExtra("category",type);
                startActivity(intent);
            }
        });
        c26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="School Supplies";
                Intent intent=new Intent(getActivity(), Productactivity.class);
                intent.putExtra("category",type);
                startActivity(intent);
            }
        });
        c27.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="sports";
                Intent intent=new Intent(getActivity(), Productactivity.class);
                intent.putExtra("category",type);
                startActivity(intent);
            }
        });
        c28.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="Fitness";
                Intent intent=new Intent(getActivity(), Productactivity.class);
                intent.putExtra("category",type);
                startActivity(intent);
            }
        });
        c29.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="Nutrition and helthcare";
                Intent intent=new Intent(getActivity(), Productactivity.class);
                intent.putExtra("category",type);
                startActivity(intent);
            }
        });
        c30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="Bike accessories";
                Intent intent=new Intent(getActivity(), Productactivity.class);
                intent.putExtra("category",type);
                startActivity(intent);
            }
        });
        c31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="Cleaning and Grooming";
                Intent intent=new Intent(getActivity(), Productactivity.class);
                intent.putExtra("category",type);
                startActivity(intent);
            }
        });
        c32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="Car accessories";
                Intent intent=new Intent(getActivity(), Productactivity.class);
                intent.putExtra("category",type);
                startActivity(intent);
            }
        });
        c33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="Tyers and engin oil";
                Intent intent=new Intent(getActivity(), Productactivity.class);
                intent.putExtra("category",type);
                startActivity(intent);
            }
        });
        c34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="Bed Room Furniture";
                Intent intent=new Intent(getActivity(), Productactivity.class);
                intent.putExtra("category",type);
                startActivity(intent);
            }
        });
        c35.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="Living Room Furniture";
                Intent intent=new Intent(getActivity(), Productactivity.class);
                intent.putExtra("category",type);
                startActivity(intent);
            }
        });
        c36.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="Study and Office Furniture";
                Intent intent=new Intent(getActivity(), Productactivity.class);
                intent.putExtra("category",type);
                startActivity(intent);
            }
        });
        c37.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="Dining and Kitchen Furniture";
                Intent intent=new Intent(getActivity(), Productactivity.class);
                intent.putExtra("category",type);
                startActivity(intent);
            }
        });







////        final TextView textView = binding.textHome;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}