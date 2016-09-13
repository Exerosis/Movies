package me.exerosis.nanodegree.movies;

public class Movie {
    private final String title;
    private final String description;

    public Movie(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }


    public String getTitle() {
        return title;
    }
}
