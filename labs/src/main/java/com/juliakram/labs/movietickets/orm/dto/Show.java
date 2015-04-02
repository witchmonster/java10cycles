package com.juliakram.labs.movietickets.orm.dto;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

/**
 * Created by yuliya.kramarenko on 06.01.2015.
 */
@Entity
public class Show {

    @Id
    @GeneratedValue(strategy = SEQUENCE)
    private Integer id;

    @Column(name = "time")
    private LocalTime time;

    @Column(name = "date")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "screen_id")
    private Screen screen;

    @OneToMany(mappedBy = "show")
    private List<Ticket> tickets;

    public Show() {
    }

    public Show(LocalTime time, LocalDate date, Movie movie, Screen screen) {
        this.time = time;
        this.date = date;
        this.movie = movie;
        this.screen = screen;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Override
    public boolean equals(Object o) {
        return this == o
                ||  o instanceof Show
                    && id.equals(((Show) o).id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Show{" +
                "id='" + id + '\'' +
                ", time=" + time +
                ", date=" + date +
                ", movie=" + movie +
                ", screen=" + screen +
                '}';
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
