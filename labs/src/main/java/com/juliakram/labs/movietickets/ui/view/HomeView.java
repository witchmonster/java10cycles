package com.juliakram.labs.movietickets.ui.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.spring.UIScope;
import org.vaadin.spring.navigator.VaadinView;

import javax.annotation.PostConstruct;

/**
 * Created by yuliya.kramarenko on 25.01.2015.
 */
@UIScope
@VaadinView(name = HomeView.NAME)
public class HomeView extends VerticalLayout implements View {

    public static final String NAME = "main";

    @PostConstruct
    private void init() {
        setMargin(true);
        setSpacing(true);
        setSizeFull();
    }

    public void setHeader(Component header) {
        addComponent(header);
        setExpandRatio(header, 1);
    }

    public void setDatePicker(Component datePicker) {
        addComponent(datePicker);
        setExpandRatio(datePicker, 2);
    }

    public void setSchedule(Component body) {
        addComponent(body);
        setExpandRatio(body, 2);
    }

    public void setMoviesOnScreens(Component footer) {
        addComponent(footer);
        setExpandRatio(footer, 3);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}
