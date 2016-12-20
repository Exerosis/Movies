package me.exerosis.nanodegree.movies.implementation.model.data;

public class Trailer {
    public static final String FORMAT_THUMBNAIL = "https://img.youtube.com/vi/%s/hqdefault.jpg";
    public static final String FORMAT_VIDEO = "vnd.youtube://%s";
    private String key;

    public Trailer(String key) {
        this.key = key;
    }

    public String getVideo() {
        return String.format(FORMAT_VIDEO, key);
    }

    public String getThumbnail() {
        return String.format(FORMAT_THUMBNAIL, key);
    }
}
