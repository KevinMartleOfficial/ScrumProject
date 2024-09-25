package be.vdab.scrumjava202409.inkomendeleveringslijnen;

public class InkomendeLeveringsLijn {

    private long inkomendeLeveringsId;
    private long artikelId;
    private int aantalGoedgekeurde; //geen 'e' op het einde (kijk de rest ook nog na in deze class)
    private int aantalTeruggestuurd;
    private long magazijnPlaatsId;


    public InkomendeLeveringsLijn(long inkomendeLeveringsId, long artikelId, int aantalGoedgekeurde, int aantalTeruggestuurd, long magazijnPlaatsId) {
        this.inkomendeLeveringsId = inkomendeLeveringsId;
        this.artikelId = artikelId;
        this.aantalGoedgekeurde = aantalGoedgekeurde;
        this.aantalTeruggestuurd = aantalTeruggestuurd;
        this.magazijnPlaatsId = magazijnPlaatsId;
    }

    public int getAantalGoedgekeurde() {
        return aantalGoedgekeurde;
    }

    public int getAantalTeruggestuurd() {
        return aantalTeruggestuurd;
    }

    public long getArtikelId() {
        return artikelId;
    }

    public long getInkomendeLeveringsId() {
        return inkomendeLeveringsId;
    }

    public long getMagazijnPlaatsId() {
        return magazijnPlaatsId;
    }
}
