class Prostokat {
    double dlugosc;
    double szerokosc;
    Punkt wierzcholek;

    Prostokat(Punkt wierzcholek, double dlugosc, double szerokosc) {
        this.wierzcholek = wierzcholek;
        this.dlugosc = dlugosc;
        this.szerokosc = szerokosc;
    }

    Prostokat(double dlugosc, double szerokosc) {
        this.dlugosc = dlugosc;
        this.szerokosc = szerokosc;
        this.wierzcholek = new Punkt(0, 0);
    }

    public String toString() {
        return "[dl: " + dlugosc + ", sz: " + szerokosc + "]" + wierzcholek.toString();
    }

    double pole() {
        return dlugosc * szerokosc;
    }

    void przesun(double u, double v) {
        wierzcholek.przesun(u, v);
    }

    boolean zawiera(Punkt obj) {
        double xMin = wierzcholek.x;
        double xMax = wierzcholek.x + dlugosc;
        double yMin = wierzcholek.y;
        double yMax = wierzcholek.y + szerokosc;

        return obj.x >= xMin && obj.x <= xMax && obj.y >= yMin && obj.y <= yMax;
    }
}