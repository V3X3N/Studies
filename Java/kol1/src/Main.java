import java.util.Random;

class Macierz3x3 {

    int[][] macierz3x3; //Definiujemy macierz

    public Macierz3x3() {
        macierz3x3 = new int[3][3];
        Random rand = new Random();

        //Macierz uzupelniamy losowymi liczbami całkowitymi od 0 do 10
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                macierz3x3[i][j] = rand.nextInt(11); //Tutaj generujemy losowosc
            }
        }
    }


    //Metoda wypisywania macierzy na ekran
    void printMacierz3x3() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.printf("%d ", macierz3x3[i][j]);
            }
            System.out.println();
        }
    }


    //Metoda transpozycji macierzy
    Macierz3x3 transpozycjaMacierz3x3() {
        Macierz3x3 wynik = new Macierz3x3();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                wynik.macierz3x3[i][j] = this.macierz3x3[j][i]; //Transpozycja
            }
        }
        return wynik;
    }


    //Metoda dodawania macierzy (przed transpozycja)
    Macierz3x3 addMacierz3x3(Macierz3x3 otherMacierz) {
        Macierz3x3 wynik = new Macierz3x3();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                wynik.macierz3x3[i][j] = this.macierz3x3[i][j] + otherMacierz.macierz3x3[i][j];
            }
        }
        return wynik;
    }


    //Mnozenie macierzy (przed transpozycja)
    Macierz3x3 multiplyMacierz3x3(Macierz3x3 otherMacierz) {
        Macierz3x3 wynik = new Macierz3x3();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                wynik.macierz3x3[i][j] = 0; //Inicjalizacja macierzy z zerami

                for (int k = 0; k < 3; k++) {
                    wynik.macierz3x3[i][j] += this.macierz3x3[i][k] * otherMacierz.macierz3x3[k][j]; //Mnozenie
                }
            }
        }
        return wynik;
    }


    //Metoda wyznaczania wyznacznika macierzy
    int Wyznacznik() {
        int w = 0;

        w += (macierz3x3[0][0] * macierz3x3[1][1] * macierz3x3[2][2]); //Pierwszy element
        w += (macierz3x3[0][1] * macierz3x3[1][2] * macierz3x3[2][0]); //Drugi element
        w += (macierz3x3[0][2] * macierz3x3[1][0] * macierz3x3[2][1]); //Trzeci element

        w -= (macierz3x3[0][2] * macierz3x3[1][1] * macierz3x3[2][0]); //Odjęcie pierwszego elementu
        w -= (macierz3x3[0][0] * macierz3x3[1][2] * macierz3x3[2][1]); //Odjęcie drugiego elementu
        w -= (macierz3x3[0][1] * macierz3x3[1][0] * macierz3x3[2][2]); //Odjęcie trzeciego elementu

        return w;
    }


    //Glowny Swiety Gral
    public static void main(String[] args) {
        Macierz3x3 macierz1 = new Macierz3x3();
        Macierz3x3 macierz2 = new Macierz3x3();


        // Wyświetlamy macierze
        System.out.println("Macierz nr.1:");
        macierz1.printMacierz3x3();

        System.out.println("Macierz nr.2:");
        macierz2.printMacierz3x3();


        // Transpozycja macierzy
        Macierz3x3 mTranspozycja1 = macierz1.transpozycjaMacierz3x3();
        System.out.println("Macierz nr.1 po transpozycji: ");
        mTranspozycja1.printMacierz3x3();

        Macierz3x3 mTranspozycja2 = macierz2.transpozycjaMacierz3x3();
        System.out.println("Macierz nr.2 po transpozycji:");
        mTranspozycja2.printMacierz3x3();



        // Dodajemy macierze
        Macierz3x3 sumaMacierzy = macierz1.addMacierz3x3(macierz2);
        System.out.println("Suma macierzy nr.1 i nr.2 przed transpozycja:");
        sumaMacierzy.printMacierz3x3();

        // Mnożymy macierze
        Macierz3x3 iloczynMacierzy = macierz1.multiplyMacierz3x3(macierz2);
        System.out.println("Iloczyn Macierzy nr.1 i nr.2: ");
        iloczynMacierzy.printMacierz3x3();


        //Wyznacznik macierzy nr. 1
        int wyznacznik = macierz1.Wyznacznik();
        System.out.println("Wyznacznik macierzy nr.1 " + wyznacznik);
    }
}