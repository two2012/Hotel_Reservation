package model;

public class FreeRoom extends Room{
    public FreeRoom(String roomNumber, double price, RoomType type) {
        super(roomNumber,0.0,type);
    }

    @Override
    public String toString() {
        return "This is a free room.";
    }
}
