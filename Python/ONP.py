def ONP(wejscie):
    stos2 = []
    wyjscie = []
    
    priorytety = {'(':0,
                  '+':1,
                 '-':1,
                 ')':1,
                 '*':2,
                 '/':2,
                 '%':2,
                 '^':3}
    
    for i in wejscie.split(' '):
        if i.isalnum():
            wyjscie.append(i)
            
        elif i == '(':
            stos2.append(i)
            
        elif i == ')':
            while stos2[len(stos2)-1] != '(':
                wyjscie.append(stos2.pop())
            stos2.pop()
                
        else:
            while stos2 != [] and priorytety[i] <= priorytety[stos2[len(stos2)-1]]:
                wyjscie.append(stos2.pop())
            stos2.append(i)
            
        print(f'wyjscie{wyjscie}')
        print(f"stos2{stos2}")
            
    while stos2 != []:
        wyjscie.append(stos2.pop())
    return ' '.join(wyjscie)

print(ONP('( ( 2 + 7 ) / 3 + ( 14 - 3 ) * 4 ) / 2') )
##wyjsice 3,
##stos2