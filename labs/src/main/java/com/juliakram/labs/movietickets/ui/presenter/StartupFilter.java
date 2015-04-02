package com.juliakram.labs.movietickets.ui.presenter;

import org.vaadin.spring.events.EventBusListenerMethodFilter;

/**
 * Created by yuliya.kramarenko on 25.01.2015.
 */
public class StartupFilter implements EventBusListenerMethodFilter {

    @Override
    public boolean filter(Object payload) {
        boolean result = false;
        if (Action.class.isAssignableFrom(payload.getClass())) {
            Action action = (Action) payload;
            if (action.equals(Action.START)) {
                result = true;
            }
        }
        return result;
    }

}