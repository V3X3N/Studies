#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define ZA_MALO_PAMIECI -1

typedef struct wezel {
  int wartosc;
  struct wezel *nastepny;
  //struct wezel *poprzedni;
  //w przypadku listy dwukierunkowej
} Wezel;


/**********************************************************/

void wypisz(Wezel *pierwszy) {
  Wezel *biezacy = pierwszy;

  if(!biezacy){
    printf("Pusta lista\n");
  }

  printf("Lista: ");
  while (biezacy != NULL) {
    printf("%d\t", biezacy->wartosc);
    biezacy = biezacy->nastepny;
  }
  printf("\n");
}

/**********************************************************/

void push_back(Wezel **pierwszy, int liczba) {//wstaw element na koniec listy

  // konstruujemy nowy węzeł
  Wezel* element;
  Wezel* iterator = *pierwszy;

  if( (element = (Wezel*)malloc(sizeof(Wezel))) == NULL ) {
    fprintf(stderr, "Za mało pamięci!\n");
    exit(ZA_MALO_PAMIECI);
  }
    element->wartosc = liczba;
    element->nastepny = NULL;

    if (iterator == NULL){//gdy pusta lista
        *pierwszy = element;
        return;
    }

    while (iterator->nastepny != NULL){
        iterator = iterator->nastepny;
    }

    iterator->nastepny = element;

}

/*************** PONIZEJ NAPISZ BRAKUJACA FUNKCJE **********/
Wezel* get_elem(Wezel *pierwszy, int pozycja) {
  Wezel *biezacy = pierwszy;
  int aktualna_pozycja = 0;

  if (!biezacy) {
    printf("Pusta lista\n");
  }

  while (biezacy != NULL && aktualna_pozycja < pozycja) {
    biezacy = biezacy -> nastepny;
    aktualna_pozycja++;
  }

  if (biezacy == NULL) {
    printf("Lista za krótka\n");
  }

  return biezacy;
}

/**********************************************************/

int main(){
  int dlugosc, x, pozycja;
  Wezel *element;
  Wezel *pierwszy = NULL;

  //wczytaj wartosci i zbuduj liste
  scanf("%d", &dlugosc);
  for (int i = 0; i < dlugosc; i++) {
    scanf("%d", &x);
    push_back(&pierwszy, x);
  }

  //wypisz liste
  wypisz(pierwszy);


  //przetestuj funkcję get_elem dla pozycji 3-ciej
  pozycja = 3;
  element = get_elem(pierwszy, pozycja);
  if (element) {
    printf("Na pozycji %d znajduje sie wartosc %d\n", pozycja, element -> wartosc);
  }

  //przetestuj funkcję get_elem dla pozycji 0-wej
  pozycja = 0;
  element = get_elem(pierwszy, pozycja);
  if (element) {
    printf("Na pozycji %d znajduje sie wartosc %d\n", pozycja, element -> wartosc);
  }

  //przetestuj funkcję get_elem dla pozycji ostatniej
  pozycja = dlugosc - 1;
  element = get_elem(pierwszy, pozycja);
  if (element) {
    printf("Na pozycji ostatniej znajduje sie wartosc %d\n", element -> wartosc);
  }

  //przetestuj funkcję get_elem dla pozycji spoza listy
  pozycja = dlugosc + 10;
  element = get_elem(pierwszy, pozycja);

  //przetestuj funkcję get_elem dla pozycji drugiej i pustej listy
  Wezel *pusta_lista = NULL;
  pozycja = 2;
  element = get_elem(pusta_lista, pozycja);
}
