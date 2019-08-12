package com.koromyslov.topmovies.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.koromyslov.topmovies.Presenter.FilmPresenter;
import com.koromyslov.topmovies.Presenter.IFilmPresenter;
import com.koromyslov.topmovies.R;
import com.koromyslov.topmovies.RVAdapter;
import com.koromyslov.topmovies.ResponseDAO.Film;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IFilmView {

    private RecyclerView rv;
    private ProgressBar progressBar;
    private IFilmPresenter filmPresenter;

    private final RVAdapter.OnItemClickListener clickListener = weatherCity -> {
        Toast.makeText(this, "HI", Toast.LENGTH_SHORT).show();
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        filmPresenter = new FilmPresenter(this);


        rv = findViewById(R.id.rv);
        progressBar=findViewById(R.id.progressBar);

        filmPresenter.getFilmData();


    }


    @Override
    public void showFilmList(List<Film> filmList) {
        rv.setAdapter(new RVAdapter(this, filmList, clickListener));
        rv.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    public void showErrorCode(Throwable t) {
        Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }

}
