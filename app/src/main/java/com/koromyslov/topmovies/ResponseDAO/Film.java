package com.koromyslov.topmovies.ResponseDAO;

public class Film {
    private String filmAvatar;
    private String filmTitle;
    private String filmRating;
    private String filmDate;
    private String filmDescritpion;
    private int filmID;

    public Film(String filmAvatar, String filmTitle, String filmRating, String filmDate, String filmDescritpion, int filmID) {
        this.filmAvatar = filmAvatar;
        this.filmTitle = filmTitle;
        this.filmRating = filmRating;
        this.filmDate = filmDate;
        this.filmDescritpion = filmDescritpion;
        this.filmID = filmID;
    }

    public int getFilmID() {
        return filmID;
    }

    public void setFilmID(int filmID) {
        this.filmID = filmID;
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
