package com.juliakram.labs.movietickets.orm.dto;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

/**
 * Created by yuliya.kramarenko on 06.01.2015.
 */
@Entity
public class Screen {

    @Id
    @GeneratedValue(strategy = SEQUENCE)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "seats")
    private int seatsInARow;

    @Column(name = "rows")
    private int rows;

    @OneToMany(mappedBy = "screen")
    private List<Show> shows;

    public Screen() {
    }

    public Screen(String name, int seatsInARow, int rows) {
        this.name = name;
        this.seatsInARow = seatsInARow;
        this.rows = rows;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeatsInARow() {
        return seatsInARow;
    }

    public void setSeatsInARow(int seatsInARow) {
        this.seatsInARow = seatsInARow;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Screen)) return false;

        Screen screen = (Screen) o;

        if (!id.equals(screen.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Screen{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", rows=" + rows +
                ", seatsInARow=" + seatsInARow +
                '}';
    }

    public List<Show> getShows() {
        return shows;
    }

    public void setShows(List<Show> shows) {
        this.shows = shows;
    }
}
