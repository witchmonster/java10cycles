package com.juliakram.labs.movietickets.ui;

import com.juliakram.labs.movietickets.ui.presenter.Action;
import com.juliakram.labs.movietickets.ui.presenter.MainPresenter;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.VaadinUI;
import org.vaadin.spring.events.EventBus;
import org.vaadin.spring.events.EventScope;

/**
 * Created by yuliya.kramarenko on 26.01.2015.
 */
@VaadinUI
@Title("Market User Interface")
public class DefaultUI extends UI {

    private static final long serialVersionUID = 1L;

    @Autowired
    EventBus eventBus;

    @Autowired
    MainPresenter presenter;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        eventBus.publish(EventScope.SESSION, this, Action.START);
        setContent(presenter.getView());
    }

}

