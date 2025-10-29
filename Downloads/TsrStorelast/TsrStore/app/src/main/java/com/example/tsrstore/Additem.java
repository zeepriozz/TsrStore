package com.example.tsrstore;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Additem extends AppCompatActivity {
    EditText productname, price, pac, des,pak;
    Spinner categorySpinner, subSpinner, subSpinner1;
    Button upload;
    ImageView img;

    String sproductname, sprice, spac, sdes, shoppack;
    String status, message, type, cate, sname, sphone, sid,type1;
    String[] scategory = {"Select your Shop Category", "Fashion", "Electronics", "Beauty and Wellness", "Home and Kitchen", "Toys, Baby and Books", "Sports and Fitness", "Auto Accessories", "Furniture"};

    private RequestQueue rQueue;
    private static ProgressDialog mProgressDialog;
    String url = config.baseurl + "additem.php";

    private Map<String, List<String>> subCategoriesMap = new HashMap<>();
    private Map<String, List<String>> subCategoriesMap1 = new HashMap<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additem);

        productname = findViewById(R.id.sproname);
        price = findViewById(R.id.sprize);
        pac = findViewById(R.id.spkg);
        des = findViewById(R.id.sdes);
        upload = findViewById(R.id.supload);
        subSpinner = findViewById(R.id.sspin);
        categorySpinner = findViewById(R.id.sspin1);
        img = findViewById(R.id.simg);
        pak=findViewById(R.id.spackage);
        subSpinner1 = findViewById(R.id.sspin2);
        final Animation buttonClickAnimation = AnimationUtils.loadAnimation(this, R.anim.button_click_animation);

        // Set up categorySpinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, scategory);
        categorySpinner.setAdapter(adapter);

        // Initialize subSpinner1 with an empty adapter
        ArrayAdapter<String> subAdapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, new ArrayList<>());
        subSpinner1.setAdapter(subAdapter1);

        // Initialize subSpinner with an empty adapter
        ArrayAdapter<String> subAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, new ArrayList<>());
        subSpinner.setAdapter(subAdapter);

        // Add subcategories for "Fashion"
        List<String> gSub = Arrays.asList("Mens", "Womens", "Kids","Other fashion items");
        subCategoriesMap.put("Fashion", gSub);

        // Add items under "Mens" category
        List<String> fashionSub = Arrays.asList(
                "T-shirts and Shirts", "Jeans and Trousers", "Casual shoe and sandels", "Sports shoe",
                 "Mens inner wear", "Mens Watches","belts and sunglasses"

        );
        subCategoriesMap1.put("Mens", fashionSub);
        List<String> fashionSub1 = Arrays.asList(
                "Dress","Sarees", "Top Wear", "Lehengas and Gowns",
                "Womens inner wear","Womens watches","Womens belts and sunglasses","Womens Casual shoe and sandels"
        );
        subCategoriesMap1.put("Womens", fashionSub1);
        List<String> fashionSub2 = Arrays.asList(
                "Boys T-shirt and Shirt", "Boys Shorts and Jeans","Girls Ethnic and T-shirts", "Girls Dress and Frocks",
                "Boys inner wear", "Girls inner wear","Boys Ethnic"
        );
        subCategoriesMap1.put("Kids", fashionSub2);
        List<String> fashionSub3 = Arrays.asList(
                "Jewellery, earrings and bangles","Bags and suitcase"
        );
        subCategoriesMap1.put("Other fashion items", fashionSub3);



        List<String> eSub = Arrays.asList("Mobiles", "Laptops", "Home electronics","Other electronic items");
        subCategoriesMap.put("Electronics", eSub);

        List<String> elecronicSub = Arrays.asList(
                "Mobile","Mobile accessorires","Mobile cases covers and more","powerbanks"

        );
        subCategoriesMap1.put("Mobiles", elecronicSub);

        List<String> elecronicSub1 = Arrays.asList(
                "Laptop","computer peripherals","computer accessories","Storage"

        );
        subCategoriesMap1.put("Laptops", elecronicSub1);

        List<String> elecronicSub2 = Arrays.asList(
                "Iron Box","Water purifier","Washing Mechine","Water heater","Fan","Refrigerator","Vaccum Cleaner",
                "Air Condioner","Remotes"

        );
        subCategoriesMap1.put("Home electronics", elecronicSub2);

        List<String> elecronicSub3= Arrays.asList(
                "Smartwatches","Personalcare","Camera","Helth care","Fan","Headphones","Speakers"

        );
        subCategoriesMap1.put("Other electronic items", elecronicSub3);


        List<String> bSub = Arrays.asList("Skincare", "Haircare", "Fragrance","Other Beauty","Daily Essentials");
        subCategoriesMap.put("Beauty and Wellness", bSub);

        List<String> beautySub = Arrays.asList(
                "Face wash","Sunscreen","Face Cream","serums","Lotions","Scrub"

        );
        subCategoriesMap1.put("Skincare", beautySub);

        List<String> beautySub1 = Arrays.asList(
                "Shampoo","Hair Oil","Hair Serum","Conditoner","Hair Color"

        );
        subCategoriesMap1.put("Haircare", beautySub1);

        List<String> beautySub2 = Arrays.asList(
                "Atter","Deodorants","Roll Ons","Perfume","Gift Set"

        );
        subCategoriesMap1.put("Fragrance", beautySub2);

        List<String> beautySub3 = Arrays.asList(
                "Foundation","Lipstick","Kajal"

        );
        subCategoriesMap1.put("Other Beauty", beautySub3);

        List<String> beautySub4 = Arrays.asList(
                "Bath and spa","Oral care","Women's hygiene","Men's Grooming","Shaving Essentials","soaps"

        );
        subCategoriesMap1.put("Daily Essentials", beautySub4);


        List<String> hSub = Arrays.asList("Home Furnishings","Home improvement Tools","Kitchen items","Decor and lighting");
        subCategoriesMap.put("Home and Kitchen", hSub);

        List<String> homesub = Arrays.asList(
                "Bedsheets","Curtains","Cushions and sofa covers","Towels and bath linen","Pillow and mattress Protector",
                "Mosquito Nets","Mansoon Blankets"

        );
        subCategoriesMap1.put("Home Furnishings", homesub);

        List<String> homesub1 = Arrays.asList(
                "Hand Tools","Curtains","Bath and Kitchen Fitting","Home Utility and Organizers","Gardening Essentials",
                "Electrical Hardware","Solar range"

        );
        subCategoriesMap1.put("Home improvement Tools", homesub1);

        List<String> homesub2 = Arrays.asList(
                "Gas stoves and accessories","Cookware Essentials","Dinning and Serveware","Kitchen storage","Cleaning Essentials",
                "Kitchen Tools","Bakeware Barware"

        );
        subCategoriesMap1.put("Kitchen items", homesub2);

        List<String> homesub3 = Arrays.asList(
                "Stickers and Wallpapers","Lighting Essentials","Electric insect killer","Wall Clock","Wall Decorative",
                "Showpieces","Table lamps"

        );
        subCategoriesMap1.put("Decor and lighting", homesub3);

        List<String> tSub = Arrays.asList("Toys","School Supplies","Baby care");
        subCategoriesMap.put("Toys, Baby and Books", tSub);

        List<String> toysub = Arrays.asList(
                "Infants ","Toddlers ","Pre Teens and Teens","indoor Toys","Ourdoor Toys","Educational Toys","Tricycle and Rideons",
                "Boars and card games","Stuffed Toys"

        );
        subCategoriesMap1.put("Toys", toysub);

        List<String> toysub1 = Arrays.asList(
                "School Supplies ","Pens","Diaries and noteboooks","Art Supplies","Calculator","Desk Organizer"


        );
        subCategoriesMap1.put("School Supplies", toysub1);
        List<String> toysub2 = Arrays.asList(
                "School Supplies ","Pens","Diaries and noteboooks","Art Supplies","Calculator","Desk Organizer"


        );
        subCategoriesMap1.put("Baby care", toysub2);

        List<String> sSub = Arrays.asList("sports","Fitness","Nutrition and helthcare");
        subCategoriesMap.put("Sports and Fitness", sSub);

        List<String> sportssub = Arrays.asList(

                "Team Sports","Cycling","Camping and Outdoors","Premium Sports"
        );
        subCategoriesMap1.put("sports", sportssub);

        List<String> sportssub1 = Arrays.asList(

                "Cardio Equipments","Weight Training","Yoga Essentials","Premium Fitness"
        );
        subCategoriesMap1.put("Fitness", sportssub1);

        List<String> sportssub2 = Arrays.asList(

                "Vitamin Supplements","Protein Suppliments","Medicines Supplies","Helth and Energy Drinks"
        );
        subCategoriesMap1.put("Nutrition and helthcare", sportssub2);


        List<String> aSub = Arrays.asList("Bike accessories","Car accessories","Tyers and engin oil","Cleaning and Grooming");
        subCategoriesMap.put("Auto Accessories", aSub);

        List<String> autosub = Arrays.asList(

                "Riding Jacket","Helmets","Crash Guars","Riding Gloves","Indicator Lights","Phone Holders"
        );
        subCategoriesMap1.put("Bike accessories", autosub);

        List<String> autosub1 = Arrays.asList(

                "Speakers","Dashcams","Charger","Media Player","Air Freshener","Phone Holders"
        );
        subCategoriesMap1.put("Car accessories", autosub1);

        List<String> autosub2 = Arrays.asList(

                "Tyre Air Pump","Bike Tyres","Car Tyres","Bike Lubricants","Car Lubricants","Chain Lubs"
        );
        subCategoriesMap1.put("Tyers and engin oil", autosub2);

        List<String> autosub3 = Arrays.asList(

                "Stickers","Haning Decors","Vehicle Washing Cloths","High Pressure Washers","Vaccum Cleaners","Shampoos"
        );
        subCategoriesMap1.put("Cleaning and Grooming", autosub3);


        List<String> fSub = Arrays.asList( "Bed Room Furniture","Living Room Furniture","Study and Office Furniture"
                ,"Dining and Kitchen Furniture","Outdoor Furniture","Kids room Furniture","Storage Furniture");
        subCategoriesMap.put("Furniture", fSub);

        List<String> furnishersub = Arrays.asList(

                "Beds","Mattresses","wardrobes","Collapsible Wardrobes","Dressing Tables","Side Tables"
        );
        subCategoriesMap1.put("Bed Room Furniture", furnishersub);

        List<String> furnishersub1 = Arrays.asList(

                "Sofas","Recliners","TV Units","Shoe Racks","Coffe Tables","Home Temples"
        );
        subCategoriesMap1.put("Living Room Furniture", furnishersub1);

        List<String> furnishersub2 = Arrays.asList(

                "Office and Study Chairs","Office and Study Tables","Portable Laptop Tables"," Bookshelves","Gamming Chairs","Cabinets and Drawers"
        );
        subCategoriesMap1.put("Study and Office Furniture", furnishersub2);

        List<String> furnishersub3 = Arrays.asList(

                "Dinning Set","Kitchen Cabinets","Dinning Tables","Kitchen Trolley","Dinning  Chairs","Bar Stools Chairs"
        );
        subCategoriesMap1.put("Dining and Kitchen Furniture", furnishersub3);

        List<String> furnishersub4 = Arrays.asList(

                "Outdoor Chairs","Outdoor Sets","Hammock Swings"
        );
        subCategoriesMap1.put("Outdoor Furniture", furnishersub4);

        List<String> furnishersub5 = Arrays.asList(

                "Kids Bean Bags","Kids Tables","Kids Seating"
        );
        subCategoriesMap1.put("Kids room Furniture", furnishersub5);

        List<String> furnishersub6 = Arrays.asList(

                "Collapsible Wardrobes","Wardrobes","Bookshelves","Cabinet and Drawers","Kitchen Cabinets"
        );
        subCategoriesMap1.put("Storage Furniture", furnishersub6);


        // Listen for selection in categorySpinner
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCategory = scategory[position];
                if (selectedCategory.equals("Fashion")) {
                    updateSubSpinner1("Fashion");
                }
              else  if (selectedCategory.equals("Electronics")) {
                    updateSubSpinner1("Electronics");
                }

                else  if (selectedCategory.equals("Beauty and Wellness")) {
                    updateSubSpinner1("Beauty and Wellness");
                }
                else  if (selectedCategory.equals("Home and Kitchen")) {
                    updateSubSpinner1("Home and Kitchen");
                }
                else  if (selectedCategory.equals("Toys, Baby and Books")) {
                    updateSubSpinner1("Toys, Baby and Books");
                }
                else  if (selectedCategory.equals("Sports and Fitness")) {
                    updateSubSpinner1("Sports and Fitness");
                }
                else  if (selectedCategory.equals("Auto Accessories")) {
                    updateSubSpinner1("Auto Accessories");
                }
                else  if (selectedCategory.equals("Furniture")) {
                    updateSubSpinner1("Furniture");
                }

                else {
                    updateSubSpinner(position);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        // Listen for selection in subSpinner1
        subSpinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedSubCategory = subSpinner1.getSelectedItem().toString();
                if (selectedSubCategory.equals("Mens")) {
                    updateSubSpinnerForSubCategory("Mens");
                } else if(selectedSubCategory.equals("Womens")) {
                    updateSubSpinnerForSubCategory("Womens");
                }
                else if(selectedSubCategory.equals("Kids")) {
                    updateSubSpinnerForSubCategory("Kids");
                }
                else if(selectedSubCategory.equals("Other fashion items")) {
                    updateSubSpinnerForSubCategory("Other fashion items");
                }
                else if(selectedSubCategory.equals("Mobiles")) {
                    updateSubSpinnerForSubCategory("Mobiles");
                }
                else if(selectedSubCategory.equals("Laptops")) {
                    updateSubSpinnerForSubCategory("Laptops");
                }
                else if(selectedSubCategory.equals("Home electronics")) {
                    updateSubSpinnerForSubCategory("Home electronics");
                }
                else if(selectedSubCategory.equals("Other electronic items")) {
                    updateSubSpinnerForSubCategory("Other electronic items");
                }
                else if(selectedSubCategory.equals("Skincare")) {
                    updateSubSpinnerForSubCategory("Skincare");
                }
                else if(selectedSubCategory.equals("Haircare")) {
                    updateSubSpinnerForSubCategory("Haircare");
                }
                else if(selectedSubCategory.equals("Fragrance")) {
                    updateSubSpinnerForSubCategory("Fragrance");
                }
                else if(selectedSubCategory.equals("Beauty")) {
                    updateSubSpinnerForSubCategory("Beauty");
                }
                else if(selectedSubCategory.equals("Daily Essentials")) {
                    updateSubSpinnerForSubCategory("Daily Essentials");
                }
                else if(selectedSubCategory.equals("Home Furnishings")) {
                    updateSubSpinnerForSubCategory("Home Furnishings");
                }
                else if(selectedSubCategory.equals("Home improvement Tools")) {
                    updateSubSpinnerForSubCategory("Home improvement Tools");
                }
                else if(selectedSubCategory.equals("Kitchen items")) {
                    updateSubSpinnerForSubCategory("Kitchen items");
                }
                else if(selectedSubCategory.equals("Decor and lighting")) {
                    updateSubSpinnerForSubCategory("Decor and lighting");
                }
                else if(selectedSubCategory.equals("Toys")) {
                    updateSubSpinnerForSubCategory("Toys");
                }
                else if(selectedSubCategory.equals("School Supplies")) {
                    updateSubSpinnerForSubCategory("School Supplies");
                }
                else if(selectedSubCategory.equals("Pens")) {
                    updateSubSpinnerForSubCategory("Pens");
                }
                else if(selectedSubCategory.equals("Diaries and noteboooks")) {
                    updateSubSpinnerForSubCategory("Diaries and noteboooks");
                }
                else if(selectedSubCategory.equals("Art Supplies")) {
                    updateSubSpinnerForSubCategory("Art Supplies");
                }
                else if(selectedSubCategory.equals("Calculator")) {
                    updateSubSpinnerForSubCategory("Calculator");
                }
                else if(selectedSubCategory.equals("Desk Organizer")) {
                    updateSubSpinnerForSubCategory("Desk Organizer");
                }
                else if(selectedSubCategory.equals("sports")) {
                    updateSubSpinnerForSubCategory("sports");
                }
                else if(selectedSubCategory.equals("Fitness")) {
                    updateSubSpinnerForSubCategory("Fitness");
                }
                else if(selectedSubCategory.equals("Nutrition and helthcare")) {
                    updateSubSpinnerForSubCategory("Nutrition and helthcare");
                }
                else if(selectedSubCategory.equals("Bike accessories")) {
                    updateSubSpinnerForSubCategory("Bike accessories");
                }
                else if(selectedSubCategory.equals("Car accessories")) {
                    updateSubSpinnerForSubCategory("Car accessories");
                }
                else if(selectedSubCategory.equals("Tyers and engin oil")) {
                    updateSubSpinnerForSubCategory("Tyers and engin oil");
                }

                else if(selectedSubCategory.equals("Cleaning and Grooming")) {
                    updateSubSpinnerForSubCategory("Cleaning and Grooming");
                }
                else if(selectedSubCategory.equals("Bed Room Furniture")) {
                    updateSubSpinnerForSubCategory("Bed Room Furniture");
                }

                else if(selectedSubCategory.equals("Living Room Furniture")) {
                    updateSubSpinnerForSubCategory("Living Room Furniture");
                }
                else if(selectedSubCategory.equals("Study and Office Furniture")) {
                    updateSubSpinnerForSubCategory("Study and Office Furniture");
                }
                else if(selectedSubCategory.equals("Dining and Kitchen Furniture")) {
                    updateSubSpinnerForSubCategory("Dining and Kitchen Furniture");
                }

                else if(selectedSubCategory.equals("Outdoor Furniture")) {
                    updateSubSpinnerForSubCategory("Outdoor Furniture");
                }

                else if(selectedSubCategory.equals("Kids room Furniture")) {
                    updateSubSpinnerForSubCategory("Kids room Furniture");
                }
                else if(selectedSubCategory.equals("Storage Furniture")) {
                    updateSubSpinnerForSubCategory("Storage Furniture");
                }





                else {
                    // Handle other selections like Womens, Kids if needed
                    subSpinner.setAdapter(null); // Clear subSpinner
                }

            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        // Dummy session data retrieval
        HashMap<String, String> data = new SessionManager(this).getUserDetails();
        sname = data.get("shopname");
        sid = data.get("id");
        sphone = data.get("shopphonenumber");

        upload.setOnClickListener(view -> {
            view.startAnimation(buttonClickAnimation);
            upload();
        });
    }

    // Function to update subSpinner1 for "Fashion"
    private void updateSubSpinner1(String category) {
        List<String> subCategories = subCategoriesMap.get(category);

        ArrayAdapter<String> subAdapter1 = (ArrayAdapter<String>) subSpinner1.getAdapter();
        subAdapter1.clear();

        if (subCategories != null) {
            subAdapter1.addAll(subCategories);
        } else {
            subAdapter1.add("No Sub-categories");
        }
        subAdapter1.notifyDataSetChanged();
    }

    // Function to update subSpinner based on subSpinner1's selection ("Mens")
    private void updateSubSpinnerForSubCategory(String subCategory) {
        List<String> subCategories = subCategoriesMap1.get(subCategory);

        ArrayAdapter<String> subAdapter = (ArrayAdapter<String>) subSpinner.getAdapter();
        subAdapter.clear();

        if (subCategories != null) {
            subAdapter.addAll(subCategories);
        } else {
            subAdapter.add("No Sub-categories");
        }
        subAdapter.notifyDataSetChanged();
    }

    private void updateSubSpinner(int position) {
        String selectedCategory = scategory[position];
        List<String> subCategories = subCategoriesMap.get(selectedCategory);

        ArrayAdapter<String> subAdapter = (ArrayAdapter<String>) subSpinner.getAdapter();
        subAdapter.clear();

        if (subCategories != null) {
            subAdapter.addAll(subCategories);
        } else {
            subAdapter.add("No Sub-categories");
        }
        subAdapter.notifyDataSetChanged();
    }

    private void upload() {
        sproductname = productname.getText().toString();
        sprice = price.getText().toString();
        shoppack = pak.getText().toString();
        sdes = des.getText().toString();
        spac = pac.getText().toString();
        type = subSpinner.getSelectedItem().toString();
        cate = categorySpinner.getSelectedItem().toString();
        type1 = subSpinner1.getSelectedItem().toString();

        if (TextUtils.isEmpty(sproductname)) {
            productname.setError("required");
            productname.requestFocus();
            return;
        } else if (TextUtils.isEmpty(sprice)) {
            price.setError("required");
            price.requestFocus();
            return;
        }
       else if (TextUtils.isEmpty(shoppack)) {
            pak.setError("required");
            pak.requestFocus();
            return;
        }else if (TextUtils.isEmpty(spac)) {
            pac.setError("required");
            pac.requestFocus();
            return;
        } else if (TextUtils.isEmpty(sdes)) {
            des.setError("required");
            des.requestFocus();
            return;
        }

        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, 1);
    }
    @SuppressLint("Range")
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            // Get the Uri of the selected file
            Uri uri = data.getData();
            String uriString = uri.toString();
            File myFile = new File(uriString);
            String path = myFile.getAbsolutePath();
            String displayName = null;
            if (uriString.startsWith("content://")) {
                Cursor cursor = null;
                try {
                    cursor =getContentResolver().query(uri, null, null, null, null);
                    if (cursor != null && cursor.moveToFirst()) {
                        displayName = cursor.getString(cursor.getColumnIndex( OpenableColumns.DISPLAY_NAME));
                        Log.d("nam  ",displayName);

                        uploadPDF(displayName,uri);
                    }
                } finally {
                    cursor.close();
                }
            } else if (uriString.startsWith("file://")) {
                displayName = myFile.getName();
                Log.d("nameeeee>>>>  ",displayName);
            }
        }

        super.onActivityResult(requestCode, resultCode, data);

    }

    private void uploadPDF(final String pdfname, Uri pdffile) {
        InputStream iStream = null;
        try {

            iStream = getContentResolver().openInputStream(pdffile);
            final byte[] inputData = getBytes(iStream);

            showSimpleProgressDialog(Additem.this, null, "Uploading image", false);
            VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest( Request.Method.POST, url,
                    new Response.Listener<NetworkResponse>() {
                        @Override
                        public void onResponse(NetworkResponse response) {
                            removeSimpleProgressDialog();
                            Log.d("res",new String(response.data));
                            rQueue.getCache().clear();
                            try {

                                JSONObject jsonObject = new JSONObject(new String(response.data));

                                jsonObject.toString().replace("\\\\","");

                                status = jsonObject.getString("status");
                                message = jsonObject.getString("message");

                                if (status.equals("1")) {
                                    //    Toast.makeText(this, " successfully", Toast.LENGTH_SHORT).show();
                                    Toast.makeText(Additem.this, "successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(Additem.this, Sellerhome.class);
                                    startActivity(intent);
                                }
                                else {
                                    Toast.makeText(Additem.this, message, Toast.LENGTH_SHORT).show();
                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            removeSimpleProgressDialog();
                            Toast.makeText(Additem.this, error.toString(), Toast.LENGTH_LONG).show();
                        }
                    }) {

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("shopid", sid);
                    params.put("shopname", sname);
                    params.put("shopnumber", sphone);
                    params.put("shopcategory", cate);
                    params.put("category", type1);
                    params.put("availableproducts", type);
                    params.put("productname", sproductname);
                    params.put("price", sprice);
                    params.put("package", shoppack);
                    params.put("location", spac);
                    params.put("description", sdes);


                    return params;





                }

                /*
                 *pass files using below method
                 * */
                @Override
                protected Map<String, DataPart> getByteData() {
                    Map<String, DataPart> params = new HashMap<>();
                    params.put("filename", new DataPart(pdfname ,inputData));
                    return params;
                }
            };


            volleyMultipartRequest.setRetryPolicy(new DefaultRetryPolicy(
                    0,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

            rQueue = Volley.newRequestQueue(this);
            rQueue.add(volleyMultipartRequest);



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }



    public byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }


    public  void removeSimpleProgressDialog() {
        try {
            if (mProgressDialog != null) {
                if (mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                    mProgressDialog = null;
                }
            }
        } catch (IllegalArgumentException ie) {
            Log.e("Log", "inside catch IllegalArgumentException");
            ie.printStackTrace();
        } catch (RuntimeException re) {
            Log.e("Log", "inside catch RuntimeException");
            re.printStackTrace();
        } catch (Exception e) {
            Log.e("Log", "Inside catch Exception");
            e.printStackTrace();
        }

    }

    public void showSimpleProgressDialog(Context context, String title,
                                         String msg, boolean isCancelable) {
        try {
            if (mProgressDialog == null) {
                mProgressDialog = ProgressDialog.show( context, title, msg );
                mProgressDialog.setCancelable( isCancelable );
            }
            if (!mProgressDialog.isShowing()) {
                mProgressDialog.show();
            }
        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();
        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

