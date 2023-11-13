import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;

//Serializable - Interfejs znacznikowy, czyli nie zawiera zadnej metody
class Osoba implements Serializable {
    String imie;
    String nazwisko;
    int rokUrodzenia;

    //Tworzymy konstruktor Osoby
    Osoba(String imie, String nazwisko, int rokUrodzenia) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.rokUrodzenia = rokUrodzenia;
    }

    //Konstruktor do czytania danych
    Osoba(BufferedReader br) {
        try {
            System.out.print("imie: ");
            this.imie = br.readLine();

            System.out.print("nazwisko: ");
            this.nazwisko = br.readLine();

            System.out.print("rok urodzenia: ");
            this.rokUrodzenia = Integer.parseInt(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Wypiswanie w postaci stringa
    public String toString()
    {
        return this.imie+" "+this.nazwisko+" "+this.rokUrodzenia;
    }

    //Zwracamy informacje w postaci tekstu
    void info() {
        System.out.println("Imie: " + imie +
                "\nNazwisko: " + nazwisko +
                "\nRok urodzenia: " + rokUrodzenia);
    }
}
