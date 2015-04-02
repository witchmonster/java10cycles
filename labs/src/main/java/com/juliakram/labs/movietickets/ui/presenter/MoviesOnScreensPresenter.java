package com.juliakram.labs.movietickets.ui.presenter;

import com.juliakram.labs.movietickets.ui.service.MovieService;
import com.juliakram.labs.movietickets.ui.view.MovieView;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.events.Event;
import org.vaadin.spring.events.EventBusListenerMethod;
import org.vaadin.spring.events.EventScope;
import org.vaadin.spring.navigator.Presenter;
import org.vaadin.spring.navigator.VaadinPresenter;

/**
 * Created by yuliya.kramarenko on 25.01.2015.
 */
@VaadinPresenter(viewName = MovieView.NAME)
public class MoviesOnScreensPresenter extends Presenter<MovieView>{

    @Autowired
    MovieService movieService;

    @EventBusListenerMethod(scope=EventScope.SESSION, filter=StartupFilter.class)
    public void onStartup(Event<Action> event) {
        getView().draw(movieService.getMoviesOnScreens());
    }
}
