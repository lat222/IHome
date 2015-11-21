package teamblowfish.ihome;
import java.util.*;

public class House{
    private Room[] rooms;
    private Door[] doors;
    private int temperature;
    /*constructs house
    @param rooms an array of rooms
    @param doors an array of doors
    @param temp an integer of the house temperature
     */
    public void House(Room[] rooms, Door[] doors, int temp){
        this.rooms = rooms;
        this.doors = doors;
        temperature = temp;
    }
    /*allows access to rooms within the house
    @return the room with the same String name. Otherwise returns null.
    @param roomName a String that represents the room that is meant to be accessed.
    */
    public Room getRoom(String roomName){
        for(int i=0; i<rooms.length;i++){
            if(rooms[i].getName().matches(roomName)) return rooms[i];
        }
        return null;
    }
    /*allows access to doors within the house
    @return the door with the same String name. Otherwise returns null.
    @param doorName a String that represents the door that is meant to be accessed.
     */
    public Door getDoor(String doorName){
        for(int i=0; i<doors.length;i++){
            if(rooms[i].getName().matches(doorName)) return doors[i];
        }
        return null;
    }
    /*Accesses the house temperature. Assumes that the house is entirely set at one temperature
    and parts of the house do not differ in temperature from others
    @return int that represents the temperature of the house
     */
    public int getTemp(){
        return temperature;
    }
    /*Sets the house temperature. Assumes that the house is entirely set at one temperature
    and parts of the house cannot be set to a different temperature from others
    @param temp an integer to set the temperature of the house to
    */
    public void setTemp(int temp){
        temperature = temp;
    }

}