package com.juliakram.labs.movietickets.ui.presenter;

import com.juliakram.labs.movietickets.ui.presenter.Action;
import com.juliakram.labs.movietickets.ui.presenter.StartupFilter;
import com.juliakram.labs.movietickets.ui.view.BodyView;
import org.vaadin.spring.events.Event;
import org.vaadin.spring.events.EventBusListenerMethod;
import org.vaadin.spring.events.EventScope;
import org.vaadin.spring.navigator.Presenter;
import org.vaadin.spring.navigator.VaadinPresenter;

/**
 * Created by yuliya.kramarenko on 25.01.2015.
 */
@VaadinPresenter(viewName = BodyView.NAME)
public class SchedulePresenter extends Presenter<BodyView>{

    @EventBusListenerMethod(scope= EventScope.SESSION, filter=StartupFilter.class)
    public void onStartup(Event<Action> event) {

    }
}
