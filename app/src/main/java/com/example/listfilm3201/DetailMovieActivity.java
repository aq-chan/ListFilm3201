package com.example.listfilm3201;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

public class DetailMovieActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        Intent intent = getIntent();

        TextView txtTitle = findViewById(R.id.txtTitle);
        TextView txtOverview = findViewById(R.id.txtOverview);
        TextView txtDate = findViewById(R.id.txtDate);
        TextView txtOriginalTitle = findViewById(R.id.txtOriginalTitle);
        TextView txtRating = findViewById(R.id.txtRating);
        TextView txtVote = findViewById(R.id.txtVote);
        TextView txtLanguage = findViewById(R.id.txtLanguage);
        ImageView picture = findViewById(R.id.picture);

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

}