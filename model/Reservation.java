package model;

import java.util.Date;

import static java.time.temporal.ChronoUnit.DAYS;


public class Reservation {

    private final Customer customer;
    private final IRoom room;
    private final Date checkInDate;
    private final Date checkOutDate;

    public Reservation(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public Customer getCustomer() {
        return customer;
    }
    public IRoom getRoom() {
        return room;
    }
    public Date getCheckInDate() {
        return checkInDate;
    }
    public Date getCheckOutDate() {
        return checkOutDate;
    }



    @Override
    public String toString() {
        Long unitConvert = 86400000L;
        return "Reservation{" +
                "customer=" + customer + "\n" +
                "room=" + room + "\n" +
                "checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate + "\n" +
                "Total Price:" + "$" + room.getRoomPrice() * (checkOutDate.getTime()-checkInDate.getTime())/unitConvert +
                '}';
    }
}

