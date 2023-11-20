class DowodOsobisty extends Dokument {
    private String numerDowodu;

    public DowodOsobisty(Osoba osoba, String numerDowodu) {
        super(osoba);
        this.numerDowodu = numerDowodu;
    }

    public boolean czyPasuje(String wzorzec) {
        Osoba osoba = getOsoba();
        return numerDowodu.equalsIgnoreCase(wzorzec)
                || osoba.getImie().equalsIgnoreCase(wzorzec)
                || osoba.getNazwisko().equalsIgnoreCase(wzorzec)
                || String.valueOf(osoba.getRokUrodzenia()).equalsIgnoreCase(wzorzec);
    }

    public String toString() {
        return "Dowód osobisty - " + numerDowodu + ", " + getOsoba().getImie() + " " + getOsoba().getNazwisko();
    }
}