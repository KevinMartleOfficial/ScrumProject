package be.vdab.scrumjava202409.artikelen;

import java.math.BigDecimal;

public class Artikel {

    private long artikelId;
    private String ean;
    private String naam;
    private String beschrijving;
    private BigDecimal prijs;
    private int gewichtInGram;
    private int bestelpeil;
    private int voorraad;
    private int minimumVoorraad;
    private int maximumVoorraad;
    private int levertijd;
    private int aantalBesteldLeverancier;
    private int maxAantalInMagazijnPlaats;
    private int leveranciersId;


    public Artikel(long artikelId, String ean, String naam, String beschrijving, BigDecimal prijs, int gewichtInGram, int bestelpeil, int voorraad, int minimumVoorraad, int maximumVoorraad, int levertijd, int aantalBesteldLeverancier, int maxAantalInMagazijnPlaats, int leverancierId){
        setArtikelId(artikelId);
        setEan(ean);
        setNaam(naam);
        setBeschrijving(beschrijving);
        setPrijs(prijs);
        setGewichtInGram(gewichtInGram);
        setBestelpeil(bestelpeil);
        setVoorraad(voorraad);
        setMinimumVoorraad(minimumVoorraad);
        setMaximumVoorraad(maximumVoorraad);
        setLevertijd(levertijd);
        setAantalBesteldLeverancier(aantalBesteldLeverancier);
        setMaxAantalInMagazijnPlaats(maxAantalInMagazijnPlaats);
        setLeveranciersId(leverancierId);

    }

    protected Artikel(){

    }


    public BigDecimal getPrijs() {
        return prijs;
    }

    public int getAantalBesteldLeverancier() {
        return aantalBesteldLeverancier;
    }

    public int getBestelpeil() {
        return bestelpeil;
    }

    public int getGewichtInGram() {
        return gewichtInGram;
    }

    public int getLeveranciersId() {
        return leveranciersId;
    }

    public int getLevertijd() {
        return levertijd;
    }

    public int getMaxAantalInMagazijnPlaats() {
        return maxAantalInMagazijnPlaats;
    }

    public int getMaximumVoorraad() {
        return maximumVoorraad;
    }

    public long getArtikelId() {
        return artikelId;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public int getMinimumVoorraad() {
        return minimumVoorraad;
    }

    public String getEan() {
        return ean;
    }

    public int getVoorraad() {
        return voorraad;
    }

    public String getNaam() {
        return naam;
    }

    private void setArtikelId(long artikelId) {
        this.artikelId = artikelId;
    }

    private void setAantalBesteldLeverancier(int aantalBesteldLeverancier) {
        this.aantalBesteldLeverancier = aantalBesteldLeverancier;
    }

    private void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    private void setBestelpeil(int bestelpeil) {
        this.bestelpeil = bestelpeil;
    }

    private void setEan(String ean) {
        this.ean = ean;
    }

    private void setGewichtInGram(int gewichtInGram) {
        this.gewichtInGram = gewichtInGram;
    }

    private void setLeveranciersId(int leveranciersId) {
        this.leveranciersId = leveranciersId;
    }

    private void setLevertijd(int levertijd) {
        this.levertijd = levertijd;
    }

    private void setMaxAantalInMagazijnPlaats(int maxAantalInMagazijnPlaats) {
        this.maxAantalInMagazijnPlaats = maxAantalInMagazijnPlaats;
    }

    private void setMaximumVoorraad(int maximumVoorraad) {
        this.maximumVoorraad = maximumVoorraad;
    }

    private void setMinimumVoorraad(int minimumVoorraad) {
        this.minimumVoorraad = minimumVoorraad;
    }

    private void setNaam(String naam) {
        this.naam = naam;
    }

    private void setPrijs(BigDecimal prijs) {
        this.prijs = prijs;
    }

    private void setVoorraad(int voorraad) {
        this.voorraad = voorraad;
    }
}
