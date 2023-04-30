package carrera;

public class Runner {

    private String name;
    private int speed;
    
    public Runner (String name, int speed){
        this.name = name;
        this.speed = speed;
    }
    
    public void setName(String name){
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
    
    public void setSpeed(int speed){
        this.speed = speed;
    }

    public int getSpeed() {
        return this.speed;
    }

}
