package me.exerosis.nanodegree.movies.mvc;

public interface Interactable<T> {
    T getListener();

    void setListener(T listener);
}
