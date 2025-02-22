package com.mountreachsolution.rescuewings.SAVIOUR;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.mountreachsolution.rescuewings.R;
import com.mountreachsolution.rescuewings.urls;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class RequestDetails extends AppCompatActivity {
    ImageView ivImage;
    TextView tvName, tvMobileno, tvLocation, tvDetails,tvUsername;
    Button btnAccept, btnReject,btnSend;
    String id;
    String username,location,details,image,name,mobileno,muUsername;
    String status1,status2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_request_details);
        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.blue));
        getWindow().setNavigationBarColor(ContextCompat.getColor(this,R.color.white));
        id=getIntent().getStringExtra("id");
        username=getIntent().getStringExtra("username");
        location=getIntent().getStringExtra("location");
        details=getIntent().getStringExtra("details");
        image=getIntent().getStringExtra("image");
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        muUsername = sharedPreferences.getString("username", "Guest");

        ivImage = findViewById(R.id.ivImage);
        tvUsername = findViewById(R.id.tvUsername);
        tvName = findViewById(R.id.tvName);
        tvMobileno = findViewById(R.id.tvMobileno);
        tvLocation = findViewById(R.id.tvLocation);
        tvDetails = findViewById(R.id.tvDetails);
        btnAccept = findViewById(R.id.btnAccept);
        btnReject = findViewById(R.id.btnReject);
        btnSend = findViewById(R.id.btnSend);

        tvDetails.setText(details);
        tvLocation.setText(location);
        tvUsername.setText(username);
        Glide.with(RequestDetails.this)
                .load(urls.address + "images/"+image)
                .skipMemoryCache(true)
                .error(R.drawable.baseline_person_24)// Resize the image to 800x800 pixels
                .into(ivImage);
        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                status1 ="Request Accepted";
                PushData();
            }
        });  btnReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                status2 ="Request Rejected";
                PushDataRe();
            }
        });
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDataToSharedPreferences(username,location,details,image,name,mobileno,muUsername);

            }
        });

      getData();

    }
    private void saveDataToSharedPreferences(String username, String location, String details, String image, String name, String mobileno, String muUsername) {
        SharedPreferences sharedPreferences = getSharedPreferences("senddata", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Saving data
        editor.putString("username", username);
        editor.putString("location", location);
        editor.putString("details", details);
        editor.putString("image", image);
        editor.putString("name", name);
        editor.putString("mobileno", mobileno);
        editor.putString("muUsername", muUsername);

        // Apply changes
        editor.apply();
        Toast.makeText(this, "Data Ready to send Another Saviour!", Toast.LENGTH_SHORT).show();
        removePost(id);
    }



    private void PushDataRe() {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("svusername", muUsername);
        params.put("location", location);
        params.put("details", details);
        params.put("nvusername", username);
        params.put("name", name);
        params.put("mobileno", mobileno);
        params.put("image", image);
        params.put("status", status2);
        client.post(urls.AddReqestr,params,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    String status=response.getString("success");
                    if (status.equals("1")){
                        Toast.makeText(RequestDetails.this, "Request Rejected", Toast.LENGTH_SHORT).show();
                        removePost(id);
                    }else{
                        Toast.makeText(RequestDetails.this, "Server Error", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }

    private void PushData() {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("svusername", muUsername);
        params.put("location", location);
        params.put("details", details);
        params.put("nvusername", username);
        params.put("name", name);
        params.put("mobileno", mobileno);
        params.put("image", image);
        params.put("status", status1);
        client.post(urls.AddReqestr,params,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    String status=response.getString("success");
                    if (status.equals("1")){
                        Toast.makeText(RequestDetails.this, "Request Accepted", Toast.LENGTH_SHORT).show();
                        removePost(id);
                    }else{
                        Toast.makeText(RequestDetails.this, "Server Error", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }

    private void removePost(String id) {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("id", id);

        client.post(urls.removeReqest, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    String status = response.getString("status");
                    if (status.equals("success")) {
                        Intent i = new Intent(RequestDetails.this,SaviourHomepage.class);
                        startActivity(i);
                        finish();

                        // Remove item from list and update RecyclerVie
                    } else {
                        Toast.makeText(RequestDetails.this, "Failed to Remove", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Toast.makeText(RequestDetails.this, "Error: Unable to Remove Diet", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getData() {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        params.put("username",username);
        client.post(urls.Profildata,params,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    JSONArray jsonArray = response.getJSONArray("getProfildata");
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                         name = jsonObject.getString("name");
                         mobileno=jsonObject.getString("mobile");
                        String gender=jsonObject.getString("gender");
                        String email=jsonObject.getString("email");
                        String image=jsonObject.getString("image");
                        String usertype=jsonObject.getString("usertype");
                        tvName.setText(name);
                        tvMobileno.setText(mobileno);

                    }

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }
}