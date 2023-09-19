package model;

public enum RoomType {
    SINGLE, DOUBLE;

    public static RoomType valueCheck(String str){
        RoomType roomType;

        if (str.toLowerCase().equals("single")){
            roomType = RoomType.SINGLE;
        } else if (str.toLowerCase().equals("double")){
            roomType = RoomType.DOUBLE;
        } else {
            System.out.println("Error! Invalid input.");
            roomType = null;
        }

        return roomType;
    }

}
