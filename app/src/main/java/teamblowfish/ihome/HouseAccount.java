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

    public HouseAccount(String accountName, Room[] rooms, Door[] doors, int temp, String userName){
        //this.house = house;
        this.rooms = rooms;
        this.doors = doors;
        temperature = temp;
        users = new LinkedList<User>();
        User admin = new User(userName);
        users.add(admin);
        admin.setAccountType('a');
        //sets up an admin account
        for(int i=0; i<rooms.length;i++){
            admin.changeRoomAccess(i,true);
        }
        for(int i=0;i<doors.length;i++){
            admin.changeDoorAccess(i,true);
        }
        admin.tempAccess(true);
        this.accountName=accountName;
    }

    /**
     * accesses account name
     * @return name of house account
     */
    public String getAccountName(){ return accountName;}
    public void add(String name){
        User newUser = new User(name);
        users.add(newUser);
    }
    public void remove(String name){
        ListIterator<User> userIterator = users.listIterator();
        User userToRemove = null;
        while (userIterator.hasNext()){
            User returnedUser = userIterator.next();
            if(returnedUser.getName().equals(name)&&returnedUser.getAccountType()!='a'){
                userToRemove=returnedUser;
            }
        }
        try{users.remove(userToRemove);}
        //here to catch if the userToRemove is still null
        catch(Exception e){}
    }
    public int getNumUsers(){
        return users.size();
    }
    /**
     * gives door names in order to create buttons
     * @return String array with door names
     */
    public int[] getDoorAccess(String userName){
        int[] doorIndices = new int[getUser(userName).getNumDoors()];
        ListIterator<Integer> accessibleDoors = getUser(userName).getAccessibleDoors();
        int j=0;
        while(accessibleDoors.hasNext()){
            int returnedIndex = accessibleDoors.next();
            doorIndices[j]=returnedIndex;
            j++;

        }
        return doorIndices;
    }
    /**
     * gives room names in order to create buttons
     * @return String array with room names
     */
    public int[] getRoomAccess(String userName){
        int[] roomIndices = new int[getUser(userName).getNumRooms()];
        ListIterator<Integer> accessibleRooms = getUser(userName).getAccessibleRooms();
        int j=0;
        while(accessibleRooms.hasNext()){
            int returnedIndex = accessibleRooms.next();
            roomIndices[j]=returnedIndex;
            j++;

        }
        return roomIndices;
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
    public ListIterator<User> getUsers(){
        return users.listIterator();
    }
    private boolean isAccessibleDoor(String userName, int index){
        User user = getUser(userName);
        ListIterator<Integer> accessDoors = user.getAccessibleDoors();
        while(accessDoors.hasNext()){
            int returnedDoor  = accessDoors.next();
            if(returnedDoor==index){
                return true;
            }
        }
        return false;
    }
    /*private boolean isAccessibleRoom(String userName, int index){
        //Assumes doors and room are not named the same thing.
        //boolean isAccessible;
        User user = getUser(userName);
        ListIterator<Integer> accessRooms = user.getAccessibleRooms();
        while(accessRooms.hasNext()){
            int returnedRoom = accessRooms.next();
            if(returnedRoom==index){
                return true;
            }
        }
        return false;
    }*/
    public Room getRoom(int index){
        return rooms[index];
    }
    public Door getDoor(int index){
        /*for(int i=0; i<doors.length;i++){
            if(doors[i].getName().equals(doorName)){
                return doors[i];
            }
        }*/
        return doors[index];
    }
    public int tempSetting(){
        return temperature;
    }
    //since only the rooms and doors that are accessible will be created into buttons
    //these methods do not need to check that.
    public void changeTemp(int temp){
        temperature=temp;
    }
    public void turnOnLight(int index){
        try{
            rooms[index].light();
        }
        catch(Exception e){

        }

    }
    public void turnOffLight(int index){
        try{
            rooms[index].turnOffLights();
        }
        catch(Exception e){

        }
    }
    public void lockDoor(int index){
        try{
            doors[index].lock();
        }
        catch(Exception e){

        }
    }
    public void unlockDoor(int index){
        try{
            doors[index].unlock();
        }
        catch(Exception e){

        }

    }
}
