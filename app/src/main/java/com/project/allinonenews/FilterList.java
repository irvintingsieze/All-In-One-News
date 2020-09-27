package com.project.allinonenews;

import android.widget.Adapter;
import android.widget.Filter;

import java.util.ArrayList;

public class FilterList extends Filter {

    private NewsViewAdapter newsAdapter;
    private ArrayList<NewsEntity> filterNews;

    public FilterList(NewsViewAdapter newsViewAdapter, ArrayList<NewsEntity>filterNews){
        this.newsAdapter = newsViewAdapter;
        this.filterNews = filterNews;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults filterResults = new FilterResults();
        if(constraint!=null && constraint.length()>0){
            constraint = constraint.toString().toLowerCase().replaceAll("\\s+", "");
            ArrayList<NewsEntity> filteredNews = new ArrayList<>();
            for (int i=0; i<filterNews.size(); i++){
                if (filterNews.get(i).getName().toLowerCase().replaceAll("\\s+", "").contains(constraint)){
                    filteredNews.add(filterNews.get(i));
                }
            }
            filterResults.count = filteredNews.size();
            filterResults.values = filteredNews;
        }else{
            filterResults.count = filterNews.size();
            filterResults.values = filterNews;
        }
        return filterResults;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        newsAdapter.newsList = (ArrayList<NewsEntity>)results.values;
        newsAdapter.notifyDataSetChanged();
    }
}
