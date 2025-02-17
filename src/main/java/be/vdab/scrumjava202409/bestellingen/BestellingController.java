package be.vdab.scrumjava202409.bestellingen;

import be.vdab.scrumjava202409.artikelen.Artikel;
import be.vdab.scrumjava202409.artikelen.ArtikelService;
import be.vdab.scrumjava202409.magazijnplaatsen.MagazijnPlaatsService;
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
    private final MagazijnPlaatsService magazijnPlaatsService;

    public BestellingController(BestellingService bestellingService, ArtikelService artikelService, UitgaandeLeveringService uitgaandeLeveringService, MagazijnPlaatsService magazijnPlaatsService) {
        this.bestellingService = bestellingService;
        this.artikelService = artikelService;
        this.uitgaandeLeveringService = uitgaandeLeveringService;
        this.magazijnPlaatsService = magazijnPlaatsService;
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

    Stream<BestelIdArtikelIdNaamAantalMagazijnplaats> alleArtikelenMetAantalVanEersteBestelling() {
        uitgaandeLeveringService.clearBanamLijst();
        List<BestelIdArtikelIdNaamAantalMagazijnplaats> kortstePad = magazijnPlaatsService.findAlleBanamVanEersteBestellingInMagazijn();
                kortstePad.forEach(uitgaandeLeveringService::voegToeAanBestelIdArtikelIdNaamAantalMagazijnplaatsList);
        return kortstePad.stream();
        /*return bestellingService.findAllBestellijnenVanEersteBestelling()
                .stream()
                .map(bestellijn -> {
                    BestelIdArtikelIdNaamAantalMagazijnplaats banam = new BestelIdArtikelIdNaamAantalMagazijnplaats(
                            bestellijn.getBestelId(), bestellijn.getArtikelId(),
                            artikelService.getArtikelById(bestellijn.getArtikelId()).getNaam(),
                            bestellijn.getAantalBesteld(), "Dit is tijdelijk");
                    //voor lijst van ArtikelAantal (edit: andere record) bij te houden in uitgaandeLeveringService
                    //om te gebruiken bij afwerken van bestelling
                    uitgaandeLeveringService.voegToeAanBestelIdArtikelIdNaamAantalMagazijnplaatsList(banam);
                    return banam;
                });*/

    }

    @GetMapping("bestellingen/artikel/{id}/detailoverzicht")
    Artikel getDetailoverzicht(@PathVariable long id) {
        return artikelService.getArtikelById(id);
    }
}
