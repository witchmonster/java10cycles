package com.juliakram.labs.movietickets.ui.service;

import com.juliakram.labs.movietickets.orm.dto.Movie;
import com.juliakram.labs.movietickets.orm.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yuliya.kramarenko on 26.01.2015.
 */
@Service
public class MovieService {

    private static final int DEFAULT_MOVIES_ONSCREENS = 5;
    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> getMoviesOnScreens() {
        return getPagedMovies(0, DEFAULT_MOVIES_ONSCREENS);
    }

    private List<Movie> getPagedMovies(int page, int size) {
        return movieRepository.findAll(new PageRequest(page, size)).getContent();
    }
}
