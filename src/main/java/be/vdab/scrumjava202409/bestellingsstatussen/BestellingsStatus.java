package be.vdab.scrumjava202409.bestellingsstatussen;

public class BestellingsStatus {
    private long bestellingsStatusId;
    private String naam;

    public BestellingsStatus(long bestellingsStatusId, String naam) {
        this.bestellingsStatusId = bestellingsStatusId;
        this.naam = naam;
    }

    public long getBestellingsStatusId() {
        return bestellingsStatusId;
    }

    public String getNaam() {
        return naam;
    }
}
