# Projekt Matematyczny

## Opis

Projekt Matematyczny to zestaw klas Java, które implementują różne matematyczne obiekty i operacje. W ramach projektu zaimplementowane są klasy reprezentujące liczby ułamkowe, liczby zespolone, wektory w przestrzeni trójwymiarowej oraz wielomiany kwadratowe. Każda klasa zawiera metody umożliwiające podstawowe operacje matematyczne związane z danym obiektem.

## Klasy

### 1. LiczbaZespolona

Klasa `LiczbaZespolona` reprezentuje liczbę zespoloną. Posiada metody umożliwiające dodawanie, odejmowanie, mnożenie i dzielenie liczb zespolonych, a także dostęp do ich części rzeczywistej i urojonej.

### 2. Ulamek

Klasa `Ulamek` reprezentuje ułamek. Zawiera metody do wykonywania operacji matematycznych na ułamkach, takich jak dodawanie, odejmowanie, mnożenie, dzielenie, odwracanie, skracanie i obliczanie wartości dziesiętnej.

### 3. Wektor

Klasa `Wektor` reprezentuje wektor w trójwymiarowej przestrzeni. Udostępnia metody do dodawania, odejmowania, obliczania iloczynu skalarnego, iloczynu wektorowego oraz obliczania długości wektora.

### 4. WielomianKwadratowy

Klasa `WielomianKwadratowy` reprezentuje wielomian kwadratowy o postaci ax^2 + bx + c. Posiada metody do dodawania, mnożenia i znajdowania miejsc zerowych tego rodzaju wielomianów.

## Instrukcja użycia

1. Sklonuj projekt do swojego środowiska programistycznego.
2. Otwórz klasę `Main` i zobacz przykłady użycia różnych klas matematycznych obiektów.
3. Uruchom program, aby zobaczyć wyniki działań na liczbach ułamkowych, liczbach zespolonych, wektorach i wielomianach kwadratowych.

## Dodatkowe Uwagi

- Projekt został stworzony w języku Java.
- Każda klasa posiada odpowiednie metody do przeprowadzenia podstawowych operacji matematycznych.
- Pamiętaj o odpowiednim zarządzaniu błędami, takimi jak dzielenie przez zero czy odwracanie ułamka o zerowym liczniku.
- W przypadku wielomianów kwadratowych, program oblicza miejsca zerowe, uwzględniając różne przypadki (dwa pierwiastki rzeczywiste, jeden pierwiastek rzeczywisty, brak pierwiastków rzeczywistych).
- Korzystaj z metod `toString()` dla wygodnego wyświetlania wyników.