import java.io.*;

//Serializable - Interfejs znacznikowy, czyli nie zawiera zadnej metody
class DowodOsobisty implements Serializable {
    Osoba posiadacz;
    String numer;

    //Konstruktor Dowodu Osobistego
    DowodOsobisty(BufferedReader br, Osoba osoba) {
        try {
            this.posiadacz = osoba;

            System.out.print("numer dowodu: ");
            this.numer = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Zwracamy dane w postaci stringu
    public String toString() {
        return posiadacz.toString() + " " + this.numer;
    }

    //Wypisujemy obiekt klasy
    void info() {
        System.out.println("Imie: " + posiadacz.imie +
                "\nNazwisko: " + posiadacz.nazwisko +
                "\nRok urodzenia: " + posiadacz.rokUrodzenia +
                "\nNumer Dowodu: " + numer);
    }
}