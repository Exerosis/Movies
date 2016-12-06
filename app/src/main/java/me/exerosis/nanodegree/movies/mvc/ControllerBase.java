package me.exerosis.nanodegree.movies.mvc;

public interface ControllerBase <T extends ViewBase> {
    T getRootView();
}
