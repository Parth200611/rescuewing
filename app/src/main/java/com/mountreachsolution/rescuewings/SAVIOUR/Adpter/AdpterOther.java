package com.mountreachsolution.rescuewings.SAVIOUR.Adpter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.mountreachsolution.rescuewings.R;
import com.mountreachsolution.rescuewings.SAVIOUR.POJO.POJOOther;
import com.mountreachsolution.rescuewings.SAVIOUR.RequestDetails;
import com.mountreachsolution.rescuewings.urls;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import cz.msebera.android.httpclient.Header;
import de.hdodenhof.circleimageview.CircleImageView;

public class AdpterOther extends RecyclerView.Adapter<AdpterOther.ViewHolder> {
    List<POJOOther>pojoOthers;
    Activity activity;
    String username,name,location,details,image,svUsername,mobileno,strUsername;

    public AdpterOther(List<POJOOther> pojoOthers, Activity activity) {
        this.pojoOthers = pojoOthers;
        this.activity = activity;
    }

    @NonNull
    @Override
    public AdpterOther.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.user,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdpterOther.ViewHolder holder, int position) {
        POJOOther user=pojoOthers.get(position);
        holder.tvName.setText(user.getName());
        holder.tvUsername.setText(user.getUsername());
        holder.tvGender.setText(user.getGender());
        Glide.with(activity)
                .load(urls.address + "images/"+user.getImage())
                .skipMemoryCache(true)
                .error(R.drawable.baseline_person_24)// Resize the image to 800x800 pixels
                .into(holder.cvImage);

        strUsername=user.getUsername();

        holder.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchDataFromSharedPreferences();
            }
        });

    }
    private void fetchDataFromSharedPreferences() {
        SharedPreferences sharedPreferences = activity.getSharedPreferences("senddata", Context.MODE_PRIVATE);

         username = sharedPreferences.getString("username", "No Data");
         location = sharedPreferences.getString("location", "No Data");
         details = sharedPreferences.getString("details", "No Data");
         image = sharedPreferences.getString("image", "No Data");
         name = sharedPreferences.getString("name", "No Data");
         mobileno = sharedPreferences.getString("mobileno", "No Data");
         svUsername = sharedPreferences.getString("muUsername", "No Data");

         pushdata();

    }

    private void pushdata() {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("svusername", svUsername);
        params.put("location", location);
        params.put("details", details);
        params.put("nvusername", username);
        params.put("name", name);
        params.put("mobileno", mobileno);
        params.put("image", image);
        params.put("rqsendto", strUsername);

        client.post(urls.senddata,params,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    String status=response.getString("success");
                    if (status.equals("1")){
                        Toast.makeText(activity, "Request Send", Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(activity, "Server Error", Toast.LENGTH_SHORT).show();
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

    @Override
    public int getItemCount() {
        return pojoOthers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView cvImage;
        TextView tvName, tvUsername, tvGender;
        Button btnSend;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cvImage = itemView.findViewById(R.id.cvImage);
            tvName = itemView.findViewById(R.id.tvName);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            tvGender = itemView.findViewById(R.id.tvGender);
            btnSend = itemView.findViewById(R.id.btnSend);
        }
    }
}
