import java.io.*;
import java.net.*;

public class Klient {
    public static final int PORT = 50007;
    public static final String HOST = "127.0.0.1";

    public static void main(String[] args) throws IOException {
        Socket sock = new Socket(HOST, PORT);
        System.out.println("Nawiazalem polaczenie: " + sock);

        // Tworzenie wątków do odbierania i wysyłania
        Odbior odbiorThread = new Odbior(sock);
        Wysylanie wysylanieThread = new Wysylanie(sock);

        // Uruchamianie wątków
        odbiorThread.start();
        wysylanieThread.start();

        try {
            // Czekaj na zakończenie wątków
            odbiorThread.join();
            wysylanieThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // Zamykanie połączenia
            sock.close();
            System.out.println("Koniec polaczenia");
        }
    }
}
