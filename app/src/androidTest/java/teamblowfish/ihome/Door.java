package teamblowfish.ihome;

public class Door{
    private Boolean locked = false;
    private String doorName;
    public Door(String name){
        doorName = name;
    }
    public boolean isLocked(){
        return locked;
    }
    public void lock(){
        locked=true;
    }
    public void unlock(){
        locked=false;
    }
    public String getName(){ return doorName; }
}