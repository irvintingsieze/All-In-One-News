package com.project.allinonenews;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class NewsViewAdapter extends RecyclerView.Adapter<NewsViewAdapter.ViewHolderNews> implements Filterable {

    private Context context;
    public ArrayList<NewsEntity> newsList, filterList;
    private FilterList filter;

    public NewsViewAdapter(Context context, ArrayList<NewsEntity> newsList){
        this.context = context;
        this.newsList = newsList;
        this.filterList = newsList;
    }

    @NonNull
    @Override
    public ViewHolderNews onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.news_provider_format, parent, false);
        return new ViewHolderNews(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderNews holder, int position) {
        NewsEntity newsEntity = newsList.get(position);
        final String id = newsEntity.getId();
        final String name = newsEntity.getName();
        final String description = newsEntity.getDescription();
        String category = newsEntity.getCategory();
        final String categories = category.substring(0, 1).toUpperCase() + category.substring(1);
        holder.mName.setText(name);
        holder.mDescription.setText(description);
        holder.mCategory.setText("Category: " + categories);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, NewsProviderDetails.class);
                i.putExtra("id", id);
                i.putExtra("name", name);
                i.putExtra("description", description);
                i.putExtra("category", categories);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    @Override
    public Filter getFilter() {
        if (filter == null){
            filter = new FilterList(this, filterList);
        }
        return filter;
    }


    class ViewHolderNews extends RecyclerView.ViewHolder{
        TextView mName;
        TextView mDescription;
        TextView mCategory;

        public ViewHolderNews(@NonNull View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.mName);
            mDescription = itemView.findViewById(R.id.mDescription);
            mCategory = itemView.findViewById(R.id.mCategory);

        }
    }
}
