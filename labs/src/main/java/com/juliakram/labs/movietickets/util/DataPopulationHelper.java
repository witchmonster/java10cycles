package com.juliakram.labs.movietickets.util;

import com.juliakram.labs.movietickets.orm.dto.Movie;
import com.juliakram.labs.movietickets.orm.dto.Screen;
import com.juliakram.labs.movietickets.orm.dto.Show;
import com.juliakram.labs.movietickets.orm.repository.MovieRepository;
import com.juliakram.labs.movietickets.orm.repository.ScreenRepository;
import com.juliakram.labs.movietickets.orm.repository.ShowRepository;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

import static com.juliakram.labs.movietickets.util.DateHelper.TODAY;
import static com.juliakram.labs.movietickets.util.LayoutHelper.imageToByteArray;
import static java.time.LocalTime.of;

/**
 * Created by yuliya.kramarenko on 23.01.2015.
 */
public class DataPopulationHelper {

    public static void setUpSchedule(ConfigurableApplicationContext context) {
        ScreenRepository screenRepository = context.getBean(ScreenRepository.class);
        Screen mainScreen = new Screen("Main screen", 20, 10);
        Screen blueScreen = new Screen("Blue screen", 10, 7);

        screenRepository.save(mainScreen);
        screenRepository.save(blueScreen);

        populateTheHobbitMovie(context, mainScreen);
        populateExodusMovie(context, mainScreen);
        populateSeventhSonMovie(context, blueScreen);

    }

    private static void populateTheHobbitMovie(ConfigurableApplicationContext context, Screen mainScreen) {
        MovieRepository movieRepository = context.getBean(MovieRepository.class);
        final Movie theHobbitMovie = new Movie(LocalDate.of(2014, 12, 11),
                "The Hobbit: The Battle of the Five Armies",
                imageToByteArray("hobbit.jpg"));
        movieRepository.save(theHobbitMovie);
        List<LocalTime> hobbitSchedule = new LinkedList<>();
        hobbitSchedule.add(of(10, 30));
        hobbitSchedule.add(of(13, 30));
        hobbitSchedule.add(of(16, 30));
        hobbitSchedule.add(of(19, 30));

        populateScheduleForWeekAhead(context, mainScreen, theHobbitMovie, hobbitSchedule);
    }

    private static void populateExodusMovie(ConfigurableApplicationContext context, Screen mainScreen) {
        MovieRepository movieRepository = context.getBean(MovieRepository.class);
        final Movie theExodusMovie = new Movie(LocalDate.of(2014, 12, 12),
                "Exodus: Gods and Kings",
                imageToByteArray("exodus.jpg"));
        movieRepository.save(theExodusMovie);

        List<LocalTime> exodusSchedule = new LinkedList<>();
        exodusSchedule.add(of(22, 30));

        populateMovieSchedule(context, mainScreen, theExodusMovie, TODAY.plusDays(2), exodusSchedule);
    }

    private static void populateSeventhSonMovie(ConfigurableApplicationContext context, Screen blueScreen) {
        MovieRepository movieRepository = context.getBean(MovieRepository.class);
        final Movie theSeventhSonMovie = new Movie(LocalDate.of(2015, 1, 1),
                "The Seventh Son",
                imageToByteArray("seventh_son.jpg"));
        movieRepository.save(theSeventhSonMovie);
        List<LocalTime> seventhSonSchedule = new LinkedList<>();
        seventhSonSchedule.add(of(14, 30));
        seventhSonSchedule.add(of(16, 30));
        seventhSonSchedule.add(of(18, 30));
        seventhSonSchedule.add(of(20, 30));

        populateScheduleForWeekAhead(context, blueScreen, theSeventhSonMovie, seventhSonSchedule);
    }

    private static void populateScheduleForWeekAhead(ConfigurableApplicationContext context, Screen screen, Movie movie, List<LocalTime> schedule) {
        for (int i = 0; i < 7; i++) {
            populateMovieSchedule(context, screen, movie, TODAY.plusDays(i), schedule);
        }
    }

    private static void populateMovieSchedule(ConfigurableApplicationContext context, Screen screen, Movie movie, LocalDate date, List<LocalTime> schedule) {

        ShowRepository showRepository = context.getBean(ShowRepository.class);
        schedule.forEach(time -> showRepository.save(new Show(time, date, movie, screen)));
    }

}
