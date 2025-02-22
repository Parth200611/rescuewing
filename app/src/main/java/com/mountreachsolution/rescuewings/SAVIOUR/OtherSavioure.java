package com.mountreachsolution.rescuewings.SAVIOUR;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.mountreachsolution.rescuewings.R;
import com.mountreachsolution.rescuewings.SAVIOUR.Adpter.AdpterOther;
import com.mountreachsolution.rescuewings.SAVIOUR.POJO.POJOOther;
import com.mountreachsolution.rescuewings.urls;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class OtherSavioure extends Fragment {
    RecyclerView rvList;
    TextView tvNoRequest;
    List<POJOOther>pojoOthers;
    AdpterOther adpterOther;
    String username,usertype;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_other_savioure, container, false);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("UserPrefs", MODE_PRIVATE);
        username = sharedPreferences.getString("username", "Guest");
        usertype="Saviour";
        rvList = view.findViewById(R.id.rvLsit);
        tvNoRequest = view.findViewById(R.id.tvNoRequest);
        rvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        pojoOthers=new ArrayList<>();
        getData(usertype,username);


        return view;
    }

    private void getData(String usertype, String username) {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        params.put("usertype",usertype);
        params.put("username",username);
        client.post(urls.getAllSv,params,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    JSONArray jsonArray =response.getJSONArray("getProfildata");
                    if (jsonArray.length()==0){
                        rvList.setVisibility(View.GONE);
                        tvNoRequest.setVisibility(View.VISIBLE);
                    }for (int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String id=jsonObject.getString("id");
                        String name=jsonObject.getString("name");
                        String mobileno=jsonObject.getString("mobile");
                        String gender=jsonObject.getString("gender");
                        String email=jsonObject.getString("email");
                        String image=jsonObject.getString("image");
                        String usertype=jsonObject.getString("usertype");
                        String pusername=jsonObject.getString("username");
                        String password=jsonObject.getString("password");
                        pojoOthers.add(new POJOOther(id,name,mobileno,email,gender,pusername,password,usertype,image));
                    }
                    adpterOther=new AdpterOther(pojoOthers,getActivity());
                    rvList.setAdapter(adpterOther);
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