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
}