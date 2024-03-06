import java.io.*;
import java.net.*;

public class SerwerHTTP {
    public static void main(String[] args) throws IOException {
        ServerSocket serv = new ServerSocket(80);

        while (true) {
            System.out.println("Oczekiwanie na polaczenie...");
            Socket sock = serv.accept();

            InputStream is = sock.getInputStream();
            OutputStream os = sock.getOutputStream();
            BufferedReader inp = new BufferedReader(new InputStreamReader(is));
            DataOutputStream outp = new DataOutputStream(os);

            // Przyjęcie kompletnego żądania (request)
            StringBuilder requestBuilder = new StringBuilder();
            String line;
            while ((line = inp.readLine()) != null && !line.isEmpty()) {
                requestBuilder.append(line).append("\n");
            }

            // Analiza nazwy pliku z żądania
            String completeRequest = requestBuilder.toString();
            System.out.println("Complete Request:\n" + completeRequest);

            String fileName = extractFileNameFromRequest(completeRequest);
            System.out.println("Requested File: " + fileName);

            FileInputStream fileInputStream = null;
            try {
                // Utworzenie FileInputStream dla żądanego pliku
                File file = new File(fileName);
                if (file.exists()) {
                    fileInputStream = new FileInputStream(file);

                    // Ustawienie nagłówków
                    outp.writeBytes("HTTP/1.0 200 OK\r\n");

                    // Content-Length
                    int contentLength = fileInputStream.available();
                    outp.writeBytes("Content-Length: " + contentLength + "\r\n");

                    // Content-Type
                    String contentType = determineContentType(fileName);
                    outp.writeBytes("Content-Type: " + contentType + "\r\n");

                    outp.writeBytes("\r\n");

                    // Przesłanie zawartości pliku do przeglądarki
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                        outp.write(buffer, 0, bytesRead);
                    }
                } else {
                    // Plik nie istnieje - odpowiedź 404 Not Found
                    outp.writeBytes("HTTP/1.0 404 Not Found\r\n");
                    outp.writeBytes("Content-Type: text/plain\r\n");
                    outp.writeBytes("\r\n");
                    outp.writeBytes("404 Not Found: The requested file does not exist\r\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            }

            // Zamykanie strumieni
            inp.close();
            outp.close();
            sock.close();
        }
    }

    private static String extractFileNameFromRequest(String request) {
        String fileName = "";

        if (request.startsWith("GET")) {
            int spaceIndex = request.indexOf(' ');
            if (spaceIndex != -1) {
                String filePath = request.substring(spaceIndex + 1, request.indexOf(' ', spaceIndex + 1));
                fileName = filePath.substring(filePath.lastIndexOf('/') + 1);
            }
        }

        return fileName;
    }

    private static String determineContentType(String fileName) {
        if (fileName.endsWith(".html") || fileName.endsWith(".htm")) {
            return "text/html";
        } else {
            return "application/octet-stream";
        }
    }
}
