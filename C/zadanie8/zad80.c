#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define ZA_MALO_PAMIECI -1

typedef struct wezel {
  int wartosc;
  struct wezel *nastepny;
  // struct wezel *poprzedni;//w przypadku listy dwukierunkowej
} Wezel;

/**********************************************************/

void wypisz(Wezel *pierwszy) {
  Wezel *biezacy = pierwszy;

  if (!biezacy) {
    printf("Pusta lista\n");
  }

  while (biezacy != NULL) {
    printf("%d\n", biezacy->wartosc);
    biezacy = biezacy->nastepny;
  }
  printf("\n");
}

/**********************************************************/

void push_back(Wezel **pierwszy, int liczba) { // wstaw element na koniec listy

  // konstruujemy nowy węzeł
  Wezel *element;
  Wezel *iterator = *pierwszy;

  if ((element = (Wezel *)malloc(sizeof(Wezel))) == NULL) {
    fprintf(stderr, "Za mało pamięci!\n");
    exit(ZA_MALO_PAMIECI);
  }
  element->wartosc = liczba;
  element->nastepny = NULL;

  if (iterator == NULL) { // gdy pusta lista
    *pierwszy = element;
    return;
  }

  while (iterator->nastepny != NULL) {
    iterator = iterator->nastepny;
  }

  iterator->nastepny = element;
}

/**********************************************************/
// tu wpisz funkcje
int pop_front(Wezel **pierwszy, int *wartosc) {
  Wezel *iterator = *pierwszy;
  int liczba;

  if (iterator == NULL) {
    return -1;
  }

  *wartosc = iterator->wartosc;
  *pierwszy = iterator->nastepny;

  free(iterator);

  return 0;
}

/**********************************************************/

void insert_in(Wezel **pierwszy, int pozycja, int liczba) {
  Wezel *nowy = (Wezel *)malloc(sizeof(Wezel));
  if (nowy == NULL) {
    fprintf(stderr, "Za mało pamięci!\n");
    exit(ZA_MALO_PAMIECI);
  }

  nowy->wartosc = liczba;

  if (pozycja == 0) {
    nowy->nastepny = *pierwszy;
    *pierwszy = nowy;
    return;
  }

  Wezel *iterator = *pierwszy;
  for (int i = 0; i < pozycja - 1 && iterator != NULL; i++) {
    iterator = iterator->nastepny;
  }

  nowy->nastepny = iterator->nastepny;
  iterator->nastepny = nowy;
}

/**********************************************************/

int delete_idx(Wezel **pierwszy, int pozycja) {
  if (*pierwszy == NULL) {
    fprintf(stderr, "Lista jest pusta!\n");
    return -1;
  }

  if (pozycja == 0) {
    Wezel *temp = *pierwszy;
    *pierwszy = (*pierwszy)->nastepny;
    free(temp);
    return 0;
  }

  Wezel *iterator = *pierwszy;
  Wezel *poprzedni = NULL;

  for (int i = 0; i < pozycja && iterator != NULL; i++) {
    poprzedni = iterator;
    iterator = iterator->nastepny;
  }

  poprzedni->nastepny = iterator->nastepny;
  free(iterator);

  return 0;
}

/**********************************************************/

int main() {
  int T;
  char linia[20];
  int x, y, pozycja;
  Wezel *pierwszy = NULL;

  scanf("%d", &T);
  for (int i = 0; i < T; i++) {
    scanf("%s", linia);

    if (strstr(linia, "push_back")) {
      scanf("%d", &x);
      printf("test %d: %s %d\n", i, linia, x);
      push_back(&pierwszy, x);
      wypisz(pierwszy);
    }

    else if (strstr(linia, "pop_front")) {
      printf("test %d: %s\n", i, linia);
      if (pop_front(&pierwszy, &y) == 0) {
        printf("Zwrocono wartosc %d\n", y);
      }
      wypisz(pierwszy);
    }

    else if (strstr(linia, "insert_in")) {
      scanf("%d %d", &pozycja, &x);
      printf("test %d: %s %d %d\n", i, linia, pozycja, x);
      insert_in(&pierwszy, pozycja, x);
      wypisz(pierwszy);
    }

    else if (strstr(linia, "delete_idx")) {
      scanf("%d", &pozycja);
      printf("test %d: %s %d\n", i, linia, pozycja);

      if (delete_idx(&pierwszy, pozycja) == 0) {
        printf("Element usunięty z pozycji %d\n", pozycja);
      }

      wypisz(pierwszy);
    }
  }
}