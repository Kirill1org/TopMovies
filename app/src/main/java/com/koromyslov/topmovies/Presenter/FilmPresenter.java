package com.koromyslov.topmovies.Presenter;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.MainThread;


import com.koromyslov.topmovies.Model.FilmService;
import com.koromyslov.topmovies.Model.NetworkModule;
import com.koromyslov.topmovies.ResponseDAO.Film;
import com.koromyslov.topmovies.ResponseDAO.FilmList;
import com.koromyslov.topmovies.ResponseDAO.FilmUnit;
import com.koromyslov.topmovies.View.IFilmView;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class FilmPresenter implements IFilmPresenter {

    private static final String API_KEY = "ee40188e0992e8a36ad413d1346b6208";

    private IFilmView filmView;
    private List<Film> filmResultList = new ArrayList<>();

    private int CHOSEN_YEAR = 0;
    private int CHOSEN_MONTH = 0;
    private int CHOSEN_DATE = 0;
    private int CHOSEN_HOUR = 0;
    private int CHOSEN_MINUTE = 0;

    public FilmPresenter(IFilmView filmView) {
        this.filmView = filmView;
    }


    @Override
    public void getFilmData() {
        FilmService api = new NetworkModule().filmService;
        api.getFilmList(API_KEY)
                .doOnSubscribe(f->filmView.showProgressBar())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .flatMap(filmList -> Observable.fromIterable(filmList.getResults()))
                .filter(filmUnit -> filmUnit.getReleaseYear().equals("2019"))
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess(f->filmView.hideProgressBar())
                .subscribe(this::addToListFilm, Throwable -> Log.e("HELLO", Throwable.getMessage()));
    }

    @Override
    public void setFilmDate(int year, int month, int date) {
        CHOSEN_YEAR=year;
        CHOSEN_MONTH=month;
        CHOSEN_DATE=date;
    }

    @Override
    public void setFilmTime(int hour, int minute, String filmTitle, int filmID) {
        CHOSEN_HOUR=hour;
        CHOSEN_MINUTE=minute;
     filmView.createNotify(filmID,filmTitle, CHOSEN_YEAR, CHOSEN_MONTH, CHOSEN_DATE, CHOSEN_HOUR, CHOSEN_MINUTE);

    }

    private void addToListFilm(List<FilmUnit> filmUnits) {
        for (FilmUnit filmUnit : filmUnits) {
            filmResultList.add(new Film(filmUnit.getPosterPath(), filmUnit.getTitle(), filmUnit.getVoteAverage().toString(), filmUnit.getReleaseDate(), filmUnit.getOverview(), filmUnit.getId()));
        }
        filmView.showFilmList(filmResultList);
    }

    private FilmList getFilm2019(FilmList filmList) {

        FilmList newFilmList = new FilmList();

        for (FilmUnit filmUnit : filmList.getResults()) {
            String[] parseDateString = filmUnit.getReleaseDate().split("-");
            if (parseDateString[0] == "2019") newFilmList.getResults().add(filmUnit);
        }
        return newFilmList;

    }

    private boolean isFilm2019(FilmList filmList) {
        boolean isFilm2019 = false;
        for (FilmUnit filmUnit : filmList.getResults()) {
            String[] parseDateString = filmUnit.getReleaseDate().split("-");
            if (parseDateString[0].equals("2019")) isFilm2019 = true;

        }
        return isFilm2019;
    }
}

