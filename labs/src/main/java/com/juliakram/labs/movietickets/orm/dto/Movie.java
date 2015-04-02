package com.juliakram.labs.movietickets.orm.dto;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

/**
 * Created by yuliya.kramarenko on 06.01.2015.
 */
@Entity(name = "Movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = SEQUENCE)
    private Integer id;
    @Column(name = "release_date")
    private LocalDate releaseDate;
    @Column(name = "title")
    private String title;
    @Lob
    @Column(name = "poster")
    private byte[] poster;
    @OneToMany(mappedBy = "movie")
    private List<Show> shows;

    public Movie() {
    }

    public Movie(LocalDate releaseDate, String title, byte[] poster) {
        this.releaseDate = releaseDate;
        this.title = title;
        this.poster = poster;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        return this == o
                || (o instanceof Movie)
                && id.equals(((Movie) o).id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", releaseDate=" + releaseDate +
                ", title='" + title + '\'' +
                '}';
    }

    public byte[] getPoster() {
        return poster;
    }

    public void setPoster(byte[] poster) {
        this.poster = poster;
    }

    public List<Show> getShows() {
        return shows;
    }

    public void setShows(List<Show> shows) {
        this.shows = shows;
    }
}
