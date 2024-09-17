package be.vdab.scrumjava202409.bestellingen;

import java.util.Date;

public class Bestelling {
    private long bestelId;
    private Date besteldatum;
    private long klantId;
    private boolean betaald;
    private String betalingscode;
    private long betaalwijzeId;
    private boolean annulatie;
    private Date annulatiedatum;
    private String terugbetalingscode;
    private long bestellingsStatusId;
    private boolean actiecodeGebruikt;
    private String bedrijfsnaam;
    private String btwNummer;
    private String voornaam;
    private String familienaam;
    private int facturatieAdresId;
    private int leveringsAdresId;

    /*public Bestelling(long bestelId, Date besteldatum, long klantId, boolean betaald, String betalingscode,
                      long betaalwijzeId, boolean annulatie, Date annulatiedatum, String terugbetalingscode,
                      long bestellingsStatusId, boolean actiecodeGebruikt, String bedrijfsnaam)
    {
        this.bestelId = bestelId;
        this.besteldatum = besteldatum;
        this.klantId = klantId;
        this.betaald = betaald;
        this.betalingscode = betalingscode;
        this.betaalwijzeId = betaalwijzeId;
        this.annulatie = annulatie;
        this.annulatiedatum = annulatiedatum;
        this.terugbetalingscode = terugbetalingscode;
        this.bestellingsStatusId = bestellingsStatusId;
        this.actiecodeGebruikt = actiecodeGebruikt;
        this.bedrijfsnaam = bedrijfsnaam;
    }*/

    public Bestelling(long bestelId, Date besteldatum,
                      long klantId, boolean betaald,
                      String betalingscode, long betaalwijzeId,
                      boolean annulatie, Date annulatiedatum,
                      String terugbetalingscode,
                      long bestellingsStatusId,
                      boolean actiecodeGebruikt,
                      String bedrijfsnaam, String btwNummer,
                      String voornaam, String familienaam,
                      int facturatieAdresId, int leveringsAdresId) {

        this.bestelId = bestelId;
        this.besteldatum = besteldatum;
        this.klantId = klantId;
        this.betaald = betaald;
        this.betalingscode = betalingscode;
        this.betaalwijzeId = betaalwijzeId;
        this.annulatie = annulatie;
        this.annulatiedatum = annulatiedatum;
        this.terugbetalingscode = terugbetalingscode;
        this.bestellingsStatusId = bestellingsStatusId;
        this.actiecodeGebruikt = actiecodeGebruikt;
        this.bedrijfsnaam = bedrijfsnaam;
        this.btwNummer = btwNummer;
        this.voornaam = voornaam;
        this.familienaam = familienaam;
        this.facturatieAdresId = facturatieAdresId;
        this.leveringsAdresId = leveringsAdresId;
    }

    public long getBestelId() {
        return bestelId;
    }

    public Date getBesteldatum() {
        return besteldatum;
    }

    public long getKlantId() {
        return klantId;
    }

    public boolean isBetaald() {
        return betaald;
    }

    public String getBetalingscode() {
        return betalingscode;
    }

    public long getBetaalwijzeId() {
        return betaalwijzeId;
    }

    public boolean isAnnulatie() {
        return annulatie;
    }

    public Date getAnnulatiedatum() {
        return annulatiedatum;
    }

    public String getTerugbetalingscode() {
        return terugbetalingscode;
    }

    public long getBestellingsStatusId() {
        return bestellingsStatusId;
    }

    public boolean isActiecodeGebruikt() {
        return actiecodeGebruikt;
    }

    public String getBedrijfsnaam() {
        return bedrijfsnaam;
    }

    public String getFamilienaam() {
        return familienaam;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public int getFacturatieAdresId() {
        return facturatieAdresId;
    }

    public String getBtwNummer() {
        return btwNummer;
    }

    public int getLeveringsAdresId() {
        return leveringsAdresId;
    }
}
