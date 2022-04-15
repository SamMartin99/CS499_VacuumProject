package Startup;

public class Location {
    // Attributes, simple x, y ints
    public int x;
    public int y;

    // Constructor, as simple as can be
    public Location(int xRef, int yRef){
        this.x = xRef;
        this.y = yRef;
    }

    // getters
    public int getLocX ()
    {
        return this.x;
    }
    public int getLocY ()
    {
        return this.y;
    }
    public Location getLocation ()
    {
        return this;
    }

    // setters
    public void setLocX (int inpX) { this.x = inpX; }
    public void setLocY (int inpY)
    {
        this.y = inpY;
    }
    public void setLocation (int inpX, int inpY)
    {
        this.x = inpX;
        this.y = inpY;
    }

    // Print values
    public void printLocation(){
        System.out.print(this.x + ", " + this.y);
    }

}
