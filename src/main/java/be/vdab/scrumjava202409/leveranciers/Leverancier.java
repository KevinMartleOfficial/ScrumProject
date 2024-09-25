package be.vdab.scrumjava202409.leveranciers;

public class Leverancier {
    private long leveranciersId;
    private String naam;
    private String btwNummer;
    private String straat;
    private String huisNummer;
    private String bus;
    private long plaatsId;
    private String familienaamContactpersoon;
    private String voornaamContactpersoon;

    public Leverancier(long leveranciersId, String naam, String btwNummer, String straat,
                       String huisNummer, String bus, long plaatsId, String familienaamContactpersoon,
                       String voornaamContactpersoon) {
        this.leveranciersId = leveranciersId;
        this.naam = naam;
        this.btwNummer = btwNummer;
        this.straat = straat;
        this.huisNummer = huisNummer;
        this.bus = bus;
        this.plaatsId = plaatsId;
        this.familienaamContactpersoon = familienaamContactpersoon;
        this.voornaamContactpersoon = voornaamContactpersoon;
    }

    public long getLeveranciersId() {
        return leveranciersId;
    }

    public String getNaam() {
        return naam;
    }

    public String getBtwNummer() {
        return btwNummer;
    }

    public String getStraat() {
        return straat;
    }

    public String getHuisNummer() {
        return huisNummer;
    }

    public String getBus() {
        return bus;
    }

    public long getPlaatsId() {
        return plaatsId;
    }

    public String getFamilienaamContactpersoon() {
        return familienaamContactpersoon;
    }

    public String getVoornaamContactpersoon() {
        return voornaamContactpersoon;
    }
}
