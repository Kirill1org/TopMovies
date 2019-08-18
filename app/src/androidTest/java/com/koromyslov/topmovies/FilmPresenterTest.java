package com.koromyslov.topmovies;

import com.koromyslov.topmovies.Model.FilmService;
import com.koromyslov.topmovies.Model.NetworkModule;
import com.koromyslov.topmovies.Presenter.FilmPresenter;

import com.koromyslov.topmovies.ResponseDAO.FilmList;
import com.koromyslov.topmovies.View.MainActivity;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import io.reactivex.subscribers.TestSubscriber;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


public class FilmPresenterTest {

    static final String API_KEY = "ee40188e0992e8a36ad413d1346b6208";

    @Mock
    IFilmView$$State filmViewState;

    private FilmPresenter filmPresenter;
    private MainActivity main;

    @Before
    public void setUp() {
        filmViewState = Mockito.mock(IFilmView$$State.class);
        main = Mockito.mock(MainActivity.class);
        filmPresenter = new FilmPresenter();
        filmPresenter.setViewState(filmViewState);


    }

    @Test
    public void testAddToListFilm() throws Exception {

        filmPresenter.addToListFilm(Mockito.anyList());
        verify(filmViewState, times(1)).showFilmList(Mockito.anyList());
    }

    @Test
    public void testShowCalednarDialog() throws Exception {
        filmPresenter.onBtnScheduleClick(Mockito.anyString(), Mockito.anyInt());
        verify(filmViewState, times(1)).showCalendarDialog(Mockito.anyString(), Mockito.anyInt());
    }

    @Test
    public void testSetFilmTime() throws Exception {
        int hour = Mockito.anyInt();
        int minute = Mockito.anyInt();
        int year = Mockito.anyInt();
        int month = Mockito.anyInt();
        int date = Mockito.anyInt();
        String title = Mockito.anyString();
        int ID = Mockito.anyInt();

        filmPresenter.setFilmTime(hour, minute, title, ID);
        verify(filmViewState, times(1)).createNotify(ID, title, year, month, date, hour, minute);

    }

    @Test
    public void testGetData() throws Exception {
        TestSubscriber<FilmList> subscriber = new TestSubscriber<>();


        FilmService api = new NetworkModule().filmService;
        api.getFilmList(API_KEY)
                .test()
                .assertSubscribed()
                .assertComplete()
                .assertNoErrors()
                .assertValueCount(1);

    }

}

