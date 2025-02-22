package com.mountreachsolution.rescuewings.SAVIOUR;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
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

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class AllSend extends AppCompatActivity {
    String username, location, details, nvUsername, name, mobile, image, resend,id;
    ImageView ivImage;
    TextView tvName, tvMobileNo, tvUsername, tvSendBy, tvLocation, tvDetails;
    AppCompatButton btnAccept, btnReject;

    String status1,status2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_all_send);
        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.blue));
        getWindow().setNavigationBarColor(ContextCompat.getColor(this,R.color.white));

        ivImage = findViewById(R.id.ivImage);
        tvName = findViewById(R.id.tvName);
        tvMobileNo = findViewById(R.id.tvMobileno);
        tvUsername = findViewById(R.id.tvUsername);
        tvSendBy = findViewById(R.id.tvSendBY);
        tvLocation = findViewById(R.id.tvLocation);
        tvDetails = findViewById(R.id.tvDetails);
        btnAccept = findViewById(R.id.btnAccept);
        btnReject = findViewById(R.id.btnReject);

        username = getIntent().getStringExtra("username");
        location = getIntent().getStringExtra("loc");
        details = getIntent().getStringExtra("deta");
        nvUsername = getIntent().getStringExtra("nvusername");
        name = getIntent().getStringExtra("name");
        mobile = getIntent().getStringExtra("mobile");
        image = getIntent().getStringExtra("image");
        resend = getIntent().getStringExtra("resend");
        id = getIntent().getStringExtra("id");

        tvName.setText(name);
        tvMobileNo.setText(mobile);
        tvUsername.setText(nvUsername);
        tvSendBy.setText(username);
        tvLocation.setText(location);
        tvDetails.setText(details);

        Glide.with(AllSend.this)
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

    }
    private void PushDataRe() {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("svusername", resend);
        params.put("location", location);
        params.put("details", details);
        params.put("nvusername", nvUsername);
        params.put("name", name);
        params.put("mobileno", mobile);
        params.put("image", image);
        params.put("status", status2);
        client.post(urls.AddReqestr,params,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    String status=response.getString("success");
                    if (status.equals("1")){
                        Toast.makeText(AllSend.this, "Request Rejected", Toast.LENGTH_SHORT).show();
                        removePost(id);
                    }else{
                        Toast.makeText(AllSend.this, "Server Error", Toast.LENGTH_SHORT).show();
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
        params.put("svusername", resend);
        params.put("location", location);
        params.put("details", details);
        params.put("nvusername", nvUsername);
        params.put("name", name);
        params.put("mobileno", mobile);
        params.put("image", image);
        params.put("status", status1);
        client.post(urls.AddReqestr,params,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    String status=response.getString("success");
                    if (status.equals("1")){
                        Toast.makeText(AllSend.this, "Request Accepted", Toast.LENGTH_SHORT).show();
                        removePost(id);
                    }else{
                        Toast.makeText(AllSend.this, "Server Error", Toast.LENGTH_SHORT).show();
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

        client.post(urls.removedata, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    String status = response.getString("status");
                    if (status.equals("success")) {
                        Intent i = new Intent(AllSend.this,AllRequest.class);
                        startActivity(i);
                        finish();

                        // Remove item from list and update RecyclerVie
                    } else {
                        Toast.makeText(AllSend.this, "Failed to Remove", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Toast.makeText(AllSend.this, "Error: Unable to Remove Diet", Toast.LENGTH_SHORT).show();
            }
        });
    }

}