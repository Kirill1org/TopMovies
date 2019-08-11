package com.koromyslov.topmovies;

public class Film {
    private String filmAvatar;
    private String filmTitle;
    private String filmRating;
    private String filmDate;
    private String filmDescritpion;

    public Film(String filmAvatar, String filmTitle, String filmRating, String filmDate, String filmDescritpion) {
        this.filmAvatar = filmAvatar;
        this.filmTitle = filmTitle;
        this.filmRating = filmRating;
        this.filmDate = filmDate;
        this.filmDescritpion = filmDescritpion;
    }

    public String getFilmAvatar() {
        return filmAvatar;
    }

    public void setFilmAvatar(String filmAvatar) {
        this.filmAvatar = filmAvatar;
    }

    public String getFilmTitle() {
        return filmTitle;
    }

    public void setFilmTitle(String filmTitle) {
        this.filmTitle = filmTitle;
    }

    public String getFilmRating() {
        return filmRating;
    }

    public void setFilmRating(String filmRating) {
        this.filmRating = filmRating;
    }

    public String getFilmDate() {
        return filmDate;
    }

    public void setFilmDate(String filmDate) {
        this.filmDate = filmDate;
    }

    public String getFilmDescritpion() {
        return filmDescritpion;
    }

    public void setFilmDescritpion(String filmDescritpion) {
        this.filmDescritpion = filmDescritpion;
    }
}
