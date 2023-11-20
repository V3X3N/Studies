class Paszport extends Dokument {
    private String numerPaszportu;

    public Paszport(Osoba osoba, String numerPaszportu) {
        super(osoba);
        this.numerPaszportu = numerPaszportu;
    }

    public boolean czyPasuje(String wzorzec) {
        Osoba osoba = getOsoba();
        return numerPaszportu.equalsIgnoreCase(wzorzec)
                || osoba.getImie().equalsIgnoreCase(wzorzec)
                || osoba.getNazwisko().equalsIgnoreCase(wzorzec)
                || String.valueOf(osoba.getRokUrodzenia()).equalsIgnoreCase(wzorzec);
    }

    public String toString() {
        return "Paszport - " + numerPaszportu + ", " + getOsoba().getImie() + " " + getOsoba().getNazwisko();
    }
}