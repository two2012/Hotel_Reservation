package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.*;

public class HotelResource {

    private static HotelResource instance;
    private HotelResource(){}
    public static HotelResource getInstance(){
        if(instance == null){
            instance = new HotelResource();
        }
        return instance;
    }

    CustomerService cs = CustomerService.getInstance();
    ReservationService rs = ReservationService.getInstance();



    public void createACustomer(String email, String firstName, String lastName){
       cs.addCustomer(email, firstName, lastName);

    }

    public Customer getCustomer(String email){

        return cs.getCustomer(email);

    }

    public IRoom getRoom(String roomNumber){
        return rs.getRoom(roomNumber);


    }

    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate){

       return rs.reserveARoom(cs.getCustomer(customerEmail), room, checkInDate, checkOutDate);

    }

    public Collection<Reservation> getCustomersReservations(String customerEmail){
        return rs.getCustomersReservation(cs.getCustomer(customerEmail));
    }

    public Collection<IRoom> findARoom(Date checkIn, Date checkOut){

        return rs.findRooms(checkIn, checkOut);
    }

}
