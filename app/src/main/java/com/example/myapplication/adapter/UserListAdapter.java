package com.example.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.UserPostActivity;
import com.example.myapplication.response.Datum;
import java.util.List;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;



public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.MyViewHolder> {

    private List<Datum> moviesList;
    Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName, tvEmail,tvGender;
        ImageView imgGender,imgStatus;

        LinearLayout linearInfo;
        public MyViewHolder(View view) {
            super(view);

            tvName = (TextView) view.findViewById(R.id.tvName);
            tvEmail = (TextView) view.findViewById(R.id.tvEmail);
            imgGender = (ImageView) view.findViewById(R.id.imgGender);
            imgStatus = (ImageView) view.findViewById(R.id.imgStatus);
            linearInfo=(LinearLayout) view.findViewById(R.id.linearInfo);
        }
    }


    public UserListAdapter(List<Datum> moviesList,Context mContext) {
        this.moviesList = moviesList;
        this.mContext=mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Datum movie = moviesList.get(position);



        holder.tvName.setText(movie.getName());
        holder.tvEmail.setText(movie.getEmail());
      //  holder.tvGender.setText(movie.getGender());

        if(movie.getStatus().equalsIgnoreCase("Active")){
            holder.imgStatus.setImageDrawable(ContextCompat.getDrawable(mContext,R.drawable.ic_filled_circle_online));
        }else{
            holder.imgStatus.setImageDrawable(ContextCompat.getDrawable(mContext,R.drawable.ic_filled_circle_offline));
        }


        if(movie.getGender().equalsIgnoreCase("Female")){
            holder.imgGender.setImageDrawable(ContextCompat.getDrawable(mContext,R.drawable.ic_female));
        }else{
            holder.imgGender.setImageDrawable(ContextCompat.getDrawable(mContext,R.drawable.ic_male_svg));
        }

        holder.linearInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Datum datum=moviesList.get(position);
                Intent intent=new Intent(mContext, UserPostActivity.class);
                intent.putExtra("id",datum.getId()+"");
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}