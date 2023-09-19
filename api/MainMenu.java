package api;


import model.IRoom;
import model.Reservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;

public class MainMenu {


    private static MainMenu instance;
    private MainMenu(){}
    public static MainMenu getInstance(){
        if (instance == null){
            instance = new MainMenu();
        }
        return instance;
    }

    private static String menuOption = null;
    private static Scanner customerInput = new Scanner(System.in);
    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    private static HotelResource hr = HotelResource.getInstance();


    private static Date checkIn;
    private static Date checkOut;
    private static String roomNumber;
    private static String customerEmail;
    private static String customerFirstName;
    private static String customerLastName;


    public void MainMenu(){

        System.out.println("Welcome to the Hotel Reservation Application");

        do{
            System.out.println("-----------Main Menu------------");
            System.out.println("1.Find and reserve a room.");
            System.out.println("2.See my reservations.");
            System.out.println("3.Create an account.");
            System.out.println("4.Admin.");
            System.out.println("5.Exit.");
            System.out.println("----------------------------------------------");
            System.out.println("Please select a number for the menu option");
            menuOption = customerInput.nextLine();

            switch (menuOption){
                case "1":
                    findAndReserveRoom();
                    break;

                case "2":
                    seeMyReservations();
                    break;
                case "3":
                    createAnAccount();
                    break;
                case "4":
                    admin();
                    break;
                case "5":
                    menuOption = "5";
                    break;
                default:
                    System.out.println("Error! Invalid input, please try again!");
            }

        }while(!(menuOption == "5"));
    }

    public static void findAndReserveRoom(){
        String hasAccount;
        String bookRoom = "n";
        boolean dFormat;

        System.out.println("Please enter your check in date in form of yyyy-mm-dd (example:2022-01-01).");
        do {
            dFormat = false;
            try {
                checkIn = formatter.parse(customerInput.nextLine());
            } catch (ParseException e) {
                System.out.println("Date format is incorrect! Please try again. yyyy-mm-dd");
                dFormat = true;
            }
        } while (dFormat);

        System.out.println("Please enter your check out date in form of yyyy-mm-dd (example:2022-01-01).");
        do {
            dFormat = false;
            try {
                checkOut = formatter.parse(customerInput.nextLine());
            } catch (ParseException e) {
                System.out.println("Date format is incorrect! Please try again. yyyy-mm-dd");
                dFormat = true;
            }
        } while (dFormat);

        Collection<IRoom> rooms = hr.findARoom(checkIn, checkOut);
        Collection<IRoom> recommendedRooms = null;
        if(rooms.isEmpty()) {
            System.out.println("No rooms are available on selected date.");
            Calendar in = Calendar.getInstance();
            Calendar out = Calendar.getInstance();
            in.setTime(checkIn);
            out.setTime(checkOut);
            in.add(Calendar.DATE, 7);
            out.add(Calendar.DATE, 7);

            checkIn = in.getTime();
            checkOut = out.getTime();
            recommendedRooms = hr.findARoom(checkIn, checkOut);
            if(!recommendedRooms.isEmpty()){
                System.out.println("---------Recommended Rooms 7 days plus to check-in and checkout date----------");
                for (IRoom room : recommendedRooms) {
                    System.out.println(room);
                }
            } else {
                System.out.println("No recommended rooms are available on selected date plus 7 days.");
            }

        }else {
            System.out.println("-----------Available Rooms------------");
            for (IRoom room : rooms) {
                System.out.println(room);
            }
        }

        if (!rooms.isEmpty() || !recommendedRooms.isEmpty()){
            System.out.println("----------------------------------------------");
            System.out.println("Would you like book a room? y/n");
            bookRoom = customerInput.nextLine().toLowerCase();
        }


        if(bookRoom.equals("y")){

            do{
                System.out.println("Do you have account? y/n");
                hasAccount = customerInput.nextLine().toLowerCase();

            } while (!(hasAccount.equalsIgnoreCase("y"))&&!(hasAccount.equalsIgnoreCase("n")));


            if (hasAccount.equals("y")){
                //have account
                System.out.println("Please enter your email. (eg. name@domain.com)");
                customerEmail = customerInput.nextLine();
                if(hr.getCustomer(customerEmail) == null){

                    customerEmail = null;
                }

            } else if(hasAccount.equalsIgnoreCase("n")) {

                createAnAccount();
                System.out.println("------------------------------------------------");
            }

            if(!rooms.isEmpty() || !recommendedRooms.isEmpty()){
                if(!(customerEmail == null)){
                    System.out.println("Please enter the room number that you want book.");
                    roomNumber = customerInput.nextLine();

                    if(recommendedRooms == null){
                        if(rooms.contains(hr.getRoom(roomNumber)) ){
                            Reservation reservation = hr.bookARoom(customerEmail, hr.getRoom(roomNumber), checkIn, checkOut);
                            System.out.println("Your reservation is completed!");
                            System.out.println(reservation);
                        } else {
                            System.out.println("The room number you entered is not available, please try again.");
                        }
                    } else {
                        if(recommendedRooms.contains(hr.getRoom(roomNumber))){
                            Reservation reservation = hr.bookARoom(customerEmail, hr.getRoom(roomNumber), checkIn, checkOut);
                            System.out.println("Your reservation is completed!");
                            System.out.println(reservation);
                        }
                    }

                }
            }

        }else if (bookRoom.equalsIgnoreCase("n")){

        } else {
            System.out.println("Error! invalided input");
        }
    }

    public static void seeMyReservations(){
        System.out.println("Please enter your email.");
        customerEmail = customerInput.nextLine();
        Collection<Reservation> customerReservations = hr.getCustomersReservations(customerEmail);
        for (Reservation reservation :
                customerReservations) {
            System.out.println(reservation);


        }
    }

    public static void createAnAccount(){
        boolean emailFormat = false;
        System.out.println("Please enter your first name to create an account.");
        customerFirstName = customerInput.nextLine();
        System.out.println("Please enter your last name to create an account.");
        customerLastName = customerInput.nextLine();

        do{
            emailFormat = false;
            System.out.println("Please enter your email to create an account.");
            customerEmail = customerInput.nextLine(); //check email format!!

            try{
                hr.createACustomer(customerEmail, customerFirstName, customerLastName);
            }catch (Exception e){
                emailFormat = true;
                System.out.println("Incorrect email format (example:name@domin.com)");
                System.out.println("------------------------------------------------");
            }
        } while (emailFormat);
        System.out.println("Your account is saved!");

    }

    public static void admin(){
        AdminMenu.getInstance().Admin();
    }

}
