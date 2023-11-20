#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct ksiazka {
  char autor[255];
  char tytul[255];
  unsigned ilosc;
  float cena;
};

void split(const char *src, char *tytul, char *autor, char *ilosc, char *cena, char delim) {
  while (*src != delim) {
    *tytul = *src;
    tytul++;
    src++;
  }

  *tytul = '\0';
  src++;
  while (*src != delim) {
    *autor = *src;
    autor++;
    src++;
  }

  *autor = '\0';
  src++;

  while (*src != delim) {
    *ilosc = *src;
    ilosc++;
    src++;
  }

  *ilosc = '\0';
  src++;
  while (*src != '\0') {
    *cena = *src;
    cena++;
    src++;
  }

  *cena = '\0';
}

bool wypelnijMagazyn(const char *nazwaPliku, struct ksiazka *magazyn) {
  int len = 0;
  char autor[255];
  char tytul[255];
  char ilosc[20];
  char cena[20];

  FILE *fp = fopen(nazwaPliku, "r");

  if (fp == NULL) {
    printf("Brak pliku z danymi\n");

    return 0;
  }

  const int max_n = 500;

  char linia[max_n], *result;

  for (int i = 0; i < ROZMIAR; i++) {
    result = fgets(linia, max_n, fp);

    if (result == NULL) {
      break;
    }

    split(linia, tytul, autor, ilosc, cena, ';');

    strcpy(magazyn[i].autor, autor);
    strcpy(magazyn[i].tytul, tytul);
    magazyn[i].ilosc = atoi(ilosc);
    magazyn[i].cena = atof(cena);
  }

  fclose(fp);
  return 1;
}

void wyswietlKsiazke(int pozycja, struct ksiazka *magazyn) {
  if (pozycja >= 0 && pozycja < ROZMIAR) {
    printf("Ksiazka nr %d to: %s %s %d %.2f\n", pozycja, magazyn[pozycja].tytul, magazyn[pozycja].autor,
           magazyn[pozycja].ilosc, magazyn[pozycja].cena);
  }
  else
  {
    printf("Pozycja poza zakresem magazynu\n");
  }
}

int cmp(const void *left, const void *right) {
  float cena1 = ((struct ksiazka *)left)->cena;
  float cena2 = ((struct ksiazka *)right)->cena;

  if (cena1 < cena2) return -1;
  if (cena1 > cena2) return 1;
  return 0;
}

void sortuj_wg_ceny(struct ksiazka *magazyn) {
  qsort(magazyn, ROZMIAR, sizeof(struct ksiazka), cmp);
}

int main(void) {
  struct ksiazka magazyn[ROZMIAR];
  int T;
  int pozycja;
  char NazwaPliku[200];

  scanf("%d", &T);

  for (int i = 0; i < T; i++) {
    scanf("%s %d", NazwaPliku, &pozycja);
    bool sukces = wypelnijMagazyn(NazwaPliku, magazyn);
    if (sukces)
      wyswietlKsiazke(pozycja, magazyn);
    else
    {
      printf("W magazynie nie ma ksiazki o podanej pozycji\n");
    }
  }

  return 0;
}