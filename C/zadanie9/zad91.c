#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define ZA_MALO_PAMIECI 0

typedef struct wezel {
  char *tytul;
  char *autor;
  double cena;
  int ilosc;
  struct wezel *nastepny;
} ksiazka;

void my_split(const char *src, char *tytul, char *autor, char *ilosc,
              char *cena, char delim) {
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

void push_back(ksiazka *nowy, ksiazka **pierwszy) {
  ksiazka *element;
  ksiazka *iterator = *pierwszy;

  if ((element = (ksiazka *)malloc(sizeof(ksiazka))) == NULL) {
    fprintf(stderr, "Za mało pamięci!\n");
    exit(ZA_MALO_PAMIECI);
  }
  element->tytul = nowy->tytul;
  element->autor = nowy->autor;
  element->cena = nowy->cena;
  element->ilosc = nowy->ilosc;
  element->nastepny = NULL;

  if (iterator == NULL) {
    *pierwszy = element;
    return;
  }

  while (iterator->nastepny != NULL) {
    iterator = iterator->nastepny;
  }

  iterator->nastepny = element;
}

void wyswietlKsiazke(int pozycja, ksiazka *pierwszy) {
  ksiazka *iterator = pierwszy;
  int pozycjaP = pozycja;

  while (pozycja != 0) {
    if (iterator == NULL) {
      printf("W magazynie nie ma ksiazki o podanej pozycji\n");
      return;
    } else{
        iterator = iterator->nastepny;
        pozycja--;
    }
  }

  printf("Ksiazka nr %d to: %s %s %d %5.2f\n",
  pozycjaP, iterator->tytul, iterator->autor, iterator->ilosc, iterator->cena);
}

bool wypelnijMagazyn(const char *nazwaPliku, ksiazka **pierwszy) {

  FILE *fp = fopen(nazwaPliku, "r");
  if (fp == NULL) {
    printf("Brak pliku z danymi\n");
    return 0;
  }


  const int max_n = 500;
  char linia[max_n], *result;
  char tytul[200];
  char autor[100];
  char ilosc[10];
  char cena[20];

  while (!feof(fp)) {
    ksiazka *element;
    if ((element = (ksiazka *)malloc(sizeof(ksiazka))) == NULL) {
      fprintf(stderr, "Za mało pamięci!\n");
      exit(ZA_MALO_PAMIECI);
    }
    result = fgets(linia, max_n, fp);
    my_split(linia, tytul, autor, ilosc, cena, ';');

    element->tytul = (char *)malloc((strlen(tytul) + 1) * sizeof(char));
    strcpy(element->tytul, tytul);

    element->autor = (char *)malloc((strlen(autor) + 1) * sizeof(char));
    strcpy(element->autor, autor);

    element->ilosc = atoi(ilosc);
    element->cena = atof(cena);

    push_back(element, pierwszy);
  }
  fclose(fp);
  return true;
}

int main() { // KOD NAPISANY WSPOLNIE Z JAKUB JAJONEK (344050)
  int T;

  scanf("%d", &T);
  for (int i = 0; i < T; i++) {
    ksiazka *pierwszy = NULL;
    char NazwaPliku[200];
    int pozycja = 0;
    scanf("%s %d", NazwaPliku, &pozycja);
    bool sukces = wypelnijMagazyn(NazwaPliku, &pierwszy);
    if (sukces)
      wyswietlKsiazke(pozycja, pierwszy);
  }
  return 0;
}
