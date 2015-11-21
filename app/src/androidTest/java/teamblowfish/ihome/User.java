package teamblowfish.ihome;
import java.util.LinkedList;
import java.util.ListIterator;

public class User{
    String name;
    LinkedList<Room> accessibleRooms;
    LinkedList<Door> accessibleDoors;
    boolean accessibleTemp;
    char accountType;

    /**
     * constructs a user with no rooms or doors accessible and no access to change temperature.
     * @param name the name of the user
     * @param type either 'l' or 'a', which stand for limited or administrator. Although if the entered
     *             char is not 'a', the user will have limited access to rooms
     */
    public User(String name, char type){
        this.name = name;
        accessibleRooms = new LinkedList<Room>();
        accessibleDoors = new LinkedList<Door>();
        accessibleTemp = false;
        if(type=='a') accountType = type;
        else accountType = 'l';
    }
    public String getName(){
        return name;
    }
    public void changeAccess(String userName, String objectName, boolean access){

    }
    public void tempAccess(String userName, boolean access){

    }
    public ListIterator<Room> getAccessibleRooms(){
        return accessibleRooms.listIterator();
    }
    public ListIterator<Door> getAccessibleDoors(){
        return accessibleDoors.listIterator();
    }
    public boolean isAccessibleTemp(){
        return accessibleTemp;
    }
}