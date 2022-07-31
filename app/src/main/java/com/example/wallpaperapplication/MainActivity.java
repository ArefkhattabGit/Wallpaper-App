package com.example.wallpaperapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements CategoryClickLInterface {

    private ImageView searchImage;
    private EditText searchEditText;
    private RecyclerView rvCategory, rvWallpaper;
    private ArrayList<String> stringArrayList;
    private ArrayList<CategoryModal> modalArrayList;
    private WallpaperRvAdapter wallpaperRvAdapter;
    private CategoryRvAdapter categoryRvAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        stringArrayList = new ArrayList<>();
        modalArrayList = new ArrayList<>();
        LinearLayoutManager linearLayoutManager
                = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        rvCategory.setLayoutManager(linearLayoutManager);
        categoryRvAdapter = new CategoryRvAdapter(this, modalArrayList, this::onCategoryClick);
        rvCategory.setAdapter(categoryRvAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        rvWallpaper.setLayoutManager(gridLayoutManager);
        wallpaperRvAdapter = new WallpaperRvAdapter(this, stringArrayList);
        rvWallpaper.setAdapter(wallpaperRvAdapter);


        getCategories();
        getWallpapers();



        searchEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String stringSearch = searchEditText.getText().toString();
                if (stringSearch.trim().isEmpty()) {
                    Toast.makeText(MainActivity.this, "please enter your search key world.", Toast.LENGTH_SHORT).show();

                } else {
                    getWallpaperByCategory(stringSearch);
                }


            }
        });
    }

    private void getWallpaperByCategory(String category) {

        String url = "https://api.pexels.com/v1/search?query=" + category + "&per_page=30&page=1";

        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONArray photo = null;
                try {
                    photo = response.getJSONArray("photos");
                    for (int i = 0; i < photo.length(); i++) {
                        JSONObject object = photo.getJSONObject(i);
                        String imageUrl = object.getJSONObject("src").getString("portrait");
                        stringArrayList.add(imageUrl);

                    }
                    wallpaperRvAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "SERVER ERROR", Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> header = new HashMap<>();
                header.put("Authorization", "563492ad6f917000010000014e6bb4a61d1946d7934e30cc51e84830");
                return header;
            }
        };
        queue.add(jsonObjectRequest);
    }

    private void getWallpapers() {
        stringArrayList.clear();
        String url = "https://api.pexels.com/v1/curated?per_page=30&page=1";
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray photo = response.getJSONArray("photos");

                    for (int i = 0; i < photo.length(); i++) {

                        JSONObject object = photo.getJSONObject(i);
                        String imageUrl = object.getJSONObject("src").getString("portrait");
                        stringArrayList.add(imageUrl);

                    }

                    wallpaperRvAdapter.notifyDataSetChanged();

                } catch (JSONException e) {

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity.this, "SERVER ERROR", Toast.LENGTH_SHORT).show();
                Log.d("fff", error.getLocalizedMessage());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> header = new HashMap<>();
                header.put("Authorization", "563492ad6f917000010000014e6bb4a61d1946d7934e30cc51e84830");
                return header;

            }
        };

        queue.add(jsonObjectRequest);

    }

    private void getCategories() {
        modalArrayList.add(new CategoryModal("Technology",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTX973am78qfaTFUNBg0IiTZtUJ4KDdZVAVBbKHy98hMqk6-S8H_ppbeOCgkPueIauLKpc&usqp=CAU"));
        modalArrayList.add(new CategoryModal("Programing",
                "https://images.unsplash.com/photo-1518773553398-650c184e0bb3?ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mjh8fGNvZGV8ZW58MHx8MHx8&ixlib=rb-1.2.1&w=1000&q=80"));
        modalArrayList.add(new CategoryModal("Nature",
                "https://img.rawpixel.com/s3fs-private/rawpixel_images/website_content/pf-misc8-minty-scotland-05421-eye-1.jpg?w=800&dpr=1&fit=default&crop=default&q=65&vib=3&con=3&usm=15&bg=F4F4F3&ixlib=js-2.2.1&s=e28da2081e2c20c48cbd3dfecede16c9"));
        modalArrayList.add(new CategoryModal("Travel",
                "https://i1.sndcdn.com/artworks-Tsj0guD4tEtii0Ib-TNnzuQ-t500x500.jpg"));
        modalArrayList.add(new CategoryModal("Architecture",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQhTy0iEdTEEfQ3hHIXJcMS4V-3hruTCZdeTA&usqp=CAU"));


        categoryRvAdapter.notifyDataSetChanged();
    }

    private void initViews() {
        // init all views in this screen.
        searchEditText = findViewById(R.id.eSearchEditText);
        searchImage = findViewById(R.id.iSearchIcon);
        rvCategory = findViewById(R.id.rvCategory);
        rvWallpaper = findViewById(R.id.rvWallpapers);

    }

    @Override
    public void onCategoryClick(int position) {

        String category = modalArrayList.get(position).getCategory();
        getWallpaperByCategory(category);


    }
}
