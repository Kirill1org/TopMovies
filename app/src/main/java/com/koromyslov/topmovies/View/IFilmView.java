package com.koromyslov.topmovies.View;

import com.koromyslov.topmovies.ResponseDAO.Film;

import java.util.List;

public interface IFilmView {
    void showFilmList(List<Film> filmList);

    void showErrorCode(Throwable t);

    void showProgressBar();

    void hideProgressBar();

    void createNotify(int ID_NOTIFY, String filmTitle, int year, int month, int date, int hour, int minute);

}
