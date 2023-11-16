class LiczbaZespolona {
    private double real;  // Część rzeczywista
    private double imag;  // Część urojona

    public LiczbaZespolona(double real, double imag) {
        this.real = real;
        this.imag = imag;
    }

    public double getReal() {
        return real;
    }

    public double getImag() {
        return imag;
    }

    public LiczbaZespolona dodaj(LiczbaZespolona other) {
        double newReal = this.real + other.real;
        double newImag = this.imag + other.imag;
        return new LiczbaZespolona(newReal, newImag);
    }

    public LiczbaZespolona odejmij(LiczbaZespolona other) {
        double newReal = this.real - other.real;
        double newImag = this.imag - other.imag;
        return new LiczbaZespolona(newReal, newImag);
    }

    public LiczbaZespolona pomnoz(LiczbaZespolona other) {
        double newReal = this.real * other.real - this.imag * other.imag;
        double newImag = this.real * other.imag + this.imag * other.real;
        return new LiczbaZespolona(newReal, newImag);
    }

    public LiczbaZespolona podziel(LiczbaZespolona other) {
        double denominator = other.real * other.real + other.imag * other.imag;
        if (denominator == 0) {
            throw new ArithmeticException("Dzielenie przez zero jest niemożliwe w liczbach zespolonych");
        }

        double newReal = (this.real * other.real + this.imag * other.imag) / denominator;
        double newImag = (this.imag * other.real - this.real * other.imag) / denominator;
        return new LiczbaZespolona(newReal, newImag);
    }

    @Override
    public String toString() {
        if (imag >= 0) {
            return real + " + " + imag + "i";
        } else {
            return real + " - " + Math.abs(imag) + "i";
        }
    }
}