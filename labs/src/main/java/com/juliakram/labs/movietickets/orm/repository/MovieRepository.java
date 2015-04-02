package com.juliakram.labs.movietickets.orm.repository;

import com.juliakram.labs.movietickets.orm.dto.Movie;
import com.juliakram.labs.movietickets.orm.dto.Screen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by yuliya.kramarenko on 06.01.2015.
 */
@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {


    @Query("SELECT m FROM Movie m JOIN m.shows s WHERE s.date = :show_date AND s.screen = :screen")
    List<Movie> findByScreenAndShowDate(@Param("screen") Screen screen, @Param("show_date") LocalDate showDate);

}
