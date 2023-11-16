public class Program {
    public static void main(String[] args) {
        Figura z = new Okrag(2);
        z.info();

        Figura[] a = {new Prostokat(3, 5), new Okrag(8), new Okrag(3), new Trojkat(3, 4, 5), new Kwadrat(4)};

        Figura x;
        double sumaPol = 0;
        double sumaObwodow = 0;

        for (int i = 0; i < a.length; i++) {
            x = a[i];
            x.info();
            sumaPol = sumaPol + x.pole();
            sumaObwodow = sumaObwodow + x.obwod();
        }

        System.out.printf("suma pol figur: %.3f\n", sumaPol);
        System.out.printf("suma obwodow figur: %.3f\n", sumaObwodow);
    }
}
