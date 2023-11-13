import java.awt.*;

public class Program
{
    public static void main(String[] args)
    {
        Prostokat a=new Prostokat(3,4); //Tworzymy obiekt a klasy Prostokat
        a.info(); //Wywolujemy metode info, ktora korzysta z toString z klasy Rectangle


        Prostokat b=new Prostokat(2,2); //Tworzymy obiekt b klasy Prostokat
        b.info(); //Wywolujemy metode info, ktora korzysta z toString z klasy Rectangle


        if (a.przylega(b)) {
            System.out.println("-- przylega --\n");
        } else {
            System.out.println("-- NIE przylega --\n");
        }


        // Przykładowe użycie konstruktora
        Point wierzcholek = new Point(1, 1);
        Prostokat c = new Prostokat(wierzcholek, 3, 4);
        c.info();


        if(a.intersects(b)) //Sprawdzamy czy przecinaja sie dzieki Rectangle. Metoda zwraca true/false w zaleznosci od przecinania sie
        {
            System.out.println("-- przecinaja sie --\n");
        }
        else
        {
            System.out.println("-- NIE przecinaja sie --\n");
        }


        a.translate(5,3); //Wykorzystanie metody translate z klasy Rectangle
        a.info(); //Wywolujemy metode info, ktora korzysta z toString z klasy Rectangle

        if(a.intersects(b)) //Sprawdzamy czy przecinaja sie dzieki Rectangle. Metoda zwraca true/false w zaleznosci od przecinania sie
        {
            System.out.println("-- przecinaja sie --\n");
        }
        else
        {
            System.out.println("-- NIE przecinaja sie --\n");
        }
    }
}

/*
Tak, konstruktory nadklasy są dziedziczone przez podklasę.
Kiedy tworzysz instancję podklasy, konstruktor nadklasy jest wywoływany automatycznie,
aby zainicjować dziedziczone pola i dostosować stan obiektu.
To jest realizowane poprzez wywołanie super() jako pierwszej instrukcji w konstruktorze podklasy
 */