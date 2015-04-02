package com.juliakram.labs.movietickets.ui.presenter;

import com.juliakram.labs.movietickets.ui.view.HeaderView;
import com.juliakram.labs.movietickets.util.Messages;
import com.vaadin.ui.Label;
import org.vaadin.spring.events.Event;
import org.vaadin.spring.events.EventBusListenerMethod;
import org.vaadin.spring.navigator.Presenter;
import org.vaadin.spring.navigator.VaadinPresenter;

/**
 * Created by yuliya.kramarenko on 25.01.2015.
 */
@VaadinPresenter(viewName = HeaderView.NAME)
public class HeaderPresenter extends Presenter<HeaderView> {

    @EventBusListenerMethod(filter=StartupFilter.class)
    public void onStartup(Event<Action> event) {
        getView().setTitle(new Label(Messages.TITLE));
        getView().setSlogan(new Label(Messages.SLOGAN));
    }
}
