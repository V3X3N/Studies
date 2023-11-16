import java.io.*;

public class Program {
    public static void main(String[] args) {
        System.out.println("-- do zapisu --");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //Tworzenie osoby
        Osoba osoba = new Osoba(br);

        //Tworzenie dowodu
        DowodOsobisty dowodOsobisty = new DowodOsobisty(br, osoba);

        //Tworzenie paszportu
        Paszport paszport = new Paszport(br, osoba);

        //Wypisywanie
        dowodOsobisty.info();
        paszport.info();

        //Zapisywanie do pliku
        try {
            ObjectOutputStream outp = new ObjectOutputStream(new FileOutputStream("dane.dat"));
            outp.writeObject(dowodOsobisty);
            outp.writeObject(paszport);
            outp.close();
        } catch (IOException e) {
            System.out.println("Błąd podczas zapisywania danych do pliku: " + e.getMessage());
        }

        System.out.println("\n-- z pliku --");
        ObjectInputStream inp;

        //Odczytywanie
        try {
            inp = new ObjectInputStream(new FileInputStream("dane.dat"));

            Object o1 = inp.readObject();
            if (o1 instanceof DowodOsobisty) {
                DowodOsobisty dowodFromfile = (DowodOsobisty) o1;
                dowodFromfile.info();
            }

            Object o2 = inp.readObject();
            if (o2 instanceof Paszport) {
                Paszport paszportFromfile = (Paszport) o2;
                paszportFromfile.info();
            }

            inp.close();

            //Zabezpieczenia na brak pliku lub brak mozliwosci owtorzenia pliku
        } catch (FileNotFoundException e) {
            System.out.println("Nie można odnaleźć pliku z danymi: " + e.getMessage());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Błąd podczas odczytu danych z pliku: " + e.getMessage());
        }
    }
}