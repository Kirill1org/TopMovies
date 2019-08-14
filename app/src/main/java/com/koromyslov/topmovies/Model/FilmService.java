package com.koromyslov.topmovies.Model;

import com.koromyslov.topmovies.ResponseDAO.FilmList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FilmService {
    @GET("3/movie/popular")
    Observable<FilmList> getFilmList(@Query("api_key") String apiKey);

}
