import java.util.Arrays;

public class WielomianKwadratowy {
    private double a;
    private double b;
    private double c;

    public WielomianKwadratowy(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }

    public WielomianKwadratowy dodaj(WielomianKwadratowy other) {
        double newA = this.a + other.a;
        double newB = this.b + other.b;
        double newC = this.c + other.c;
        return new WielomianKwadratowy(newA, newB, newC);
    }

    public WielomianKwadratowy pomnoz(WielomianKwadratowy other) {
        // (a1x^2 + b1x + c1) * (a2x^2 + b2x + c2)
        double newA = this.a * other.a;
        double newB = this.a * other.b + this.b * other.a;
        double newC = this.a * other.c + this.b * other.b + this.c * other.a;
        return new WielomianKwadratowy(newA, newB, newC);
    }

    public double[] znajdzMiejscaZerowe() {
        // Obliczanie deltay
        double delta = b * b - 4 * a * c;

        if (delta > 0) {
            // Dwa pierwiastki rzeczywiste
            double x1 = (-b + Math.sqrt(delta)) / (2 * a);
            double x2 = (-b - Math.sqrt(delta)) / (2 * a);
            return new double[]{x1, x2};
        } else if (delta == 0) {
            // Jeden pierwiastek rzeczywisty (podwójny)
            double x = -b / (2 * a);
            return new double[]{x};
        } else {
            // Brak pierwiastków rzeczywistych
            return new double[]{};
        }
    }

    @Override
    public String toString() {
        return a + "x^2 + " + b + "x + " + c;
    }
}
