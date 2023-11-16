public class Main {
    public static void main(String[] args) {
        // Tworzenie punktu
        Punkt obj;
        obj = new Punkt(0, 0);
        System.out.println("Punkt przed przesunięciem: " + obj);

        // Przesunięcie punktu
        obj.przesun(-1, 3);
        System.out.println("Punkt po przesunięciu: " + obj);

        // Tworzenie prostokąta
        Prostokat obj2;
        obj2 = new Prostokat(obj, 3, 4);
        System.out.println("Prostokąt: " + obj2);

        // Przesunięcie prostokąta
        obj2.przesun(7, -3);
        System.out.println("Prostokąt po przesunięciu: " + obj2);

        // Obliczanie pola prostokąta
        double p = obj2.pole();
        System.out.println("Pole prostokąta: " + p);

        // Tworzenie okręgu
        Okrag obj3;
        obj3 = new Okrag(obj, 5);
        System.out.println("Okrąg: " + obj3);

        // Przesunięcie okręgu
        obj3.przesun(2, -1);
        System.out.println("Okrąg po przesunięciu: " + obj3);

        // Obliczanie pola okręgu
        double poleOkręgu = obj3.pole();
        System.out.println("Pole okręgu: " + poleOkręgu);

        // Testowanie metody zawiera dla prostokąta
        Punkt punkt1 = new Punkt(1, 2);
        Punkt punkt2 = new Punkt(5, 5);

        System.out.println("Czy prostokąt zawiera punkt1? " + obj2.zawiera(punkt1));
        System.out.println("Czy prostokąt zawiera punkt2? " + obj2.zawiera(punkt2));

        // Testowanie metody zawiera dla okręgu
        Punkt punkt3 = new Punkt(4, 4);
        Punkt punkt4 = new Punkt(8, 8);

        System.out.println("Czy okrąg zawiera punkt3? " + obj3.zawiera(punkt3));
        System.out.println("Czy okrąg zawiera punkt4? " + obj3.zawiera(punkt4));

        // Tworzenie drugiego okręgu
        Okrag obj4;
        obj4 = new Okrag(new Punkt(8, 8), 3);
        System.out.println("Drugi okrąg: " + obj4);

        // Przesunięcie drugiego okręgu
        obj4.przesun(-2, 2);
        System.out.println("Drugi okrąg po przesunięciu: " + obj4);

        // Testowanie metody przecina dla okręgów
        System.out.println("Czy pierwszy okrąg przecina drugi okrąg? " + obj3.przecina(obj4));
    }
}
