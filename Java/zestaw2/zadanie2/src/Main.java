import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // Przykłady dla klasy Ulamek
        Ulamek obj1 = new Ulamek(6, 8);
        Ulamek obj2 = new Ulamek(3, 7);

        System.out.println("Ulamek 1: " + obj1);
        System.out.println("Ulamek 2: " + obj2);

        double x = obj1.rozwDziesietne();
        System.out.println("Wartość dziesiętna Ulamka 1: " + x);

        Ulamek obj3 = obj1.plus(obj2);
        System.out.println("Suma Ulamka 1 i Ulamka 2: " + obj3);

        Ulamek obj4 = obj1.minus(obj2);
        System.out.println("Różnica Ulamka 1 i Ulamka 2: " + obj4);

        Ulamek obj5 = obj1.razy(obj2);
        System.out.println("Iloczyn Ulamka 1 i Ulamka 2: " + obj5);

        obj1.odwroc();
        System.out.println("Odwrocony Ulamek 1: " + obj1);

        obj1.skroc();
        System.out.println("Skrócony Ulamek 1: " + obj1);

        obj2.skroc();
        System.out.println("Skrócony Ulamek 2: " + obj2);

        // Przykłady dla klasy LiczbaZespolona
        LiczbaZespolona z1 = new LiczbaZespolona(2, 3);
        LiczbaZespolona z2 = new LiczbaZespolona(1, 2);

        System.out.println("Liczba zespolona z1: " + z1);
        System.out.println("Liczba zespolona z2: " + z2);

        LiczbaZespolona suma = z1.dodaj(z2);
        System.out.println("Suma z1 i z2: " + suma);

        LiczbaZespolona roznica = z1.odejmij(z2);
        System.out.println("Różnica z1 i z2: " + roznica);

        LiczbaZespolona iloczyn = z1.pomnoz(z2);
        System.out.println("Iloczyn z1 i z2: " + iloczyn);

        LiczbaZespolona iloraz = z1.podziel(z2);
        System.out.println("Iloraz z1 i z2: " + iloraz);

        // Przykłady dla klasy Wektor
        Wektor v1 = new Wektor(1, 2, 3);
        Wektor v2 = new Wektor(4, 5, 6);

        System.out.println("Wektor v1: " + v1);
        System.out.println("Wektor v2: " + v2);

        Wektor sumaWektorow = v1.dodajWektory(v2);
        System.out.println("Suma v1 i v2: " + sumaWektorow);

        Wektor roznicaWektorow = v1.odejmijWektory(v2);
        System.out.println("Różnica v1 i v2: " + roznicaWektorow);

        double iloczynSkalarny = v1.obliczIloczynSkalarny(v2);
        System.out.println("Iloczyn skalarny v1 i v2: " + iloczynSkalarny);

        Wektor iloczynWektorowy = v1.obliczIloczynWektorowy(v2);
        System.out.println("Iloczyn wektorowy v1 i v2: " + iloczynWektorowy);

        double dlugoscV1 = v1.obliczDlugosc();
        System.out.println("Długość v1: " + dlugoscV1);

        // Przykłady dla klasy WielomianKwadratowy
        WielomianKwadratowy wielomian1 = new WielomianKwadratowy(1, -3, 2);
        WielomianKwadratowy wielomian2 = new WielomianKwadratowy(2, 1, -1);

        System.out.println("Wielomian 1: " + wielomian1);
        System.out.println("Wielomian 2: " + wielomian2);

        WielomianKwadratowy sumaWielomianow = wielomian1.dodaj(wielomian2);
        System.out.println("Suma wielomianów: " + sumaWielomianow);

        WielomianKwadratowy iloczynWielomianow = wielomian1.pomnoz(wielomian2);
        System.out.println("Iloczyn wielomianów: " + iloczynWielomianow);

        double[] miejscaZerowe = wielomian1.znajdzMiejscaZerowe();
        System.out.println("Miejsca zerowe wielomianu 1: " + Arrays.toString(miejscaZerowe));
    }
}
