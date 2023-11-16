import java.io.*;

class Paszport implements Serializable {
    Osoba posiadacz;
    String numerPaszportu;

    //Konstruktor paszportu
    Paszport(BufferedReader br, Osoba osoba) {
        try {
            this.posiadacz = osoba;

            System.out.print("numer paszportu: ");
            this.numerPaszportu = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Zwracamy dane w postaci stringu
    public String toString() {
        return posiadacz.toString() + " " + this.numerPaszportu;
    }

    //Wypisujemy obiekt
    void info() {
        System.out.println("Numer Paszportu: " + numerPaszportu);
    }
}