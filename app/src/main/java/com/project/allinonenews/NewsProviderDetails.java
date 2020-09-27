package com.project.allinonenews;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

public class NewsProviderDetails extends AppCompatActivity {

    private TextView mName, mDescription;
    private RecyclerView mNewsDetailsRV;

    private ArrayList<NewsDetailsEntity> newsDetailsEntityArrayList;
    private NewsDetailsAdapter newsDetailsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_provider_details);

        ActionBar ab = getSupportActionBar();
        ab.hide();

        mName = findViewById(R.id.name);
        mDescription = findViewById(R.id.description);
        mNewsDetailsRV = findViewById(R.id.latestNewsRV);


        Intent i = getIntent();
        String id = i.getStringExtra("id");
        String name = i.getStringExtra("name");
        String description = i.getStringExtra("description");



        mName.setText(name);
        mDescription.setText(description);

        loadNewsDetails(id);


    }

    private void loadNewsDetails(String id) {
        newsDetailsEntityArrayList = new ArrayList<>();
        String apiNewsDetails = "https://newsapi.org/v2/top-headlines?sources="+id+"&apiKey=" + API_KEY_VALUE.API_KEY;
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading News...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, apiNewsDetails, new Response.Listener<String>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("articles");
                    for (int i = 0; i<jsonArray.length(); i++){
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String title = jsonObject1.getString("title");
                        String description = jsonObject1.getString("description");
                        String url = jsonObject1.getString("url");
                        String urlToImage = jsonObject1.getString("urlToImage");
                        String publishedAt = jsonObject1.getString("publishedAt");
                        String content = jsonObject1.getString("content");



                        SimpleDateFormat getDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                        SimpleDateFormat requiredDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        String displayDate;
                        try{
                            Date date = getDateFormat.parse(publishedAt);
                            displayDate = requiredDateFormat.format(date);
                        }catch (Exception e){
                            displayDate = publishedAt;
                        }



                        NewsDetailsEntity newsDetailsEntity = new NewsDetailsEntity(title, description, url, urlToImage,displayDate,content);
                        newsDetailsEntityArrayList.add(newsDetailsEntity);
                    }

                    newsDetailsAdapter = new NewsDetailsAdapter(NewsProviderDetails.this, newsDetailsEntityArrayList);
                    mNewsDetailsRV.setAdapter(newsDetailsAdapter);
                    progressDialog.dismiss();

                }catch (Exception e){
                    progressDialog.dismiss();
                    System.out.println("AllInOneNews ERROR: " + e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                System.out.println("AllInOneNews ERROR: " + error.getMessage());
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

}
