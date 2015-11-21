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
    public void changeTemp(){
    }
    public void turnOnLight(){

    }
    public void turnOffLight(){

    }
    public void lockDoor(){

    }
    public void unlockDoor(){

    }
}
