class Ulamek {
    private int licznik;
    private int mianownik;

    public Ulamek(int licznik, int mianownik) {
        if (mianownik == 0) {
            throw new IllegalArgumentException("Mianownik nie może być zerem");
        }
        this.licznik = licznik;
        this.mianownik = mianownik;
    }

    @Override
    public String toString() {
        return licznik + "/" + mianownik;
    }

    public double rozwDziesietne() {
        return (double) licznik / mianownik;
    }

    public Ulamek plus(Ulamek other) {
        int wspolnyMianownik = this.mianownik * other.mianownik;
        int nowyLicznik = this.licznik * other.mianownik + other.licznik * this.mianownik;
        return skroc(new Ulamek(nowyLicznik, wspolnyMianownik));
    }

    public Ulamek minus(Ulamek other) {
        int wspolnyMianownik = this.mianownik * other.mianownik;
        int nowyLicznik = this.licznik * other.mianownik - other.licznik * this.mianownik;
        return skroc(new Ulamek(nowyLicznik, wspolnyMianownik));
    }

    public Ulamek razy(Ulamek other) {
        int nowyLicznik = this.licznik * other.licznik;
        int nowyMianownik = this.mianownik * other.mianownik;
        return skroc(new Ulamek(nowyLicznik, nowyMianownik));
    }

    public void odwroc() {
        if (licznik == 0) {
            throw new ArithmeticException("Nie można odwrócić ułamka o zerowym liczniku");
        }
        int temp = licznik;
        licznik = mianownik;
        mianownik = temp;
    }

    public void skroc() {
        int gcd = gcd(licznik, mianownik);
        if (gcd > 1) {
            licznik /= gcd;
            mianownik /= gcd;
        }
    }

    private Ulamek skroc(Ulamek ulamek) {
        int gcd = gcd(ulamek.licznik, ulamek.mianownik);
        if (gcd > 1) {
            ulamek.licznik /= gcd;
            ulamek.mianownik /= gcd;
        }
        return ulamek;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return Math.abs(a);
    }
}