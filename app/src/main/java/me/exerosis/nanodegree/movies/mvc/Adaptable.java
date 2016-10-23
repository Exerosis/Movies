package me.exerosis.nanodegree.movies.mvc;

public interface Adaptable<T> {
    void setAdapter(T adapter);
    T getAdapter();
}
