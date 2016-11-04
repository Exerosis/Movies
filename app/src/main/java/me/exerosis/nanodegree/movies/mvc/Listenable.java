package me.exerosis.nanodegree.movies.mvc;

public interface Listenable<T> {
    T getListener();

    void setListener(T listener);
}
