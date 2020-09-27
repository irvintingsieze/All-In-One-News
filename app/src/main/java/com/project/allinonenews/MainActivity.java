package com.project.allinonenews;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText mSearch;
    private RecyclerView mSource;
    private ArrayList<NewsEntity> newsList;
    private NewsViewAdapter newsViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar ab = getSupportActionBar();
        ab.hide();
        setContentView(R.layout.activity_main);
        mSearch = findViewById(R.id.mSearch);
        mSource = findViewById(R.id.mNewsOrigin);
        loadSources();
        mSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try{
                    newsViewAdapter.getFilter().filter(s);
                }catch (Exception e){
                    System.out.println("AllInOneNews ERROR: " + e.getMessage());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void loadSources() {
        newsList = new ArrayList<>();
        newsList.clear();
        String API = "https://newsapi.org/v2/sources?language=en&apiKey=" + API_KEY_VALUE.API_KEY;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, API, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("sources");
                    for (int i = 0; i<jsonArray.length(); i++){

                        JSONObject jsonObjects = jsonArray.getJSONObject(i);
                        String id = jsonObjects.getString("id");
                        String name = jsonObjects.getString("name");
                        String descriptions = jsonObjects.getString("description");
                        String url = jsonObjects.getString("url");
                        String category = jsonObjects.getString("category");
                        NewsEntity newsEntity = new NewsEntity(id,name,descriptions,url, category);

                        newsList.add(newsEntity);
                    }
                    newsViewAdapter = new NewsViewAdapter(MainActivity.this, newsList);
                    mSource.setAdapter(newsViewAdapter);

                }catch (Exception e){

                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
