package com.juliakram.labs.movietickets.orm.dto;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

/**
 * Created by yuliya.kramarenko on 06.01.2015.
 */
@Entity(name = "ticket")
public class Ticket implements Comparable<Ticket> {

    @Id
    @GeneratedValue(strategy = SEQUENCE)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "show_id")
    private Show show;

    @Column
    private int row;

    @Column
    private int seat;

    @Column
    private String userId;

    public Ticket() {
    }

    public Ticket(Show show, int row, int seat) {
        this.show = show;
        this.row = row;
        this.seat = seat;
        this.userId = null;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ticket)) return false;

        Ticket t = (Ticket) o;

        if(id == null || t.getId() == null){
            return show.equals(t.getShow())
                    && row == t.getRow()
                    && seat == t.getSeat();
        }

        return id.equals(t.id);
    }

    @Override
    public int hashCode() {
        int result = show.hashCode();
        result = 31 * result + row;
        result = 31 * result + seat;
        return result;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id='" + id + '\'' +
                ", show=" + show +
                ", row=" + row +
                ", seat=" + seat +
                '}';
    }

    @Override
    public int compareTo(Ticket o) {
        if (!show.equals(o.getShow())){
             return show.getMovie().getTitle().compareTo(o.getShow().getMovie().getTitle());
        }

        if(row != o.getRow()) return row > o.getRow() ? -1: 1;
        if(seat != o.getSeat()) return seat > o.getSeat() ? -1:1;

        return 0;
    }
}
