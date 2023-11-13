import java.io.*;

public class Program {
    public static void main(String[] args) {
        System.out.println("-- do zapisu --");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        DowodOsobisty z = new DowodOsobisty(br);
        z.info();

        //Zapisujemy wartosci do pliku
        try {
            ObjectOutputStream outp = new ObjectOutputStream(new FileOutputStream("plik.dat"));
            outp.writeObject(z);
            outp.close();

            //Zabezpieczenie przed bledem zapisu
        } catch (IOException e) {
            System.out.println("Błąd podczas zapisywania do pliku: " + e.getMessage());
        }

        System.out.println("\n-- z pliku --");
        ObjectInputStream inp;

        //Odczytujemy wartosci z pliku i rzutujemy na typ DowodOsobisty
        try {
            inp = new ObjectInputStream(new FileInputStream("plik.dat"));
            Object o = inp.readObject();
            DowodOsobisty x = (DowodOsobisty) o;
            inp.close();
            x.info();

            //Zabezpieczamy warunki jak nie ma pliku lub plik nie moze byc odczytany
        } catch (FileNotFoundException e) {
            System.out.println("Nie można odnaleźć pliku: " + e.getMessage());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Błąd podczas odczytu z pliku: " + e.getMessage());
        }
    }
}