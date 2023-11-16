public class Punkt {
    double x;
    double y;

    Punkt(double x,double y)
    {
        this.x=x;
        this.y=y;
    }

    public String toString()
    {
        return "[x: "+x+", y: "+y+"]";
    }

    public void przesun(double dx, double dy)
    {
        this.x+=dx;
        this.y+=dy;
        //przesuwa punkt
    }
}
