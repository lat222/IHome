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
        //House house1 = new House(rooms1,doors1,temp1);
        LinkedList<User> users1 = new LinkedList();
        HouseAccount house1Account = new HouseAccount("Roomies", rooms1, doors1, temp1, "Dave");
        houses[0] = house1Account;

        Room bathroom = new Room("Bathroom");
        Room[] rooms2 = {kitchen,bedroom,bathroom};
        Door[] doors2 = {front};
        int temp2 = 63;
        //House house2 = new House(rooms2,doors2,temp2);
        LinkedList<User> users2 = new LinkedList();
        HouseAccount house2Account = new HouseAccount("Cottage", rooms2, doors2, temp2,"Kelly");
        houses[1] = house2Account;

        Room bedroom1 = new Room("Bobby's room");
        Room bedroom2 = new Room("Anna's room");
        Room bedroom3 = new Room("Greg's room");
        Room[] rooms3 = {kitchen,bedroom1,bedroom2,bedroom3,living,bathroom};
        Door[] doors3 = {front,back};
        doors3[1].lock();
        int temp3 = 44;
        //House house3 = new House(rooms3,doors3,temp3);
        LinkedList<User> users3 = new LinkedList();
        User roomate5 = new User("Anna");
        HouseAccount house3Account = new HouseAccount("House3", rooms3, doors3, temp3,"Anna");
        houses[2] = house3Account;


    }
    public HouseAccount findHouseAccount(String houseName){
        for(int i=0; i<houses.length;i++){
            if(houses[i].getAccountName().equals(houseName)){
                return houses[i];
            }
        }
        return null;
    }
}
