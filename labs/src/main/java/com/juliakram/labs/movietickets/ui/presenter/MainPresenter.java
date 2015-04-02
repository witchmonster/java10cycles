package com.juliakram.labs.movietickets.ui.presenter;

import com.juliakram.labs.movietickets.ui.view.HomeView;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.events.Event;
import org.vaadin.spring.events.EventBusListenerMethod;
import org.vaadin.spring.events.EventScope;
import org.vaadin.spring.navigator.Presenter;
import org.vaadin.spring.navigator.VaadinPresenter;

/**
 * Created by yuliya.kramarenko on 25.01.2015.
 */
@VaadinPresenter(viewName = HomeView.NAME)
public class MainPresenter extends Presenter<HomeView> {

    @Autowired
    private HeaderPresenter header;
    @Autowired
    private SchedulePresenter schedule;
    @Autowired
    private MoviesOnScreensPresenter moviesOnScreens;
    @Autowired
    private DatePickerPresenter datePicker;

    @EventBusListenerMethod(scope= EventScope.SESSION, filter=StartupFilter.class)
    public void onStartup(Event<Action> event) {
        getView().setHeader(header.getView());
        getView().setDatePicker(datePicker.getView());
        getView().setSchedule(schedule.getView());
        getView().setMoviesOnScreens(moviesOnScreens.getView());
    }
}
