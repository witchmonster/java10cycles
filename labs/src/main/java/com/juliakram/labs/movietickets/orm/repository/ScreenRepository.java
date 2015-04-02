package com.juliakram.labs.movietickets.orm.repository;

import com.juliakram.labs.movietickets.orm.dto.Screen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by yuliya.kramarenko on 06.01.2015.
 */
@Repository
public interface ScreenRepository extends JpaRepository<Screen, Integer> {
}
