package be.vdab.scrumjava202409.inkomendeleveringslijnen;


import jakarta.validation.constraints.*;

public class InkomendeLeveringsLijnGeenMagazijnId {

    private long inkomendeLeveringsId;
    private long artikelId;
    private int aantalGoedgekeurd;
    private int aantalTeruggestuurd;

    public InkomendeLeveringsLijnGeenMagazijnId(@Positive long inkomendeLeveringsId,
                                         @Positive long artikelId,
                                         @PositiveOrZero int aantalGoedgekeurd,
                                         @PositiveOrZero int aantalTeruggestuurd){
        this.inkomendeLeveringsId = inkomendeLeveringsId;
        this.artikelId = artikelId;
        this.aantalGoedgekeurd = aantalGoedgekeurd;
        this.aantalTeruggestuurd = aantalTeruggestuurd;

    }

    public InkomendeLeveringsLijnGeenMagazijnId(InkomendeLeveringsLijnGeenMagazijnId inkomendeLeveringsLijnGeenMagazijnId){
        this(inkomendeLeveringsLijnGeenMagazijnId.getInkomendeLeveringsId(), inkomendeLeveringsLijnGeenMagazijnId.getArtikelId(), inkomendeLeveringsLijnGeenMagazijnId.getAantalGoedgekeurd(), inkomendeLeveringsLijnGeenMagazijnId.getAantalTeruggestuurd());
    }

    public long getInkomendeLeveringsId() {
        return inkomendeLeveringsId;
    }

    public long getArtikelId() {
        return artikelId;
    }

    public int getAantalGoedgekeurd() {
        return aantalGoedgekeurd;
    }

    public int getAantalTeruggestuurd() {
        return aantalTeruggestuurd;
    }

    public void verminderAantalGoedgekeurd(int aantalWeggelegd){
        this.aantalGoedgekeurd = this.aantalGoedgekeurd - aantalWeggelegd;
    }


