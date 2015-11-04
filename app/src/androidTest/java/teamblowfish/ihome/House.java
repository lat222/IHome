public class House{
    private Room[] rooms;
    private Door[] doors;
    public void House(){
        rooms = new Room[6];
        rooms[0] = new Room("Master");
        rooms[1] = new Room("Guest");
        rooms[2] = new Room("Bathroom");
        rooms[3] = new Room("Kitchen");
        rooms[4] = new Room("Living Room");

        doors = new Door[3];
        doors[1] = new Door("Front Door");
        doors[2] = new Door("Back Door");
        doors[3] = new Door("Garage Door");

    }
}