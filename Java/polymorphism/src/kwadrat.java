class Kwadrat extends Figura {
    double bok;

    Kwadrat(double bok) {
        this.bok = bok;
    }

    double pole() {
        return bok * bok;
    }

    double obwod() {
        return 4 * bok;
    }

    public String toString() {
        return "kwadrat o boku " + bok;
    }
}