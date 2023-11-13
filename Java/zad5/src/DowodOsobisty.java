import java.io.*;

//Serializable - Interfejs znacznikowy, czyli nie zawiera zadnej metody
class DowodOsobisty implements Serializable {
    Osoba posiadacz;
    String numer;

    //Konstruktor Dowodu Osobistego
    DowodOsobisty(BufferedReader br) {
        try {
            this.posiadacz = new Osoba(br);

            System.out.print("numer do: ");
            this.numer = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Zwracamy dane w postaci stringu
    public String toString() {
        return "<do:> " + posiadacz.toString() + " " + this.numer;
    }

    //Wypisujemy obiekt klasy
    void info() {
        System.out.println(this);
    }
}
