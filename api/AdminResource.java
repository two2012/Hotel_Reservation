package api;

import model.*;
import service.CustomerService;
import service.ReservationService;

import java.util.*;

import static model.RoomType.SINGLE;

public class AdminResource {

    private static AdminResource instance;
    private AdminResource(){}
    public static AdminResource getInstance(){
        if(instance == null){
            instance = new AdminResource();
        }
        return instance;
    }

    CustomerService cs = CustomerService.getInstance();
    ReservationService rs = ReservationService.getInstance();


    public Customer getCustomer(String email){
        return cs.getCustomer(email);

    }

    public void addRoom(List<IRoom> rooms){
        for (IRoom room : rooms) {
            rs.addRoom(room);
        }
    }

    public Collection<IRoom> getAllRooms(){
        return rs.getAllRooms();
    }

    public Collection<Customer> getAllCustomers(){
        return cs.getAllCustomers();
    }

    public void displayAllReservations(){
        rs.printAllReservation();
    }

}
