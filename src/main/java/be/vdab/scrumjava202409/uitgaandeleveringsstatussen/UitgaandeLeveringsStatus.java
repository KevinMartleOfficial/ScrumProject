package be.vdab.scrumjava202409.uitgaandeleveringsstatussen;

class UitgaandeLeveringsStatus {
    private final long uitgaandeLeveringsStatusId;
    private final String naam;

    public UitgaandeLeveringsStatus(long uitgaandeLeveringsStatusId, String naam) {
        this.uitgaandeLeveringsStatusId = uitgaandeLeveringsStatusId;
        this.naam = naam;
    }

    public long getUitgaandeLeveringsStatusId() {
        return uitgaandeLeveringsStatusId;
    }

    public String getNaam() {
        return naam;
    }
}
