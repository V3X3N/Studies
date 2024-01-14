#include <stdio.h>
#include <stdlib.h>
#define ZA_MALO_PAMIECI -1

typedef struct wezel
{
    int wartosc;
    struct wezel *lewy, *prawy;

} Wezel;
Wezel *korzen = NULL;

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
    printf("%d\n", wezelek->wartosc);
    if (wezelek->lewy != NULL)
        preorder(wezelek->lewy);

    if (wezelek->prawy != NULL)
        preorder(wezelek->prawy);
}

//---------------------------------------------------
Wezel *znajdzMin(Wezel *wezelek)
{
    if (wezelek == NULL)
    {
        fprintf(stderr, "Drzewo puste\n");
        exit(1);
    }

    while (wezelek->lewy != NULL)
    {
        wezelek = wezelek->lewy;
    }

    return wezelek;
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

    Wezel *element = znajdzMin(korzen);
    printf("Element najmniejszy to %d\n", element->wartosc);
}