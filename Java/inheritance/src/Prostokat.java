import java.awt.*;

class Prostokat extends Rectangle
{
    Prostokat(int a,int b)
    {
        super(a,b);
    }

    Prostokat(Point wierzcholek, int dlugosc, int szerokosc)
    {
        //Wywołanie konstruktora nadklasy z argumentami
        super(wierzcholek.x, wierzcholek.y, dlugosc, szerokosc);
    }

    boolean przylega(Prostokat innyProstokat) {
        return (this.intersects(innyProstokat.x - 1, innyProstokat.y - 1, innyProstokat.width + 2, innyProstokat.height + 2));
    }

    void info()
    {
        System.out.println(this);
    }
}