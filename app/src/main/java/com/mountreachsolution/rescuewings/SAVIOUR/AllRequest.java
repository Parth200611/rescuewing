package com.mountreachsolution.rescuewings.SAVIOUR;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.mountreachsolution.rescuewings.R;
import com.mountreachsolution.rescuewings.SAVIOUR.Adpter.AdpterHistroey;
import com.mountreachsolution.rescuewings.SAVIOUR.Adpter.AdpterSend;
import com.mountreachsolution.rescuewings.SAVIOUR.POJO.POJOHistory;
import com.mountreachsolution.rescuewings.SAVIOUR.POJO.POJOSend;
import com.mountreachsolution.rescuewings.urls;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class AllRequest extends AppCompatActivity {
    RecyclerView rvList;
    TextView tvNoRequest;
    String username;
    List<POJOSend> pojoSends;
    AdpterSend adpterSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_all_request);
        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.blue));
        getWindow().setNavigationBarColor(ContextCompat.getColor(this,R.color.white));
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        username = sharedPreferences.getString("username", "Guest");
        rvList = findViewById(R.id.rvLsit);
        tvNoRequest = findViewById(R.id.tvNoRequest);
        rvList.setLayoutManager(new LinearLayoutManager(AllRequest.this));
        pojoSends=new ArrayList<>();
        getData(username);

    }

    private void getData(String username) {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("username",username);
        client.post(urls.getSend,params,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    JSONArray jsonArray = response.getJSONArray("getSEnd");
                    if (jsonArray.length()==0){
                        rvList.setVisibility(View.GONE);
                        tvNoRequest.setVisibility(View.VISIBLE);
                    }
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String id=jsonObject.getString("id");
                        String sv=jsonObject.getString("saviorusername");
                        String locatrion=jsonObject.getString("location");
                        String details=jsonObject.getString("details");
                        String nv=jsonObject.getString("needyusername");
                        String name=jsonObject.getString("name");
                        String mobileno=jsonObject.getString("mobileno");
                        String image=jsonObject.getString("image");
                        String rqsendto=jsonObject.getString("rqsendto");
                        pojoSends.add(new POJOSend(id,sv,locatrion,details,nv,name,mobileno,image,rqsendto));
                    }
                    adpterSend=new AdpterSend(pojoSends,AllRequest.this);
                    rvList.setAdapter(adpterSend);
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
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // Navigate to SaviourHome activity
        Intent intent = new Intent(AllRequest.this, SaviourHomepage.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

}