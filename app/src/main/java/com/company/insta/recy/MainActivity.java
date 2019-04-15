package com.company.insta.recy;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Category> categoriesList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MyAdapter mAdapter;
    ProgressDialog pd;
    public static final String myUrl = "http://servdoservice.com/api/rest/v1/categories.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler);



        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // recyclerView.setAdapter(mAdapter);

        //  prepareMovieData();
//        pd = new ProgressDialog(this);
//        pd.setTitle("My progress dialog");
//        pd.setMessage("Downloading......");
//        pd.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, myUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
//                    pd.dismiss();
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("CATEGORIES");
                    for (int i = 0; i<=jsonArray.length(); i++){
                        JSONObject myCategories = jsonArray.getJSONObject(i);
                        String name = myCategories.getString("categoryName");
                        String description = myCategories.getString("categoryDescription");
                        String image = myCategories.getString("categoryImage");

                        Category categories = new Category(name, description, image);

                        categoriesList.add(categories);
                        mAdapter = new MyAdapter(categoriesList);
                        recyclerView.setAdapter(mAdapter);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


//    private void prepareMovieData() {
//        Movies movie = new Movies("Mad Max: Fury Road", "Action & Adventure", "2015");
//        moviesList.add(movie);
//
//        movie = new Movies("Inside Out", "Animation, Kids & Family", "2015");
//        moviesList.add(movie);
//
//        movie = new Movies("Star Wars: Episode VII - The Force Awakens", "Action", "2015");
//        moviesList.add(movie);
//
//        movie = new Movies("Shaun the Sheep", "Animation", "2015");
//        moviesList.add(movie);
//
//        movie = new Movies("The Martian", "Science Fiction & Fantasy", "2015");
//        moviesList.add(movie);
//
//        movie = new Movies("Mission: Impossible Rogue Nation", "Action", "2015");
//        moviesList.add(movie);
//
//        movie = new Movies("Up", "Animation", "2009");
//        moviesList.add(movie);
//
//        movie = new Movies("Star Trek", "Science Fiction", "2009");
//        moviesList.add(movie);
//
//        movie = new Movies("The LEGO Movie", "Animation", "2014");
//        moviesList.add(movie);
//
//        movie = new Movies("Iron Man", "Action & Adventure", "2008");
//        moviesList.add(movie);
//
//        movie = new Movies("Aliens", "Science Fiction", "1986");
//        moviesList.add(movie);
//
//        movie = new Movies("Chicken Run", "Animation", "2000");
//        moviesList.add(movie);
//
//        movie = new Movies("Back to the Future", "Science Fiction", "1985");
//        moviesList.add(movie);
//
//        movie = new Movies("Raiders of the Lost Ark", "Action & Adventure", "1981");
//        moviesList.add(movie);
//
//        movie = new Movies("Goldfinger", "Action & Adventure", "1965");
//        moviesList.add(movie);
//
//        movie = new Movies("Guardians of the Galaxy", "Science Fiction & Fantasy", "2014");
//        moviesList.add(movie);
//
//        mAdapter.notifyDataSetChanged();
//    }
}
