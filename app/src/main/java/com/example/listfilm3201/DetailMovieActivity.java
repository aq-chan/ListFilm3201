package com.example.listfilm3201;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.listfilm3201.Model.Films;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DetailMovieActivity extends AppCompatActivity{

    Context context;

    myDbAdapter helper;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        helper = new myDbAdapter(this);

        setContentView(R.layout.activity_detail_movie);

        Intent intent = getIntent();
        TextView txtId = findViewById(R.id.txtId);
        TextView txtTitle = findViewById(R.id.txtTitle);
        TextView txtOverview = findViewById(R.id.txtOverview);
        TextView txtDate = findViewById(R.id.txtDate);
        TextView txtOriginalTitle = findViewById(R.id.txtOriginalTitle);
        TextView txtRating = findViewById(R.id.txtRating);
        TextView txtVote = findViewById(R.id.txtVote);
        TextView txtLanguage = findViewById(R.id.txtLanguage);
        ImageView picture = findViewById(R.id.picture);

        txtId.setVisibility(View.GONE);
        txtTitle.setText(intent.getStringExtra("txtTitle"));
        txtOverview.setText(intent.getStringExtra("txtOverview"));
        txtDate.setText(intent.getStringExtra("txtDate"));
        txtOriginalTitle.setText(intent.getStringExtra("txtOriginalTitle"));
        txtRating.setText(intent.getStringExtra("txtRating"));
        txtVote.setText(intent.getStringExtra("txtVote"));
        txtLanguage.setText(intent.getStringExtra("txtLanguage"));

        String urlPicture = intent.getStringExtra("picture");
        Picasso.get().load(urlPicture).fit().centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(picture);
    }

    public void addMovie(View view) {
        Intent intent = getIntent();
        String movie_title = intent.getStringExtra("txtTitle");
        long id = helper.insertData(movie_title);

        String berhasil = getString(R.string.tambah_berhasil);
        String gagal = getString(R.string.tambah_gagal);
        if(id <= 0) {
            Toast.makeText(getApplicationContext(),gagal,
                    Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(getApplicationContext(),berhasil,
                    Toast.LENGTH_LONG).show();
        }
    }

    public void removeMovie(View view) {
        Intent intent = getIntent();
        String movie_title = intent.getStringExtra("txtTitle");
        long id = helper.removeData(movie_title);

        String berhasil = getString(R.string.hapus_berhasil);
        String gagal = getString(R.string.hapus_gagal);
        if(id <= 0) {
            Toast.makeText(getApplicationContext(),gagal,
                    Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(getApplicationContext(),berhasil,
                    Toast.LENGTH_LONG).show();

        }
    }

    public void showBookmark(View view) {
        Intent intent = new Intent(DetailMovieActivity.this, ListBookmarkActivity.class);
        DetailMovieActivity.this.startActivity(intent);
    }
}