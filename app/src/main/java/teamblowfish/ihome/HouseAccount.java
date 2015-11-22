package teamblowfish.ihome;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Created by Leia on 11/21/2015.
 */
public class HouseAccount {
    House house;
    String accountName;
    LinkedList<User> users;

    public HouseAccount(String accountName, House house, LinkedList<User> users){
        this.house = house;
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
        Door[] doors = house.getDoors();
        String[] doorNames = new String[getUser(userName).getNumDoors()];
        for(int i=0;i<doors.length;i++){
            if(isAccessible(userName,doors[i].getName())){
                doorNames[i]=doors[i].getName();
            }
        }
        return doorNames;
    }
    /**
     * gives room names in order to create buttons
     * @return String array with room names
     */
    public String[] getRoomNames(String userName){
        Room[] rooms = house.getRooms();
        String[] roomNames = new String[getUser(userName).getNumRooms()];
        for(int i=0;i<rooms.length;i++){
            if(isAccessible(userName,rooms[i].getName())){
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
    private boolean isAccessible(String userName, String objectName){
        //Assumes doors and room are not named the same thing.
        boolean isAccessible;
        User user = getUser(userName);
        ListIterator<Door> accessDoors = user.getAccessibleDoors();
        while(accessDoors.hasNext()){
            Door returnedDoor  = accessDoors.next();
            if(returnedDoor.getName().equals(objectName)){
                return true;
            }
        }
        ListIterator<Room> accessRooms = user.getAccessibleRooms();
        while(accessRooms.hasNext()){
            Room returnedRoom = accessRooms.next();
            if(returnedRoom.getName().equals(objectName)){
                return true;
            }
        }
        return false;
    }
    public boolean doorSetting(String doorName){
        //TODO fix nullException
        if(house.getDoor(doorName)!=null){
            return house.getDoor(doorName).isLocked();
        }
        //boolean locked = house.getDoor(doorName).isLocked();
        return false;
    }
    public boolean roomSetting(String roomName){
        boolean lit = house.getRoom(roomName).isLit();
        return lit;
    }
    public int tempSetting(){
        return house.getTemp();
    }
    //since only the rooms and doors that are accessible will be created into buttons
    //these methods do not need to check that.
    public void changeTemp(int temp){
        try{
            house.setTemp(temp);
        }
        catch(Exception e){

        }
    }
    public void turnOnLight(String roomName){
        try{
            house.getRoom(roomName).light();
        }
        catch(Exception e){

        }

    }
    public void turnOffLight(String roomName){
        try{
            house.getRoom(roomName).turnOffLights();
        }
        catch(Exception e){

        }
    }
    public void lockDoor(String doorName){
        try{
            house.getDoor(doorName).lock();
        }
        catch(Exception e){

        }
    }
    public void unlockDoor(String doorName){
        try{
            house.getDoor(doorName).unlock();
        }
        catch(Exception e){

        }

    }
}
