/*
package com.koromyslov.topmovies;

import com.koromyslov.topmovies.Presenter.FilmPresenter;


import static org.hamcrest.Matchers.is;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class FilmPresenterTest {



    @Mock
    IFilmView$$State filmViewState;

    private FilmPresenter filmPresenter;


    @Before
    public void setUp() {
        //MockitoAnnotations.initMocks(this);
        filmViewState=Mockito.mock(IFilmView.class);
        filmPresenter = new FilmPresenter();
        filmPresenter.setViewState(filmViewState);

    }

    @Test
    public void showProgressBarSuccess() {
        if (filmViewState==null) return;
        //filmPresenter.initTest();
        verify(filmViewState).showProgressBar();

    }
}
*/
