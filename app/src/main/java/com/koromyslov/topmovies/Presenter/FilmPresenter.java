package com.koromyslov.topmovies.Presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.koromyslov.topmovies.Model.FilmService;
import com.koromyslov.topmovies.Model.NetworkModule;
import com.koromyslov.topmovies.ResponseDAO.Film;
import com.koromyslov.topmovies.ResponseDAO.FilmUnit;
import com.koromyslov.topmovies.IFilmView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class FilmPresenter extends MvpPresenter<IFilmView> implements IFilmPresenter {

    private static final String API_KEY = "ee40188e0992e8a36ad413d1346b6208";

    private List<Film> filmResultList = new ArrayList<>();

    private int CHOSEN_YEAR = 0;
    private int CHOSEN_MONTH = 0;
    private int CHOSEN_DATE = 0;
    private int CHOSEN_HOUR = 0;
    private int CHOSEN_MINUTE = 0;

    public FilmPresenter() {
        getFilmData();

    }


    @Override
    public void getFilmData() {
        getViewState().showProgressBar();
        FilmService api = new NetworkModule().filmService;
        api.getFilmList(API_KEY)
                .doOnSubscribe(f -> getViewState().showProgressBar())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .flatMap(filmList -> Observable.fromIterable(filmList.getResults()))
                .filter(filmUnit -> filmUnit.getReleaseYear().equals("2019"))
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(() -> getViewState().hideProgressBar())
                .subscribe(this::addToListFilm, Throwable -> getViewState().showErrorCode(Throwable));
    }

    @Override
    public void setFilmDate(int year, int month, int date) {
        CHOSEN_YEAR = year;
        CHOSEN_MONTH = month;
        CHOSEN_DATE = date;
    }

    @Override
    public void setFilmTime(int hour, int minute, String filmTitle, int filmID) {
        CHOSEN_HOUR = hour;
        CHOSEN_MINUTE = minute;
        getViewState().createNotify(filmID, filmTitle, CHOSEN_YEAR, CHOSEN_MONTH, CHOSEN_DATE, CHOSEN_HOUR, CHOSEN_MINUTE);

    }

    @Override
    public void getFilmDataNotify(String filmTitle, int filmID) {
        getViewState().dataSet(filmTitle, filmID);
    }

    private void addToListFilm(List<FilmUnit> filmUnits) {
        for (FilmUnit filmUnit : filmUnits) {
            filmResultList.add(new Film(filmUnit.getPosterPath(), filmUnit.getTitle(), filmUnit.getVoteAverage().toString(), filmUnit.getReleaseDate(), filmUnit.getOverview(), filmUnit.getId()));
        }
        getViewState().showFilmList(filmResultList);
    }
}

