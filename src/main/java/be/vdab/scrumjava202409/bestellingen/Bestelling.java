package be.vdab.scrumjava202409.bestellingen;
import java.util.Date;

public class Bestelling {
        long bestelId;
        Date bestelDatum;
        long klantId;
        boolean betaald;
        String betalingsCode;
        long betaalwijzeId;
        boolean annulatie;
        Date annulatieDatum;
        String terugbetalingsCode;
        long BestellingsStatusId;
        boolean ActriecodeGebruikt;
        String bedrijfsNaam;


        public Bestelling(long bestelId, Date bestelDatum, long klantId, boolean betaald, String betalingsCode, long betaalwijzeId, boolean annulatie, Date annulatieDatum, String terugbetalingsCode, long bestellingsStatusId, boolean actriecodeGebruikt, String bedrijfsNaam) {
            this.bestelId = bestelId;
            this.bestelDatum = bestelDatum;
            this.klantId = klantId;
            this.betaald = betaald;
            this.betalingsCode = betalingsCode;
            this.betaalwijzeId = betaalwijzeId;
            this.annulatie = annulatie;
            this.annulatieDatum = annulatieDatum;
            this.terugbetalingsCode = terugbetalingsCode;
            BestellingsStatusId = bestellingsStatusId;
            ActriecodeGebruikt = actriecodeGebruikt;
            this.bedrijfsNaam = bedrijfsNaam;
        }

        public long getBestelId() {
            return bestelId;
        }

        public Date getBestelDatum() {
            return bestelDatum;
        }

        public long getKlantId() {
            return klantId;
        }

        public boolean isBetaald() {
            return betaald;
        }

        public String getBetalingsCode() {
            return betalingsCode;
        }

        public long getBetaalwijzeId() {
            return betaalwijzeId;
        }

        public boolean isAnnulatie() {
            return annulatie;
        }

        public Date getAnnulatieDatum() {
            return annulatieDatum;
        }

        public String getTerugbetalingsCode() {
            return terugbetalingsCode;
        }

        public long getBestellingsStatusId() {
            return BestellingsStatusId;
        }

        public boolean isActriecodeGebruikt() {
            return ActriecodeGebruikt;
        }

        public String getBedrijfsNaam() {
            return bedrijfsNaam;
        }
    }
