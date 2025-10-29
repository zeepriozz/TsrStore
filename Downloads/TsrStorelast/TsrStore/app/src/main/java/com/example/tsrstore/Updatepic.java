package com.example.tsrstore;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Updatepic extends AppCompatActivity {
    ImageView update;
    String supdate,sphone,status,message;
    private RequestQueue rQueue;

    private static ProgressDialog mProgressDialog;
    String url=config.baseurl+"updateprofile.php";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_updatepic);
        update=findViewById(R.id.prof);


        HashMap<String,String> map=new SessionManager(Updatepic.this).getUserDetails();

        supdate=map.get("logo");
        sphone=map.get("shopphonenumber");


        Picasso.get().load(config.imgurl + supdate).into(update);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatepic();
            }
        });

    }

    private void updatepic() {
        Intent intent = new Intent();
        intent.setAction( Intent.ACTION_GET_CONTENT );
        intent.setType( "image/*" );
        //        intent.setType("video/mp4");
        //        intent.setType("application/pdf");
        startActivityForResult( intent, 1 );
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

            showSimpleProgressDialog(getApplicationContext(), null, "Uploading image", false);

            VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest( Request.Method.POST,url,
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
                                if (status.equals("2")) {
                                    Toast.makeText(Updatepic.this, message, Toast.LENGTH_SHORT).show();

                                }

                                if (status.equals("1")) {
                                    Toast.makeText(Updatepic.this, message, Toast.LENGTH_SHORT).show();
                                    Intent object=new Intent(Updatepic.this, Home.class);
                                    startActivity(object);
                                }
                                else {
                                    Toast.makeText(Updatepic.this, message, Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                        }
                    }) {
                @Override
                protected Map<String, DataPart> getByteData() {
                    Map<String, DataPart> params = new HashMap<>();
                    params.put("filename", new DataPart(pdfname ,inputData));
                    return params;
                }
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> ma = new HashMap<>();
//                    ma.put("username", s1);
//                    ma.put("phonenumber", s2 );
                    ma.put("shopphonenumber", sphone);

                    return ma;


                }

                /*
                 *pass files using below method
                 * */

            };


            volleyMultipartRequest.setRetryPolicy(new DefaultRetryPolicy(
                    0,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

            rQueue = Volley.newRequestQueue(Updatepic.this);
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



