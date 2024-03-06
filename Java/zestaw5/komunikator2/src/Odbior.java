import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

class Odbior extends Thread {
    Socket sock;
    BufferedReader sockReader;

    public Odbior(Socket sock) throws IOException {
        this.sock = sock;
        this.sockReader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
    }

    public void run() {
        try {
            String receivedMessage;
            while ((receivedMessage = sockReader.readLine()) != null) {
                // Odbieranie i wyświetlanie wiadomości
                System.out.println("Odebrano: " + receivedMessage);

                // Sprawdzenie warunku zakończenia
                if (receivedMessage.equalsIgnoreCase("koniec")) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Koniec odbierania");
        }
    }
}