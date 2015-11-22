package teamblowfish.ihome;
import java.util.LinkedList;
import java.util.ListIterator;

public class User{
    String name;
    LinkedList<Room> accessibleRooms;
    LinkedList<Door> accessibleDoors;
    boolean accessibleTemp;
    char accountType;

    /**
     * constructs a user with no rooms or doors accessible and no access to change temperature.
     * @param name the name of the user
     * @param type either 'l' or 'a', which stand for limited or administrator. Although if the entered
     *             char is not 'a', the user will have limited access to rooms
     */
    public User(String name, char type){
        this.name = name;
        accessibleRooms = new LinkedList<Room>();
        accessibleDoors = new LinkedList<Door>();
        accessibleTemp = false;
        if(type=='a') accountType = type;
        else accountType = 'l';
    }
    public String getName(){
        return name;
    }
    public void changeRoomAccess(String roomName, boolean access){
        if(access){
            ListIterator<Room> roomIterator = accessibleRooms.listIterator();
            while(roomIterator.hasNext()){
                Room returnedRoom = roomIterator.next();
                if(returnedRoom.getName().equals(roomName)){
                    break;
                }
            }
            Room roomToAdd = new Room(roomName);
            accessibleRooms.add(roomToAdd);
        }
        else{
            ListIterator<Room> roomIterator = accessibleRooms.listIterator();
            while(roomIterator.hasNext()){
                Room returnedRoom = roomIterator.next();
                if(returnedRoom.getName().equals(roomName)){
                    accessibleRooms.remove(returnedRoom);
                    break;
                }
            }
        }
    }
    public void changeDoorAccess(String doorName, boolean access){
        if(access){
            ListIterator<Door> doorIterator = accessibleDoors.listIterator();
        }
        else{

        }
    }
    public void tempAccess(boolean access){

    }
    public ListIterator<Room> getAccessibleRooms(){
        return accessibleRooms.listIterator();
    }
    public int getNumRooms(){
        return accessibleRooms.size();
    }
    public ListIterator<Door> getAccessibleDoors(){
        return accessibleDoors.listIterator();
    }
    public int getNumDoors(){
        return accessibleDoors.size();
    }
    public boolean isAccessibleTemp(){
        return accessibleTemp;
    }
}