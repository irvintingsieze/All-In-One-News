package com.project.allinonenews;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class NewsDetailsAdapter extends RecyclerView.Adapter<NewsDetailsAdapter.ViewHolderNewsDetails>{

    private Context mContext;
    private ArrayList<NewsDetailsEntity> newsDetailsEntityArrayList;
    RequestOptions option;

    public NewsDetailsAdapter (Context mContext, ArrayList<NewsDetailsEntity> newsDetailsEntityArrayList){
        this.mContext = mContext;
        this.newsDetailsEntityArrayList = newsDetailsEntityArrayList;
        option=new RequestOptions().centerCrop().placeholder(R.drawable.news_placeholder).error(R.drawable.news_placeholder);

    }

    @NonNull
    @Override
    public ViewHolderNewsDetails onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflater
        View v = LayoutInflater.from(mContext).inflate(R.layout.news_details,parent, false);

        return new ViewHolderNewsDetails(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderNewsDetails holder, final int position) {

        holder.title.setText(newsDetailsEntityArrayList.get(position).getTitle());
        holder.description.setText(newsDetailsEntityArrayList.get(position).getTitle());
        holder.date.setText(newsDetailsEntityArrayList.get(position).getPublishedAt());

        Glide.with(mContext).load(newsDetailsEntityArrayList.get(position).getUrlToImage()).apply(option).into(holder.detailsImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, NewsInformationActivity.class);
                i.putExtra("url", newsDetailsEntityArrayList.get(position).getUrl());
                mContext.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsDetailsEntityArrayList.size();
    }


    class ViewHolderNewsDetails extends RecyclerView.ViewHolder{

        TextView title, description, date;
        ImageView detailsImage;

        public ViewHolderNewsDetails(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            detailsImage = itemView.findViewById(R.id.newsImage);
            description = itemView.findViewById(R.id.descriptionHeader);
            date = itemView.findViewById(R.id.date);
        }
    }
}
