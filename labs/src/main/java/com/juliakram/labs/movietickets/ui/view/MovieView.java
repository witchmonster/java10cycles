package com.juliakram.labs.movietickets.ui.view;

import com.juliakram.labs.movietickets.orm.dto.Movie;
import com.juliakram.labs.movietickets.util.Messages;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.StreamResource;
import com.vaadin.ui.*;
import org.vaadin.spring.UIScope;
import org.vaadin.spring.navigator.VaadinView;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by yuliya.kramarenko on 25.01.2015.
 */
@UIScope
@VaadinView(name = MovieView.NAME)
public class MovieView extends HorizontalLayout implements View {
    public static final String NAME = "footer";


    public void draw(List<Movie> movies) {
        if (movies != null && !movies.isEmpty()) {
            movies.forEach(movie -> addComponent(drawMovie(movie)));
        } else {
            addComponent(new Label(Messages.NOTHING_TO_SHOW));
        }
    }

    public Component drawMovie(Movie movie) {
        VerticalLayout movieTitleAndImage = new VerticalLayout();
        Label title = new Label(movie.getTitle());
        title.setHeight("100px");
        movieTitleAndImage.addComponent(title);
        movieTitleAndImage.setMargin(true);
        movieTitleAndImage.setSpacing(true);
        movieTitleAndImage.setWidth("200px");
        movieTitleAndImage.addComponent(drawPoster(movie, "100px"));
        return movieTitleAndImage;
    }

    public Component drawPoster(Movie movie, String height) {
        byte[] img = movie.getPoster();
        if (img == null) {
            return new Label(movie.getTitle() + ".png");
        }
        StreamResource.StreamSource imageSource = () -> new ByteArrayInputStream(img);
        StreamResource imageResource = new StreamResource(imageSource, movie.getTitle() + ".png");
        imageResource.setCacheTime(0);
        Embedded image = new Embedded(null, imageResource);
        image.markAsDirty();
        image.setHeight(height);
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String filename = movie.getTitle() + df.format(java.util.Calendar.getInstance().getTime()) + ".png";
        imageResource.setFilename(filename);
        return image;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }
}
