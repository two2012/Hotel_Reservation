package model;

public class Room implements IRoom{

    private final String roomNumber;
    private final Double price;
    private final RoomType type;

    public Room(String roomNumber, Double price, RoomType type) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.type = type;
    }


    @Override
    public String getRoomNumber() {
        return this.roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return this.price;
    }

    @Override
    public RoomType getRoomType() {
        return this.type;
    }

    @Override
    public boolean isFree() {
        return true;
    }



    @Override
    public String toString() {
        return "Room{" +
                "room Number ='" + roomNumber + '\'' +
                ", price per night = $" + price +
                ", room type =" + type +
                '}';
    }


}


