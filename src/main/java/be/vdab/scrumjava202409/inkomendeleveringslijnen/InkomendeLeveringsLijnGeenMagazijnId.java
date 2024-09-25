package be.vdab.scrumjava202409.inkomendeleveringslijnen;

public record InkomendeLeveringsLijnGeenMagazijnId(long inkomendeLeveringsId, long artikelId, int aantalGoedgekeurde, int aantalTeruggestuurd) {
//'e' bij goedgekeurde verwijderen
    // mss ook valid-annotaties gebruiken? @NotBlank enz
}
