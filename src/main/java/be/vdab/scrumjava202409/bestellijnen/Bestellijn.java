package be.vdab.scrumjava202409.bestellijnen;

public class Bestellijn {
    private long bestellijnId;
    private long bestelId;
    private long artikelId;
    private int aantalBesteld;
    private int aantalGeannuleerd;

    Bestellijn(long bestellijnId, long bestelId, long artikelId, int aantalBesteld, int aantalGeannuleerd) {
        this.bestellijnId = bestellijnId;
        this.bestelId = bestelId;
        this.artikelId = artikelId;
        this.aantalBesteld = aantalBesteld;
        this.aantalGeannuleerd = aantalGeannuleerd;
    }

    public long getBestellijnId() {
        return bestellijnId;
    }

    public long getBestelId() {
        return bestelId;
    }

    public long getArtikelId() {
        return artikelId;
    }

    public int getAantalBesteld() {
        return aantalBesteld;
    }

    public int getAantalGeannuleerd() {
        return aantalGeannuleerd;
    }

}
