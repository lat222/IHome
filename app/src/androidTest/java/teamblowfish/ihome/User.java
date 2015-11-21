package teamblowfish.ihome;
import java.util.LinkedList;

public class User{
    String name;
    LinkedList<Room> accessibleRooms;
    LinkedList<Door> accessibleDoors;
    boolean accessibleTemp;
    char accountType;

    /*constructs a user
    @param name the name of the user
    @param type either 'l' or 'a', which stand for limited or administrator. Although if the entered
    char is not 'a', the user will have limited access to rooms
     */
    public User(String name, char type){
        this.name = name;
        accessibleRooms = new LinkedList<Room>();
        accessibleDoors = new LinkedList<Door>();
        accessibleTemp = true;
        if(type=='a') accountType = type;
        else accountType = 'l';
    }
    public void changeAccess(String userName, String objectName, boolean access){

    }
    public void tempAccess(String userName, boolean access){

    }
}