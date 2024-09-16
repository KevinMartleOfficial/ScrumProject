package be.vdab.scrumjava202409.magazijnplaatsen;

public class MagazijnPlaats {
    private long magazijnPlaatsId;
    private long artikelId;
    private char rij;
    private int rek;
    private int aantal;

    public MagazijnPlaats(long magazijnPlaatsId, long artikelId, char rij, int rek, int aantal) {
        this.magazijnPlaatsId = magazijnPlaatsId;
        this.artikelId = artikelId;
        this.rij = rij;
        this.rek = rek;
        this.aantal = aantal;
    }

    public long getMagazijnPlaatsId() {
        return magazijnPlaatsId;
    }

    public long getArtikelId() {
        return artikelId;
    }

    public char getRij() {
        return rij;
    }

    public int getRek() {
        return rek;
    }

    public int getAantal() {
        return aantal;
    }
}
