package me.exerosis.nanodegree.movies.mvc;

public interface Interactable<T> {
    void setListener(T listener);
    T getListener();
}
