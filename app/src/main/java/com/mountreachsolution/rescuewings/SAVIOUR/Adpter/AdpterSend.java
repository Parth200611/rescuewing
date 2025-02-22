package com.mountreachsolution.rescuewings.SAVIOUR.Adpter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mountreachsolution.rescuewings.R;
import com.mountreachsolution.rescuewings.SAVIOUR.AllSend;
import com.mountreachsolution.rescuewings.SAVIOUR.POJO.POJOSend;
import com.mountreachsolution.rescuewings.urls;

import java.util.List;

public class AdpterSend extends RecyclerView.Adapter<AdpterSend.ViewHolder> {
    List<POJOSend> pojoSends;
    Activity activity;

    public AdpterSend(List<POJOSend> pojoSends, Activity activity) {
        this.pojoSends = pojoSends;
        this.activity = activity;
    }

    @NonNull
    @Override
    public AdpterSend.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.send,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdpterSend.ViewHolder holder, int position) {
        POJOSend user = pojoSends.get(position);
        holder.tvUsername.setText(user.getNvusername());
        holder.tvLocation.setText(user.getLoc());
        holder.tvDetails.setText(user.getDeta());
        holder.tvSendBY.setText(user.getUsername());

        Glide.with(activity)
                .load(urls.address + "images/" + user.getImage())
                .skipMemoryCache(true)
                .error(R.drawable.baseline_camera_alt_24)
                .into(holder.ivImage);
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(activity, AllSend.class);
                i.putExtra("username",user.getUsername());
                i.putExtra("loc",user.getLoc());
                i.putExtra("deta",user.getDeta());
                i.putExtra("nvusername",user.getNvusername());
                i.putExtra("name",user.getName());
                i.putExtra("mobile",user.getMobile());
                i.putExtra("image",user.getImage());
                i.putExtra("resend",user.getResend());
                i.putExtra("id",user.getId());
                activity.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return pojoSends.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImage;
        TextView tvUsername, tvLocation, tvDetails, tvSendBY;
        CardView card;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.ivImage);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            tvLocation = itemView.findViewById(R.id.tvLocation);
            tvDetails = itemView.findViewById(R.id.tvDetails);
            tvSendBY = itemView.findViewById(R.id.tvSendBY);
            card = itemView.findViewById(R.id.card);
        }
    }
}
