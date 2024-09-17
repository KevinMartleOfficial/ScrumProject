package be.vdab.scrumjava202409.bestellingen;

import be.vdab.scrumjava202409.artikelen.Artikel;
import be.vdab.scrumjava202409.artikelen.ArtikelService;
import be.vdab.scrumjava202409.uitgaandeleveringen.UitgaandeLeveringService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;
import java.util.List;

@RequestMapping
@RestController
class BestellingController {
    private final BestellingService bestellingService;
    private final ArtikelService artikelService;
    private final UitgaandeLeveringService uitgaandeLeveringService;

    public BestellingController(BestellingService bestellingService, ArtikelService artikelService, UitgaandeLeveringService uitgaandeLeveringService) {
        this.bestellingService = bestellingService;
        this.artikelService = artikelService;
        this.uitgaandeLeveringService = uitgaandeLeveringService;
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
    Stream<ArtikelAantalMagazijnplaats> alleArtikelenMetAantalVanEersteBestelling() {
        return bestellingService.findAllBestellijnenVanEersteBestelling()
                .stream()
                .map(bestellijn -> {
                    ArtikelAantalMagazijnplaats artikelAantal = new ArtikelAantalMagazijnplaats(
                            artikelService.getArtikelById(bestellijn.getArtikelId()).getNaam(),
                            bestellijn.getAantalBesteld(), "A1");
                    //voor lijst van ArtikelAantal bij te houden in uitgaandeLeveringService
                    //om te gebruiken bij afwerken van bestelling
                    uitgaandeLeveringService.voegToeAanArtikelAantalList(artikelAantal);
                    return artikelAantal;
                });

    }

    @GetMapping("bestellingen/artikel/{id}/detailoverzicht")
    Artikel getDetailoverzicht(@PathVariable long id) {
        return artikelService.getArtikelById(id);
    }
}
