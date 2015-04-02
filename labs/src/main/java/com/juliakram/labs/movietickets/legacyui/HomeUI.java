package com.juliakram.labs.movietickets.legacyui;

import com.juliakram.labs.movietickets.Application;
import com.juliakram.labs.movietickets.BookingSession;
import com.juliakram.labs.movietickets.orm.dto.Movie;
import com.juliakram.labs.movietickets.orm.dto.Screen;
import com.juliakram.labs.movietickets.orm.dto.Show;
import com.juliakram.labs.movietickets.orm.dto.Ticket;
import com.juliakram.labs.movietickets.orm.repository.MovieRepository;
import com.juliakram.labs.movietickets.orm.repository.ScreenRepository;
import com.juliakram.labs.movietickets.orm.repository.ShowRepository;
import com.juliakram.labs.movietickets.orm.repository.TicketRepository;
import com.juliakram.labs.movietickets.util.Messages;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.vaadin.spring.VaadinSessionScope;
import org.vaadin.spring.VaadinUI;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.juliakram.labs.movietickets.util.DateHelper.*;
import static com.juliakram.labs.movietickets.util.Messages.*;
import static com.juliakram.labs.movietickets.legacyui.MovieView.drawPoster;
import static java.lang.String.format;
import static java.time.format.DateTimeFormatter.ofPattern;

/**
 * Created by yuliya.kramarenko on 06.01.2015.
 */
@Title("BOOK MOVIE TICKETS")
@Theme("valo")
@VaadinUI(path = "/old")
@VaadinSessionScope
public class HomeUI extends UI {

    public static final int BOOKING_DELAY = 20 * 1000;
    private static final int DEFAULT_RECENT_MOVIES = 5;
    @Autowired
    private MovieRepository movieDao;

    @Autowired
    private ShowRepository showDao;

    @Autowired
    private ScreenRepository screenDao;

    @Autowired
    private TicketRepository ticketDao;

    private VerticalLayout main;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        initMain();
        setContent(main);
    }

    private void initMain() {
        main = new VerticalLayout();

        main.addComponent(renderHeader(), 0);
        main.addComponent(renderDatePicker(), 1);
        main.addComponent(renderScheduleFor(TODAY), 2);
        main.addComponent(renderMoviesOnScreens(), 3);

        main.setMargin(true);
    }


    private VerticalLayout renderHeader() {
        VerticalLayout header = new VerticalLayout();
        header.setMargin(true);
        header.setSpacing(true);

        Label title = new Label(TITLE);
        Label slogan = new Label(SLOGAN);

        header.addComponent(title);
        header.addComponent(slogan);
        return header;
    }

    private Component renderMoviesOnScreens() {
        HorizontalMovieList movieView = new HorizontalMovieList();
        movieView.setContent(movieDao.findAll(new PageRequest(0, DEFAULT_RECENT_MOVIES)).getContent());
        movieView.setShowPosters(true);

        VerticalLayout moviesOnScreens = new VerticalLayout();
        moviesOnScreens.addComponent(new Label(Messages.CURRENTLY_ON_SCREENS));
        moviesOnScreens.addComponent(movieView.draw());

        return moviesOnScreens;
    }

    private VerticalLayout renderDatePicker() {

        VerticalLayout layout = new VerticalLayout();

        PopupDateField calendarPicker = new PopupDateField();

        calendarPicker.setRangeStart(toDate(TODAY));
        calendarPicker.setRangeEnd(toDate(TODAY.plusDays(7)));
        calendarPicker.setDateOutOfRangeMessage(Messages.DATE_OUT_OF_RANGE);
        calendarPicker.setValue(toDate(TODAY));

        calendarPicker.addValueChangeListener(event -> {
            Date changedDate = refineDate((Date) event.getProperty().getValue());
            calendarPicker.setValue(changedDate);
            main.removeComponent(main.getComponent(2));
            main.addComponent(renderScheduleFor(toLocalDate(changedDate)), 2);
        });
        calendarPicker.setImmediate(true);

        layout.addComponent(new Label(Messages.PICK_A_DATE));
        layout.addComponent(calendarPicker);

        return layout;
    }

    private VerticalLayout renderScheduleFor(LocalDate date) {
        List<Screen> screens = screenDao.findAll();
        VerticalLayout showTimes = new VerticalLayout();
        showTimes.setMargin(true);
        showTimes.setSpacing(true);

        showTimes.addComponent(new Label(SHOWTIMES));
        screens.forEach(screen -> {
            Set<Movie> movies = movieDao.findByScreenAndShowDate(screen, date).stream().collect(Collectors.toSet());
            showTimes.addComponent(new Label(screen.getName()));
            showTimes.addComponent(drawScheduleForScreen(date, screen, movies));
        });
        return showTimes;
    }

    private VerticalLayout drawScheduleForScreen(LocalDate date, Screen screen, Set<Movie> movies) {
        VerticalLayout scheduleForScreen = new VerticalLayout();
        scheduleForScreen.setSpacing(true);
        movies.forEach(movie -> scheduleForScreen.addComponent(drawScheduleForMovie(date, screen, movie)));
        return scheduleForScreen;
    }

    private VerticalLayout drawScheduleForMovie(LocalDate date, Screen screen, Movie movie) {
        VerticalLayout scheduleForMovie = new VerticalLayout();

        scheduleForMovie.addComponent(new Label(movie.getTitle()));
        scheduleForMovie.addComponent(drawShowsForMovie(date, screen, movie));
        return scheduleForMovie;
    }

    private HorizontalLayout drawShowsForMovie(LocalDate date, Screen screen, Movie movie) {
        HorizontalLayout showsForMovie = new HorizontalLayout();
        List<Show> showsForScreen = showDao.findByScreenAndMovieAndDate(screen, movie, date);
        showsForScreen.forEach(show -> showsForMovie.addComponent(drawBookButton(show)));
        return showsForMovie;
    }


    private Button drawBookButton(Show show) {
        Button button = new Button(show.getTime().format(ofPattern("HH:mm")));
        button.addClickListener(event -> {
            Window sub = initBookATicketWindow(show);
            UI.getCurrent().addWindow(sub);
        });
        return button;
    }

    private Window initBookATicketWindow(Show show) {
        Window bookATicketWindow = new Window(format(Messages.BOOK_A_TICKET_FOR_MOVIE_AND_TIME,
                show.getMovie().getTitle(),
                show.getTime().format(ofPattern("HH:mm"))));
        bookATicketWindow.center();

        VerticalLayout content = drawBookScreen(bookATicketWindow, show);

        bookATicketWindow.setContent(content);

        bookATicketWindow.setClosable(false);
        bookATicketWindow.setModal(true);
        bookATicketWindow.setDraggable(false);
        bookATicketWindow.setResizable(false);

        Button cancel = new Button("CLOSE");
        cancel.addClickListener(event -> bookATicketWindow.close());
        content.addComponent(cancel);
        return bookATicketWindow;
    }

    private VerticalLayout drawBookScreen(Window window, Show show) {
        VerticalLayout content = new VerticalLayout();

        Component poster = drawPoster(show.getMovie(), "300px");
        content.addComponent(poster);
        content.setComponentAlignment(poster, Alignment.TOP_CENTER);

        IntStream.rangeClosed(1, show.getScreen().getRows()).forEach(row -> content.addComponent(drawBookingRow(window, show, row)));

        content.setSpacing(true);
        content.setMargin(true);

        return content;
    }

    private HorizontalLayout drawBookingRow(Window window, Show show, int row) {
        HorizontalLayout rowLayout = new HorizontalLayout();
        Label rowLabel = new Label(row + ":");
        rowLabel.setWidth("25px");
        rowLayout.addComponent(rowLabel);
        IntStream.rangeClosed(1, show.getScreen().getSeatsInARow()).forEach(seat -> {
            final Ticket ticket = new Ticket(show, row, seat);
            Button bookButton = drawBookButton(window, ticket);
            bookButton.setEnabled(isAvailable(ticket));
            rowLayout.addComponent(bookButton);
        });
        return rowLayout;
    }


    private Button drawBookButton(Window window, Ticket ticket) {
        Button button = new Button(String.valueOf(ticket.getSeat()));
        button.addClickListener(event -> {
            if (isAvailable(ticket)) {
                reserveTicket(ticket);
                startBookingTimer(ticket);
                UI.getCurrent().addWindow(drawConfirmationWindow(window, ticket));
            } else {
                window.close();
                UI.getCurrent().addWindow(drawTicketBookingFailed(ticket));
            }
        });
        return button;
    }

    private void reserveTicket(Ticket chosenTicket) {
        Application.reservationMap.put(chosenTicket, myBookingSession());
    }

    private void startBookingTimer(final Ticket chosenTicket) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                expireThisSession(chosenTicket);
            }
        }, BOOKING_DELAY);
    }

    private boolean isAvailable(Ticket ticket) {
        return !isBooked(ticket)
                && !isReserved(ticket);
    }

    private void expireThisSession(Ticket ticket) {
        if (isReservedByMe(ticket)) {
            Application.reservationMap.replace(ticket, myExpiredBookingSession());
        }
    }

    private BookingSession myExpiredBookingSession() {
        return new BookingSession(VaadinSession.getCurrent().getSession().getId(), BookingSession.EXPIRED);
    }

    private BookingSession myBookingSession() {
        return new BookingSession(VaadinSession.getCurrent().getSession().getId(), BookingSession.ALIVE);
    }

    private Window drawConfirmationWindow(Window window, Ticket ticket) {
        Window confirmationWindow = new Window(BOOKING_CONFIRMATION);
        confirmationWindow.center();

        VerticalLayout content = new VerticalLayout();
        content.setMargin(true);
        content.setSpacing(true);
        confirmationWindow.setContent(content);

        confirmationWindow.setClosable(false);
        confirmationWindow.setModal(true);
        confirmationWindow.setDraggable(false);
        confirmationWindow.setResizable(false);

        content.addComponent(new Label(format(DO_YOU_CONFIRM_TICKET,
                ticket.getShow().getMovie().getTitle(),
                ticket.getShow().getTime().format(ofPattern("HH:mm")),
                ticket.getRow(),
                ticket.getSeat())));


        Button confirm = new Button("CONFIRM");
        confirm.addClickListener(event -> {
            if (isReservedByMe(ticket)) {
                book(ticket);
                liftReservationFor(ticket);
                confirmationWindow.close();
                window.close();
                UI.getCurrent().addWindow(drawTicketBooked(ticket));
            } else if (wasReservedByMe(ticket)) {
                Notification.show(Messages.BOOKING_SESSION_EXPIRED, Notification.Type.ERROR_MESSAGE);
                liftReservationFor(ticket);
                confirmationWindow.close();
                window.close();
            } else {
                confirmationWindow.close();
                window.close();
                UI.getCurrent().addWindow(drawTicketBookingFailed(ticket));
                liftReservationFor(ticket);
            }
        });

        Button cancel = new Button("CANCEL");
        cancel.addClickListener(event -> {
            liftReservationFor(ticket);
            confirmationWindow.close();
        });

        HorizontalLayout buttons = new HorizontalLayout();
        buttons.setMargin(true);
        buttons.setSpacing(true);
        buttons.addComponent(confirm);
        buttons.addComponent(cancel);
        content.addComponent(buttons);

        return confirmationWindow;
    }

    private void book(Ticket ticket) {
        ticketDao.save(ticket);
    }

    private void liftReservationFor(Ticket ticket) {
        Application.reservationMap.remove(ticket);
    }

    private boolean wasReservedByMe(Ticket ticket) {
        return wasReserved(ticket)
                && isSessionMine(ticket);
    }

    private boolean wasReserved(Ticket ticket) {
        return Application.reservationMap.containsKey(ticket) && !Application.reservationMap.get(ticket).isAlive();
    }

    private boolean isBooked(Ticket ticket) {
        return ticketDao.findByShowAndRowAndSeat(ticket.getShow(), ticket.getRow(), ticket.getSeat()) != null;
    }

    private boolean isReservedByMe(Ticket ticket) {
        return isReserved(ticket)
                && isSessionMine(ticket);
    }

    private boolean isSessionMine(Ticket ticket) {
        return Application.reservationMap.get(ticket).getUserId().equals(VaadinSession.getCurrent().getSession().getId());
    }

    private boolean isReserved(Ticket ticket) {
        return Application.reservationMap.containsKey(ticket) && Application.reservationMap.get(ticket).isAlive();
    }

    private Window drawTicketBooked(Ticket ticket) {
        Window ticketBookedWindow = new Window(THANK_YOU_BOOKED);

        ticketBookedWindow.center();
        ticketBookedWindow.setClosable(false);
        ticketBookedWindow.setResizable(false);
        ticketBookedWindow.setModal(true);

        VerticalLayout content = new VerticalLayout();

        content.setMargin(true);
        content.setSpacing(true);

        ticketBookedWindow.setContent(content);

        content.addComponent(new Label("Booking ID is " + ticket.getId()));
        content.addComponent(new Label(PLEASE_WRITE_DOWN));
        content.addComponent(new Label("Movie: " + ticket.getShow().getMovie().getTitle()));
        content.addComponent(new Label("Screen: " + ticket.getShow().getScreen().getName()));
        content.addComponent(new Label("Date: " + ticket.getShow().getDate()));
        content.addComponent(new Label("Time: " + ticket.getShow().getTime()));
        content.addComponent(new Label("Row: " + ticket.getRow()));
        content.addComponent(new Label("Seat: " + ticket.getSeat()));

        Button ok = new Button("OK");
        ok.addClickListener(event -> ticketBookedWindow.close());
        content.addComponent(ok);

        return ticketBookedWindow;
    }


    private Window drawTicketBookingFailed(Ticket ticket) {
        Window errorWindow = new Window(SORRY_NOT_BOOKED);

        errorWindow.center();
        errorWindow.setClosable(false);
        errorWindow.setResizable(false);
        errorWindow.setModal(true);

        VerticalLayout content = new VerticalLayout();

        content.setMargin(true);
        content.setSpacing(true);

        errorWindow.setContent(content);

        content.addComponent(new Label(format(TICKET_IS_UNAVAILABLE_AT_THE_MOMENT, ticket.getRow(), ticket.getSeat())));

        Button ok = new Button("OK");
        ok.addClickListener(event -> errorWindow.close());
        content.addComponent(ok);

        return errorWindow;
    }
}
