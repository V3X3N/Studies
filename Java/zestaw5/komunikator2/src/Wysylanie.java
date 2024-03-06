import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class Wysylanie extends Thread {
    Socket sock;
    PrintWriter sockWriter;
    BufferedReader consoleReader;

    public Wysylanie(Socket sock) throws IOException {
        this.sock = sock;
        this.sockWriter = new PrintWriter(sock.getOutputStream(), true);
        this.consoleReader = new BufferedReader(new InputStreamReader(System.in));
    }

    public void run() {
        try {
            String message;
            while (true) {
                // Wczytywanie wiadomości z konsoli
                message = consoleReader.readLine();

                // Wysyłanie wiadomości do serwera
                sockWriter.println(message);

                // Sprawdzenie warunku zakończenia
                if (message.equalsIgnoreCase("koniec")) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Koniec wysylania");
        }
    }
}