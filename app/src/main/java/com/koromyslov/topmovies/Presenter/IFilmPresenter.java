package com.koromyslov.topmovies.Presenter;

public interface IFilmPresenter {


    void getFilmData();

    void setFilmDate(int year, int month, int date);

    void setFilmTime(int hour, int minute, String filmTitle, int filmID);

    void onBtnScheduleClick(String filmTitle, int filmID);


}
