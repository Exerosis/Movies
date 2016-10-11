package me.exerosis.nanodegree.movies.impl.scaffolding.view;

import me.exerosis.nanodegree.movies.mvc.Interactable;
import me.exerosis.nanodegree.movies.mvc.ViewBase;

public interface AppScaffolding extends ViewBase, Interactable<AppScaffoldingListener> {
    boolean setDrawerOpen(boolean open);
}
