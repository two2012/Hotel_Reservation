package api;

import model.*;

import java.util.*;

public class AdminMenu {

    private static AdminMenu instance;
    private AdminMenu(){}
    public static AdminMenu getInstance(){
        if (instance == null){
            instance = new AdminMenu();
        }
        return instance;
    }


    private static AdminResource ar = AdminResource.getInstance();
    private static String menuOption = null;
    private static Scanner scanner = new Scanner(System.in);
    private static String roomNumber;
    private static Double price;
    private static RoomType type;


    public void Admin() {


       do{

           System.out.println("--------Admin Menu--------");
           System.out.println("1.See all Customers.");
           System.out.println("2.See all Rooms.");
           System.out.println("3.See all Reservations.");
           System.out.println("4.Add a Room.");
           System.out.println("5.Back to Main Menu");
           System.out.println("----------------------------------------------");
           System.out.println("Please select a number for the menu option");
           menuOption = scanner.nextLine();
           switch (menuOption){

               case "1":
                   seeAllCustomers();
                   break;
               case "2":
                   seeAllRooms();
                   break;
               case "3":
                   seeAllReservations();
                   break;
               case "4":
                   addARoom();
                   break;
               case "5":
                   menuOption = "5";
                   break;
               default:
                   System.out.println("Error! Invalid input, please try again!");
           }

       }while(!(menuOption == "5"));
    }

    public static void seeAllCustomers(){
        for (Customer customer : ar.getAllCustomers()) {
            System.out.println(customer);
        }

    }

    public static void seeAllRooms(){
        Collection<IRoom> rooms = ar.getAllRooms();
        if (rooms.isEmpty()){
            System.out.println("No room found.");
        } else {
            for (IRoom room : rooms) {
                System.out.println(room);
            }
        }
    }

    public static void seeAllReservations(){
        ar.displayAllReservations();
    }

    public static void addARoom(){
        System.out.println("----------Add a Room----------");
        boolean continueAdd = false;
        String addRoom;
        List<IRoom> rooms = new ArrayList<IRoom>();
        do{
            System.out.println("Please enter room number.");
            roomNumber = scanner.nextLine();

            do {
                System.out.println("Please enter room type. single/double");
                type = RoomType.valueCheck(scanner.nextLine());
                System.out.println("----------------------------------------------");

            }while(type == null);

            boolean isDouble;
            do{
                System.out.println("Please enter the price.");
                isDouble = false;
                try{
                    price = Double.valueOf(scanner.nextLine());
                } catch (Exception e) {
                    isDouble = true;
                    System.out.println("Error! Invalid input.");
                    System.out.println("----------------------------------------------");
                }
            }while (isDouble);

            rooms.add(new Room(roomNumber, price,type));
            System.out.println("Do you want add another room? y/n");
            addRoom = scanner.nextLine();
            if (addRoom.toLowerCase().equals("y")){
                continueAdd = true;
            } else {
                continueAdd = false;
            }
        } while(continueAdd);
        ar.addRoom(rooms);
        System.out.println("Rooms are saved!");
    }

}
