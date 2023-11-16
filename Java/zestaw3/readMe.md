# Currency Converter

## Opis
Projekt "Currency Converter" to prosty kalkulator umożliwiający przeliczanie kwoty z polskich złotych (PLN) na trzy inne waluty: amerykański dolar (USD), euro (EUR) oraz funt brytyjski (GBP). Program pozwala użytkownikowi wybrać docelową walutę i wprowadzić kwotę w PLN, a następnie dokonuje przeliczenia według aktualnych kursów.

## Klasy

### CurrencyConverter
Główna klasa projektu, zawierająca metodę `main`, która obsługuje interakcję z użytkownikiem i przeliczenia walut. 

Metody:
- `main(String[] args)`: Punkt wejścia programu, obsługujący logikę przeliczeń.

### Opis klas w projekcie:
Brak dodatkowych klas w tym projekcie. Logika przeliczeń jest zawarta bezpośrednio w klasie `CurrencyConverter`.

## Instrukcja Użycia
1. Uruchom program.
2. Wybierz docelową walutę, wpisując numer odpowiadający wybranej opcji.
3. Podaj kwotę w polskich złotych (PLN) do przeliczenia.
4. Program wyświetli przeliczoną kwotę w wybranej walucie.

Przykład:
```
Wybierz walutę:
1. USD (Dolar amerykański)
2. EUR (Euro)
3. GBP (Funt brytyjski)
Twój wybór: 2
Podaj kwotę w PLN: 100
Przeliczona kwota: 100.0 PLN na 2: 23.26
```

## Dodatkowe Uwagi
- Kursy walut są zdefiniowane w kodzie źródłowym i mogą być dostosowane według aktualnych wartości.
- Program obsługuje trzy główne waluty, ale może być rozbudowany o obsługę dodatkowych walut.
- W przypadku wprowadzenia nieprawidłowej wartości (np. niepoprawnego wyboru waluty lub błędnej kwoty), program poinformuje użytkownika o błędzie.