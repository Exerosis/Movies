package me.exerosis.nanodegree.movies;

public class Movie {
    private String title;
    private String posterURL;

    public Movie(String title, String posterURL) {
        this.title = title;
        this.posterURL = posterURL;
    }

    public String getTitle() {
        return title;
    }

    public String getPosterURL() {
        return posterURL;
    }


    @Override
    public boolean equals(Object obj) {
        return obj instanceof Movie && title.equals(((Movie) obj).title);
    }
}
