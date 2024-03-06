import java.io.*;
import java.net.*;

class ObslugaZadania extends Thread {
    Socket sock;

    ObslugaZadania(Socket klientSocket) {
        this.sock = klientSocket;
    }

    public void run() {
        try {
            // Odczyt danych przesłanych przez klienta
            BufferedReader input = new BufferedReader(new InputStreamReader(sock.getInputStream()));

            // Odczyt pierwszej linii zapytania HTTP (ignorujemy ją)
            String requestLine = input.readLine();

            // Wysłanie odpowiedzi HTTP
            BufferedWriter output = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));

            // Odpowiedź - nagłówek HTTP
            output.write("HTTP/1.1 200 OK\r\n");
            output.write("Content-Type: text/html\r\n\r\n");

            // Odpowiedź - treść HTML
            output.write("<html><body>");
            output.write("<h1>Witaj na mojej stronie!</h1>");
            output.write("<p>To jest wielowątkowy serwer HTTP w Javie.</p>");

            // Dodawanie obrazków
            for (int i = 1; i <= 5; i++) {
                output.write("<img src=\"image" + i + ".jpg\" alt=\"Image " + i + "\">");
            }

            // Dodawanie apletów Java
            for (int i = 1; i <= 2; i++) {
                output.write("<applet code=\"Applet" + i + ".class\" width=\"300\" height=\"300\">");
                output.write("Applet " + i);
                output.write("</applet>");
            }

            output.write("</body></html>");
            output.flush();

            // Zamknięcie socketa
            sock.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class SerwerHTTP {
    public static void main(String[] args) throws IOException {
        ServerSocket serv = new ServerSocket(8080);

        while (true) {
            System.out.println("Oczekiwanie na połączenie...");
            Socket sock = serv.accept();
            new ObslugaZadania(sock).start();
        }
    }
}
