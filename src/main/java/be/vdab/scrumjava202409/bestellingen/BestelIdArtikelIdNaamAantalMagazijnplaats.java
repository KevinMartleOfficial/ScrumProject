package be.vdab.scrumjava202409.bestellingen;

public record BestelIdArtikelIdNaamAantalMagazijnplaats(
        long bestelId, long artikelId, String artikelNaam, int aantal, String magazijnPlaats) {}
