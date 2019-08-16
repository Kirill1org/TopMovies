package com.koromyslov.topmovies;

import com.arellomobile.mvp.MvpView;

import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import com.koromyslov.topmovies.ResponseDAO.Film;

import java.util.List;

public interface IFilmView extends MvpView {

    @StateStrategyType(value = AddToEndStrategy.class)
    void showFilmList(List<Film> filmList);

    @StateStrategyType(value = OneExecutionStateStrategy.class)
    void showErrorCode(Throwable t);

    @StateStrategyType(value = SkipStrategy.class)
    void showProgressBar();

    @StateStrategyType(value = SkipStrategy.class)
    void hideProgressBar();

    @StateStrategyType(value = SkipStrategy.class)
    void createNotify(int ID_NOTIFY, String filmTitle, int year, int month, int date, int hour, int minute);

    @StateStrategyType(value = SkipStrategy.class)
    void dataSet(String filmTitle, int filmID);

}
