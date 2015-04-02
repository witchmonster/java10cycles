package com.juliakram.labs.movietickets.ui.view;

import com.juliakram.labs.movietickets.util.Messages;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.VerticalLayout;

import static com.juliakram.labs.movietickets.util.DateHelper.TODAY;
import static com.juliakram.labs.movietickets.util.DateHelper.toDate;

/**
 * Created by yuliya.kramarenko on 26.01.2015.
 */
public class DatePickerView extends VerticalLayout implements View {
    public static final String NAME = "datePicker";

    public void draw() {

        PopupDateField calendarPicker = new PopupDateField();

        calendarPicker.setRangeStart(toDate(TODAY));
        calendarPicker.setRangeEnd(toDate(TODAY.plusDays(7)));
        calendarPicker.setDateOutOfRangeMessage(Messages.DATE_OUT_OF_RANGE);
        calendarPicker.setValue(toDate(TODAY));

//        calendarPicker.addValueChangeListener(event -> {
//            Date changedDate = refineDate((Date) event.getProperty().getValue());
//            calendarPicker.setValue(changedDate);
//            main.removeComponent(main.getComponent(2));
//            main.addComponent(renderScheduleFor(toLocalDate(changedDate)), 2);
//        });
        calendarPicker.setImmediate(true);

        addComponent(new Label(Messages.PICK_A_DATE));
        addComponent(calendarPicker);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}
