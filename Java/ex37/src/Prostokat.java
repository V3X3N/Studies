import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Prostokat {
    double dlugosc;
    double szerokosc;
    Punkt wierzcholek;

    Prostokat() {
        //metoda przy wywołaniu obiektu zbiera od użytkownika parametry prostokąta z których następnie buduje obiekt
        //prostokąt
        Scanner scanner = new Scanner(System.in);

        System.out.print("Podaj długość prostokąta: ");
        this.dlugosc = scanner.nextDouble();
        System.out.print("Podaj szerokość prostokąta: ");
        this.szerokosc = scanner.nextDouble();
        System.out.print("Podaj współrzędną x wierzchołka prostokąta: ");
        double x = scanner.nextDouble();
        System.out.print("Podaj współrzędną y wierzchołka prostokąta: ");
        double y = scanner.nextDouble();
        this.wierzcholek = new Punkt(x, y);
    }

    public String toString() {
        return "[dl: " + dlugosc + ", sz: " + szerokosc + "] " + wierzcholek.toString();
        //zwraca długość, szerokość i początek prostokąta opakowane w etykiety i w typie string
    }

    double pole() {
        return dlugosc * szerokosc;
        //zwraca pole prostokąta
    }

    void przesun(double u, double v) {
        this.wierzcholek.przesun(u, v);
        // przesuwa prostokąt
    }

    boolean zawiera(Punkt P) {
        double x1 = this.wierzcholek.x;
        double x2 = this.wierzcholek.x + this.szerokosc;
        double y1 = this.wierzcholek.y;
        double y2 = this.wierzcholek.y + this.dlugosc;

        return P.x >= x1 && P.x <= x2 && P.y >= y1 && P.y <= y2;
        //sprawdza czy punkty zawiera się w prostokącie
    }

    void save(String plik) {
        //zapisuje
        try {
            FileWriter writer = new FileWriter(plik);
            writer.write("Długość: " + dlugosc + "\n");
            writer.write("Szerokość: " + szerokosc + "\n");
            writer.write("Współrzędne wierzchołka: (x: " + wierzcholek.x + ",y: " + wierzcholek.y + ")\n");
            writer.close();
            System.out.println("Dane zapisane do pliku: " + plik);
        } catch(Exception e){System.out.println(e);}
    }
}
