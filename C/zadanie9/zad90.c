#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define ZA_MALO_PAMIECI -1

typedef struct wezel {
  struct wezel *nastepny;
  int wartosc;
} Wezel;

void stworzCykliczna(Wezel **pierwszy) {
  int num;
  Wezel *prev = NULL;
  Wezel *temp = NULL;

  while (1) {
    char enter = getchar();

    if (enter == '\n') {
      break;
    }

    if (scanf("%d", &num) != 1) {
      fprintf(stderr, "Blad wczytywania liczby\n");
      exit(EXIT_FAILURE);
    }

    temp = (Wezel *)malloc(sizeof(Wezel));

    temp->wartosc = num;
    temp->nastepny = NULL;

    if (*pierwszy == NULL) {
      *pierwszy = temp;
    } else {
      prev->nastepny = temp;
    }

    prev = temp;
  }

  if (prev != NULL) {
    prev->nastepny = *pierwszy;
  }
}

int rozmiarListy(Wezel *poczatek) {
  if (poczatek == NULL) {
    return 0;
  }

  int rozmiar = 0;
  Wezel *temp = poczatek;
  do {
    rozmiar++;
    temp = temp->nastepny;
  } while (temp != poczatek);

  return rozmiar;
}

int main(void) {

  srand((unsigned)time(NULL));
  int rozmiar, shift;

  Wezel *pierwszy = NULL;

  stworzCykliczna(&pierwszy);
  Wezel *p = pierwszy;
  shift = rand() % 100;
  printf("shift = %d\n", shift);

  for (int i = 0; i < shift; i++) {
    p = p->nastepny;
  }

  rozmiar = rozmiarListy(p);
  printf("Rozmiar wynosi %d\n", rozmiar);
  return 0;
}