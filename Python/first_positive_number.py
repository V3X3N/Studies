from random import randint

lista_liczb_losowych = []
i = 1
while i <= 10:
    lista_liczb_losowych.append(randint(-100, 100))
    i += 1
print(lista_liczb_losowych)

indeks = 0 #przygotowujemy zmienna indeksu listy
element = 0 #element iteracyjny

while indeks <= 10:
    if lista_liczb_losowych[indeks] > 0: #sprawdzamy czy wartosc na podanym miejscu jest dodatnia
        element = lista_liczb_losowych[indeks] #jezeli tak, wypisujemy ja do zmiennej element...
        break #...i konczymy petle
    else:
        indeks+=1 #jezeli nie, zwiekszamy indeks i powtarzamy
        
print(element)