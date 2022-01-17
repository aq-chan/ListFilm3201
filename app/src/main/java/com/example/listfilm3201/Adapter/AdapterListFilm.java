package com.example.listfilm3201.Adapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.listfilm3201.DetailMovieActivity;
import com.example.listfilm3201.Model.Films;
import com.example.listfilm3201.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterListFilm extends RecyclerView.Adapter<AdapterListFilm.ViewHolder> {

    Context context;
    List<Films> viewItems;

    public AdapterListFilm(Context context, List<Films> filmsList) {
        this.context = context;
        this.viewItems = filmsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_films_layout, null);
        return new ViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        String thumbnailUrl = viewItems.get(position).getPosterPath();
        Picasso.get().load(thumbnailUrl).fit().centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.photos);


        holder.txtTitle.setText(viewItems.get(position).getTitle());
        holder.txtOverview.setText(viewItems.get(position).getOverview());
        holder.txtDate.setText(viewItems.get(position).getReleaseDate());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), DetailMovieActivity.class);
                    intent.putExtra("txtTitle",viewItems.get(position).getTitle());
                    intent.putExtra("txtId",viewItems.get(position).toString());
                    intent.putExtra("txtOverview",viewItems.get(position).getOverview());
                    intent.putExtra("txtDate",viewItems.get(position).getReleaseDate());
                    intent.putExtra("txtLanguage",viewItems.get(position).getOriginalLanguage());
                    intent.putExtra("txtOriginalTitle",viewItems.get(position).getOriginalTitle());
                    intent.putExtra("txtRating",viewItems.get(position).getVoteAverage().toString());
                    intent.putExtra("txtVote",viewItems.get(position).getVoteCount().toString());
                    intent.putExtra("picture",viewItems.get(position).getBackdropPath());
                    context.startActivity(intent);
                    Toast.makeText(context, viewItems.get(position).getTitle(), Toast.LENGTH_SHORT).show();
                }
        });
    }

    @Override
    public int getItemCount() {
        return viewItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView photos, picture;
        TextView txtId, txtTitle, txtOverview, txtDate, txtOriginalTitle, txtLanguage, txtRating, txtVote;

        public ViewHolder(View itemView) {
            super(itemView);

            photos = itemView.findViewById(R.id.icon);
            picture = itemView.findViewById(R.id.picture);
            txtId = itemView.findViewById(R.id.txtId);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtOverview = itemView.findViewById(R.id.txtOverview);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtOriginalTitle = itemView.findViewById(R.id.txtOriginalTitle);
            txtLanguage = itemView.findViewById(R.id.txtLanguage);
            txtRating = itemView.findViewById(R.id.txtRating);
            txtVote = itemView.findViewById(R.id.txtVote);
        }
    }
}