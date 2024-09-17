package be.vdab.scrumjava202409.bestellingen;

import be.vdab.scrumjava202409.bestellijnen.Bestellijn;

import java.util.List;

public record BestellingTVDTO(long bestelId, int aantalProducten, int totaalGewicht) {
    public BestellingTVDTO(Bestelling bestelling, int aantalProducten, int totaalGewicht){
        this(bestelling.getBestelId(), aantalProducten, totaalGewicht);
    }
}
