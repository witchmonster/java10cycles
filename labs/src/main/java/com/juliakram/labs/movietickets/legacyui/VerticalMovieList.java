package com.juliakram.labs.movietickets.legacyui;

import com.vaadin.ui.VerticalLayout;

/**
 * Created by yuliya.kramarenko on 06.01.2015.
 */
public class VerticalMovieList extends MovieView<VerticalLayout> {

    public VerticalMovieList() {
    }

    @Override
    protected VerticalLayout initContainer() {
        return new VerticalLayout();
    }


}
