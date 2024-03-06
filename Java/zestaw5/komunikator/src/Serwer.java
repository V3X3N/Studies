import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Serwer {
    public static final int PORT = 50007;

    public static void main(String args[]) throws IOException {
        ServerSocket serv;
        serv = new ServerSocket(PORT);
        System.out.println("Nasluchuje: " + serv);

        Socket sock;
        sock = serv.accept();
        System.out.println("Jest polaczenie: " + sock);

        BufferedReader inp;
        inp = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        PrintWriter outp;
        outp = new PrintWriter(sock.getOutputStream(), true);

        BufferedReader klaw;
        klaw = new BufferedReader(new InputStreamReader(System.in));

        String str;
        while (true) {
            System.out.print("<Wysylamy:> ");
            str = klaw.readLine();
            outp.println(str);

            str = inp.readLine();
            System.out.println("<Nadeszlo:> " + str);
            //rozłącza z klientem gdy dostanie słowo koniec
            if (str.equalsIgnoreCase("koniec")) {
                break;
            }
        }

        System.out.println("Koniec polaczenia");
        inp.close();
        outp.close();
        klaw.close();
        sock.close();
        serv.close();
    }
}
