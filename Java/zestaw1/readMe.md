# Projekt Geometrii Obiektowej

## Opis

Projekt Geometrii Obiektowej to program napisany w języku Java, który implementuje trzy klasy reprezentujące geometrię punktu, prostokąta i okręgu. Program wykonuje różnorodne operacje, takie jak przemieszczanie punktów, prostokątów i okręgów, obliczanie ich pól, sprawdzanie, czy punkt znajduje się wewnątrz prostokąta lub okręgu, oraz sprawdzanie przecięcia dwóch okręgów.

## Klasy

### Punkt
- Reprezentuje punkt na płaszczyźnie za pomocą współrzędnych x i y.
- Metody:
  - `toString`: Zwraca reprezentację punktu jako string.
  - `przesun(dx, dy)`: Przesuwa punkt o podane wartości dx i dy.

### Prostokat
- Reprezentuje prostokąt o określonej długości, szerokości i wierzchołku (top-left vertex).
- Metody:
  - `toString`: Zwraca reprezentację prostokąta jako string.
  - `przesun(u, v)`: Przesuwa prostokąt o podane wartości u i v.
  - `zawiera(punkt)`: Sprawdza, czy punkt znajduje się wewnątrz prostokąta.
  - `pole()`: Oblicza pole prostokąta.

### Okrag
- Reprezentuje okrąg o określonym promieniu i środku.
- Metody:
  - `toString`: Zwraca reprezentację okręgu jako string.
  - `przesun(u, v)`: Przesuwa okrąg o podane wartości u i v.
  - `zawiera(punkt)`: Sprawdza, czy punkt znajduje się wewnątrz okręgu.
  - `pole()`: Oblicza pole okręgu.
  - `przecina(okrag)`: Sprawdza, czy dwa okręgi się przecinają.

## Instrukcja Użycia

1. Uruchom program, uruchamiając klasę `Main`.
2. Program stworzy różne instancje punktów, prostokątów i okręgów.
3. Wykonaj różne operacje, takie jak przesuwanie, obliczanie pól i sprawdzanie zawierania.
4. Zobacz wyniki w konsoli.

## Dodatkowe Uwagi

- Program został napisany w języku Java.
- Użyto klas i metod obiektowo zorientowanego programowania do reprezentacji geometrii i operacji na obiektach.
- Zachęcamy do eksperymentowania i modyfikowania kodu zgodnie z własnymi potrzebami.

