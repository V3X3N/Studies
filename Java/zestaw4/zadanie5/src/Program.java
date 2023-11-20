public class Program {
    public static void main(String[] args) {
        Osoba osoba1 = new Osoba("Jan", "Kowalski", 1985);
        Osoba osoba2 = new Osoba("Anna", "Nowak", 1990);

        Dokument[] bazaDanych = {
                new Paszport(osoba1, "AB123456"),
                new DowodOsobisty(osoba2, "XYZ987654"),
                new Paszport(osoba2, "CD654321")
        };

        String wzorzec = "Nowak";

        for (Dokument dokument : bazaDanych) {
            if (dokument.czyPasuje(wzorzec)) {
                System.out.println("Znaleziono: " + dokument);
            }
            else {
                System.out.println("Dokument nie pasuje do wzorca");
            }
        }
    }
}