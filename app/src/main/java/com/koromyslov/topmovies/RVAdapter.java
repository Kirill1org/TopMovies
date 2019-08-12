package com.koromyslov.topmovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.koromyslov.topmovies.ResponseDAO.Film;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.FilmViewHolder> {

    private List<Film> filmList;
    private LayoutInflater inflater;
    private Context context;
    private final OnItemClickListener clickListener;

    public RVAdapter(Context context, List<Film> filmList, OnItemClickListener clickListener) {
        this.filmList = filmList;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.clickListener = clickListener;


    }

    @NonNull
    @Override
    public FilmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FilmViewHolder(inflater.inflate(R.layout.film_unit, parent, false), clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmViewHolder holder, int position) {
        holder.bind(filmList.get(position));
    }

    @Override
    public int getItemCount() {
        return filmList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Film film);
    }

    class FilmViewHolder extends RecyclerView.ViewHolder {

        private ImageView filmAvatar;
        private TextView filmRating;
        private TextView filmTitle;
        private TextView filmDate;
        private TextView filmDescription;

        public FilmViewHolder(@NonNull View itemView, @NonNull OnItemClickListener listener) {
            super(itemView);
            itemView.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(filmList.get(position));
                }
            });

            filmAvatar = itemView.findViewById(R.id.film_avatar);
            filmRating = itemView.findViewById(R.id.film_rating_img);
            filmTitle = itemView.findViewById(R.id.filmTitle);
            filmDate = itemView.findViewById(R.id.filmDate);
            filmDescription = itemView.findViewById(R.id.filmDescription);

        }


        public void bind(Film film) {
            Glide
                    .with(context)
                    .load("https://image.tmdb.org/t/p/w500"+film.getFilmAvatar())
                    .into(filmAvatar);
            filmRating.setText(film.getFilmRating());
            filmTitle.setText(film.getFilmTitle());
            filmDate.setText(film.getFilmDate());
            filmDescription.setText(film.getFilmDescritpion());
        }
    }
}
