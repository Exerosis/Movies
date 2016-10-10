package me.exerosis.nanodegree.movies.impl.scaffolding.controller;

import android.os.Bundle;
import android.support.v4.content.Loader;

import java.util.Collection;

import me.exerosis.nanodegree.movies.impl.movielist.model.Movie;
import me.exerosis.nanodegree.movies.mvc.BiFunction;

public interface AppScaffoldingController extends BiFunction<Integer,Bundle,Loader<Collection<Movie>>>  {
}
