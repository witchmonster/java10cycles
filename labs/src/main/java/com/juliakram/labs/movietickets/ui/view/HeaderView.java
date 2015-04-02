package com.juliakram.labs.movietickets.ui.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.spring.UIScope;
import org.vaadin.spring.navigator.VaadinView;

/**
 * Created by yuliya.kramarenko on 25.01.2015.
 */
@UIScope
@VaadinView(name = HeaderView.NAME)
public class HeaderView extends VerticalLayout implements View {
    public static final String NAME = "header";

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }

    public void setTitle(Component title) {
        addComponent(title);
    }

    public void setSlogan(Component slogan) {
        addComponent(slogan);
    }
}
