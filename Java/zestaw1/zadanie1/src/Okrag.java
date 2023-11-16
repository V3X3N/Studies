class Okrag {
    double promien;
    Punkt srodek;

    Okrag(Punkt srodek, double promien) {
        this.srodek = srodek;
        this.promien = promien;
    }

    Okrag(double promien) {
        this.promien = promien;
        this.srodek = new Punkt(0, 0);
    }

    public String toString() {
        return "[promien: " + promien + "]" + srodek.toString();
    }

    double pole() {
        return Math.PI * Math.pow(promien, 2);
    }

    void przesun(double u, double v) {
        srodek.przesun(u, v);
    }

    boolean zawiera(Punkt obj) {
        double odlegloscKwadrat = Math.pow(obj.x - srodek.x, 2) + Math.pow(obj.y - srodek.y, 2);
        return odlegloscKwadrat <= Math.pow(promien, 2);
    }

    boolean przecina(Okrag obj) {
        double odlegloscMiedzySrodkami = Math.sqrt(Math.pow(obj.srodek.x - this.srodek.x, 2) + Math.pow(obj.srodek.y - this.srodek.y, 2));
        double sumaPromieni = this.promien + obj.promien;

        return odlegloscMiedzySrodkami <= sumaPromieni;
    }
}