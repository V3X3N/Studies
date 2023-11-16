import java.io.*;
import java.text.DecimalFormat;

class CurrencyConverter {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Wybierz walutę:");
            System.out.println("1. USD (Dolar amerykański)");
            System.out.println("2. EUR (Euro)");
            System.out.println("3. GBP (Funt brytyjski)");

            System.out.print("Twój wybór: ");
            String currencyChoice = br.readLine(); //Uzytkownik podaje wybor

            double kurs = 1.0; //Domyślny kurs (PLN)

            switch (currencyChoice) {
                case "1":
                    kurs = 3.8; //Kurs dla USD
                    break;
                case "2":
                    kurs = 4.3; //Kurs dla EUR
                    break;
                case "3":
                    kurs = 5.2; //Kurs dla GBP
                    break;
                default:
                    System.out.println("Niepoprawny wybór waluty.");
                    return;
            }

            System.out.print("Podaj kwotę w PLN: ");
            String str = br.readLine(); //Uzytkownik podaje kwote

            double x = Double.parseDouble(str);
            double przeliczonaKwota = x / kurs; //Dzieje sie magia - przeliczamy waluty

            DecimalFormat df = new DecimalFormat("#.00"); //Formatujemy by wyswietlac tylko dwie liczny po przecinku
            String formatowanaKwota = df.format(przeliczonaKwota);

            System.out.println("Przeliczona kwota: " + x + " PLN na " + currencyChoice + ": " + formatowanaKwota); //Wypisujemy
        } catch (IOException e1) {
            System.out.println("Błąd operacji wejścia/wyjścia.");
        } catch (NumberFormatException e2) {
            System.out.println("Nieprawidłowy format liczby.");
        }
    }
}
