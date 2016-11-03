package me.exerosis.nanodegree.movies.mvc;

public interface Adaptable<T> {
    T getAdapter();

    void setAdapter(T adapter);
}
