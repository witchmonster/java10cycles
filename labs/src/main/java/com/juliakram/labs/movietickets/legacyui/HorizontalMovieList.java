package com.juliakram.labs.movietickets.legacyui;

import com.vaadin.ui.HorizontalLayout;

/**
 * Created by yuliya.kramarenko on 06.01.2015.
 */
public class HorizontalMovieList extends MovieView<HorizontalLayout> {

    public HorizontalMovieList() {
    }

    @Override
    protected HorizontalLayout initContainer() {
        return new HorizontalLayout();
    }
}
