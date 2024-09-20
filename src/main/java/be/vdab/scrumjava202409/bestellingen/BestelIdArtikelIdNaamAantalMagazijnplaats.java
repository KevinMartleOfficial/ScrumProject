package be.vdab.scrumjava202409.bestellingen;

public record BestelIdArtikelIdNaamAantalMagazijnplaats(
        long bestelId, long artikelId, String artikelNaam, int aantal, String magazijnPlaats, int hoeveelheidOpMagazijnplaats) {
        public BestelIdArtikelIdNaamAantalMagazijnplaats(BestelIdArtikelIdNaamAantalMagazijnplaats copy){
            this(copy.bestelId, copy.artikelId, copy.artikelNaam, copy.aantal, copy.magazijnPlaats, copy.hoeveelheidOpMagazijnplaats);
        }
}
