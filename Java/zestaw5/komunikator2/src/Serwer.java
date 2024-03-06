import java.io.*;
import java.net.*;

public class Serwer {
    public static final int PORT = 50007;

    public static void main(String args[]) throws IOException {
        // Tworzenie gniazda serwerowego
        ServerSocket serv = new ServerSocket(PORT);

        // Oczekiwanie na połączenie i tworzenie gniazda sieciowego
        System.out.println("Nasluchuje: " + serv);
        Socket sock = serv.accept();
        System.out.println("Jest polaczenie: " + sock);

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
            serv.close();
            sock.close();
            System.out.println("Koniec polaczenia");
        }
    }
}
