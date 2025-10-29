package com.example.tsrstore;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Sellerprof extends AppCompatActivity {
    EditText regname, regphone,regpass;
    Button update;
    ImageView proimage,logimg;
    TextView logtext;
    private RequestQueue rQueue;
    String sname,sno,spass,simg,url= config.baseurl+"profile.php",status,message;
    String ppregname,ppregphone,ppregpass,ppid;

    private static ProgressDialog mProgressDialog;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sellerprof);
        regname = findViewById(R.id.registername1);
        logimg=findViewById(R.id.sellerlogout1);
        logtext=findViewById(R.id.sellerlogout);
        regphone = findViewById(R.id.registernumber1);

        regpass = findViewById(R.id.registerpass1);

        update = findViewById(R.id.registerbutton1);
        proimage = findViewById(R.id.profilepic);
//        updateimg=findViewById(R.id.updateimg);



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        HashMap<String, String> data = new SessionManager(this).getUserDetails();


        ppid = data.get("id");
        sname = data.get("shopname");
        sno = data.get("shopphonenumber");
        spass = data.get("password");
        simg = data.get("logo");


        regname.setText(sname);
        regphone.setText(sno);
        regpass.setText(spass);
        Picasso.get().load(config.imgurl + simg).into(proimage);

        logtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sellerlogout();
            }

        });
        logimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sellerlogout();

            }
        });
        proimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inten=new Intent(Sellerprof.this,Updatepic.class);
                startActivity(inten);
            }
        });

//        updateimg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent in=new Intent(Sellerprof.this,Updatepic.class);
//                startActivity(in);
//            }
//        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                submit();
            }
        });

    }


    private void submit() {
        ppregname=regname.getText().toString();
        ppregphone=regphone.getText().toString();
        ppregpass=regpass.getText().toString();


        if (TextUtils.isEmpty(ppregname)){
            regname.requestFocus();
            regname.setError("required field");
            return;
        }


        if (TextUtils.isEmpty(ppregphone)){
            regphone.requestFocus();
            regphone.setError("required field");
            return;
        }

        if (TextUtils.isEmpty(ppregpass)){
            regpass.requestFocus();
            regpass.setError("required field");
            return;
        }
        StringRequest str = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(Sellerprof.this, response, Toast.LENGTH_SHORT).show();


                try {
                    JSONObject json = new JSONObject(response);
                    status = json.getString("status");
                    message = json.getString("message");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if ("0".equals(status)) {
                    Toast.makeText(Sellerprof.this, message, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Sellerprof.this, "updation successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Sellerprof.this,Sellerhome.class));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(Sellerprof.this, error.toString(), Toast.LENGTH_SHORT).show();

            }
        })

        {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id",ppid);
                params.put("shopname",ppregname);
                params.put("shopphonenumber",ppregphone);
                params.put("password",ppregpass);

                return params;
            }
        };

        RequestQueue rq = Volley.newRequestQueue(Sellerprof.this);
        rq.add(str);
    }
    private void sellerlogout() {

        new AlertDialog.Builder(Sellerprof.this)
                .setTitle("Logout")
                .setMessage("Are you sure you want to logout from your account?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(Sellerprof.this, MainLogin.class));
                        finish();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing
                    }
                })
                .show();
    }

}