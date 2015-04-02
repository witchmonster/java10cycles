package com.juliakram.labs.movietickets.legacyui;

import com.juliakram.labs.movietickets.util.Messages;
import com.juliakram.labs.movietickets.orm.dto.Movie;
import com.vaadin.server.StreamResource;
import com.vaadin.ui.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by yuliya.kramarenko on 06.01.2015.
 */
public abstract class MovieView<L extends AbstractOrderedLayout> {
    private List<Movie> movies;
    private boolean showPosters;

    protected MovieView() {
    }

    public static Component drawPoster(Movie movie, String height) {
        byte[] img = movie.getPoster();
        String title = movie.getTitle();
        if (img == null) {
            return new Label(title + ".png");
        } else {
            StreamResource.StreamSource imageSource = new StreamResource.StreamSource() {
                @Override
                public InputStream getStream() {
                    return new ByteArrayInputStream(img);
                }
            };
            StreamResource imageResource = new StreamResource(imageSource, title + ".png");
            imageResource.setCacheTime(0);
            Embedded image = new Embedded(null, imageResource);
            image.markAsDirty();
            image.setHeight(height);
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            String filename = title + df.format(java.util.Calendar.getInstance().getTime()) + ".png";
            imageResource.setFilename(filename);
            return image;
        }
    }

    public static Component drawMovie(Movie movie, boolean showPoster) {
        VerticalLayout movieTitleAndImage = new VerticalLayout();
        Label title = new Label(movie.getTitle());
        title.setHeight("100px");
        movieTitleAndImage.addComponent(title);
        movieTitleAndImage.setMargin(true);
        movieTitleAndImage.setSpacing(true);
        movieTitleAndImage.setWidth("200px");
        if (showPoster) movieTitleAndImage.addComponent(drawPoster(movie, "100px"));
        return movieTitleAndImage;
    }

    public void setContent(List<Movie> movies) {
        this.movies = movies;
    }

    public void setShowPosters(boolean showPosters) {
        this.showPosters = showPosters;
    }

    public L draw() {
        L container = initContainer();
        if (movies != null && !movies.isEmpty()) {
            movies.forEach(movie -> container.addComponent(drawMovie(movie, showPosters)));
        } else {
            container.addComponent(new Label(Messages.NOTHING_TO_SHOW));
        }
        return container;
    }

    protected abstract L initContainer();
}
