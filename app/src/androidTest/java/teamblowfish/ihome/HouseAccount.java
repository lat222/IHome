package teamblowfish.ihome;

import java.util.LinkedList;

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

        users.remove(userToRemove);
    }
}
