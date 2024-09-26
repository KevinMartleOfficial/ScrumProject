package be.vdab.scrumjava202409.inkomendeleveringslijnen;

public class InkomendeLeveringsLijnMetStringMagazijnplaats {

    private long inkomendeLeveringsId;
    private long artikelId;
    private int aantalGoedgekeurd;
    private int aantalTeruggestuurd;
    private int hoeveelheidOpMagazijnplaats;
    private String magazijnPlaats;
    private int maxAantalOpPlaats;
    private int hoeveelheidWeggelegd;

    public InkomendeLeveringsLijnMetStringMagazijnplaats(long inkomendeLeveringsId,
                                                         long artikelId,
                                                         int aantalGoedgekeurd,
                                                         int aantalTeruggestuurd,
                                                         int hoeveelheidOpMagazijnplaats,
                                                         String magazijnPlaats,
                                                         int maxAantalOpPlaats,
                                                         int hoeveelheidWeggelegd) {
        this.inkomendeLeveringsId = inkomendeLeveringsId;
        this.artikelId = artikelId;
        this.aantalGoedgekeurd = aantalGoedgekeurd;
        this.aantalTeruggestuurd = aantalTeruggestuurd;
        this.hoeveelheidOpMagazijnplaats = hoeveelheidOpMagazijnplaats;
        this.magazijnPlaats = magazijnPlaats;
        this.maxAantalOpPlaats = maxAantalOpPlaats;
        this.hoeveelheidWeggelegd = hoeveelheidWeggelegd;
    }

    public InkomendeLeveringsLijnMetStringMagazijnplaats(InkomendeLeveringsLijnMetStringMagazijnplaats inkomendeLeveringsLijnMetStringMagazijnplaats){
        this(inkomendeLeveringsLijnMetStringMagazijnplaats.getInkomendeLeveringsId(),
                inkomendeLeveringsLijnMetStringMagazijnplaats.getArtikelId(),
                inkomendeLeveringsLijnMetStringMagazijnplaats.getAantalGoedgekeurd(),
                inkomendeLeveringsLijnMetStringMagazijnplaats.getAantalTeruggestuurd(),
                inkomendeLeveringsLijnMetStringMagazijnplaats.getHoeveelheidOpMagazijnplaats(),
                inkomendeLeveringsLijnMetStringMagazijnplaats.getMagazijnPlaats(),
                inkomendeLeveringsLijnMetStringMagazijnplaats.getMaxAantalOpPlaats(),
                inkomendeLeveringsLijnMetStringMagazijnplaats.getHoeveelheidWeggelegd());
    }

    public String getMagazijnPlaats() {
        return magazijnPlaats;
    }

    public int getHoeveelheidOpMagazijnplaats() {
        return hoeveelheidOpMagazijnplaats;
    }

    public int getAantalTeruggestuurd() {
        return aantalTeruggestuurd;
    }

    public int getAantalGoedgekeurd() {
        return aantalGoedgekeurd;
    }

    public long getArtikelId() {
        return artikelId;
    }

    public long getInkomendeLeveringsId() {
        return inkomendeLeveringsId;
    }

    public int getMaxAantalOpPlaats() {
        return maxAantalOpPlaats;
    }

    public int getHoeveelheidWeggelegd() {
        return hoeveelheidWeggelegd;
    }

    @Override
    public String toString() {
        return "InkomendeLeveringsLijnMetStringMagazijnplaats{" +
                ", artikelId=" + artikelId +
                ", aantalGoedgekeurd=" + aantalGoedgekeurd +
                ", hoeveelheidOpMagazijnplaats=" + hoeveelheidOpMagazijnplaats +
                ", magazijnPlaats='" + magazijnPlaats + '\'' +
                ", maxAantalOpPlaats=" + maxAantalOpPlaats +
                ", hoeveelheidWeggelegd=" + hoeveelheidWeggelegd +
                '}';
    }
}
