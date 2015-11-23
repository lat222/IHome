package teamblowfish.ihome;
import java.util.LinkedList;
import java.util.ListIterator;

public class User{
    String name;
    LinkedList<Integer> accessibleRooms;
    LinkedList<Integer> accessibleDoors;
    boolean accessibleTemp;
    char accountType;

    /**
     * constructs a user with no rooms or doors accessible and no access to change temperature.
     * @param name the name of the user
     */
    public User(String name){
        this.name = name;
        accessibleRooms = new LinkedList<Integer>();
        accessibleDoors = new LinkedList<Integer>();
        accessibleTemp = false;
        accountType = 'l';
    }
    public char getAccountType(){ return accountType; }
    public void setAccountType(char access){
        if(access=='a'){
            accountType = 'a';
        }
        else{
            accountType = 'l';
        }
    }
    public String getName(){
        return name;
    }
    public void changeRoomAccess(int index, boolean access){
        if(access){
            ListIterator<Integer> roomIterator = accessibleRooms.listIterator();
            while(roomIterator.hasNext()){
                int returnedRoom = roomIterator.next();
                if(returnedRoom==index){
                    break;
                }
            }
            int roomToAdd = index;
            accessibleRooms.add(roomToAdd);
        }
        else{
            ListIterator<Integer> roomIterator = accessibleRooms.listIterator();
            while(roomIterator.hasNext()){
                int returnedRoom = roomIterator.next();
                if(returnedRoom==index){
                    accessibleRooms.remove(returnedRoom);
                    break;
                }
            }
        }
    }
    public void changeDoorAccess(int index, boolean access){
        if(access){
            ListIterator<Integer> doorIterator = accessibleDoors.listIterator();
            while(doorIterator.hasNext()){
                int returnedDoor = doorIterator.next();
                if(returnedDoor==index){
                    break;
                }
            }
            int doorToAdd = index;
            accessibleDoors.add(doorToAdd);
        }
        else{
            ListIterator<Integer> doorIterator = accessibleDoors.listIterator();
            while(doorIterator.hasNext()){
                int returnedDoor = doorIterator.next();
                if(returnedDoor==index){
                    accessibleDoors.remove(returnedDoor);
                    break;
                }
            }
        }
    }
    public void tempAccess(boolean access){
        accessibleTemp=access;
    }
    public ListIterator<Integer> getAccessibleRooms(){
        return accessibleRooms.listIterator();
    }
    public int getNumRooms(){
        return accessibleRooms.size();
    }
    public ListIterator<Integer> getAccessibleDoors(){
        return accessibleDoors.listIterator();
    }
    public int getNumDoors(){
        return accessibleDoors.size();
    }
    public boolean isAccessibleTemp(){
        return accessibleTemp;
    }
}