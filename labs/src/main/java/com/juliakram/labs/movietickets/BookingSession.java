package com.juliakram.labs.movietickets;

/**
 * Created by yuliya.kramarenko on 23.01.2015.
 */
public class BookingSession {

    public static final boolean EXPIRED = false;
    public static final boolean ALIVE = true;

    private String userId;
    private boolean bookingSessionStatus;

    public BookingSession(String userId, boolean bookingSessionStatus) {
        this.userId = userId;
        this.bookingSessionStatus = bookingSessionStatus;
    }

    public boolean isAlive() {
        return bookingSessionStatus;
    }

    public void setBookingSessionStatus(boolean bookingSessionStatus) {
        this.bookingSessionStatus = bookingSessionStatus;
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
        if (!(o instanceof BookingSession)) return false;

        BookingSession bookingSesssion = (BookingSession) o;

        if (bookingSessionStatus != bookingSesssion.bookingSessionStatus) return false;
        if (!userId.equals(bookingSesssion.userId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId.hashCode();
        result = 31 * result + (bookingSessionStatus ? 1 : 0);
        return result;
    }
}
