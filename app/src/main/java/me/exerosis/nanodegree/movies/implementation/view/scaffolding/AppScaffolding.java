package me.exerosis.nanodegree.movies.implementation.view.scaffolding;

import me.exerosis.nanodegree.movies.mvc.Interactable;
import me.exerosis.nanodegree.movies.mvc.ViewBase;

public interface AppScaffolding extends ViewBase, Interactable<AppScaffoldingListener> {
    boolean setDrawerOpen(boolean open);
    int getFragmentContainerID();
}
