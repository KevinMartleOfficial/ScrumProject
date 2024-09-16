package be.vdab.scrumjava202409.uitgaandeleveringen;

import java.time.LocalDate;

public class UitgaandeLevering {
    private long uitgaandeLeveringsId;
    private long bestelId;
    private LocalDate vertrekDatum;
    private LocalDate aankomstDatum;
    private String trackingcode;
    private long klantId;
    private long uitgaandeLeveringsStatusId;
    public UitgaandeLevering (long uitgaandeLeveringsId, long bestelId, LocalDate vertrekDatum,
                              LocalDate aankomstDatum, String trackingcode, long klantId,
                              long uitgaandeLeveringsStatusId) {
        this.uitgaandeLeveringsId = uitgaandeLeveringsId;
        this.bestelId = bestelId;
        this.vertrekDatum = vertrekDatum;
        this.aankomstDatum = aankomstDatum;
        this.trackingcode = trackingcode;
        this.klantId = klantId;
        this.uitgaandeLeveringsStatusId = uitgaandeLeveringsStatusId;
    }

    public long getUitgaandeLeveringsId() {
        return uitgaandeLeveringsId;
    }

    public long getBestelId() {
        return bestelId;
    }

    public LocalDate getVertrekDatum() {
        return vertrekDatum;
    }

    public LocalDate getAankomstDatum() {
        return aankomstDatum;
    }

    public String getTrackingcode() {
        return trackingcode;
    }

    public long getKlantId() {
        return klantId;
    }

    public long getUitgaandeLeveringsStatusId() {
        return uitgaandeLeveringsStatusId;
    }
}
