import java.io.*;

class CurrencyConverter {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Wybierz walutę do przeliczenia (1 - Euro, 2 - Dolar, 3 - Funt): ");
            String choiceStr = br.readLine();
            int choice = Integer.parseInt(choiceStr);

            double kurs = 0.0;
            String currencyName = "";

            switch (choice) {
                case 1 -> {
                    kurs = 4.45;  // Kurs Euro
                    currencyName = "Euro";
                }
                case 2 -> {
                    kurs = 4.2;  // Kurs Dolara
                    currencyName = "Dolar";
                }
                case 3 -> {
                    kurs = 5.1;  // Kurs Funta
                    currencyName = "Funt";
                }
                default -> {
                    System.out.println("Niepoprawny wybór waluty.");
                    return;
                }
            }

            System.out.print("Podaj kwotę w " + currencyName + ": ");
            String str = br.readLine();
            double amount = Double.parseDouble(str);

            double plnAmount = amount * kurs;
            System.out.println("PLN: " + plnAmount);

        } catch (IOException e) {
            System.out.println("Błąd operacji wejścia/wyjścia");
        } catch (NumberFormatException e) {
            System.out.println("Nieprawidłowy format liczby");
        }
    }
}