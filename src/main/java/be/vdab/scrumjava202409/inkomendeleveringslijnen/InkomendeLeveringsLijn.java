package be.vdab.scrumjava202409.inkomendeleveringslijnen;

public class InkomendeLeveringsLijn {

    private long inkomendeLeveringsId;
    private long artikelId;
    private int aantalGoedgekeurd;
    private int aantalTeruggestuurd;
    private long magazijnPlaatsId;


    public InkomendeLeveringsLijn(long inkomendeLeveringsId, long artikelId, int aantalGoedgekeurd, int aantalTeruggestuurd, long magazijnPlaatsId) {
        this.inkomendeLeveringsId = inkomendeLeveringsId;
        this.artikelId = artikelId;
        this.aantalGoedgekeurd = aantalGoedgekeurd;
        this.aantalTeruggestuurd = aantalTeruggestuurd;
        this.magazijnPlaatsId = magazijnPlaatsId;
    }

    public int getAantalGoedgekeurd() {
        return aantalGoedgekeurd;
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
