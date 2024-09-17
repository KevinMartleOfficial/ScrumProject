package be.vdab.scrumjava202409.bestellingen;

import be.vdab.scrumjava202409.artikelen.ArtikelRepository;
import be.vdab.scrumjava202409.bestellijnen.Bestellijn;
import be.vdab.scrumjava202409.bestellijnen.BestellijnRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
class BestellingService {
    private final BestellingRepository bestellingRepository;
    private final BestellijnRepository bestellijnRepository;
    private final ArtikelRepository artikelRepository;

    public BestellingService(BestellingRepository bestellingRepository, BestellijnRepository bestellijnRepository, ArtikelRepository artikelRepository) {
        this.bestellingRepository = bestellingRepository;
        this.bestellijnRepository = bestellijnRepository;
        this.artikelRepository = artikelRepository;
    }

    List<Bestellijn> findAllBestellijnenVanEersteBestelling() {
        var eersteBestelling = bestellingRepository.findEersteBestellingMetStatusKlaarmaken();
        return bestellijnRepository.findAllBestellijnenByBestelId(eersteBestelling.getBestelId());
    }
}