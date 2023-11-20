abstract class Dokument implements Przeszukiwalne {
    private Osoba osoba;

    public Dokument(Osoba osoba) {
        this.osoba = osoba;
    }

    public Osoba getOsoba() {
        return osoba;
    }
    public boolean czyPasuje(String wzorzec) {
        Osoba osoba = getOsoba();
        return osoba.getImie().equalsIgnoreCase(wzorzec)
                || osoba.getNazwisko().equalsIgnoreCase(wzorzec)
                || String.valueOf(osoba.getRokUrodzenia()).equalsIgnoreCase(wzorzec);
    }
}

