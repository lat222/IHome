package teamblowfish.ihome;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Created by Leia on 11/21/2015.
 */
public class HouseAccount {
    House house;
    LinkedList<User> users;

    public HouseAccount(House house, LinkedList<User> users){
        this.house = house;
        this.users = users;
    }
    public void add(String name, char accessType){
        User newUser = new User(name,accessType);
        users.add(newUser);
    }
    public void delete(String name){
        ListIterator<User> userIterator = users.listIterator();
        User userToRemove = null;
        while (userIterator.hasNext()){
            User returnedUser = userIterator.next();
            if(returnedUser.getName().matches(name)){
                userToRemove=returnedUser;
            }
        }
        try{users.remove(userToRemove);}
        //here to catch if the userToRemove is still null
        catch(Exception e){}
    }
    private User getUser(String userName){
        ListIterator<User> userIterator = users.listIterator();
        while(userIterator.hasNext()){
            User returnedUser = userIterator.next();
            if(returnedUser.getName().matches(userName)){
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
            if(returnedDoor.getName().matches(objectName)){
                return true;
            }
        }
        ListIterator<Room> accessRooms = user.getAccessibleRooms();
        while(accessRooms.hasNext()){
            Room returnedRoom = accessRooms.next();
            if(returnedRoom.getName().matches(objectName)){
                return true;
            }
        }
        return false;
    }
    public void changeTemp(String userName, int temp){
        try{
            if(getUser(userName).isAccessibleTemp()) house.setTemp(temp);
        }
        catch(Exception e){

        }
    }
    public void turnOnLight(String userName, String roomName){
        try{
            if(isAccessible(userName,roomName)) house.getRoom(roomName).light();
        }
        catch(Exception e){

        }

    }
    public void turnOffLight(String userName, String roomName){
        try{
            if(isAccessible(userName,roomName)) house.getRoom(roomName).turnOffLights();
        }
        catch(Exception e){

        }
    }
    public void lockDoor(String userName, String doorName){
        try{
            if(isAccessible(userName,doorName)) house.getDoor(doorName).lock();
        }
        catch(Exception e){

        }
    }
    public void unlockDoor(String userName, String doorName){
        try{
            if(isAccessible(userName,doorName)) house.getDoor(doorName).unlock();
        }
        catch(Exception e){

        }

    }
}
