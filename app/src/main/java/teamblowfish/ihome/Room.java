package teamblowfish.ihome;

public class Room{
    private Boolean lit = false;
    private String roomName;
    public Room(String name){
        roomName = name;
    }
    public boolean isLit(){
        return lit;
    }
    public void light(){
        lit=true;
    }
    public void turnOffLights(){
        lit=false;
    }
    public String getName(){ return roomName; }
    public void setName(String name){ roomName = name; }
}