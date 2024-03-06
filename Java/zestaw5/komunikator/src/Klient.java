import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.net.SocketException;

public class Klient {
    public static final int PORT = 50007;
    public static final String HOST = "127.0.0.1";

    public static void main(String[] args) {
        try {
            Socket sock;
            sock = new Socket(HOST, PORT);
            System.out.println("Nawiazalem polaczenie: " + sock);

            BufferedReader inp;
            inp = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            PrintWriter outp;
            outp = new PrintWriter(sock.getOutputStream(), true);

            BufferedReader klaw;
            klaw = new BufferedReader(new InputStreamReader(System.in));

            String str;
            while (true) {
                str = inp.readLine();
                System.out.println("<Nadeszlo:> " + str);

                if (str.equalsIgnoreCase("koniec")) {
                    break;
                }

                System.out.print("<Wysylamy:> ");
                str = klaw.readLine();
                outp.println(str);
            }

            System.out.println("Koniec polaczenia");
            inp.close();
            outp.close();
            klaw.close();
            sock.close();
        } catch (UnknownHostException e) {
            //obsługa błędu związanego z złym adresem hosta
            System.out.println("Nie mozna polaczyc z serwerem: Nieznany host");
        } catch (SocketException e) {
            //obsługa błędu związanego z protokołem tcp (np rozłączenie z internetem)
            System.out.println("Polaczenie zostalo przerwane: " + e.getMessage());
        }catch (IOException e) {
            //obsługa pozostałych błędów
            System.out.println("Polaczenie zostalo przerwane");
        }
    }
}
