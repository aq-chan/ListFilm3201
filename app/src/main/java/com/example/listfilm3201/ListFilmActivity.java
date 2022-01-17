package com.example.listfilm3201;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listfilm3201.Adapter.AdapterListFilm;
import com.example.listfilm3201.Model.Films;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListFilmActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private final List<Films> viewItems = new ArrayList<>();

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.lst_films)
    RecyclerView lstFilms;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_film);
        ButterKnife.bind(this);

        lstFilms.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        lstFilms.setHasFixedSize(true);
        lstFilms.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        AdapterListFilm adapterListFilm = new AdapterListFilm(this, viewItems);
        lstFilms.setAdapter(adapterListFilm);

        addItemsFromJSON();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void addItemsFromJSON() {
        try {
            String jsonDataString = readJSONDataFromFile();
            JSONArray jsonArray = new JSONArray(jsonDataString);

            for(int i = 0; i < jsonArray.length(); i++){

                JSONObject itemObj = jsonArray.getJSONObject(i);

                Films films = new Films();
                films.setAdult(itemObj.getBoolean("adult"));
                films.setBackdropPath(itemObj.getString("backdrop_path"));
                films.setId(itemObj.getInt("id"));
                films.setOriginalLanguage(itemObj.getString("original_language"));
                films.setOriginalTitle(itemObj.getString("original_title"));
                films.setOverview(itemObj.getString("overview"));
                films.setPopularity(itemObj.getDouble("popularity"));
                films.setPosterPath(itemObj.getString("poster_path"));
                films.setReleaseDate(itemObj.getString("release_date"));
                films.setTitle(itemObj.getString("title"));
                films.setVideo(itemObj.getBoolean("video"));
                films.setVoteAverage(itemObj.getDouble("vote_average"));
                films.setVoteCount(itemObj.getInt("vote_count"));

                viewItems.add(films);
            }
        } catch (IOException | JSONException e) {
            Log.d(TAG, "addItemsFromJSON: ", e);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private String readJSONDataFromFile() throws IOException {

        InputStream inputStream = null;
        StringBuilder builder = new StringBuilder();

        try {
            String jsonString;
            inputStream = getResources().openRawResource(R.raw.list_movie);
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(inputStream, StandardCharsets.UTF_8));

            while ((jsonString = bufferedReader.readLine()) != null) {
                builder.append(jsonString);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return new String(builder);
    }
}