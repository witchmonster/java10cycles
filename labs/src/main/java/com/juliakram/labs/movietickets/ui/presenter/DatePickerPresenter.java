package com.juliakram.labs.movietickets.ui.presenter;

import com.juliakram.labs.movietickets.ui.view.DatePickerView;
import org.vaadin.spring.events.Event;
import org.vaadin.spring.events.EventBusListenerMethod;
import org.vaadin.spring.events.EventScope;
import org.vaadin.spring.navigator.Presenter;
import org.vaadin.spring.navigator.VaadinPresenter;

/**
 * Created by yuliya.kramarenko on 26.01.2015.
 */
@VaadinPresenter(viewName = DatePickerView.NAME)
public class DatePickerPresenter extends Presenter<DatePickerView> {

    @EventBusListenerMethod(scope=EventScope.SESSION, filter=StartupFilter.class)
    public void onStartup(Event<Action> event) {
        getView().draw();
    }
}
