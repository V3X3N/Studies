import java.awt.Rectangle;
import java.awt.Point;
class Prostokat extends Rectangle {
    Prostokat(Point wierzcholek, int dlugosc, int szerokosc) {
        super(wierzcholek.x, wierzcholek.y, dlugosc, szerokosc);
    }
    Prostokat(int a,int b)
    {
        super(a,b);
    }

    void info()
    {
        System.out.println(this);
    }
}