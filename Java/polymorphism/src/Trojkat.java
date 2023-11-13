class Trojkat extends Figura {
    double a;
    double b;
    double c;

    Trojkat(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    double pole() {
        double p = (a + b + c) / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

    double obwod() {
        return a + b + c;
    }

    public String toString() {
        return "trojkat o bokach " + a + ", " + b + ", " + c;
    }
}