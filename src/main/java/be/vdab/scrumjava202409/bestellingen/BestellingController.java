package be.vdab.scrumjava202409.bestellingen;

import be.vdab.scrumjava202409.artikelen.Artikel;
import be.vdab.scrumjava202409.artikelen.ArtikelRepository;
import be.vdab.scrumjava202409.bestellijnen.Bestellijn;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;
import java.util.List;

@RequestMapping
@RestController
class BestellingController {
    private final BestellingService bestellingService;
    private final ArtikelRepository artikelRepository;
    public BestellingController(BestellingService bestellingService, ArtikelRepository artikelRepository) {
        this.bestellingService = bestellingService;
        this.artikelRepository = artikelRepository;
    }

    private class ArtikelAantal {
        private final String artikelNaam;
        private final int aantal;
        ArtikelAantal(Bestellijn bestellijn) {
            this.artikelNaam = artikelRepository.getArtikelById(bestellijn.getArtikelId()).getNaam();
            this.aantal = bestellijn.getAantalBesteld();
        }
    }

    @GetMapping("bestelling/tv")
    public List<BestellingTVDTO> eerste5bestellingenTV() {
        return bestellingService.eerste5bestellingenTV();
    }

    @GetMapping("bestellingen/aantal")
    long findAantalBestellingen() {
        return bestellingService.findAantalBestellingen();
    }

    @GetMapping("bestellingen/eerste")
    Stream<ArtikelAantal> alleArtikelenMetAantal() {
        return bestellingService.findAllBestellijnenVanEersteBestelling()
                .stream()
                .map(bestellijn -> new ArtikelAantal(bestellijn));

    }
}
