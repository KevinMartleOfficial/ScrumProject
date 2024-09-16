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
        private long BestellingsStatusId;
        private boolean ActiecodeGebruikt;
        private String bedrijfsnaam;


        public Bestelling(long bestelId, Date besteldatum, long klantId, boolean betaald, String betalingscode, long betaalwijzeId, boolean annulatie, Date annulatiedatum, String terugbetalingscode, long bestellingsStatusId, boolean actiecodeGebruikt, String bedrijfsnaam) {
            this.bestelId = bestelId;
            this.besteldatum = besteldatum;
            this.klantId = klantId;
            this.betaald = betaald;
            this.betalingscode = betalingscode;
            this.betaalwijzeId = betaalwijzeId;
            this.annulatie = annulatie;
            this.annulatiedatum = annulatiedatum;
            this.terugbetalingscode = terugbetalingscode;
            BestellingsStatusId = bestellingsStatusId;
            ActiecodeGebruikt = actiecodeGebruikt;
            this.bedrijfsnaam = bedrijfsnaam;
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
            return BestellingsStatusId;
        }

        public boolean isActiecodeGebruikt() {
            return ActiecodeGebruikt;
        }

        public String getBedrijfsnaam() {
            return bedrijfsnaam;
        }
    }
