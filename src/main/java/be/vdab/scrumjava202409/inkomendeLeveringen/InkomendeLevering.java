package be.vdab.scrumjava202409.inkomendeLeveringen;

import java.time.LocalDate;

public class InkomendeLevering {
    private final long inkomendeLeveringsId;
    private final long leveranciersId;
    private final String leveringsbonNummer;
    private final LocalDate leveringsbondatum;
    private final LocalDate leverDatum = LocalDate.now();
    private final long ontvangerPersoneelslidId = 4;

    public InkomendeLevering(long inkomendeLeveringsId, long leveranciersId, String leveringsbonNummer,
                             LocalDate leveringsbondatum)
    {
        this.inkomendeLeveringsId = inkomendeLeveringsId;
        this.leveranciersId = leveranciersId;
        this.leveringsbonNummer = leveringsbonNummer;
        this.leveringsbondatum = leveringsbondatum;
    }

    public long getInkomendeLeveringsId() {
        return inkomendeLeveringsId;
    }

    public long getLeveranciersId() {
        return leveranciersId;
    }

    public String getLeveringsbonNummer() {
        return leveringsbonNummer;
    }

    public LocalDate getLeveringsbondatum() {
        return leveringsbondatum;
    }

    public LocalDate getLeverDatum() {
        return leverDatum;
    }

    public long getOntvangerPersoneelslidId() {
        return ontvangerPersoneelslidId;
    }
}
