package com.juliakram.labs.movietickets;

import com.juliakram.labs.movietickets.orm.dto.Ticket;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.juliakram.labs.movietickets.util.DataPopulationHelper.setUpSchedule;


@Controller
@ComponentScan
@EnableAutoConfiguration
public class Application {

    public static Map<Ticket, BookingSession> reservationMap;

    @Deprecated
    public static void main(String[] args) throws Exception {

        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

        setUpSchedule(context);

        reservationMap = new ConcurrentHashMap<>();
    }


}