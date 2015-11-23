package teamblowfish.ihome;

import android.widget.Toast;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Created by Leia on 11/21/2015.
 */
public class HouseAccount {
    //House house;
    Room[] rooms;
    Door[] doors;
    int temperature;
    String accountName;
    LinkedList<User> users;

    public HouseAccount(String accountName, Room[] rooms, Door[] doors, int temp, LinkedList<User> users){
        //this.house = house;
        this.rooms = rooms;
        this.doors = doors;
        temperature = temp;
        this.users = users;
        this.accountName=accountName;
    }

    /**
     * accesses account name
     * @return name of house account
     */
    public String getAccountName(){ return accountName;}
    public void add(String name, char accessType){
        User newUser = new User(name,accessType);
        users.add(newUser);
    }
    public void delete(String name){
        ListIterator<User> userIterator = users.listIterator();
        User userToRemove = null;
        while (userIterator.hasNext()){
            User returnedUser = userIterator.next();
            if(returnedUser.getName().equals(name)){
                userToRemove=returnedUser;
            }
        }
        try{users.remove(userToRemove);}
        //here to catch if the userToRemove is still null
        catch(Exception e){}
    }
    /**
     * gives door names in order to create buttons
     * @return String array with door names
     */
    public String[] getDoorNames(String userName){
        String[] doorNames = new String[getUser(userName).getNumDoors()];
        int j=0;
        for(int i=0;i<doors.length;i++){
            if(isAccessibleDoor(userName, doors[i].getName())==true){

                doorNames[j]=doors[i].getName();
                j++;
            }
        }
        return doorNames;
    }
    /**
     * gives room names in order to create buttons
     * @return String array with room names
     */
    public String[] getRoomNames(String userName){
        //Room[] rooms = house.getRooms();
        String[] roomNames = new String[getUser(userName).getNumRooms()];
        for(int i=0;i<rooms.length;i++){
            if(isAccessibleRoom(userName,rooms[i].getName())==true){
                roomNames[i]=rooms[i].getName();
            }

        }
        return roomNames;
    }
    public User getUser(String userName){
        ListIterator<User> userIterator = users.listIterator();
        while(userIterator.hasNext()){
            User returnedUser = userIterator.next();
            if(returnedUser.getName().equals(userName)){
                return returnedUser;
            }
        }
        return null;
    }
    private boolean isAccessibleDoor(String userName, String objectName){
        User user = getUser(userName);
        ListIterator<Door> accessDoors = user.getAccessibleDoors();
        while(accessDoors.hasNext()){
            Door returnedDoor  = accessDoors.next();
            if(returnedDoor.getName().equals(objectName)){
                return true;
            }
        }
        return false;
    }
    private boolean isAccessibleRoom(String userName, String objectName){
        //Assumes doors and room are not named the same thing.
        //boolean isAccessible;
        User user = getUser(userName);
        ListIterator<Room> accessRooms = user.getAccessibleRooms();
        while(accessRooms.hasNext()){
            Room returnedRoom = accessRooms.next();
            if(returnedRoom.getName().equals(objectName)){
                return true;
            }
        }
        return false;
    }
    public Room getRoom(String roomName){
        for(int i=0; i<rooms.length;i++){
            if(rooms[i].getName().equals(roomName)) return rooms[i];
        }
        return null;
    }
    public Door getDoor(String doorName){
        for(int i=0; i<doors.length;i++){
            if(doors[i].getName().equals(doorName)){
                return doors[i];
            }
        }
        return doors[0];
    }
    public int tempSetting(){
        return temperature;
    }
    //since only the rooms and doors that are accessible will be created into buttons
    //these methods do not need to check that.
    public void changeTemp(int temp){
        temperature=temp;
    }
    public void turnOnLight(String roomName){
        try{
            getRoom(roomName).light();
        }
        catch(Exception e){

        }

    }
    public void turnOffLight(String roomName){
        try{
            getRoom(roomName).turnOffLights();
        }
        catch(Exception e){

        }
    }
    public void lockDoor(String doorName){
        try{
            getDoor(doorName).lock();
        }
        catch(Exception e){

        }
    }
    public void unlockDoor(String doorName){
        try{
            getDoor(doorName).unlock();
        }
        catch(Exception e){

        }

    }
}
