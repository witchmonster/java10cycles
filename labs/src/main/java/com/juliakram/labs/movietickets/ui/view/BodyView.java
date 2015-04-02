package com.juliakram.labs.movietickets.ui.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.spring.UIScope;
import org.vaadin.spring.navigator.VaadinView;

/**
 * Created by yuliya.kramarenko on 25.01.2015.
 */
@UIScope
@VaadinView(name = BodyView.NAME)
public class BodyView extends VerticalLayout implements View {
    public static final String NAME = "body";

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}
