package com.juliakram.labs.movietickets.orm.repository;

import com.juliakram.labs.movietickets.orm.dto.Movie;
import com.juliakram.labs.movietickets.orm.dto.Screen;
import com.juliakram.labs.movietickets.orm.dto.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by yuliya.kramarenko on 06.01.2015.
 */
@Repository
public interface ShowRepository extends JpaRepository<Show, Integer> {

    List<Show> findByScreenAndMovieAndDate(Screen mainScreen, Movie theHobbitMovie, LocalDate today);

    List<Show> findByScreenAndDate(Screen screen, LocalDate date);
}
