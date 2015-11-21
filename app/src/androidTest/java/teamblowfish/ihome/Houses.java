package teamblowfish.ihome;

import java.util.LinkedList;

/**
 * Created by Leia on 11/21/2015.
 */
public class Houses{
    HouseAccount[] houses;
    public Houses(){
        houses = new HouseAccount[3];

        Room living = new Room("Living Room");
        Room kitchen = new Room("Kitchen");
        Room bedroom = new Room("Bedroom");
        Room[] rooms1 = {living,kitchen,bedroom};
        Door front = new Door("Front Door");
        Door back = new Door("Back Door");
        Door[] doors1 = {front,back};
        int temp1 = 60;
        House house1 = new House(rooms1,doors1,temp1);
        LinkedList<User> users = new LinkedList<User>();
        HouseAccount house1Account = new HouseAccount(house1,users);
        houses[1] = house1Account;
    }
    public HouseAccount getHouseAccount(int index){
        try{return houses[index];}
        catch(IndexOutOfBoundsException e){}
        return null;
    }
}
