package com.juliakram.labs.movietickets.orm.repository;

import com.juliakram.labs.movietickets.orm.dto.Show;
import com.juliakram.labs.movietickets.orm.dto.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by yuliya.kramarenko on 06.01.2015.
 */
@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    Ticket findByShowAndRowAndSeat(Show show, int row, int seat);
}
