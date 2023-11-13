import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
//Biblioteki

class Osoba implements Serializable { //Interfejs znacznikowy, czyli nie zawiera zadnej metody
    String imie;
    String nazwisko;
    int rokUrodzenia;

    Osoba(String imie, String nazwisko, int rokUrodzenia) { //Tworzymy konstruktor Osoby
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.rokUrodzenia = rokUrodzenia;
    }

    Osoba(BufferedReader br) { //Konstruktor do czytania danych
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

    public String toString() {
        return this.imie + " " + this.nazwisko + " " + this.rokUrodzenia;
    }
}
