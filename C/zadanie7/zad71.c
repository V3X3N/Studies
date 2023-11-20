#include <stdio.h>
#include <string.h>
#include <stdlib.h>

typedef struct {
    int licznik;
    int mianownik;
} wymierna;

int nwd(int a, int b) {
    while (b != 0) {
        int temp = b;
        b = a % b;
        a = temp;
    }
    return a;
}

void skroc(wymierna *ulamek) {
    int skr = nwd(ulamek->licznik, ulamek->mianownik);
    ulamek->licznik /= skr;
    ulamek->mianownik /= skr;
}

wymierna suma(wymierna A, wymierna B) {
    wymierna result;
    result.licznik = A.licznik * B.mianownik + B.licznik * A.mianownik;
    result.mianownik = A.mianownik * B.mianownik;
    skroc(&result);
    return result;
}

void wypiszWymierna(wymierna ulamek) {
    printf("%d/%d\n", ulamek.licznik, ulamek.mianownik);
}

int main() {
    int T;
    scanf("%d", &T);

    for (int i = 0; i < T; i++) {
        wymierna pierwsza, druga, wynik;
        scanf("%d %d %d %d", &pierwsza.licznik, &pierwsza.mianownik, &druga.licznik, &druga.mianownik);
        wynik = suma(pierwsza, druga);
        printf("Suma ulamkow: ");
        wypiszWymierna(wynik);
    }

    return 0;
}