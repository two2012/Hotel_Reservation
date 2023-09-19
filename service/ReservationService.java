package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class ReservationService {

    private static ReservationService instance;
    private ReservationService(){}
    public static ReservationService getInstance(){
        if(instance == null){
            instance = new ReservationService();
        }
        return instance;
    }
    Map<String, IRoom> allRooms = new HashMap<String, IRoom>();
    Collection<Reservation> allReservations = new ArrayList<Reservation>();

    public Collection<IRoom> getAllRooms() {
        if(allRooms.isEmpty()) {
            System.out.println("No room found.");
        }
        return allRooms.values();
    }

    public void addRoom(IRoom room){
        allRooms.put(room.getRoomNumber(), room);
    }

    public IRoom getRoom(String roomId){
        if(allRooms.containsKey(roomId)){
            return allRooms.get(roomId);
        } else {
            System.out.println("No room found.");
            return null;
        }
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        allReservations.add(reservation);

        return reservation;
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate){
        List<IRoom> foundRooms = new ArrayList<IRoom>();
        for (IRoom room : allRooms.values()) {
            foundRooms.add(room);
        }
        for(Reservation reservedRoom : allReservations){
            if(!(reservedRoom.getCheckInDate().after(checkOutDate) || reservedRoom.getCheckOutDate().before(checkInDate))){
                foundRooms.remove(reservedRoom.getRoom());
            }
        }

        return foundRooms;
    }

    public Collection<Reservation> getCustomersReservation(Customer customer){
        List<Reservation> customerReservations = new ArrayList<Reservation>();
        for (Reservation customerReservation : allReservations) {
            if(customerReservation.getCustomer().equals(customer)){
                customerReservations.add(customerReservation);
            }
        }
        return customerReservations;
    }

    public void printAllReservation(){
        if (allReservations.isEmpty()){
            System.out.println("No reservation found.");
        }else {
            for (Reservation reservation : allReservations) {
                System.out.println(reservation);
            }
        }
    }

    void defaultMethod(){
        System.out.println("this is a example of default method.");
    }
}
