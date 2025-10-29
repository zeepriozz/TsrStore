package com.example.tsrstore.ui;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tsrstore.Home;
import com.example.tsrstore.SessionManagerUser;
import com.example.tsrstore.Userlogin;
import com.example.tsrstore.config;
import com.example.tsrstore.R;
import com.example.tsrstore.feedback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ProfileFragment extends Fragment {

    EditText uname, umail, uphone, upass;
    Button update;

    TextView ulog,feed;

    String pregname, pregmail, pregphone, pregpass, url = config.baseurl + "profileupdation.php";
    String ppid, ppregname, ppregmail, ppregphone, ppregpass, status, message;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        uname = v.findViewById(R.id.usname);
        umail = v.findViewById(R.id.usemail);
        uphone = v.findViewById(R.id.usphone);
        upass = v.findViewById(R.id.uspass);
        update = v.findViewById(R.id.usbutton);
        feed = v.findViewById(R.id.feed);
        ulog = v.findViewById(R.id.logout);

        HashMap<String, String> data = new SessionManagerUser(getActivity()).getUserDetails();

        ppid = data.get("id");
        pregname = data.get("username");
        pregmail = data.get("email");
        pregphone = data.get("phone");
        pregpass = data.get("Address");

        uname.setText(pregname);
        umail.setText(pregmail);
        uphone.setText(pregphone);
        upass.setText(pregpass);
        feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent t=new Intent(getActivity(), feedback.class);
                startActivity(t);
            }
        });
        ulog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                logout();
            }
        });


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });
        return v;
    }

    private void submit() {
        ppregname = uname.getText().toString();
        ppregmail = umail.getText().toString();
        ppregphone = uphone.getText().toString();
        ppregpass = upass.getText().toString();

        // Check if input fields are empty
        if (TextUtils.isEmpty(ppregname)) {
            uname.requestFocus();
            uname.setError("required field");
            return;
        }
        if (TextUtils.isEmpty(ppregmail)) {
            umail.requestFocus();
            umail.setError("required field");
            return;
        }
        if (TextUtils.isEmpty(ppregphone)) {
            uphone.requestFocus();
            uphone.setError("required field");
            return;
        }
        if (TextUtils.isEmpty(ppregpass)) {
            upass.requestFocus();
            upass.setError("required field");
            return;
        }

        StringRequest str = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getActivity(), response, Toast.LENGTH_SHORT).show();
                try {
                    JSONObject json = new JSONObject(response);
                    status = json.getString("status");
                    message = json.getString("message");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if ("0".equals(status)) {
                    Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Update successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getActivity(), Home.class));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id", ppid);
                params.put("username", ppregname);
                params.put("email", ppregmail);
                params.put("phone", ppregphone);
                params.put("Address", ppregpass);
                return params;
            }
        };

        RequestQueue rq = Volley.newRequestQueue(getActivity());
        rq.add(str);
    }

    private void logout() {
        new AlertDialog.Builder(getActivity())
                .setTitle("Logout")
                .setMessage("Are you sure you want to logout from your account?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(getActivity(), Userlogin.class));
                        getActivity().finish(); // Use getActivity() to finish the activity
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Clean up any references if necessary
    }
}
