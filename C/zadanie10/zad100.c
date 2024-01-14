#include <stdio.h>
#include <stdlib.h>

#define ZA_MALO_PAMIECI -1

typedef struct wezel
{
    int wartosc;
    struct wezel *lewy, *prawy;
} Wezel;

//---------------------------------------------------

Wezel *nowyWezel(int liczba)
{
    Wezel *nowy;

    if ((nowy = (Wezel *)malloc(sizeof(Wezel))) == NULL)
    {
        fprintf(stderr, "Za mało pamięci!\n");
        exit(ZA_MALO_PAMIECI);
    }
    nowy->wartosc = liczba;
    nowy->lewy = NULL;
    nowy->prawy = NULL;

    return nowy;
}

//---------------------------------------------------
Wezel *wstaw(Wezel **wezelek, int liczba)
{

    if (*wezelek == NULL)
    {
        *wezelek = nowyWezel(liczba);
    }
    else
    {
        if ((*wezelek)->wartosc > liczba)
        {
            (*wezelek)->lewy = wstaw(&(*wezelek)->lewy, liczba);
        }
        else
        {
            (*wezelek)->prawy = wstaw(&(*wezelek)->prawy, liczba);
        }
    }
    return *wezelek;
}
//---------------------------------------------------
// wypisz preorder (korzen, lewe, prawe)
void preorder(Wezel *wezelek)
{
    if (wezelek != NULL)
    {
        printf("%d\n", wezelek->wartosc);
        if (wezelek->lewy != NULL)
            preorder(wezelek->lewy);

        if (wezelek->prawy != NULL)
            preorder(wezelek->prawy);
    }
}

//---------------------------------------------------
Wezel *szukaj(Wezel *wezelek, int liczba)
{
    if (wezelek == NULL || wezelek->wartosc == liczba)
    {
        return wezelek;
    }

    if (wezelek->wartosc > liczba)
    {
        return szukaj(wezelek->lewy, liczba);
    }

    return szukaj(wezelek->prawy, liczba);
}

//---------------------------------------------------
int main()
{

    int liczba;

    Wezel *korzen = NULL;
    do
    {
        scanf("%d", &liczba);
        korzen = wstaw(&korzen, liczba);
    } while (getchar() != '\n');

    printf("Wypisz preorder\n");
    preorder(korzen);
    Wezel *element = szukaj(korzen, liczba);
    if (element)
        printf("Znaleziono element, ktory zawiera wartosc %d\n", element->wartosc);
    else
        printf("Nie znaleziono elementu, ktory zawiera wartosc %d\n", liczba);

    return 0;
}
